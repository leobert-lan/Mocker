package osp.leobert.utils.mocker

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * ```
 * **Package:** osp.leobert.utils.mocker
 * **Project:** Mocker
 * **Classname:** ValuePoolTest
 * ```
 *
 * Created by leobert on 2020/11/22.
 */
@RunWith(JUnit4::class)
internal class ValuePoolTest {

    enum class E {
        A, B
    }

    @Test
    fun randomGetEnum() {
        val pool = ValuePool.EnumValuePool().apply {
            this.clazz = E::class.java
        }
        for (i in 0..100) {
            pool.randomGet(MockContext()).let {
                print(it)
                assertEquals(E::class.java.name, it.declaringClass.name)
            }
        }
    }
}