package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.MockException
import java.lang.reflect.Field
import java.lang.reflect.Type

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
//            is GenericArrayType -> {
//
//            }
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

    private fun insertItemIntoArray(context: MockContext, clazz: Class<*>, array: Array<Any?>) {

        val itemMockHandler = BaseMockHandler<Any?>(clazz)

        for (i in array.indices) {
            array[i] = itemMockHandler.mock(context)
        }
    }


}