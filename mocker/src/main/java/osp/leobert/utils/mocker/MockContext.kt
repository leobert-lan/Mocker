package osp.leobert.utils.mocker

import osp.leobert.utils.mocker.adapter.ComposeFieldMockAdapter
import osp.leobert.utils.mocker.adapter.FieldMockAdapter
import osp.leobert.utils.mocker.adapter.android.IntDefAdapter
import osp.leobert.utils.mocker.adapter.android.IntRangeAdapter
import osp.leobert.utils.mocker.adapter.android.LongDefAdapter
import osp.leobert.utils.mocker.adapter.android.LongRangeAdapter
import osp.leobert.utils.mocker.handler.BeanMockHandler
import java.lang.reflect.Field

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

    fun beanMocker(field: Field): BeanMockHandler {
        //todo consider lru cache
        return BeanMockHandler(field.type)
    }

    //todo 其他基本类型的adapter
}