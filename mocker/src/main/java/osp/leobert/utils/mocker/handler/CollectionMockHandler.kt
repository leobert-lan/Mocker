package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field
import java.lang.reflect.Type

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> CollectionMockHandler </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2020/11/24.
 */
class CollectionMockHandler(
    private val clazz: Class<*>, val genericTypes: Array<Type>
) :MockHandler<Any> {
    override fun mock(context: MockContext, field: Field?, owner: Any?): Any {
//        Collection
        TODO("Not yet implemented")
    }
}