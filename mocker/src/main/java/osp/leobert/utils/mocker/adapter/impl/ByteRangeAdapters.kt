package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockIntRange
import java.lang.reflect.Field

/**
 * Classname: ByteRangeAdapters </p>
 * Description: TODO </p>
 * Created by leobert on 2022/10/20.
 */
//@Deprecated("cannot support different configurations")
//object ByteRangeAdapter : FieldMockAdapter {
//    override fun adapt(context: MockContext, field: Field) {
//        if (field.isAnnotationPresent(MockIntRange::class.java)) {
//            field.getAnnotation(MockIntRange::class.java).let {
//                context.byteValuePool.setRange(it.from.toByte(), it.to.toByte())
//            }
//        }
//    }
//}

object ByteRangeAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockIntRange(groups)?.let {
            context.byteValuePool.setRange(it.from.toByte(), it.to.toByte())
        }
    }
}