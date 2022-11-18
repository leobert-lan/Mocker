# Mocker

为POJO生成假数据，面向kotlin和java

## 能解决什么问题？

很方便的生成假数据！！但是仅面向Java或者Kotlin.

## 为什么写这个库？

Mock是一个很长久的话题了，搞这个轮子的原因如下：

* 有趣、有意思且用得上
* 常见的mock都难以解决 "取值范围定制性" 问题
* 避免自己一直写业务写的脑子坏掉，适当的练练😀

## 最简单的使用：

您将从下文得到以下类型的使用示例：

* mock基本类型
* mock对象类
* mock泛型类
* mock列表等集合, List、Set、Map
* mock数组

### mock基本类型

```kotlin
Mocker.mock(Int::class.java)
Mocker.mock(Long::class.java)
Mocker.mock(Short::class.java)
Mocker.mock(Char::class.java)
Mocker.mock(Boolean::class.java)
Mocker.mock(Float::class.java)
Mocker.mock(Double::class.java)
Mocker.mock(String::class.java)
```

### mock一个对象

当然，您需要先定义一个类

```kotlin
class Example(val aInt: Int?, val aStr: String)
```

然后就是这么简单：

```kotlin
val entity: Example = Mocker.mock(Example::class.java)
```

也可以是 data class

```kotlin
data class DataExample(val aInt: Int?, val aStr: String)

val entity: DataExample = Mocker.mock(DataExample::class.java)
```

也可以Java类或者是这样：

```java
public class JavaExample {
    private String aStr;
    private Integer aInt;
}
```

```kotlin
val entity = Mocker.mock(JavaExample::class.java)
```

```kotlin
class PropertyExample {
    var aInt: Int? = null
    var aStr: String? = null
}

val entity = Mocker.mock(PropertyExample::class.java)
```

### mock泛型类

```kotlin
class A<T>(val t: T)
```

```kotlin
val entity = Mocker.mock(object : TypeToken<A<Example>>() {})
```

### mock列表等集合

List本质上也是泛型了，内部提供ArrayList实现

```kotlin
val entity = Mocker.mock(object : TypeToken<List<Int>>() {})
```

Set:

```kotlin
val entity = Mocker.mock(object : TypeToken<Set<A<Example>>>() {})
```

Map:

```kotlin
val entity = Mocker.mock(object : TypeToken<Map<DataExample, Example>>() {})
```

### mock Array

和List等类似：

```kotlin
val entity = Mocker.mock(object : TypeToken<Array<Int>>() {})
val entity2 = Mocker.mock(object : TypeToken<Array<Example>>() {})
```

**注意：尚未在内部支持Kotlin的IntArray、LongArray等**

## 进阶1 - 使用上下文（MockContext) 实现定制

### 约束取值范围

虽然这一需求可以被 MockContext 部分支持 ，**但这样做并不是最佳的做法** ，_将在下一节展示最佳做法_

```kotlin
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
val entity: Example = Mocker.mock(Example::class.java, context)
```

您会发现，Int范围已经生效，但String语料集并未生效


### 特殊类的构造器

### 还需要了解下文内容才能展开

## 一些有意思的内容

**相比于使用语料集等，面向注解进行了mock限定**，可以使得Mock结果更加符合预期

这个灵感从Android而来，Android中提出了一系列的注解（参考androidx-annotation）， 其中有一部分注解可以增强代码的可读性，并且已配合lint或者基于APT实现的JSR-380功能库。

而在Mocker中，我们反向操作一波，利用这些注解限定mock的边界。但是Android不太提倡运行时反射（更加提倡编译时生成代码，APT技术或者编译器层面技术） 这导致了androidx-annotation中的注解仅保留至Class，所以Mocker参考并添加了一系列注解：

* MockCharDef
* MockCharRange
* MockFalse
* MockTrue
* MockFloatRange
* MockIntDef
* MockIntRange
* MockSize
* MockStringDef

对于MockXXXDef，以MockStringDef为例：

```
@Retention(RUNTIME)
@Target({ANNOTATION_TYPE})
public @interface MockStringDef {
    /** Defines the allowed constants for this element */
    String[] value() default {};

    /**
     * Whether any other values are allowed. Normally this is
     * not the case, but this allows you to specify a set of
     * expected constants, which helps code completion in the IDE
     * and documentation generation and so on, but without
     * flagging compilation warnings if other values are specified.
     */
    boolean open() default false;
}
```

其用于注解一个注解，e.g.

```
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@MockStringDef(value = ["Leobert", "Tony"])
annotation class Name
```

这代表了一个"取值范围"，这样对被注解的field进行Mock限定。

而MockXXXRange， 以MockIntRange为例：

```
@Retention(RUNTIME)
@Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE})
public @interface MockIntRange {
    /**
     * Smallest value, inclusive
     */
    long from() default Long.MIN_VALUE;

    /**
     * Largest value, inclusive
     */
    long to() default Long.MAX_VALUE;
}
```

很显然，它表达了一个取值范围，可以用于Int和Long，MockFloatRange可以用于float和double。

MockSize可以限定集合或者数组的长度，但是对于多维情况，并没有那么的"自由"😂

MockTrue/MockFalse 直接指定Boolean的值。

## 使用介绍

### 普通类

```
class Foo(val name: String)

val foo: Foo = Mocker.mock(Foo::class.java)
println(Gson().toJson(foo))
```

因为没有使用注解限定，我们会使用默认语料

```
{"name":"一切都是瞬息，一切都将会过去；"}
```

又如：

```
val i:Int = Mocker.mock(Int::class.java)
println(i)
```

演示注解限定：

