package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> MockHandler </p>
 * Created by leobert on 2020/11/18.
 */
interface MockHandler<T> {
    fun mock(context: MockContext): T
}

/**
 *
 *
 * ```
 *
 * ```
 *
 * */