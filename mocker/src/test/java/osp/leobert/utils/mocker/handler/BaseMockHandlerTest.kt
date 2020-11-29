package osp.leobert.utils.mocker.handler

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.TypeToken
import osp.leobert.utils.mocker.notation.MockIntRange
import osp.leobert.utils.mocker.notation.MockSize

/**
 *
 * **Package:** osp.leobert.utils.mocker.handler
 * **Project:** Mocker
 * **Classname:** BaseMockHandlerTest
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

    class CollectionTestCase(
        @field:MockSize(min = 3, max = 4)
        var list: List<Int>? = null,

        @field:MockSize(value = 2)
        var arrayList: ArrayList<Int>? = null,

        //相当于：? extends osp.leobert.utils.mocker.handler.BaseMockHandlerTest$Foo
        //因为是out Foo
        @field:MockSize(min = 3, max = 4)
        var set: Set<Foo>? = null,

        @field:MockSize(value = 2)
        var hashSet: HashSet<Foo>? = null,

        @field:MockSize(min = 3, max = 4)
        var listOfList: List<List<Foo>>? = null,
    ) {

        override fun toString(): String {
            return "CollectionTestCase(list=$list, " +
                    "arrayList=$arrayList, set=$set, " +
                    "hashSet=$hashSet, listOfList=$listOfList)"
        }
    }


    @Test
    fun mockCollection() {

        BaseMockHandler<CollectionTestCase>(CollectionTestCase::class.java).mock(MockContext())
            .let {
                println(it)
                //todo check size and type
            }
    }

    class ArrayTestCase {
        @field:MockSize(min = 3, max = 4)
        var intArray: Array<Char>? = null

        @field:MockSize(min = 3, max = 4)
        var charArrayArray: Array<Array<Int>>? = null

        @field:MockSize(value = 2)
        var fooArray: Array<Foo>? = null



        override fun toString(): String {
            return "ArrayTestCase:${Gson().toJson(this)}"
        }


    }


    @Test
    fun mockArray() {

        BaseMockHandler<ArrayTestCase>(ArrayTestCase::class.java).mock(MockContext()).let {
            println(it)
        }
    }
}