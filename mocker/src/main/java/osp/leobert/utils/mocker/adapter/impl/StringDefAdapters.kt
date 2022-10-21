package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockStringDefAboveNotation
import java.lang.reflect.Field

/**
 * Classname: StringDefAdapters </p>
 * Description: TODO </p>
 * Created by leobert on 2022/10/21.
 */
//@Deprecated("cannot support different configurations")
//object StringDefAdapter : FieldMockAdapter {
//    override fun adapt(context: MockContext, field: Field) {
//        field.annotations?.lastOrNull { it.annotationClass.java.isAnnotationPresent(MockStringDef::class.java) }
//            ?.let {
//                it.annotationClass.java.getAnnotation(MockStringDef::class.java).value
//                    .toMutableList()
//                    .let { values ->
//                        context.stringValuePool.setEnumValues(values)
//                    }
//
//            }
//    }
//}

object StringDefAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, vararg groups: Class<*>) {
        field.findMockStringDefAboveNotation(*groups)?.value
            ?.toMutableList()
            ?.let { values ->
                context.stringValuePool.setEnumValues(values)
            }
    }
}
