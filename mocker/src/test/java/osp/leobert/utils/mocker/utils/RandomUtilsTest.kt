package osp.leobert.utils.mocker.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 *
 * **Package:** osp.leobert.utils.mocker.utils
 *
 * **Project:** Mocker
 *
 * **Classname:** RandomUtilsTest
 *
 * **Description:** TODO
 * Created by leobert on 2020/11/18.
 */
internal class RandomUtilsTest {

    @Test
    fun nextBoolean() {
    }

    @Test
    fun nextInt() {
        RandomUtils.nextInt(0, 1).let {
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