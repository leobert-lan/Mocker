package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockCharDefAboveNotation
import java.lang.reflect.Field

/**
 * Classname: CharDefAdapters </p>
 * Description: adapters for Char Def mock </p>
 * Created by leobert on 2022/10/21.
 */
object CharDefAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockCharDefAboveNotation(groups)?.value
            ?.toMutableList()
            ?.let { values ->
                context.charValuePool.setEnumValues(values)
            }
    }
}