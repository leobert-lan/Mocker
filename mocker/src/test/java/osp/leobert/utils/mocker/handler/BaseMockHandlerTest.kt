package osp.leobert.utils.mocker.handler

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import osp.leobert.utils.mocker.MockContext
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

    @Test
    fun mockBean() {
        BaseMockHandler<Bean>(Bean::class.java).mock(MockContext()).let {
            print(it)
            assertEquals(it.javaClass, Bean::class.java)
        }
    }
}