package osp.leobert.utils.mocker.handler

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.TypeToken
import osp.leobert.utils.mocker.notation.MockIntRange

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
    }

    class Bean : Foo() {
        @MockIntRange(from = 3, to = 4)
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
}