package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> GenericMockHandler </p>
 * Created by leobert on 2020/11/19.
 */
internal class GenericMockHandler(private val type: ParameterizedType) : MockHandlerV2<Any> {
//    override fun mock(context: MockContext, field: Field?, owner: Any?): Any {
//        context.parseParameterizedType(type)
//        return BaseMockHandler<Any>(type.rawType, type.actualTypeArguments).mock(context,field, owner)
//    }

    override fun mock(
        context: MockContext,
        field: Field?,
        owner: Any?,
        vararg groups: Class<*>
    ): Any {
        context.parseParameterizedType(type)
        return BaseMockHandler<Any>(type.rawType, type.actualTypeArguments).mock(context,field, owner,*groups)
    }
}