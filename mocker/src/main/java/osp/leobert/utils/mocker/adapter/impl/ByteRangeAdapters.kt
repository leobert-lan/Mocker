package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockIntRange
import java.lang.reflect.Field

/**
 * Classname: ByteRangeAdapters </p>
 * Description: adapters for Byte Range mock </p>
 * Created by leobert on 2022/10/20.
 */
object ByteRangeAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockIntRange(groups)?.let {
            context.byteValuePool.setRange(it.from.toByte(), it.to.toByte())
        }
    }
}