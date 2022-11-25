package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockStringDefAboveNotation
import java.lang.reflect.Field

/**
 * Classname: StringDefAdapters </p>
 * Description: adapters for String Def mock </p>
 * Created by leobert on 2022/10/21.
 */
object StringDefAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockStringDefAboveNotation(groups)?.value
            ?.toMutableList()
            ?.let { values ->
                context.stringValuePool.setEnumValues(values)
            }
    }
}
