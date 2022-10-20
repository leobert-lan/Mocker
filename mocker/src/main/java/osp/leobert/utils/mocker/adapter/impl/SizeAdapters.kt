package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapter
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockSize
import osp.leobert.utils.mocker.notation.MockSize
import java.lang.reflect.Field

/**
 * Classname: SizeAdapters </p>
 * Description: TODO </p>
 * Created by leobert on 2022/10/20.
 */
@Deprecated("cannot support different configurations")
object SizeAdapter : FieldMockAdapter {
    private val list by lazy { arrayListOf<Int>() }
    override fun adapt(context: MockContext, field: Field) {
        if (field.isAnnotationPresent(MockSize::class.java)) {
            field.getAnnotation(MockSize::class.java).let {

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
}

object SizeAdapterV2 : FieldMockAdapterV2 {
    private val list by lazy { arrayListOf<Int>() }

    override fun adapt(context: MockContext, field: Field, vararg groups: Class<*>) {
        field.findMockSize(*groups)?.let {
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