package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockIntRange
import java.lang.reflect.Field

/**
 * Classname: IntRangeAdapters </p>
 * Description: adapters for Int Range mock </p>
 * Created by leobert on 2022/10/20.
 */

//@Deprecated("cannot support different configurations")
//object IntRangeAdapter : FieldMockAdapter {
//    override fun adapt(context: MockContext, field: Field) {
//        if (field.isAnnotationPresent(MockIntRange::class.java)) {
//            field.getAnnotation(MockIntRange::class.java).let {
//
//                context.intValuePool.setRange(it.from.toInt(), it.to.toInt())
//            }
//        }
//    }
//}

object IntRangeAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockIntRange(groups)?.let {
            context.intValuePool.setRange(it.from.toInt(), it.to.toInt())
        }
    }
}