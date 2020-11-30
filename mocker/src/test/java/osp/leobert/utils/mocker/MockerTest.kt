package osp.leobert.utils.mocker

import org.junit.jupiter.api.Test

/**
 *
 * **Package:** osp.leobert.utils.mocker
 *
 * **Project:** Mocker
 *
 * **Classname:** MockerTest
 *
 * **Description:** TODO
 * Created by leobert on 2020/11/29.
 */
internal class MockerTest {

    @Test
    fun mock() {
        class Foo {}
        class Bar<T>(val t: T? = null)

        val bar: Bar<Foo> = Mocker.mock(object : TypeToken<Bar<Foo>>() {})
    }

    @Test
    fun testMock() {
    }

    @Test
    fun testMock1() {
    }

    @Test
    fun testMock2() {
    }
}