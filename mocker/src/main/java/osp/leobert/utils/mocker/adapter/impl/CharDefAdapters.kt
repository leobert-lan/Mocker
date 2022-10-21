package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapter
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockCharDefAboveNotation
import osp.leobert.utils.mocker.notation.MockCharDef
import java.lang.reflect.Field

/**
 * Classname: CharDefAdapters </p>
 * Description: TODO </p>
 * Created by leobert on 2022/10/21.
 */

@Deprecated("cannot support different configurations")
object CharDefAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        field.annotations?.lastOrNull { it.annotationClass.java.isAnnotationPresent(MockCharDef::class.java) }
            ?.let {
                it.annotationClass.java.getAnnotation(MockCharDef::class.java).value
                    .toMutableList()
                    .let { values ->
                        context.charValuePool.setEnumValues(values)
                    }

            }
    }
}

object CharDefAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, vararg groups: Class<*>) {
        field.findMockCharDefAboveNotation(*groups)?.value
            ?.toMutableList()
            ?.let { values ->
                context.charValuePool.setEnumValues(values)
            }
    }
}