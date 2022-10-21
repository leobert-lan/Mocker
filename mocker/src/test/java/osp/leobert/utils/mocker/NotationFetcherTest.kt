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
        assertEquals(1L, field.findMockIntRange(arrayOf())?.from)
        assertEquals(1L, field.findMockIntRange(arrayOf(Default::class.java))?.from)
        assertEquals(2L, field.findMockIntRange(arrayOf(NotationFetcherTest::class.java))?.from)

        assertEquals(null, field.findMockIntRange(arrayOf(Foo::class.java))?.from)

        assertEquals(3L, field.findMockIntRange(arrayOf(Integer::class.java))?.from)
        assertEquals(
            3L,
            field.findMockIntRange(arrayOf(Foo::class.java, Integer::class.java))?.from
        )

        assertEquals(
            1L,
            field.findMockIntRange(
                arrayOf(Foo::class.java, Default::class.java, Integer::class.java)
            )?.from
        )

        //集合方式验证
        val field2 = Foo::class.java.getDeclaredField("aInt2")
        assertEquals(1L, field2.findMockIntRange(arrayOf())?.from)
        assertEquals(1L, field2.findMockIntRange(arrayOf(Default::class.java))?.from)
        assertEquals(2L, field2.findMockIntRange(arrayOf(NotationFetcherTest::class.java))?.from)

        assertEquals(null, field2.findMockIntRange(arrayOf(Foo::class.java))?.from)

        assertEquals(3L, field2.findMockIntRange(arrayOf(Integer::class.java))?.from)
        assertEquals(
            3L,
            field2.findMockIntRange(arrayOf(Foo::class.java, Integer::class.java))?.from
        )

        assertEquals(
            1L,
            field2.findMockIntRange(
                arrayOf(
                    Foo::class.java,
                    Default::class.java,
                    Integer::class.java
                )
            )?.from
        )

    }


    @Test
    fun testFieldAnnotationFetcherJava() {

        //java8方式验证
        val field = JavaFoo::class.java.getDeclaredField("aInt")
        assertEquals(1L, field.findMockIntRange(arrayOf())?.from)
        assertEquals(1L, field.findMockIntRange(arrayOf(Default::class.java))?.from)
        assertEquals(2L, field.findMockIntRange(arrayOf(NotationFetcherTest::class.java))?.from)

        assertEquals(null, field.findMockIntRange(arrayOf(Foo::class.java))?.from)

        assertEquals(3L, field.findMockIntRange(arrayOf(Integer::class.java))?.from)
        assertEquals(
            3L,
            field.findMockIntRange(arrayOf(Foo::class.java, Integer::class.java))?.from
        )

        assertEquals(
            1L,
            field.findMockIntRange(
                arrayOf(
                    Foo::class.java,
                    Default::class.java,
                    Integer::class.java
                )
            )?.from
        )

        //集合方式验证
        val field2 = JavaFoo::class.java.getDeclaredField("aInt2")
        assertEquals(1L, field2.findMockIntRange(arrayOf())?.from)
        assertEquals(1L, field2.findMockIntRange(arrayOf(Default::class.java))?.from)
        assertEquals(2L, field2.findMockIntRange(arrayOf(NotationFetcherTest::class.java))?.from)

        assertEquals(null, field2.findMockIntRange(arrayOf(Foo::class.java))?.from)

        assertEquals(3L, field2.findMockIntRange(arrayOf(Integer::class.java))?.from)
        assertEquals(
            3L,
            field2.findMockIntRange(arrayOf(Foo::class.java, Integer::class.java))?.from
        )

        assertEquals(
            1L,
            field2.findMockIntRange(
                arrayOf(
                    Foo::class.java,
                    Default::class.java,
                    Integer::class.java
                )
            )?.from
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
        assertEquals(11, field.findMockIntDefAboveNotation(arrayOf())?.value?.first())
        assertEquals(
            11,
            field.findMockIntDefAboveNotation(arrayOf(Default::class.java))?.value?.first()
        )
        assertEquals(
            12,
            field.findMockIntDefAboveNotation(arrayOf(NotationFetcherTest::class.java))?.value?.first()
        )

        assertEquals(null, field.findMockIntDefAboveNotation(arrayOf(Foo::class.java)))

        assertEquals(
            13L,
            field.findMockIntDefAboveNotation(arrayOf(Integer::class.java))?.value?.first()
        )
        assertEquals(
            13L,
            field.findMockIntDefAboveNotation(
                arrayOf(
                    Foo::class.java,
                    Integer::class.java
                )
            )?.value?.first()
        )

        assertEquals(
            11L,
            field.findMockIntDefAboveNotation(
                arrayOf(
                    Foo::class.java,
                    Default::class.java,
                    Integer::class.java
                )
            )?.value?.first()
        )

//        //集合方式验证
        val field2 = Bar::class.java.getDeclaredField("aInt2")
        assertEquals(21L, field2.findMockIntDefAboveNotation(arrayOf())?.value?.first())
        assertEquals(
            21L,
            field2.findMockIntDefAboveNotation(arrayOf(Default::class.java))?.value?.first()
        )
        assertEquals(
            22L,
            field2.findMockIntDefAboveNotation(arrayOf(NotationFetcherTest::class.java))?.value?.first()
        )

        assertEquals(
            null,
            field2.findMockIntDefAboveNotation(arrayOf(Foo::class.java))?.value?.first()
        )

        assertEquals(
            23L,
            field2.findMockIntDefAboveNotation(arrayOf(Integer::class.java))?.value?.first()
        )
        assertEquals(
            23L,
            field2.findMockIntDefAboveNotation(
                arrayOf(
                    Foo::class.java,
                    Integer::class.java
                )
            )?.value?.first()
        )

        assertEquals(
            21L,
            field2.findMockIntDefAboveNotation(
                arrayOf(
                    Foo::class.java,
                    Default::class.java,
                    Integer::class.java
                )
            )?.value?.first()
        )

    }
}