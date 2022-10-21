package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockIntDefAboveNotation
import java.lang.reflect.Field

/**
 * Classname: ShortDefAdapters </p>
 * Description: TODO </p>
 * Created by leobert on 2022/10/21.
 */

//@Deprecated("cannot support different configurations")
//object ShortDefAdapter : FieldMockAdapter {
//    override fun adapt(context: MockContext, field: Field) {
//        field.annotations?.lastOrNull { it.annotationClass.java.isAnnotationPresent(MockIntDef::class.java) }
//            ?.let {
//                it.annotationClass.java.getAnnotation(MockIntDef::class.java).value
//                    .map { l -> l.toShort() }
//                    .toMutableList()
//                    .let { values ->
//                        context.shortValuePool.setEnumValues(values)
//                    }
//
//            }
//    }
//}

object ShortDefAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, vararg groups: Class<*>) {
        field.findMockIntDefAboveNotation(*groups)?.value
            ?.map { l -> l.toShort() }
            ?.toMutableList()
            ?.let { values ->
                context.shortValuePool.setEnumValues(values)
            }
    }
}
