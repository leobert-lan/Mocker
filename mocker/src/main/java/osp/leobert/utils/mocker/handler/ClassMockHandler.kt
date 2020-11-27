package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field
import java.lang.reflect.Type

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> ClassMockHandler </p>
 * Created by leobert on 2020/11/19.
 */
internal class ClassMockHandler(
    private val clazz: Class<*>, private val genericTypes: Array<Type>
) : MockHandler<Any?> {


    override fun mock(context: MockContext, field: Field?, owner: Any?): Any? {
        return when {
//            clazz.isArray -> {
//                mocker = ArrayMocker(clazz)
//            }
//            MutableMap::class.java.isAssignableFrom(clazz) -> {
//                mocker = MapMocker(genericTypes)
//            }
            Collection::class.java.isAssignableFrom(clazz) -> {
                CollectionMockHandler(clazz, genericTypes)
            }
            clazz.isEnum -> FieldMockHandler.EnumFieldMockHandler()
            else -> context.mockHandler(clazz) ?: FieldMockHandler.BeanFieldMockHandler(clazz)

        }.mock(context, field, owner)
    }
}