package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockCharRange
import java.lang.reflect.Field

/**
 * Classname: CharRangeAdapters </p>
 * Description: TODO </p>
 * Created by leobert on 2022/10/20.
 */

//@Deprecated("cannot support different configurations")
//object CharRangeAdapter : FieldMockAdapter {
//    override fun adapt(context: MockContext, field: Field) {
//        if (field.isAnnotationPresent(MockCharRange::class.java)) {
//            field.getAnnotation(MockCharRange::class.java).let {
//                context.charValuePool.setRange(it.from, it.to)
//            }
//        }
//    }
//}


object CharRangeAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, vararg groups: Class<*>) {
        field.findMockCharRange(*groups)?.let {
            context.charValuePool.setRange(it.from, it.to)
        }
    }
}