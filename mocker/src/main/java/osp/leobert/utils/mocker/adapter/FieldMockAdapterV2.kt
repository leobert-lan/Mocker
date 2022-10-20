package osp.leobert.utils.mocker.adapter

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.notation.group.Default
import java.lang.reflect.Field

/**
 * Classname: FieldMockAdapterV2 </p>
 * Description: consider different configurations when adapt </p>
 * Created by leobert on 2022/10/20.
 */
interface FieldMockAdapterV2 : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        adapt(context, field, Default::class.java)
    }

    fun adapt(context: MockContext, field: Field, vararg groups: Class<*>)
}