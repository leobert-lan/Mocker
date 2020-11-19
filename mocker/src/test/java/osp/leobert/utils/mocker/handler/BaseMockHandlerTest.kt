package osp.leobert.utils.mocker.handler

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import osp.leobert.utils.mocker.MockContext

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

    class Bean

    @Test
    fun mockBean() {
        BaseMockHandler<Bean>(Bean::class.java).mock(MockContext()).let {
            assertEquals(it.javaClass, Bean::class.java)
        }
    }
}