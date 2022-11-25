package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockIntDefAboveNotation
import java.lang.reflect.Field

/**
 * Classname: ShortDefAdapters </p>
 * Description: adapters for Short Range mock </p>
 * Created by leobert on 2022/10/21.
 */
object ShortDefAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockIntDefAboveNotation(groups)?.value
            ?.map { l -> l.toShort() }
            ?.toMutableList()
            ?.let { values ->
                context.shortValuePool.setEnumValues(values)
            }
    }
}
