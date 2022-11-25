package osp.leobert.utils.mocker

import osp.leobert.utils.mocker.adapter.ComposeFieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.FieldMockAdapterV2
import osp.leobert.utils.mocker.adapter.impl.*
import osp.leobert.utils.mocker.constructor.ConstructorConstructor
import osp.leobert.utils.mocker.constructor.InstanceCreator
import osp.leobert.utils.mocker.handler.FieldMockHandler
import osp.leobert.utils.mocker.handler.MockHandlerV2
import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.TypeVariable


/**
 * <p><b>Package:</b> osp.leobert.utils.mocker </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> MockContext </p>
 * Created by leobert on 2020/11/18.
 */
class MockContext {

    var logger: Logger = object : Logger {
        override fun log(any: Any, thr: Throwable?) {
            println(any)
            thr?.let { println(it) }
        }
    }

    /**
     * If true, will use the same beans that have been created ever when mock the same type.
     *
     * */
    var skipSameType = false

    ///////////////////////////////////////////////////////////////////////////
    // default configs
    ///////////////////////////////////////////////////////////////////////////
    var byteRange = byteArrayOf(0, 127)
    var shortRange = shortArrayOf(0, 1000)
    var intRange = intArrayOf(0, 10000)
    var floatRange = floatArrayOf(0.0f, 10000.00f)
    var doubleRange = doubleArrayOf(0.0, 10000.00)
    var longRange = longArrayOf(0L, 10000L)
    var dateRange = arrayOf("1970-01-01", "2100-12-31")

    //存在嵌套使用时的风险，需深度优先，创建完目标size后立即使用，再对item进行mock
    var sizeRange = intArrayOf(2, 3)

    val intValuePool: ValuePool<Int> = ValuePool.IntValuePool()
    val shortValuePool: ValuePool<Short> = ValuePool.ShortValuePool()
    val byteValuePool: ValuePool<Byte> = ValuePool.ByteValuePool()
    val floatValuePool: ValuePool<Float> = ValuePool.FloatValuePool()
    val doubleValuePool: ValuePool<Double> = ValuePool.DoubleValuePool()
    val longValuePool: ValuePool<Long> = ValuePool.LongValuePool()

    val boolValuePool: ValuePool<Boolean> = ValuePool.BoolValuePool()
    val charValuePool: ValuePool<Char> = ValuePool.CharValuePool()

    //    val enumValuePool: ValuePool.EnumValuePool<*> = ValuePool.EnumValuePool()
    val stringValuePool: ValuePool<String> = ValuePool.StringValuePool()

    val sizeValuePool: ValuePool<Int> = ValuePool.SizeValuePool()


    ///////////////////////////////////////////////////////////////////////////
    // field mock adapter
    ///////////////////////////////////////////////////////////////////////////


    var intMockAdapter: FieldMockAdapterV2 =
        ComposeFieldMockAdapterV2(arrayListOf(IntRangeAdapterV2, IntDefAdapterV2))

    var longMockAdapter: FieldMockAdapterV2 =
        ComposeFieldMockAdapterV2(arrayListOf(LongRangeAdapterV2, LongDefAdapterV2))

    var shortMockAdapter: FieldMockAdapterV2 =
        ComposeFieldMockAdapterV2(arrayListOf(ShortRangeAdapterV2, ShortDefAdapterV2))

    var byteMockAdapter: FieldMockAdapterV2 =
        ComposeFieldMockAdapterV2(arrayListOf(ByteRangeAdapterV2, ByteDefAdapterV2))

    var floatMockAdapter: FieldMockAdapterV2 = FloatRangeAdapterV2

    var doubleMockAdapter: FieldMockAdapterV2 = DoubleRangeAdapterV2

    var booleanMockAdapter: FieldMockAdapterV2 = BooleanAdapterV2

    var charMockAdapter: FieldMockAdapterV2 =
        ComposeFieldMockAdapterV2(arrayListOf(CharRangeAdapterV2, CharDefAdapterV2))

    var stringMockAdapter: FieldMockAdapterV2 = StringDefAdapterV2

