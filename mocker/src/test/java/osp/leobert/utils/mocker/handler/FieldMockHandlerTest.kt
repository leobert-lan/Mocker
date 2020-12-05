package osp.leobert.utils.mocker.handler

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.notation.*

/**
 *
 * **Package:** osp.leobert.utils.mocker.handler
 *
 * **Project:** Mocker
 *
 * **Classname:** FieldMockHandlerTest
 *
 * Created by leobert on 2020/11/21.
 */
internal class FieldMockHandlerTest {
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    @MockIntDef(value = [3, 4, 6])
    annotation class Type

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    @MockCharDef(value = ['M'])
    annotation class CharTest

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    @MockStringDef(value = ["Leobert", "Tony"])
    annotation class Name

    class Sub(
        @field:MockIntRange(from = -5, to = -1)
        var intRange: Int? = null
    )

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

        @field:MockTrue
        var trueTest: Boolean? = null

        @field:MockFalse
        var falseTest: Boolean? = null

        @field:MockCharRange(from = 'a', to = 'z')
        var charRange: Char? = null

        @field:CharTest
        var charDef: Char? = null

        @field:Name
        var stringDef: String? = null

        var sub: Sub? = null
    }

    @Test
    fun mockIntRange() {
        val field = Case::class.java.getDeclaredField("intRange")
        
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

    @Test
    fun mockBool() {
        var field = Case::class.java.getDeclaredField("trueTest")
        
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.BooleanFieldMockHandler.mock(
                context, field
            ).let {
                assert(it)
            }
        }

        field = Case::class.java.getDeclaredField("falseTest")
        
        for (i in 0..100) {
            FieldMockHandler.BooleanFieldMockHandler.mock(
                context, field
            ).let {
                assert(it == false)
            }
        }
    }

    @Test
    fun mockCharRange() {
        val field = Case::class.java.getDeclaredField("charRange")
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.CharFieldMockHandler.mock(
                context, field
            ).let {
                println(it)
                assert((it >= 'a') && (it <= 'z'))
            }
        }
    }

    @Test
    fun mockCharDef() {
        val field = Case::class.java.getDeclaredField("charDef")
        
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.CharFieldMockHandler.mock(
                context, field
            ).let {
                println(it)
                assert(it == 'M')
            }
        }
    }

    @Test
    fun mockStringDef() {
        val field = Case::class.java.getDeclaredField("stringDef")
        
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.StringFieldMockHandler.mock(
                context, field
            ).let {
                println(it)
                assert(it == "Leobert" || it == "Tony")
            }
        }
    }

    @Test
    fun mockBean() {
        val field = Case::class.java.getDeclaredField("sub")
        val context = MockContext()

        FieldMockHandler.BeanFieldMockHandler(field.type).mock(
            context, field
        ).let {
            println(it)
            assert(it != null)
            assertEquals( Sub::class.java.name,it?.javaClass?.name)
        }
    }
}