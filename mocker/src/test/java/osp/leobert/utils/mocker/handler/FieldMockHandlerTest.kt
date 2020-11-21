package osp.leobert.utils.mocker.handler

import org.junit.jupiter.api.Test
import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.notation.MockFloatRange
import osp.leobert.utils.mocker.notation.MockIntDef
import osp.leobert.utils.mocker.notation.MockIntRange

/**
 *
 * **Package:** osp.leobert.utils.mocker.handler
 *
 * **Project:** Mocker
 *
 * **Classname:** FieldMockHandlerTest
 *
 * **Description:** TODO
 * Created by leobert on 2020/11/21.
 */
internal class FieldMockHandlerTest {
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    @MockIntDef(value = [3, 4,6])
    annotation class Type

    class Case {
        @field:MockIntRange(from = -5, to = -1)
        var intRange: Int? = null

        @field:Type
        var intDef: Int? = null


        @field:MockIntRange(from = -5, to = -1)
        var longRange: Long? = null

        @field:Type
        var longDef: Long? = null

        @field:MockIntRange(from = 1, to = 5)
        var shortRange: Short? = null

        @field:Type
        var shortDef: Short? = null

        @field:MockIntRange(from = 1, to = 5)
        var byteRange: Byte? = null

        @field:Type
        var byteDef: Byte? = null

        @field:MockFloatRange(from = -1000f, to = 1000f)
        var floatRange: Float? = null

        @field:MockFloatRange(from = -1000f, to = 1000f)
        var doubleRange: Byte? = null
    }

    @Test
    fun mockIntRange() {
        val field = Case::class.java.getDeclaredField("intRange")
        //JavaCase::class.java.getDeclaredField("range")
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.IntFieldMockHandler.mock(
                context, field
            ).let {
                println(it)
                assert((it >= -5) && (it <= -1))
            }
        }
    }

    @Test
    fun mockIntDef() {
        val field = Case::class.java.getDeclaredField("intDef")
        //JavaCase::class.java.getDeclaredField("range")
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.IntFieldMockHandler.mock(
                context, field
            ).let {
                println(it)
                assert((it == 3) || (it == 4) || it == 6)
            }
        }
    }

    @Test
    fun mockLongRange() {
        val field = Case::class.java.getDeclaredField("longRange")
        //JavaCase::class.java.getDeclaredField("range")
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.LongFieldMockHandler.mock(
                context, field
            ).let {
                println(it)
                assert((it >= -5) && (it <= -1))
            }
        }
    }

    @Test
    fun mockLongDef() {
        val field = Case::class.java.getDeclaredField("longDef")
        //JavaCase::class.java.getDeclaredField("range")
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.LongFieldMockHandler.mock(
                context, field
            ).let {
                println(it)
                assert((it == 3L) || (it == 4L) || it == 6L)
            }
        }
    }

    @Test
    fun mockShortRange() {
        val field = Case::class.java.getDeclaredField("shortRange")
        //JavaCase::class.java.getDeclaredField("range")
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.ShortFieldMockHandler.mock(
                context, field
            ).let {
                println(it)
                assert((it >= 1) && (it <= 5))
            }
        }
    }

    @Test
    fun mockShortDef() {
        val field = Case::class.java.getDeclaredField("shortDef")
        //JavaCase::class.java.getDeclaredField("range")
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.ShortFieldMockHandler.mock(
                context, field
            ).let {
                println(it)
                assert((it == 3.toShort()) || (it == 4.toShort()) || it == 6.toShort())
            }
        }
    }

    @Test
    fun mockByteRange() {
        val field = Case::class.java.getDeclaredField("byteRange")
        //JavaCase::class.java.getDeclaredField("range")
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.ByteFieldMockHandler.mock(
                context, field
            ).let {
                println(it)
                assert((it >= 1) && (it <= 5))
            }
        }
    }

    @Test
    fun mockByteDef() {
        val field = Case::class.java.getDeclaredField("byteDef")
        //JavaCase::class.java.getDeclaredField("range")
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.ByteFieldMockHandler.mock(
                context, field
            ).let {
                println(it)
                assert((it == 3.toByte()) || (it == 4.toByte()) || it == 6.toByte())
            }
        }
    }

    @Test
    fun mockFloatRange() {
        val field = Case::class.java.getDeclaredField("floatRange")
        //JavaCase::class.java.getDeclaredField("range")
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.FloatFieldMockHandler.mock(
                context, field
            ).let {
                println(it)
                assert((it >= -1000f) && (it <= 1000))
            }
        }
    }

    @Test
    fun mockDoubleRange() {
        val field = Case::class.java.getDeclaredField("doubleRange")
        //JavaCase::class.java.getDeclaredField("range")
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.DoubleFieldMockHandler.mock(
                context, field
            ).let {
                println(it)
                assert((it >= -1000) && (it <= 1000))
            }
        }
    }
}