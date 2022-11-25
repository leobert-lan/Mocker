package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockIntRange
import java.lang.reflect.Field

/**
 * Classname: ShortRangeAdapters </p>
 * Description: adapters for Short Range mock </p>
 * Created by leobert on 2022/10/20.
 */
object ShortRangeAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockIntRange(groups)?.let {
            context.shortValuePool.setRange(it.from.toShort(), it.to.toShort())
        }
    }
}