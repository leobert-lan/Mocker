package osp.leobert.utils.mocker.handler

import org.junit.jupiter.api.Test
import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.notation.MockIntDef
import osp.leobert.utils.mocker.notation.MockIntRange

/**
 *
 * **Package:** osp.leobert.utils.mocker.handler
 *
 * **Project:** Mocker
 *
 * **Classname:** IntFieldMockHandlerTest
 *
 * **Description:** TODO
 * Created by leobert on 2020/11/19.
 */
internal class IntFieldMockHandlerTest {

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    @MockIntDef(value = [3, 4,6])
    annotation class Type

    class Case {
        @field:MockIntRange(from = -5, to = -1)
        var range: Int? = null

        @field:Type
        var type: Int? = null
    }

    @Test
    fun mock() {
        val field = Case::class.java.getDeclaredField("range")
        //JavaCase::class.java.getDeclaredField("range")
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.IntFieldMockHandler.mock(
                context, field
            ).let {
                System.out.println(it)
                assert((it >= -5) && (it <= -1))
            }
        }
    }

    @Test
    fun mockDef() {
        val field = Case::class.java.getDeclaredField("type")
        //JavaCase::class.java.getDeclaredField("range")
        val context = MockContext()
        for (i in 0..100) {
            FieldMockHandler.IntFieldMockHandler.mock(
                context, field
            ).let {
                System.out.println(it)
                assert((it == 3) || (it == 4) || it == 6)
            }
        }
    }
}