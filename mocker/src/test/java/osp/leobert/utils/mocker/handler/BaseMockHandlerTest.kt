package osp.leobert.utils.mocker.handler

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.TypeToken
import osp.leobert.utils.mocker.notation.MockIntRange
import osp.leobert.utils.mocker.notation.MockSize

/**
 *
 * **Package:** osp.leobert.utils.mocker.handler
 *
 * **Project:** Mocker
 *
 * **Classname:** BaseMockHandlerTest
 *
 * **Description:** TODO
 * Created by leobert on 2020/11/19.
 */
internal class BaseMockHandlerTest {

    open class Foo {
        @MockIntRange(from = 3, to = 4)
        open var i2: Int? = null
        override fun toString(): String {
            return "Foo(i2=$i2)"
        }


    }

    class Bean : Foo() {
        @MockIntRange(from = 30, to = 40)
        private var i: Int? = null
        override fun toString(): String {
            return "Bean(i=$i,i2=$i2)"
        }
    }

    class ParameterizedTypeCase<T> {

        var t: T? = null
        override fun toString(): String {
            return "ParameterizedTypeCase(t=${t.toString()})"
        }


    }

    @Test
    fun mockBean() {
        BaseMockHandler<Bean>(Bean::class.java).mock(MockContext()).let {
            print(it)
            assertEquals(it.javaClass, Bean::class.java)
        }
    }

    class Foo2<A, B>(var a: A? = null, var b: B? = null) {
        override fun toString(): String {
            return "Foo2(a=$a, b=$b)"
        }
    }

    @Test
    fun mockParameterizedType() {
        val type = object : TypeToken<ParameterizedTypeCase<Bean>>() {}.type
        BaseMockHandler<ParameterizedTypeCase<Bean>>(type)
            .mock(MockContext().apply { parseParameterizedType(type) }).let {
                print(it)
                assertEquals(it.javaClass, ParameterizedTypeCase::class.java)
                assertEquals(it.t?.javaClass, Bean::class.java)
            }
    }

    @Test
    fun mockParameterizedType2() {
        val type = object : TypeToken<Foo2<Foo, Bean>>() {}.type
        val context = MockContext().apply { parseParameterizedType(type) }

        for (i in 0..100)
            BaseMockHandler<Foo2<Foo, Bean>>(type)
                .mock(context).let {
                    println(it)
                    assertEquals(it.javaClass, Foo2::class.java)
//                assertEquals(it.t?.javaClass, Bean::class.java)
                }
    }

    class ListTestCase(
        @field:MockSize(min = 3, max = 4)
        var list: List<Int>? = null,

        @field:MockSize(value = 2)
        var arrayList: ArrayList<Int>? = null
    ) {
        override fun toString(): String {
            return "ListTestCase(list=$list, arrayList=$arrayList)"
        }
    }


    @Test
    fun mockList() {
//        val type = object : TypeToken<Foo2<Foo, Bean>>() {}.type
//        val context = MockContext().apply { parseParameterizedType(type) }
//
//        for (i in 0..100)
//            BaseMockHandler<Foo2<Foo, Bean>>(type)
//                .mock(context).let {
//                    println(it)
//                    assertEquals(it.javaClass, Foo2::class.java)
//                }

        BaseMockHandler<ListTestCase>(ListTestCase::class.java).mock(MockContext()).let {
            println(it)
        }
    }

}