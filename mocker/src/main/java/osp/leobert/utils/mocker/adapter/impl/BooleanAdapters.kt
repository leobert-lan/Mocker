package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockFalse
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockTrue
import java.lang.reflect.Field

/**
 * Classname: BooleanAdapters </p>
 * Description: adapters for boolean mock </p>
 * Created by leobert on 2022/10/20.
 */
object BooleanAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockTrue(groups)?.let {
            context.boolValuePool.setEnumValues(arrayListOf(true))
        }
        field.findMockFalse(groups)?.let {
            context.boolValuePool.setEnumValues(arrayListOf(false))
        }
    }
}