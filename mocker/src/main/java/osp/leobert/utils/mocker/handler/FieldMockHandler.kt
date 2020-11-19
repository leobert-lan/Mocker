package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> FieldMockHandler </p>
 * Created by leobert on 2020/11/18.
 */
sealed class FieldMockHandler<T> {

    abstract fun mock(context: MockContext, field: Field): T

    class IntFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(context: MockContext, field: Field): Int {
            context.intMockAdapter.adapt(context, field)
            return context.intValuePool.randomGet(context)
        }
    }

    class ShortFieldMockHandler : FieldMockHandler<Short>() {
        override fun mock(context: MockContext, field: Field): Short {
            context.shortMockAdapter.adapt(context, field)
            return context.shortValuePool.randomGet(context)
        }
    }

    class LongFieldMockHandler : FieldMockHandler<Long>() {
        override fun mock(context: MockContext, field: Field): Long {
            context.longMockAdapter.adapt(context, field)
            return context.longValuePool.randomGet(context)
        }
    }

    class FloatFieldMockHandler : FieldMockHandler<Float>() {
        override fun mock(context: MockContext, field: Field): Float {
            context.floatMockAdapter.adapt(context, field)
            return context.floatValuePool.randomGet(context)
        }
    }

    class DoubleFieldMockHandler : FieldMockHandler<Double>() {
        override fun mock(context: MockContext, field: Field): Double {
            context.doubleMockAdapter.adapt(context, field)
            return context.doubleValuePool.randomGet(context)
        }
    }

    //todo next

    class BooleanFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(context: MockContext, field: Field): Int {
            context.booleanMockAdapter.adapt(context, field)
            return context.intValuePool.randomGet(context)
        }
    }

    class CharFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(context: MockContext, field: Field): Int {
            context.charMockAdapter.adapt(context, field)
            return context.intValuePool.randomGet(context)
        }
    }

    class ByteFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(context: MockContext, field: Field): Int {
            context.byteMockAdapter.adapt(context, field)
            return context.intValuePool.randomGet(context)
        }
    }

    class StringFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(context: MockContext, field: Field): Int {
            context.enumMockAdapter.adapt(context, field)
            return context.intValuePool.randomGet(context)
        }
    }

    class EnumFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(context: MockContext, field: Field): Int {
            context.enumMockAdapter.adapt(context, field)
            return context.intValuePool.randomGet(context)
        }
    }

    //todo 基本类型、enum、object等

    class BeanFieldMockHandler: FieldMockHandler<Any?>() {
        override fun mock(context: MockContext, field: Field): Any? {
            return context.beanMocker(field).mock(context)
        }
    }

}