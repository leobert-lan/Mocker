package osp.leobert.utils.mocker.adapter.android

import androidx.annotation.IntRange
import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.adapter.FieldMockAdapter
import osp.leobert.utils.mocker.notation.MockIntDef
import osp.leobert.utils.mocker.notation.MockIntRange
import java.lang.reflect.Field

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.adapter.android </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> AndroidNotationAdapters </p>
 * <p><b>Description:</b> android中的注解限制 </p>
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
        if (field.isAnnotationPresent(IntRange::class.java)) {
            field.getAnnotation(IntRange::class.java).let {
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