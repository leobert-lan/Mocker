package osp.leobert.utils.mocker;

import com.google.gson.Gson
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import osp.leobert.utils.mocker.jcase.JavaExample

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



    @Test
    fun mockExample7() {
        println("### 进阶2-使用MockContext约束取值范围")
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

}
