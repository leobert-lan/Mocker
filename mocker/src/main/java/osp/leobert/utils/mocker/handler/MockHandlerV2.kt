package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field

/**
 * Classname: MockHandlerV2 </p>
 * Description: consider different configurations when mock</p>
 * Created by leobert on 2022/10/20.
 */
interface MockHandlerV2<T> /*: MockHandler<T>*/ {

//    override fun mock(context: MockContext, field: Field?, owner: Any?): T {
//        return mock(context, field, owner, Default::class.java)
//    }

    fun mock(
        context: MockContext,
        field: Field? = null,
        owner: Any? = null,
        groups: Array<out Class<*>>
    ): T
}