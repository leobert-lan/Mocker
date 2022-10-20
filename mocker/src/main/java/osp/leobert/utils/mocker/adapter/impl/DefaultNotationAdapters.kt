package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapter
import osp.leobert.utils.mocker.notation.*
import java.lang.reflect.Field

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.adapter.android </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> AndroidNotationAdapters </p>
 * <p><b>Description:</b> 原意是想使用android中的注解限制,然而androidx的注解仅保留到class，迁移了一部分注解 </p>
 * Created by leobert on 2020/11/19.
 */


object IntDefAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        field.annotations?.lastOrNull { it.annotationClass.java.isAnnotationPresent(MockIntDef::class.java) }
            ?.let {
                it.annotationClass.java.getAnnotation(MockIntDef::class.java).value
                    .map { l -> l.toInt() }
                    .toMutableList()
                    .let { values ->
                        context.intValuePool.setEnumValues(values)
                    }

            }
    }
}


object LongDefAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        field.annotations?.lastOrNull { it.annotationClass.java.isAnnotationPresent(MockIntDef::class.java) }
            ?.let {
                it.annotationClass.java.getAnnotation(MockIntDef::class.java).value.toMutableList()
                    .let { values ->
                        context.longValuePool.setEnumValues(values)
                    }

            }
    }
}



@Deprecated("cannot support different configurations")
object ShortDefAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        field.annotations?.lastOrNull { it.annotationClass.java.isAnnotationPresent(MockIntDef::class.java) }
            ?.let {
                it.annotationClass.java.getAnnotation(MockIntDef::class.java).value
                    .map { l -> l.toShort() }
                    .toMutableList()
                    .let { values ->
                        context.shortValuePool.setEnumValues(values)
                    }

            }
    }
}



@Deprecated("cannot support different configurations")
object ByteDefAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        field.annotations?.lastOrNull { it.annotationClass.java.isAnnotationPresent(MockIntDef::class.java) }
            ?.let {
                it.annotationClass.java.getAnnotation(MockIntDef::class.java).value
                    .map { l -> l.toByte() }
                    .toMutableList()
                    .let { values ->
                        context.byteValuePool.setEnumValues(values)
                    }

            }
    }
}


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

@Deprecated("cannot support different configurations")
object StringDefAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        field.annotations?.lastOrNull { it.annotationClass.java.isAnnotationPresent(MockStringDef::class.java) }
            ?.let {
                it.annotationClass.java.getAnnotation(MockStringDef::class.java).value
                    .toMutableList()
                    .let { values ->
                        context.stringValuePool.setEnumValues(values)
                    }

            }
    }
}
