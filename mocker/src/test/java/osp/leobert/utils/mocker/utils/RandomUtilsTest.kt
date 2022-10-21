package osp.leobert.utils.mocker.utils

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 *
 * **Package:** osp.leobert.utils.mocker.utils
 *
 * **Project:** Mocker
 *
 * **Classname:** RandomUtilsTest
 * Created by leobert on 2020/11/18.
 */
@RunWith(JUnit4::class)
internal class RandomUtilsTest {

    @Test
    fun nextBoolean() {
    }

    @Test
    fun nextInt() {
        RandomUtils.nextInt(0, 0).let {
            assertEquals(it, 0)
        }
    }

    @Test
    fun nextLong() {
    }

    @Test
    fun nextFloat() {
    }

    @Test
    fun nextDouble() {
    }

    @Test
    fun nextSize() {
    }
}