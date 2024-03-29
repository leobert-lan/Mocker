package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockFloatRange
import java.lang.reflect.Field

/**
 * Classname: FloatRangeAdapters </p>
 * Description: adapters for Float Range mock </p>
 * Created by leobert on 2022/10/20.
 */
object FloatRangeAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockFloatRange(groups)?.let {
            context.floatValuePool.setRange(it.from, it.to)
        }
    }
}