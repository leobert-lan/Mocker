package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> BaseMockHandler </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2020/11/19.
 */
@Suppress("UNCHECKED_CAST")
class BaseMockHandler<T>(
    private val type: Type,
    private val genericTypes: Array<Type> = arrayOf(),
) : MockHandler<T> {

    //if it is a field, we should use FieldMockHandler, thus we can apply the mock result
    //to it's owner; otherwise , we should use MockHandler to supply a target mock result

    override fun mock(context: MockContext, field: Field?, owner: Any?): T {
        when (type) {
            is ParameterizedType -> {
                return GenericMockHandler(type).mock(context) as T
            }
            //todo 完成这个复杂的玩意

//            is GenericArrayType -> {
//                mocker = ArrayMocker(type)
//            }
//            is TypeVariable<*> ->{
//                todo
//             return null
//            }

            else -> ClassMockHandler(type as Class<*>, genericTypes)

        }
    }

    //class的参考
    //return when {
    ////            clazz.isArray -> {
    ////                mocker = ArrayMocker(clazz)
    ////            }
    ////            MutableMap::class.java.isAssignableFrom(clazz) -> {
    ////                mocker = MapMocker(genericTypes)
    ////            }
    ////            MutableCollection::class.java.isAssignableFrom(clazz) -> {
    ////                mocker = CollectionMocker(clazz, genericTypes[0])
    ////            }
    //            clazz.isEnum -> {
    ////                mocker = EnumMocker(clazz)
    //                BeanMockHandler(clazz)
    //            }
    //            else -> context.mockHandler(clazz) ?: BeanMockHandler(clazz)
    //
    //        }.mock(context)
}