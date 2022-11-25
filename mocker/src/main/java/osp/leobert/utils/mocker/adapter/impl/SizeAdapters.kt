package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockSize
import java.lang.reflect.Field

/**
 * Classname: SizeAdapters </p>
 * Description: adapters for Size mock </p>
 * Created by leobert on 2022/10/20.
 */
object SizeAdapterV2 : FieldMockAdapterV2 {
    private val list by lazy { arrayListOf<Int>() }

    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockSize(groups)?.let {
            if (it.value < 0) {
                context.sizeValuePool.setRange(it.min, it.max)
            } else {
                list.clear()
                list.add(it.value)
                context.sizeValuePool.setEnumValues(list)
            }
        }
    }
}