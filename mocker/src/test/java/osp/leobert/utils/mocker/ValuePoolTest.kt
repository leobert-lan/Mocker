package osp.leobert.utils.mocker

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 *
 * **Package:** osp.leobert.utils.mocker
 *
 * **Project:** Mocker
 *
 * **Classname:** ValuePoolTest
 *
 * **Description:** TODO
 * Created by leobert on 2020/11/22.
 */
internal class ValuePoolTest {

    enum class E {
        A, B
    }

    @Test
    fun randomGetEnum() {
        val pool = ValuePool.EnumValuePool<E>().apply {
            this.clazz = E::class.java
        }
        for (i in 0..100) {
            pool.randomGet(MockContext()).let {
                print(it)
                assertEquals(it.declaringClass.name, E::class.java.name)
            }
        }
    }
}