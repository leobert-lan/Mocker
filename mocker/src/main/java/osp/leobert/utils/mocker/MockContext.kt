package osp.leobert.utils.mocker

import osp.leobert.utils.mocker.adapter.ComposeFieldMockAdapter
import osp.leobert.utils.mocker.adapter.FieldMockAdapter
import osp.leobert.utils.mocker.adapter.android.*
import osp.leobert.utils.mocker.handler.BeanMockHandler
import osp.leobert.utils.mocker.handler.FieldMockHandler
import osp.leobert.utils.mocker.handler.MockHandler
import osp.leobert.utils.mocker.utils.UnsafeUtils
import java.lang.reflect.Field
import java.lang.reflect.Type
import java.util.*


/**
 * <p><b>Package:</b> osp.leobert.utils.mocker </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> MockContext </p>
 * Created by leobert on 2020/11/18.
 */
class MockContext {
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
    var sizeRange = intArrayOf(2, 20)

    val intValuePool: ValuePool<Int> = ValuePool.IntValuePool()
    val shortValuePool: ValuePool<Short> = ValuePool.ShortValuePool()
    val byteValuePool: ValuePool<Byte> = ValuePool.ByteValuePool()
    val floatValuePool: ValuePool<Float> = ValuePool.FloatValuePool()
    val doubleValuePool: ValuePool<Double> = ValuePool.DoubleValuePool()
    val longValuePool: ValuePool<Long> = ValuePool.LongValuePool()

    val boolValuePool: ValuePool<Boolean> = ValuePool.BoolValuePool()
    val charValuePool: ValuePool<Char> = ValuePool.CharValuePool()


///////////////////////////////////////////////////////////////////////////
// field mock adapter
///////////////////////////////////////////////////////////////////////////

    //long,float double boolean char byte string enum todo

    //int long short byte
    val intMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))

    val longMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(LongRangeAdapter, LongDefAdapter))

    val shortMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(ShortRangeAdapter, ShortDefAdapter))

    val byteMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(ByteRangeAdapter, ByteDefAdapter))

    val floatMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(FloatRangeAdapter))

    val doubleMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(DoubleRangeAdapter))

    val booleanMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(BooleanAdapter))

    val charMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(CharRangeAdapter, CharDefAdapter))

    //

    val stringMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))

    val enumMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))

    ///////////////////////////////////////////////////////////////////////////
// strategy
///////////////////////////////////////////////////////////////////////////
    val fieldMockStrategy: MutableMap<Class<*>, MockHandler<*>> =
        hashMapOf<Class<*>, MockHandler<*>>().apply {
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

            // TODO: 2020/11/20 next

            //        registerMocker(BIG_INTEGER_MOCKER, BigInteger.class);
            //        registerMocker(BIG_DECIMAL_MOCKER, BigDecimal.class);
            //        registerMocker(STRING_MOCKER, String.class);
            //        registerMocker(DATE_MOCKER, Date.class);
        }

    ///////////////////////////////////////////////////////////////////////////
    // cache
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Bean缓存
     */
    private val beanCache: Map<String, Any> = HashMap()

    /**
     * TypeVariable缓存
     */
    private val typeVariableCache: MutableMap<String, Type> = HashMap<String, Type>()

    fun beanMocker(field: Field): BeanMockHandler {
        //todo consider lru cache
        return BeanMockHandler(field.type)
    }

    fun mockHandler(clazz: Class<*>): MockHandler<*>? {
        return fieldMockStrategy[clazz]
    }

    fun getVariableType(name: String): Type {
        return typeVariableCache[name] ?: throw MockException("$name not init")
    }

    fun createInstance(clazz: Class<*>): Any {
//缓存？
        //adapter

        return UnsafeUtils.newInstance(clazz)
    }

    fun applyField(value: Any?, field: Field?, owner: Any?) {
        owner?.let {
            field?.isAccessible = true
            field?.set(it, value)
        }
    }

    //todo 其他基本类型的adapter
}