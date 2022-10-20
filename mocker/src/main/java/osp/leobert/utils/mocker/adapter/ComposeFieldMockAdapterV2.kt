package osp.leobert.utils.mocker.adapter

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field

/**
 * Classname: ComposeFieldMockAdapterV2 </p>
 * Description: Compose different FieldMockAdapterV2 ,consider different configurations when adapt </p>
 *
 * @see [FieldMockAdapterV2]
 *
 * Created by leobert on 2022/10/20.
 */
class ComposeFieldMockAdapterV2 constructor(val leaf: MutableList<FieldMockAdapterV2>) :
    FieldMockAdapterV2 {

    override fun adapt(context: MockContext, field: Field) {
        leaf.forEach { it.adapt(context, field) }
    }

    override fun adapt(context: MockContext, field: Field, vararg groups: Class<*>) {
        leaf.forEach { it.adapt(context, field, *groups) }
    }
}