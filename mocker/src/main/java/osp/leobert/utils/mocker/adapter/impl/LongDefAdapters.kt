package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockIntDefAboveNotation
import java.lang.reflect.Field

/**
 * Classname: LongDefAdapters </p>
 * Description: TODO </p>
 * Created by leobert on 2022/10/21.
 */
//object LongDefAdapter : FieldMockAdapter {
//    override fun adapt(context: MockContext, field: Field) {
//        field.annotations?.lastOrNull { it.annotationClass.java.isAnnotationPresent(MockIntDef::class.java) }
//            ?.let {
//                it.annotationClass.java.getAnnotation(MockIntDef::class.java).value.toMutableList()
//                    .let { values ->
//                        context.longValuePool.setEnumValues(values)
//                    }
//
//            }
//    }
//}

object LongDefAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockIntDefAboveNotation(groups)?.value
            ?.toMutableList()
            ?.let { values ->
                context.longValuePool.setEnumValues(values)
            }
    }
}