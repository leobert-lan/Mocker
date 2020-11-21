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

    //todo add pending jobs / supply result to owner immediately


    object IntFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Int {
            field?.let {
                context.intMockAdapter.adapt(context, field)
            }
            return context.intValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object ShortFieldMockHandler : FieldMockHandler<Short>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Short {
            field?.let {
                context.shortMockAdapter.adapt(context, field)
            }
            return context.shortValuePool.randomGet(context)
        }
    }

    object LongFieldMockHandler : FieldMockHandler<Long>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Long {
            field?.let {
                context.longMockAdapter.adapt(context, field)
            }
            return context.longValuePool.randomGet(context)
        }
    }

    object FloatFieldMockHandler : FieldMockHandler<Float>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Float {
            field?.let {
                context.floatMockAdapter.adapt(context, field)
            }
            return context.floatValuePool.randomGet(context)
        }
    }

    object DoubleFieldMockHandler : FieldMockHandler<Double>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Double {
            field?.let {
                context.doubleMockAdapter.adapt(context, field)
            }
            return context.doubleValuePool.randomGet(context)
        }
    }

    object ByteFieldMockHandler : FieldMockHandler<Byte>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Byte {
            field?.let {
                context.byteMockAdapter.adapt(context, field)
            }
            return context.byteValuePool.randomGet(context)
        }
    }

    object BooleanFieldMockHandler : FieldMockHandler<Boolean>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Boolean {
            field?.let {
                context.booleanMockAdapter.adapt(context, field)
            }
            return context.boolValuePool.randomGet(context)
        }
    }

    object CharFieldMockHandler : FieldMockHandler<Char>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Char {
            field?.let {
                context.charMockAdapter.adapt(context, field)
            }
            return context.charValuePool.randomGet(context)
        }
    }


    //todo next





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


    class BeanFieldMockHandler(private val clazz: Class<*>) : FieldMockHandler<Any?>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Any? {
            field?.let {
                return context.beanMocker(field).mock(context, field, owner).apply {
                    context.applyField(this, field, owner)
                }
            }
            return context.createInstance(clazz).apply {
                var currentClass = clazz
                while (currentClass != Any::class.java) {
                    currentClass.declaredFields.forEach {
                        BaseMockHandler<Any>(type = it.genericType).mock(context, it, this)
                    }
                    currentClass = currentClass.superclass
                }
            }
        }
    }

}