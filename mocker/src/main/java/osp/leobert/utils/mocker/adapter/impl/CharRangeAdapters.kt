package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockCharRange
import java.lang.reflect.Field

/**
 * Classname: CharRangeAdapters </p>
 * Description: adapters for Char Range mock </p>
 * Created by leobert on 2022/10/20.
 */
object CharRangeAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockCharRange(groups)?.let {
            context.charValuePool.setRange(it.from, it.to)
        }
    }
}