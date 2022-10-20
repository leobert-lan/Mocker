package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapter
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockFalse
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockTrue
import osp.leobert.utils.mocker.notation.MockFalse
import osp.leobert.utils.mocker.notation.MockTrue
import java.lang.reflect.Field

/**
 * Classname: BooleanAdapters </p>
 * Description: TODO </p>
 * Created by leobert on 2022/10/20.
 */
@Deprecated("cannot support different configurations")
object BooleanAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        if (field.isAnnotationPresent(MockTrue::class.java)) {
            context.boolValuePool.setEnumValues(arrayListOf(true))
        }
        if (field.isAnnotationPresent(MockFalse::class.java)) {
            context.boolValuePool.setEnumValues(arrayListOf(false))
        }
    }
}

object BooleanAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, vararg groups: Class<*>) {
        field.findMockTrue(*groups)?.let {
            context.boolValuePool.setEnumValues(arrayListOf(true))
        }
        field.findMockFalse(*groups)?.let {
            context.boolValuePool.setEnumValues(arrayListOf(false))
        }
    }
}