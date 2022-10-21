package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockFloatRange
import java.lang.reflect.Field

/**
 * Classname: FloatRangeAdapters </p>
 * Description: TODO </p>
 * Created by leobert on 2022/10/20.
 */
//@Deprecated("cannot support different configurations")
//object FloatRangeAdapter : FieldMockAdapter {
//    override fun adapt(context: MockContext, field: Field) {
//        if (field.isAnnotationPresent(MockFloatRange::class.java)) {
//            field.getAnnotation(MockFloatRange::class.java).let {
//                context.floatValuePool.setRange(it.from, it.to)
//            }
//        }
//    }
//}

object FloatRangeAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, vararg groups: Class<*>) {
        field.findMockFloatRange(*groups)?.let {
            context.floatValuePool.setRange(it.from, it.to)
        }
    }
}