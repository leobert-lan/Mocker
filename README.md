# Mocker
为POJO生成假数据，面向kotlin和java

## 能解决什么问题？
很方便的生成假数据！！但是仅面向Java或者Kotlin.

## 为什么写这个库？
Mock是一个很长久的话题了，单纯是因为想自己实现一些有意思的内容&避免自己一直写业务写的脑子坏掉了😀

## 一些有意思的内容

**相比于使用语料集等，面向注解进行了mock限定**，可以使得Mock结果更加符合预期

这个灵感从Android而来，Android中提出了一系列的注解（参考androidx-annotation），
其中有一部分注解可以增强代码的可读性，并且已配合lint或者基于APT实现的JSR-380功能库。

而在Mocker中，我们反向操作一波，利用这些注解限定mock的边界。但是Android不太提倡运行时反射（更加提倡编译时生成代码，APT技术或者编译器层面技术）
这导致了androidx-annotation中的注解仅保留至Class，所以Mocker参考并添加了一系列注解：

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

更多内容先行略去。
可以在单元测试中找到上述内容。

*我实在是一个不太喜欢写这类文档的人*😂😂