```
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@MockIntDef(value = [3, 4, 6])
annotation class Type

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@MockCharDef(value = ['M'])
annotation class CharTest

class Sample {
    @field:MockIntRange(from = -5, to = -1)
    var intRange: Int? = null

    @field:Type
    var intDef: Int? = null


    @field:MockIntRange(from = -5, to = -1)
    var longRange: Long? = null

    @field:Type
    var longDef: Long? = null

    @field:MockIntRange(from = 1, to = 5)
    var shortRange: Short? = null

    @field:Type
    var shortDef: Short? = null

    @field:MockIntRange(from = 1, to = 5)
    var byteRange: Byte? = null

    @field:Type
    var byteDef: Byte? = null

    @field:MockFloatRange(from = -1000f, to = 1000f)
    var floatRange: Float? = null

    @field:MockFloatRange(from = -1000f, to = 1000f)
    var doubleRange: Byte? = null

    @field:MockTrue
    var trueTest: Boolean? = null

    @field:MockFalse
    var falseTest: Boolean? = null

    @field:MockCharRange(from = 'a', to = 'z')
    var charRange: Char? = null

    @field:CharTest
    var charDef: Char? = null

    @field:Name
    var stringDef: String? = null

}

val bean: Sample = Mocker.mock(Sample::class.java)
println(Gson().toJson(bean))

```

### 泛型

通过TypeToken

```
class Foo(val name: String)
class Bar<T>(val t: T? = null)

val bar: Bar<Foo> = Mocker.mock(object : TypeToken<Bar<Foo>>() {})
println(Gson().toJson(bar))
```

如果是已经明确指定

```
class BarFoo(val bar: Bar<Foo>)

val bean: BarFoo = Mocker.mock(BarFoo::class.java)
println(Gson().toJson(bean))
```

这样是没有问题的.

但是这样，目前是没啥用的：

```
open class I
class A(val a: Int) : I()

class B(val b: Boolean) : I()

val bean: List<out I> = Mocker.mock(object : TypeToken<List<out I>>() {})
println(Gson().toJson(bean))

//目前对这种情况有点不成熟的小想法，还未实测
```

## 集合

以list为例

```
val bean: List<BarFoo> = Mocker.mock(object :TypeToken<List<BarFoo>>(){})
println(Gson().toJson(bean))
```

更多内容先行略去。 可以在单元测试中找到上述内容。

## 也可以指定Mock时的上下文

```
inline fun <reified T> mock(): T {
    return mock(MockContext())
}

inline fun <reified T> mock(context: MockContext): T {
    return mock(T::class.java, context)
}

fun <T> mock(clazz: Class<T>): T {
    return mock(clazz, MockContext())
}

fun <T> mock(clazz: Class<T>, context: MockContext): T {
    return BaseMockHandler<T>(clazz).mock(context)
}

fun <T> mock(typeToken: TypeToken<T>): T {
    return mock(typeToken,MockContext())
}

fun <T> mock(typeToken: TypeToken<T>, context: MockContext): T {
    return BaseMockHandler<T>(typeToken.type).mock(context.apply { this.parseParameterizedType(typeToken.type) })
}
```

我们可以指定对象依赖成环的处理策略，以及不使用注解限定时的默认限定。修改以下配置：

```
 /**
 * If true, will use the same beans that have been created ever when mock the same type.
 *
 * */
var skipSameType = false

///////////////////////////////////////////////////////////////////////////
// default configs
///////////////////////////////////////////////////////////////////////////
var byteRange = byteArrayOf(0, 127)
var shortRange = shortArrayOf(0, 1000)
var intRange = intArrayOf(0, 10000)
var floatRange = floatArrayOf(0.0f, 10000.00f)
var doubleRange = doubleArrayOf(0.0, 10000.00)
var longRange = longArrayOf(0L, 10000L)
var dateRange = arrayOf("1970-01-01", "2100-12-31")

//存在嵌套使用时的风险，需深度优先，创建完目标size后立即使用，再对item进行mock
var sizeRange = intArrayOf(2, 3)
```

当然，我们也可以扩展注解限定

注解限定的处理中，存在Adapter机制，可以影响Mock时的取值池

对于需要扩展的情况，如：

```
var intMockAdapter: FieldMockAdapter =
        ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))

var longMockAdapter: FieldMockAdapter =
    ComposeFieldMockAdapter(arrayListOf(LongRangeAdapter, LongDefAdapter))

var shortMockAdapter: FieldMockAdapter =
    ComposeFieldMockAdapter(arrayListOf(ShortRangeAdapter, ShortDefAdapter))

var byteMockAdapter: FieldMockAdapter =
    ComposeFieldMockAdapter(arrayListOf(ByteRangeAdapter, ByteDefAdapter))

var floatMockAdapter: FieldMockAdapter =
    ComposeFieldMockAdapter(arrayListOf(FloatRangeAdapter))

var doubleMockAdapter: FieldMockAdapter =
    ComposeFieldMockAdapter(arrayListOf(DoubleRangeAdapter))

var booleanMockAdapter: FieldMockAdapter =
    ComposeFieldMockAdapter(arrayListOf(BooleanAdapter))

var charMockAdapter: FieldMockAdapter =
    ComposeFieldMockAdapter(arrayListOf(CharRangeAdapter, CharDefAdapter))

var stringMockAdapter: FieldMockAdapter =
    ComposeFieldMockAdapter(arrayListOf(StringDefAdapter))

var enumMockAdapter: FieldMockAdapter =
    ComposeFieldMockAdapter(arrayListOf(IntRangeAdapter, IntDefAdapter))

var collectionMockAdapter: FieldMockAdapter =
    ComposeFieldMockAdapter(arrayListOf(SizeAdapter))
```

对应增加Adapter即可。

更多内容还请探究源码吧，*我实在是一个不太喜欢写这类文档的人*😂😂

喜欢的话，点个星？

