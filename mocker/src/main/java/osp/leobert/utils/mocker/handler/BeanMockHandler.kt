package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> BeanMockHandler </p>
 * <p><b>Description:</b> mocker for bean </p>
 * Created by leobert on 2020/11/19.
 */
class BeanMockHandler(private val clazz: Class<*>) : MockHandler<Any?> {

    override fun mock(context: MockContext, field: Field?, owner: Any?): Any? {
        //consider circle todo
        return context.createInstance(clazz)
//        return UnsafeUtils.newInstance(clazz)
    }
}