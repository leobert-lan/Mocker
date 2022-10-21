package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockIntDefAboveNotation
import java.lang.reflect.Field

/**
 * Classname: ByteDefAdapters </p>
 * Description: TODO </p>
 * Created by leobert on 2022/10/21.
 */
//@Deprecated("cannot support different configurations")
//object ByteDefAdapter : FieldMockAdapter {
//    override fun adapt(context: MockContext, field: Field) {
//        field.annotations?.lastOrNull { it.annotationClass.java.isAnnotationPresent(MockIntDef::class.java) }
//            ?.let {
//                it.annotationClass.java.getAnnotation(MockIntDef::class.java).value
//                    .map { l -> l.toByte() }
//                    .toMutableList()
//                    .let { values ->
//                        context.byteValuePool.setEnumValues(values)
//                    }
//
//            }
//    }
//}

object ByteDefAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, vararg groups: Class<*>) {
        field.findMockIntDefAboveNotation(*groups)?.value
            ?.map { l -> l.toByte() }
            ?.toMutableList()
            ?.let { values ->
                context.byteValuePool.setEnumValues(values)
            }
    }
}