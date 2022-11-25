package osp.leobert.utils.mocker;

import com.google.gson.Gson
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import osp.leobert.utils.mocker.constructor.InstanceCreator
import osp.leobert.utils.mocker.jcase.JavaExample
import osp.leobert.utils.mocker.notation.MockIgnore
import osp.leobert.utils.mocker.notation.MockIntRange
import osp.leobert.utils.mocker.notation.MockStringDef
import osp.leobert.utils.mocker.notation.group.Default
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Classname: ReadmeExampleTest </p>
 * Description: TODO </p>
 * Created by leobert on 2022/11/18.
 */
@RunWith(JUnit4::class)
@Suppress("unused")
internal class ReadmeExampleTest {

    class Example(val aInt: Int?, val aStr: String)


    data class DataExample(val aInt: Int?, val aStr: String)

    class PropertyExample {
        var aInt: Int? = null
        var aStr: String? = null
    }

    @Test
    fun mockExample1() {
        println("### mock基础数据类型")
        repeat(10) {

            println(Mocker.mock(Int::class.java))
            println(Mocker.mock(Long::class.java))
            println(Mocker.mock(Short::class.java))
            println(Mocker.mock(Char::class.java))
            println(Mocker.mock(Boolean::class.java))
            println(Mocker.mock(Float::class.java))
            println(Mocker.mock(Double::class.java))
            println(Mocker.mock(String::class.java))

        }
    }

    @Test
    fun mockExample2() {
        println("### mock简单对象类")
        repeat(10) {

            println("simple")
            val entity: Example = Mocker.mock(Example::class.java)
            println(Gson().toJson(entity))

            println("data class")
            val entity2: DataExample = Mocker.mock(DataExample::class.java)
            println(Gson().toJson(entity2))

            println("simple2")
            val entity3 = Mocker.mock(PropertyExample::class.java)
            println(Gson().toJson(entity3))

            println("java")
            val entity4 = Mocker.mock(JavaExample::class.java)
            println(Gson().toJson(entity4))

        }
    }

    class A<T>(val t: T)

    @Test
    fun mockExample3() {
        println("### mock泛型类")
        repeat(10) {
            val entity = Mocker.mock(object : TypeToken<A<Example>>() {})
            println(Gson().toJson(entity))
        }
    }

    @Test
    fun mockExample4() {
        println("### mock List、Set、Map")
        repeat(10) {

            val entity = Mocker.mock(object : TypeToken<List<Int>>() {})
            println(Gson().toJson(entity))

            val entity2 = Mocker.mock(object : TypeToken<Set<A<Example>>>() {})
            println(Gson().toJson(entity2))

            val entity3 = Mocker.mock(object : TypeToken<Map<DataExample, Example>>() {})
            println(Gson().toJson(entity3))
        }
    }

    @Test
    fun mockExample5() {
        println("### mock Array")
        repeat(10) {

            val entity = Mocker.mock(object : TypeToken<Array<Int>>() {})
            println(Gson().toJson(entity))

            val entity2 = Mocker.mock(object : TypeToken<Array<Example>>() {})
            println(Gson().toJson(entity2))

        }
    }

    @Test
    fun mockExample6() {
        println("### 进阶1-使用MockContext约束取值范围")
        val context = MockContext()
        context.intRange = intArrayOf(-5, 5)
        context.stringValuePool.setEnumValues(
            arrayListOf(
                "道可道，非常道；名可名，非常名。",
                "无名，天地之始，有名，万物之母。",
                "故常无欲，以观其妙，常有欲，以观其徼。",
                "此两者，同出而异名，同谓之玄，玄之又玄，众妙之门。"
            )
        )
        repeat(10) {
            val entity: Example = Mocker.mock(Example::class.java, context)
            println(Gson().toJson(entity))

        }
    }

    class Cons1(t: LocalDateTime) {
        var str: String? = null

        @field:MockIgnore
        val time: String?

        init {
            time = t.format(DateTimeFormatter.BASIC_ISO_DATE)
        }
    }

    class Cons2(t: LocalDateTime) {

        var str: String? = null

        init {
            println(t.format(DateTimeFormatter.ISO_DATE_TIME))
            throw RuntimeException("amazing!")
        }
    }

    @Test
    fun mockExample7() {
        println("进阶1 - 特殊构造器")
        val context = MockContext()
        context.constructorMap[Cons1::class.java] = InstanceCreator { Cons1(LocalDateTime.now()) }
        repeat(10) {
            val entity = Mocker.mock(Cons1::class.java, context)
            println(Gson().toJson(entity))

            val entity2 = Mocker.mock(Cons2::class.java)
            println(Gson().toJson(entity2))

        }
    }

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    @MockStringDef(value = ["Leobert", "Tony"])
    annotation class Name

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    @MockIntRange(from = 1, to = 200)
    annotation class Age

    class NotationDemo(
        @field:Name val name: String,
        @field:MockIntRange(from = 1, to = 200) val age: Int?,
        @field:Age val age2: Int?, //尚未支持
    )

    @Test
    fun mockExample8() {
        println("进阶2 - 使用注解限定Mock取值区间")
        repeat(10) {
            val entity: NotationDemo = Mocker.mock(NotationDemo::class.java)
            println(Gson().toJson(entity))

        }
    }


    interface Group1
    interface Group2
    interface Group3

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    @MockStringDef(value = ["Leobert", "Tony"], groups = [Group1::class, Default::class])
    @MockStringDef(value = ["Leobert2"], groups = [Group2::class])
    annotation class Name2

    class NotationDemo2(
        @field:Name2 val name: String,
        @field:MockIntRange(from = 1, to = 200, groups = [Group3::class]) val age: Int?,
    )

    @Test
    fun mockExample9() {
        println("### 进阶3-使用groups支持不同的策略")
        repeat(10) {
            val entity = Mocker.mockWithGroup(
                NotationDemo2::class.java,
                Group1::class.java,
                Group3::class.java
            )
            println(Gson().toJson(entity))
        }
    }

    class MockIgnoreDemo(
        @field:MockIgnore val key: String,
        @field:MockIgnore(groups = [Group3::class]) @field:Name2 val name: String
    )

    @Test
    fun mockExample10() {
        println("### 进阶3-使用MockIgnore忽略")
        repeat(10) {
            val entity = Mocker.mock(MockIgnoreDemo::class.java)
            println(Gson().toJson(entity))

            val entity2 = Mocker.mockWithGroup(MockIgnoreDemo::class.java, Group2::class.java)
            println(Gson().toJson(entity2))

            val entity3 = Mocker.mockWithGroup(MockIgnoreDemo::class.java, Group3::class.java)
            println(Gson().toJson(entity3))
        }
    }

}
