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
            context.intValuePool.reset()
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
            context.shortValuePool.reset()
            field?.let {
                context.shortMockAdapter.adapt(context, field)
            }
            return context.shortValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object LongFieldMockHandler : FieldMockHandler<Long>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Long {
            context.longValuePool.reset()
            field?.let {
                context.longMockAdapter.adapt(context, field)
            }
            return context.longValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object FloatFieldMockHandler : FieldMockHandler<Float>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Float {
            context.floatValuePool.reset()
            field?.let {
                context.floatMockAdapter.adapt(context, field)
            }
            return context.floatValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object DoubleFieldMockHandler : FieldMockHandler<Double>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Double {
            context.doubleValuePool.reset()
            field?.let {
                context.doubleMockAdapter.adapt(context, field)
            }
            return context.doubleValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object ByteFieldMockHandler : FieldMockHandler<Byte>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Byte {
            context.byteValuePool.reset()
            field?.let {
                context.byteMockAdapter.adapt(context, field)
            }
            return context.byteValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object BooleanFieldMockHandler : FieldMockHandler<Boolean>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Boolean {
            context.boolValuePool.reset()
            field?.let {
                context.booleanMockAdapter.adapt(context, field)
            }
            return context.boolValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object CharFieldMockHandler : FieldMockHandler<Char>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): Char {
            context.charValuePool.reset()
            field?.let {
                context.charMockAdapter.adapt(context, field)
            }
            return context.charValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }


    //todo next





    class StringFieldMockHandler : FieldMockHandler<String>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): String {
            context.stringValuePool.reset()
            field?.let {
                context.stringMockAdapter.adapt(context, field)
            }
            return context.stringValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    class EnumFieldMockHandler<T:Enum<T>> : FieldMockHandler<T>() {
        override fun mock(context: MockContext, field: Field?, owner: Any?): T {
            context.enumValuePool.reset()
            field?.let {
                context.enumMockAdapter.adapt(context, field)
            }
            return (context.enumValuePool.randomGet(context) as T).apply {
                context.applyField(this, field, owner)
            }
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

                //todo missing apply to owner?
            }
        }
    }

}