package osp.leobert.utils.mocker.adapter.android

import androidx.annotation.IntDef
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
        field.annotations?.lastOrNull { it.javaClass.isAnnotationPresent(MockIntDef::class.java) }
            ?.let {
                it.javaClass.getAnnotation(MockIntDef::class.java).value.toMutableList().let { values ->
                    context.intValuePool.setEnumValues(values)
                }

            }
    }
}

class LongRangeAdapter : FieldMockAdapter {
    override fun adapt(context: MockContext, field: Field) {
        if (field.isAnnotationPresent(IntRange::class.java)) {
            field.getAnnotation(IntRange::class.java).let {

                //todo
                context.intValuePool.setRange(it.from.toInt(), it.to.toInt())
            }
        }
    }
}