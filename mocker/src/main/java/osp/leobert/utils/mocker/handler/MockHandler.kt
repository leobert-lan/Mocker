package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> MockHandler </p>
 * Created by leobert on 2020/11/18.
 */
@Deprecated(message = "")
interface MockHandler<T> {
    fun mock(
        context: MockContext,
        field: Field? = null,
        owner: Any? = null
    ): T
}

/**
 *
 *
 * ```
 *
 * ```
 *
 * */