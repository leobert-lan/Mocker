package osp.leobert.utils.mocker

import com.google.gson.Gson
import org.junit.jupiter.api.Test
import osp.leobert.utils.mocker.notation.*

/**
 *
 * **Package:** osp.leobert.utils.mocker
 *
 * **Project:** Mocker
 *
 * **Classname:** MockerTest
 * Created by leobert on 2020/11/29.
 */
internal class MockerTest {

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    @MockStringDef(value = ["Leobert", "Tony"])
    annotation class Name

    class Foo(@field:Name val name: String)
    class Bar<T>(val t: T? = null)

    class BarFoo(val bar: Bar<Foo>)

    @Test
    fun mock() {
        val bar: Bar<Foo> = Mocker.mock(object : TypeToken<Bar<Foo>>() {})
        println(Gson().toJson(bar))
    }


    @Test
    fun testMock() {
        val foo: Foo = Mocker.mock(Foo::class.java)
        println(Gson().toJson(foo))
    }

    @Test
    fun testMock1() {
        val bean: BarFoo = Mocker.mock(BarFoo::class.java)
        println(Gson().toJson(bean))
    }

    @Test
    fun testMock2() {
        val bean: List<BarFoo> = Mocker.mock(object : TypeToken<List<BarFoo>>() {})
        println(Gson().toJson(bean))
    }

    open class I
    class A(val a: Int) : I()

    class B(val b: Boolean) : I()

    @Test
    fun testMock3() {
        val bean: List<out I> = Mocker.mock(object : TypeToken<List<out I>>() {})
        println(Gson().toJson(bean))
    }

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    @MockIntDef(value = [3, 4, 6])
    annotation class Type

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    @MockCharDef(value = ['M'])
    annotation class CharTest

    class Sample {
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

    }

    @Test
    fun testMock4() {
        val bean: Sample = Mocker.mock(Sample::class.java)
        println(Gson().toJson(bean))

        val i:Int = Mocker.mock(Int::class.java)
        println(i)
    }


}