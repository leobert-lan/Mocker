package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.MockException
import java.lang.reflect.*


/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> ArrayMockHandler </p>
 * Created by leobert on 2020/11/29.
 */
class ArrayMockHandler(private val type: Type) : MockHandler<Any?> {

    override fun mock(context: MockContext, field: Field?, owner: Any?): Any? {
        return when (type) {
            is Class<*> -> {
                createArray(context, field, type.componentType)?.apply {
                    insertItemIntoArray(context, type.componentType, this as Array<Any?>)
                }
            }
            // e.g. TODO:Array mock;
            //  osp.leobert.utils.mocker.handler.BaseMockHandlerTest$ParameterizedTypeCase<osp.leobert.utils.mocker.handler.BaseMockHandlerTest$Foo>[],
            //  osp.leobert.utils.mocker.handler.BaseMockHandlerTest$ParameterizedTypeCase<osp.leobert.utils.mocker.handler.BaseMockHandlerTest$Foo>[]
            is GenericArrayType -> {
                // TODO: 2020/11/29
                createGenericArray(context,field, type)
            }
            else -> throw MockException("TODO:Array mock; ${type.typeName},${type}")
        }.apply {
            context.applyField(this, field, owner)
        }
    }


    private fun createArray(context: MockContext, field: Field?, clazz: Class<*>): Any? {
        context.sizeValuePool.reset()
        field?.let {
            context.collectionMockAdapter.adapt(context, field)
        }
        val size = context.sizeValuePool.randomGet(context)
        return java.lang.reflect.Array.newInstance(clazz, size)
    }

    private fun insertItemIntoArray(context: MockContext, type: Type, array: Array<Any?>) {

        val itemMockHandler = BaseMockHandler<Any?>(type)

        for (i in array.indices) {
            array[i] = itemMockHandler.mock(context)
        }
    }

    private fun createGenericArray(context: MockContext,field: Field?, type: GenericArrayType): Any? {

        val model = parse(context, type, 1)

        context.sizeValuePool.reset()
        field?.let {
            context.collectionMockAdapter.adapt(context, field)
        }

        var clazz = model.second.first

        val list: MutableList<Array<Any?>> = ArrayList(model.first)

        for (i in (0 until model.first).reversed()) {
            val array: Array<Any?> = java.lang.reflect.Array.newInstance(clazz, context.sizeValuePool.randomGet(context)) as Array<Any?>
            clazz = array.javaClass
            list.add(array)
        }

        var baseResult: Any? = BaseMockHandler<Any?>(model.second.first,model.second.second?: arrayOf()).mock(context)

        //上一次是尾递归，此处头递归是从内而外的。
        for (i in list.indices) {
            val array = list[i]
            for (j in 0 until context.sizeValuePool.randomGet(context)) {
               java.lang.reflect.Array.set(array, j, baseResult)
            }
            baseResult = array
        }

        return baseResult
    }


    private fun parse(
        context: MockContext,
        genericArrayType: GenericArrayType,
        deep: Int
    ): Pair<Int, Pair<Class<*>, Array<Type>?>> {

        when (val componentType = genericArrayType.genericComponentType) {
            is GenericArrayType -> {
                //继续递归
                return parse(context, componentType, deep + 1)
            }
            is ParameterizedType -> {
                context.parseParameterizedType(componentType)
                return Pair(
                    deep,
                    Pair(
                        componentType.rawType as Class<*>,
                        componentType.actualTypeArguments
                    )
                )
            }
            is TypeVariable<*> -> {
                return Pair(
                    deep,
                    Pair(
                        context.getVariableType(componentType.typeName) as Class<*>,
                        null
                    )
                )
            }
            else -> {
                return Pair(
                    deep,
                    Pair(
                        componentType as Class<*>,
                        null
                    )
                )
            }
        }
    }

}