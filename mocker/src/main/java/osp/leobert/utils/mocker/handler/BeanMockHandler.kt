package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.utils.UnsafeUtils

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> BeanMockHandler </p>
 * <p><b>Description:</b> mocker for bean </p>
 * Created by leobert on 2020/11/19.
 */
class BeanMockHandler(private val clazz: Class<*>):MockHandler<Any?> {

    override fun mock(context: MockContext): Any? {
        //consider circle todo
        return UnsafeUtils.newInstance(clazz)
    }
}