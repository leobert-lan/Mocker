package osp.leobert.utils.mocker.adapter.android

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapter
import osp.leobert.utils.mocker.notation.*
import java.lang.reflect.Field

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.adapter.android </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> AndroidNotationAdapters </p>
 * <p><b>Description:</b> android中的注解限制,然而androidx的注解仅保留到class，迁移了一部分注解 </p>
 * Created by leobert on 2020/11/19.
 */
object IntRangeAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        if (field.isAnnotationPresent(MockIntRange::class.java)) {
            field.getAnnotation(MockIntRange::class.java).let {

                context.intValuePool.setRange(it.from.toInt(), it.to.toInt())
            }
        }
    }
}

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

object LongRangeAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        if (field.isAnnotationPresent(MockIntRange::class.java)) {
            field.getAnnotation(MockIntRange::class.java).let {
                context.longValuePool.setRange(it.from, it.to)
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

object ShortRangeAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        if (field.isAnnotationPresent(MockIntRange::class.java)) {
            field.getAnnotation(MockIntRange::class.java).let {
                context.shortValuePool.setRange(it.from.toShort(), it.to.toShort())
            }
        }
    }
}

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

object ByteRangeAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        if (field.isAnnotationPresent(MockIntRange::class.java)) {
            field.getAnnotation(MockIntRange::class.java).let {
                context.byteValuePool.setRange(it.from.toByte(), it.to.toByte())
            }
        }
    }
}

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

object FloatRangeAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        if (field.isAnnotationPresent(MockFloatRange::class.java)) {
            field.getAnnotation(MockFloatRange::class.java).let {
                context.floatValuePool.setRange(it.from, it.to)
            }
        }
    }
}

object DoubleRangeAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        if (field.isAnnotationPresent(MockFloatRange::class.java)) {
            field.getAnnotation(MockFloatRange::class.java).let {
                context.doubleValuePool.setRange(it.from.toDouble(), it.to.toDouble())
            }
        }
    }
}

object BooleanAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        if (field.isAnnotationPresent(MockTrue::class.java)) {
                context.boolValuePool.setEnumValues(arrayListOf(true))
        }
        if (field.isAnnotationPresent(MockFalse::class.java)) {
            context.boolValuePool.setEnumValues(arrayListOf(false))
        }
    }
}

object CharRangeAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        if (field.isAnnotationPresent(MockCharRange::class.java)) {
            field.getAnnotation(MockCharRange::class.java).let {
//todo got exception
                context.charValuePool.setRange(it.from, it.to)
            }
        }
    }
}

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