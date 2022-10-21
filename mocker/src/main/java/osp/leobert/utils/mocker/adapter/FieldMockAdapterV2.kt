package osp.leobert.utils.mocker.adapter

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.Mocker
import java.lang.reflect.Field

/**
 * Classname: FieldMockAdapterV2 </p>
 * Description: consider different configurations when adapt </p>
 * Created by leobert on 2022/10/20.
 */
interface FieldMockAdapterV2 : FieldMockAdapter {

    override fun adapt(context: MockContext, field: Field) {
        adapt(context, field, Mocker.DEFAULT_GROUP)
    }

    fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>)
}