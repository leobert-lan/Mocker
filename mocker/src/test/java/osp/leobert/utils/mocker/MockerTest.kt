package osp.leobert.utils.mocker

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import osp.leobert.utils.mocker.notation.*
import osp.leobert.utils.mocker.notation.group.Default

/**
 *
 * **Package:** osp.leobert.utils.mocker
 *
 * **Project:** Mocker
 *
 * **Classname:** MockerTest
 * Created by leobert on 2020/11/29.
 */
@RunWith(JUnit4::class)
@Suppress("unused")
internal class MockerTest {

    interface Group1
    interface Group2

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    @MockStringDef(value = ["Leobert", "Tony"], groups = [Group1::class, Default::class])
    @MockStringDef(value = ["Leobert2"], groups = [Group2::class])
    annotation class Name

    class Foo(@field:Name val name: String) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Foo

            if (name != other.name) return false

            return true
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }

    class Bar<T>(val t: T? = null)

    class BarFoo(val bar: Bar<Foo>)

    @Test
    fun mock() {
        repeat(10) {

            val bar: Bar<Foo> = Mocker.mock(object : TypeToken<Bar<Foo>>() {})
            println(Gson().toJson(bar))

            val bar2: Bar<Foo> =
                Mocker.mockWithGroup(object : TypeToken<Bar<Foo>>() {}, Group2::class.java)
            println(Gson().toJson(bar2))
        }
    }


    @Test
    fun testMock() {
        val group1Results = hashSetOf(Foo("Leobert"), Foo("Tony"))
        val group2Results = hashSetOf(Foo("Leobert2"))

        repeat(20) {
            val foo: Foo = Mocker.mock(Foo::class.java)
            println(Gson().toJson(foo))
            Assert.assertEquals(true, group1Results.contains(foo))

            Assert.assertEquals(
                true,
                group1Results.contains(
                    Mocker.mockWithGroup(
                        Foo::class.java,
                        Group1::class.java
                    )
                )
            )

            Assert.assertEquals(
                true,
                group1Results.contains(
                    Mocker.mockWithGroup(
                        Foo::class.java,
                        Default::class.java
                    )
                )
            )

            Assert.assertEquals(
                true,
                group1Results.contains(
                    Mocker.mockWithGroup(
                        Foo::class.java,
                        Group1::class.java, Group2::class.java
                    )
                )
            )

            Assert.assertEquals(
                false,
                group1Results.contains(
                    Mocker.mockWithGroup(
                        Foo::class.java,
                        Group2::class.java
                    )
                )
            )

            Assert.assertEquals(
                true,
                group2Results.contains(
                    Mocker.mockWithGroup(
                        Foo::class.java,
                        Group2::class.java
                    )
                )
            )

        }

    }

    @Test
    fun testMock1() {
        repeat(10) {
            val bean: BarFoo = Mocker.mock(BarFoo::class.java)
            println(Gson().toJson(bean))

            val bean2: BarFoo = Mocker.mockWithGroup(BarFoo::class.java, Group2::class.java)
            println(Gson().toJson(bean2))

            //will miss group
            val bean3: BarFoo = Mocker.mockWithGroup(BarFoo::class.java, MockerTest::class.java)
            println(Gson().toJson(bean3))
        }
    }

    @Test
    fun testMock2() {
//        val bean: List<BarFoo> = Mocker.mock(object : TypeToken<List<BarFoo>>() {})
//        println(Gson().toJson(bean))

        //todo error,传递参数丢失
        val bean2: List<BarFoo> =
            Mocker.mockWithGroup(object : TypeToken<List<BarFoo>>() {}, Group2::class.java)
        println(Gson().toJson(bean2))
    }

    interface I
    open class S<out S> : I
    class A(val a: Int) : S<A>(), I

    class B(val b: Boolean) : S<B>(), I

    @Test
    fun testMock3() {
        val bean: List<out S<A>> = Mocker.mock(object : TypeToken<List<out S<A>>>() {})
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
//        @field:MockIntRange(from = 1, to = 2)
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

        val i: Int = Mocker.mock(Int::class.java)
        println(i)

        MockContext().intMockAdapter
    }


}