    var enumMockAdapter: FieldMockAdapterV2 =
        ComposeFieldMockAdapterV2(arrayListOf(IntRangeAdapterV2, IntDefAdapterV2))

    var collectionMockAdapter: FieldMockAdapterV2 = SizeAdapterV2

    ///////////////////////////////////////////////////////////////////////////
    // strategy
    ///////////////////////////////////////////////////////////////////////////
    val fieldMockStrategy: MutableMap<Class<*>, MockHandlerV2<*>> =
        hashMapOf<Class<*>, MockHandlerV2<*>>().apply {
            this[Int::class.java] = FieldMockHandler.IntFieldMockHandler
            this[Integer::class.java] = FieldMockHandler.IntFieldMockHandler

            this[Byte::class.java] = FieldMockHandler.ByteFieldMockHandler
            this[java.lang.Byte::class.java] = FieldMockHandler.ByteFieldMockHandler

            this[Short::class.java] = FieldMockHandler.ShortFieldMockHandler
            this[java.lang.Short::class.java] = FieldMockHandler.ShortFieldMockHandler

            this[Long::class.java] = FieldMockHandler.LongFieldMockHandler
            this[java.lang.Long::class.java] = FieldMockHandler.LongFieldMockHandler

            this[Float::class.java] = FieldMockHandler.FloatFieldMockHandler
            this[java.lang.Float::class.java] = FieldMockHandler.FloatFieldMockHandler

            this[Double::class.java] = FieldMockHandler.DoubleFieldMockHandler
            this[java.lang.Double::class.java] = FieldMockHandler.DoubleFieldMockHandler

            this[Boolean::class.java] = FieldMockHandler.BooleanFieldMockHandler
            this[java.lang.Boolean::class.java] = FieldMockHandler.BooleanFieldMockHandler

            this[Char::class.java] = FieldMockHandler.CharFieldMockHandler
            this[java.lang.Character::class.java] = FieldMockHandler.CharFieldMockHandler

            this[java.lang.String::class.java] = FieldMockHandler.StringFieldMockHandler

            // TODO: 一些服务端的常用类

            //        registerMocker(BIG_INTEGER_MOCKER, BigInteger.class);
            //        registerMocker(BIG_DECIMAL_MOCKER, BigDecimal.class);
            //        registerMocker(DATE_MOCKER, Date.class);
        }

    ///////////////////////////////////////////////////////////////////////////
    // cache
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Bean缓存
     */
    private val beanCache: MutableMap<String, Any> = HashMap()

    /**
     * TypeVariable缓存
     */
    private val typeVariableCache: MutableMap<String, Type> = HashMap()

    val constructorMap: MutableMap<Type, InstanceCreator<*>> = hashMapOf()

    private val constructorConstructor: ConstructorConstructor =
        ConstructorConstructor(constructorMap)

    fun parseParameterizedType(type: Type) {
        if (type is ParameterizedType) {
            val clazz = type.rawType as Class<*>
            val types: Array<Type> = type.actualTypeArguments
            val typeVariables: Array<out TypeVariable<out Class<*>>>? = clazz.typeParameters
            if (typeVariables != null && typeVariables.isNotEmpty()) {
                for (index in typeVariables.indices) {
                    typeVariableCache[typeVariables[index].name] = types[index]
                }
            }
        }
    }

    fun mockHandler(clazz: Class<*>): MockHandlerV2<*>? {
        return fieldMockStrategy[clazz]
    }

    fun getVariableType(name: String): Type {
        return typeVariableCache[name] ?: throw MockException("$name not init")
    }

    fun createInstance(clazz: Class<*>): Any {
        return if (skipSameType) {
            beanCache.getOrPut(clazz.typeName) {
                construct(clazz).apply {
                    beanCache[clazz.typeName] = this
                }
            }
        } else {
            construct(clazz).apply {
                beanCache[clazz.typeName] = this
            }
        }
    }

    private fun construct(clazz: Class<*>): Any {
        return constructorConstructor.get(TypeToken[clazz]).construct()
    }

    fun applyField(value: Any?, field: Field?, owner: Any?) {
        owner?.let {
            field?.isAccessible = true
            field?.set(it, value)
        }
    }
}