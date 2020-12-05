package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.MockException
import java.lang.reflect.*


/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> BaseMockHandler </p>
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
        return when (type) {
            is ParameterizedType -> {
                GenericMockHandler(type)
            }
            is GenericArrayType -> {
                ArrayMockHandler(type)
            }
            is TypeVariable<*> -> {
                BaseMockHandler<T>(context.getVariableType(type.name))
            }
            is WildcardType -> {
                BaseMockHandler<Any>(type.upperBounds[0], genericTypes)
            }
            is Class<*> -> {
                ClassMockHandler(type, genericTypes)
            }
            else -> throw MockException("暂不支持${type.typeName}")
        }.mock(context, field, owner) as T
    }


}