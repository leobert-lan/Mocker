package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockFloatRange
import java.lang.reflect.Field

/**
 * Classname: DoubleRangeAdapters </p>
 * Description: adapters for Double Range mock </p>
 * Created by leobert on 2022/10/20.
 */
object DoubleRangeAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockFloatRange(groups)?.let {
            context.doubleValuePool.setRange(it.from.toDouble(), it.to.toDouble())
        }
    }
}