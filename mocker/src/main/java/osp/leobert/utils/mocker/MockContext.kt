package osp.leobert.utils.mocker

import osp.leobert.utils.mocker.adapter.ComposeFieldMockAdapter
import osp.leobert.utils.mocker.adapter.FieldMockAdapter
import osp.leobert.utils.mocker.adapter.android.IntDefAdapter
import osp.leobert.utils.mocker.adapter.android.IntRangeAdapter
import osp.leobert.utils.mocker.adapter.android.LongDefAdapter
import osp.leobert.utils.mocker.adapter.android.LongRangeAdapter
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
    val floatValuePool: ValuePool<Float> = ValuePool.FloatValuePool()
    val doubleValuePool: ValuePool<Double> = ValuePool.DoubleValuePool()
    val longValuePool: ValuePool<Long> = ValuePool.LongValuePool()


    internal var currentObj: Any? = null

///////////////////////////////////////////////////////////////////////////
// field mock adapter
///////////////////////////////////////////////////////////////////////////

    val intMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))

    //long,float double boolean char byte string enum todo
    val longMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(LongRangeAdapter, LongDefAdapter))

    val shortMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))

    val byteMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))

    val floatMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))

    val doubleMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))

    val booleanMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))

    val charMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))


    val stringMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))

    val enumMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))

    ///////////////////////////////////////////////////////////////////////////
// strategy
///////////////////////////////////////////////////////////////////////////
    val fieldMockStrategy: MutableMap<Class<*>, FieldMockHandler<*>> =
        hashMapOf<Class<*>, FieldMockHandler<*>>().apply {
            //Integer.class, int.class);
            this[Int::class.java] = FieldMockHandler.IntFieldMockHandler()
            // registerMocker(BYTE_MOCKER, byte.class, Byte.class);
            this[Byte::class.java] = FieldMockHandler.ByteFieldMockHandler()

            // TODO: 2020/11/20 next

            //        registerMocker(BOOLEAN_MOCKER, boolean.class, Boolean.class);
            //        registerMocker(CHARACTER_MOCKER, char.class, Character.class);
            //        registerMocker(SHORT_MOCKER, short.class, Short.class);
            //        registerMocker(LONG_MOCKER, long.class, Long.class);
            //        registerMocker(FLOAT_MOCKER, float.class, Float.class);
            //        registerMocker(DOUBLE_MOCKER, double.class, Double.class);
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

    fun mockHandler(clazz: Class<*>): FieldMockHandler<*>? {
        return fieldMockStrategy[clazz]
    }

    fun getVariableType(name: String): Type {
        return typeVariableCache[name] ?: throw MockException("$name not init")
    }

    fun createInstance(clazz: Class<*>):Any {
//缓存？
        //adapter

        return UnsafeUtils.newInstance(clazz)
    }

    //todo 其他基本类型的adapter
}