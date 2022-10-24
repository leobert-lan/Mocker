package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.ValuePool
import osp.leobert.utils.mocker.adapter.impl.Utils.shouldIgnoreMock
import java.lang.reflect.Field
import java.lang.reflect.Modifier

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> FieldMockHandler </p>
 * 处理特定的field，扩展了注解约束处理
 * Created by leobert on 2020/11/18.
 */
sealed class FieldMockHandler<T> : MockHandlerV2<T> {

    object IntFieldMockHandler : FieldMockHandler<Int>() {
        override fun mock(
            context: MockContext,
            field: Field?,
            owner: Any?,
            groups: Array<out Class<*>>
        ): Int {
            context.intValuePool.reset()
            field?.let {
                context.intMockAdapter.adapt(context, field, groups)
            }
            return context.intValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object ShortFieldMockHandler : FieldMockHandler<Short>() {
        override fun mock(
            context: MockContext,
            field: Field?,
            owner: Any?,
            groups: Array<out Class<*>>
        ): Short {
            context.shortValuePool.reset()
            field?.let {
                context.shortMockAdapter.adapt(context, field, groups)
            }
            return context.shortValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object LongFieldMockHandler : FieldMockHandler<Long>() {
        override fun mock(
            context: MockContext,
            field: Field?,
            owner: Any?,
            groups: Array<out Class<*>>
        ): Long {
            context.longValuePool.reset()
            field?.let {
                context.longMockAdapter.adapt(context, field, groups)
            }
            return context.longValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object FloatFieldMockHandler : FieldMockHandler<Float>() {
        override fun mock(
            context: MockContext,
            field: Field?,
            owner: Any?,
            groups: Array<out Class<*>>
        ): Float {
            context.floatValuePool.reset()
            field?.let {
                context.floatMockAdapter.adapt(context, field, groups)
            }
            return context.floatValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object DoubleFieldMockHandler : FieldMockHandler<Double>() {
        override fun mock(
            context: MockContext,
            field: Field?,
            owner: Any?,
            groups: Array<out Class<*>>
        ): Double {
            context.doubleValuePool.reset()
            field?.let {
                context.doubleMockAdapter.adapt(context, field, groups)
            }
            return context.doubleValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object ByteFieldMockHandler : FieldMockHandler<Byte>() {
        override fun mock(
            context: MockContext,
            field: Field?,
            owner: Any?,
            groups: Array<out Class<*>>
        ): Byte {
            context.byteValuePool.reset()
            field?.let {
                context.byteMockAdapter.adapt(context, field, groups)
            }
            return context.byteValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object BooleanFieldMockHandler : FieldMockHandler<Boolean>() {
        override fun mock(
            context: MockContext,
            field: Field?,
            owner: Any?,
            groups: Array<out Class<*>>
        ): Boolean {
            context.boolValuePool.reset()
            field?.let {
                context.booleanMockAdapter.adapt(context, field, groups)
            }
            return context.boolValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

    object CharFieldMockHandler : FieldMockHandler<Char>() {
        override fun mock(
            context: MockContext,
            field: Field?,
            owner: Any?,
            groups: Array<out Class<*>>
        ): Char {
            context.charValuePool.reset()
            field?.let {
                context.charMockAdapter.adapt(context, field, groups)
            }
            return context.charValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }


    object StringFieldMockHandler : FieldMockHandler<String>() {
        override fun mock(
            context: MockContext,
            field: Field?,
            owner: Any?,
            groups: Array<out Class<*>>
        ): String {
            context.stringValuePool.reset()
            field?.let {
                context.stringMockAdapter.adapt(context, field, groups)
            }
            return context.stringValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }

//    class EnumFieldMockHandler<T : Enum<T>> : FieldMockHandler<T>() {
//        override fun mock(context: MockContext, field: Field?, owner: Any?): T {
//            val enumValuePool: ValuePool.EnumValuePool<T> = ValuePool.EnumValuePool()
//            enumValuePool.reset()
//            field?.let {
//                enumValuePool.clazz = field.type as Class<T>?
//                context.enumMockAdapter.adapt(context, field)
//            }
//            return enumValuePool.randomGet(context).apply {
//                context.applyField(this, field, owner)
//            }
//        }
//    }

    object EnumFieldMockHandler2 : FieldMockHandler<Enum<*>>() {
        override fun mock(
            context: MockContext,
            field: Field?,
            owner: Any?,
            groups: Array<out Class<*>>
        ): Enum<*> {
            val enumValuePool: ValuePool.EnumValuePool = ValuePool.EnumValuePool()
            enumValuePool.reset()
            field?.let {
                enumValuePool.clazz = field.type
                context.enumMockAdapter.adapt(context, field, groups)
            }
            return enumValuePool.randomGet(context).apply {
                context.applyField(this, field, owner)
            }
        }
    }


    class BeanFieldMockHandler(
        private val clazz: Class<*>,
        private val considerSuper: Boolean = true
    ) : FieldMockHandler<Any?>() {

        override fun mock(
            context: MockContext,
            field: Field?,
            owner: Any?,
            groups: Array<out Class<*>>
        ): Any {

            // if any where request to mock a bean , we always return a nonnull result
            // otherwise it has been ignored by an activated 'MockIgnore'

            return context.createInstance(clazz).apply {
                context.applyField(this, field, owner)

                var currentClass = clazz
                while (considerSuper && currentClass != Any::class.java) {
                    currentClass.declaredFields.forEach {
                        val fieldModifiers = it.modifiers
                        it.isAccessible = true

                        when {
                            it.get(this) != null -> {                                               //ignore not null
                                context.logger.log("ignore not null: ${it.name}")
                            }
                            it.shouldIgnoreMock(
                                groups,
                                context.logger
                            ) -> {                                                                  //annotated with MockIgnore
                                context.logger.log("ignore MockIgnore: ${it.name}")
                            }
                            Modifier.isAbstract(fieldModifiers) -> {                                //is abstract
                                context.logger.log("ignore abstract: ${it.name}")
                            }
                            Modifier.isStatic(fieldModifiers) -> {                                  //ignore static
                            }
                            else ->
                                BaseMockHandler<Any>(type = it.genericType).mock(
                                    context,
                                    it,
                                    this,
                                    groups
                                )
                        }
                    }
                    currentClass = currentClass.superclass
                }
            }
        }
    }

}