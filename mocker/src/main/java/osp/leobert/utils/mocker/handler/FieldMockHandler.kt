package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> FieldMockHandler </p>
 * 处理特定的field，扩展了注解约束处理
 * Created by leobert on 2020/11/18.
 */
sealed class FieldMockHandler<T> : MockHandler<T> {

    //todo add mockhandler as reference
    //todo remove return add owner
    //todo add pending jobs / supply result to owner immediately

//    abstract fun mock(context: MockContext, field: Field): T

    class IntFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Int {
            field?.let {
                context.intMockAdapter.adapt(context, field)
            }
            return context.intValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    class ShortFieldMockHandler : FieldMockHandler<Short>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Short {
            field?.let {
                context.shortMockAdapter.adapt(context, field)
            }
            return context.shortValuePool.randomGet(context)
        }
    }

    class LongFieldMockHandler : FieldMockHandler<Long>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Long {
            field?.let {
                context.longMockAdapter.adapt(context, field)
            }
            return context.longValuePool.randomGet(context)
        }
    }

    class FloatFieldMockHandler : FieldMockHandler<Float>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Float {
            field?.let {
                context.floatMockAdapter.adapt(context, field)
            }
            return context.floatValuePool.randomGet(context)
        }
    }

    class DoubleFieldMockHandler : FieldMockHandler<Double>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Double {
            field?.let {
                context.doubleMockAdapter.adapt(context, field)
            }
            return context.doubleValuePool.randomGet(context)
        }
    }

    //todo next

    class BooleanFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Int {
            field?.let {
                context.booleanMockAdapter.adapt(context, field)
            }
            return context.intValuePool.randomGet(context)
        }
    }

    class CharFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Int {
            field?.let {
                context.charMockAdapter.adapt(context, field)
            }
            return context.intValuePool.randomGet(context)
        }
    }

    class ByteFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Int {
            field?.let {
                context.byteMockAdapter.adapt(context, field)
            }
            return context.intValuePool.randomGet(context)
        }
    }

    class StringFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Int {
            field?.let {
                context.enumMockAdapter.adapt(context, field)
            }
            return context.intValuePool.randomGet(context)
        }
    }

    class EnumFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Int {
            field?.let {
                context.enumMockAdapter.adapt(context, field)
            }
            return context.intValuePool.randomGet(context)
        }
    }

    //todo 基本类型、enum、object等

    //这个没有必要
//    class BeanFieldMockHandler : FieldMockHandler<Any?>() {
//        override fun mock(context: MockContext, field: Field?, owner: Any?): Any? {
//            return context.beanMocker(field).mock(context)
//        }
//    }

}