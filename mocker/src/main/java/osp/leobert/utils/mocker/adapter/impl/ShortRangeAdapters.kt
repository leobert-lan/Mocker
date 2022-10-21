package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockIntRange
import java.lang.reflect.Field

/**
 * Classname: ShortRangeAdapters </p>
 * Description: TODO </p>
 * Created by leobert on 2022/10/20.
 */

//@Deprecated("cannot support different configurations")
//object ShortRangeAdapter : FieldMockAdapter {
//    override fun adapt(context: MockContext, field: Field) {
//        if (field.isAnnotationPresent(MockIntRange::class.java)) {
//            field.getAnnotation(MockIntRange::class.java).let {
//                context.shortValuePool.setRange(it.from.toShort(), it.to.toShort())
//            }
//        }
//    }
//}

object ShortRangeAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, vararg groups: Class<*>) {
        field.findMockIntRange(*groups)?.let {
            context.shortValuePool.setRange(it.from.toShort(), it.to.toShort())
        }
    }
}