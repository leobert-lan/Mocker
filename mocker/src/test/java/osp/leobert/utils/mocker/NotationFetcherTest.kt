package osp.leobert.utils.mocker

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockIntDefAboveNotation
import osp.leobert.utils.mocker.adapter.impl.Utils.findMockIntRange
import osp.leobert.utils.mocker.jcase.JavaFoo
import osp.leobert.utils.mocker.notation.MockIntDef
import osp.leobert.utils.mocker.notation.MockIntRange
import osp.leobert.utils.mocker.notation.group.Default
import osp.leobert.utils.mocker.notation.repeat.MockIntDefs
import osp.leobert.utils.mocker.notation.repeat.MockIntRanges

/**
 * ```
 * **Package:** osp.leobert.utils.mocker
 * **Project:** Mocker
 * **Classname:** NotationFetcherTest
 * ```
 *
 * Created by leobert on 2022/10/20.
 */
@RunWith(JUnit4::class)
internal class NotationFetcherTest {

    class Foo {
        @field:MockIntRange(from = 1)
        @field:MockIntRange(from = 2, groups = [NotationFetcherTest::class])
        @field:MockIntRange(from = 3, groups = [NotationFetcherTest::class, Integer::class])
        var aInt: Int? = null

        @field:MockIntRanges(
            value = [
                MockIntRange(from = 1),
                MockIntRange(from = 2, groups = [NotationFetcherTest::class]),
                MockIntRange(from = 3, groups = [NotationFetcherTest::class, Integer::class])
            ]
        )
        val aInt2: Int? = null
    }

    @Test
    fun testFieldAnnotationFetcher() {

        //java8方式验证
        val field = Foo::class.java.getDeclaredField("aInt")
        assertEquals(1L, field.findMockIntRange()?.from)
        assertEquals(1L, field.findMockIntRange(Default::class.java)?.from)
        assertEquals(2L, field.findMockIntRange(NotationFetcherTest::class.java)?.from)

        assertEquals(null, field.findMockIntRange(Foo::class.java)?.from)

        assertEquals(3L, field.findMockIntRange(Integer::class.java)?.from)
        assertEquals(3L, field.findMockIntRange(Foo::class.java, Integer::class.java)?.from)

        assertEquals(
            1L,
            field.findMockIntRange(Foo::class.java, Default::class.java, Integer::class.java)?.from
        )

        //集合方式验证
        val field2 = Foo::class.java.getDeclaredField("aInt2")
        assertEquals(1L, field2.findMockIntRange()?.from)
        assertEquals(1L, field2.findMockIntRange(Default::class.java)?.from)
        assertEquals(2L, field2.findMockIntRange(NotationFetcherTest::class.java)?.from)

        assertEquals(null, field2.findMockIntRange(Foo::class.java)?.from)

        assertEquals(3L, field2.findMockIntRange(Integer::class.java)?.from)
        assertEquals(3L, field2.findMockIntRange(Foo::class.java, Integer::class.java)?.from)

        assertEquals(
            1L,
            field2.findMockIntRange(Foo::class.java, Default::class.java, Integer::class.java)?.from
        )

    }


    @Test
    fun testFieldAnnotationFetcherJava() {

        //java8方式验证
        val field = JavaFoo::class.java.getDeclaredField("aInt")
        assertEquals(1L, field.findMockIntRange()?.from)
        assertEquals(1L, field.findMockIntRange(Default::class.java)?.from)
        assertEquals(2L, field.findMockIntRange(NotationFetcherTest::class.java)?.from)

        assertEquals(null, field.findMockIntRange(Foo::class.java)?.from)

        assertEquals(3L, field.findMockIntRange(Integer::class.java)?.from)
        assertEquals(3L, field.findMockIntRange(Foo::class.java, Integer::class.java)?.from)

        assertEquals(
            1L,
            field.findMockIntRange(Foo::class.java, Default::class.java, Integer::class.java)?.from
        )

        //集合方式验证
        val field2 = JavaFoo::class.java.getDeclaredField("aInt2")
        assertEquals(1L, field2.findMockIntRange()?.from)
        assertEquals(1L, field2.findMockIntRange(Default::class.java)?.from)
        assertEquals(2L, field2.findMockIntRange(NotationFetcherTest::class.java)?.from)

        assertEquals(null, field2.findMockIntRange(Foo::class.java)?.from)

        assertEquals(3L, field2.findMockIntRange(Integer::class.java)?.from)
        assertEquals(3L, field2.findMockIntRange(Foo::class.java, Integer::class.java)?.from)

        assertEquals(
            1L,
            field2.findMockIntRange(Foo::class.java, Default::class.java, Integer::class.java)?.from
        )
    }

    @MockIntDef(value = [11])
    @MockIntDef(value = [12], groups = [NotationFetcherTest::class])
    @MockIntDef(value = [13], groups = [NotationFetcherTest::class, Integer::class])
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    annotation class IntEnum

    @MockIntDefs(
        value = [
            MockIntDef(value = [21]),
            MockIntDef(value = [22], groups = [NotationFetcherTest::class]),
            MockIntDef(value = [23], groups = [NotationFetcherTest::class, Integer::class])
        ]
    )
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    annotation class IntEnum2


    class Bar {
        @IntEnum //优先生效
        @IntEnum2
        var aInt: Int? = null

        @IntEnum2
        @IntEnum
        val aInt2: Int? = null
    }

    @Test
    fun testFieldAnnotationDefFetcher() {

        val field = Bar::class.java.getDeclaredField("aInt")
        assertEquals(11, field.findMockIntDefAboveNotation()?.value?.first())
        assertEquals(11, field.findMockIntDefAboveNotation(Default::class.java)?.value?.first())
        assertEquals(12, field.findMockIntDefAboveNotation(NotationFetcherTest::class.java)?.value?.first())

        assertEquals(null, field.findMockIntDefAboveNotation(Foo::class.java))

        assertEquals(13L, field.findMockIntDefAboveNotation(Integer::class.java)?.value?.first())
        assertEquals(13L, field.findMockIntDefAboveNotation(Foo::class.java, Integer::class.java)?.value?.first())

        assertEquals(
            11L,
            field.findMockIntDefAboveNotation(Foo::class.java, Default::class.java, Integer::class.java)?.value?.first()
        )

//        //集合方式验证
        val field2 = Bar::class.java.getDeclaredField("aInt2")
        assertEquals(21L, field2.findMockIntDefAboveNotation()?.value?.first())
        assertEquals(21L, field2.findMockIntDefAboveNotation(Default::class.java)?.value?.first())
        assertEquals(22L, field2.findMockIntDefAboveNotation(NotationFetcherTest::class.java)?.value?.first())

        assertEquals(null, field2.findMockIntDefAboveNotation(Foo::class.java)?.value?.first())

        assertEquals(23L, field2.findMockIntDefAboveNotation(Integer::class.java)?.value?.first())
        assertEquals(23L, field2.findMockIntDefAboveNotation(Foo::class.java, Integer::class.java)?.value?.first())

        assertEquals(
            21L,
            field2.findMockIntDefAboveNotation(Foo::class.java, Default::class.java, Integer::class.java)?.value?.first()
        )

    }
}