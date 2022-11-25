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

## 进阶

### 进阶1 - 使用上下文（MockContext) 实现定制

#### 约束默认取值范围

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

您会发现，Int范围已经生效，但String语料集并未生效！！！

_在初版开发时，我为他们都设计了默认取值范围配置，经过反复思考，我对String移除了该功能，我期望您通过 `MockStringDef` 方式实现需求_

* context.intRange 实际上为 `设置默认取值范围`
* context.stringValuePool.setEnumValues 实际上为设置 `mock取值池` ，在进阶5进行自定义扩展开发时，您会用到它。

注意流程上： "确定取值范围" -> "设置到 mock 取值池" -> "mock"。

请注意："默认取值范围配置" 的特性在后续版本中可能会发生改变，以期使用更方便的方式进行使用。

#### 特殊类的构造器

#### 还需要了解下文内容才能展开

### 进阶2 - 使用注解限定Mock取值区间

灵感来自于Android的 `androidx-annotation`，需要注意的是，Mocker中使用了反射，而androidx的annotation库是Source级别，无法直接使用。

所以定义了以下注解：

* MockCharDef
* MockCharRange
* MockFalse
* MockTrue
* MockFloatRange
* MockIntDef
* MockIntRange
* MockSize
* MockStringDef

#### 参考示例

对于MockXXXDef，以MockStringDef为例：

定义一个 `@Retention(AnnotationRetention.RUNTIME)` 的注解 `Name`, 并用 `MockStringDef` 指定其全部可能取值：

```
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@MockStringDef(value = ["Leobert", "Tony"])
annotation class Name
```

而对应MockXXXRange，它是一个取值范围，以MockIntRange为例：

_截至1.0.1-alpha版本，MockXXXRange 仅被部分支持，当其直接注解于属性时有效，但注解于注解并使用新注解时尚未支持_

一个有效的设置如下：

```kotlin
class NotationDemo(
    @field:Name val name: String,
    @field:MockIntRange(from = 1, to = 200) val age: Int?
)

val entity: NotationDemo = Mocker.mock(NotationDemo::class.java)
```

MockSize可以限定集合或者数组的长度，但是对于多维情况，尚不能自由的指定每一层的size😂

MockTrue/MockFalse 直接指定Boolean的值。

### 进阶3 - 使用group区分mock策略

start from: `1.0.1-alpha`

* 日常开发中，POJO类在不同业务场景下进行复用的现象不可避免，但在不同的业务场景下，其属性值势必存在不同的限制；
* 从测试覆盖角度看，我们也需要获取不同方案的数据支撑用例

**灵感来自于 spring-boot中的jsr380实现**

从 1.0.1-alpha 开始，为进阶2中的注解追加了：

* Repeatable 支持
* Class<?>[] groups() default {}; 参数配置

对于未设置 `groups` 参数的情况，内建默认其等价于 `groups = [Default.class]`, _这样可以对原业务方实现无缝兼容_

一个有效的示例如下：

```kotlin
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@MockStringDef(value = ["Leobert", "Tony"], groups = [Group1::class, Default::class])
@MockStringDef(value = ["Leobert2"], groups = [Group2::class])
annotation class Name2
```

几点必要的说明：

* `@MockStringDef(value = ["Leobert2"], groups = [Group2::class])` 表示仅可匹配
  Group2，不可匹配 `Default::class.java`
* `groups = [Group1::class, Default::class]` 可匹配 `Group1` 或 `Default` 无顺序差别，但不可匹配 `Group2`
* 可以按照习惯使用 Repeat形式的注解，或者直接使用其组合注解，如 `MockStringDefs`

使用示例如下：

```kotlin
class NotationDemo2(
    @field:Name2 val name: String,
    @field:MockIntRange(from = 1, to = 200, groups = [Group3::class]) val age: Int?,
)

val entity = Mocker.mockWithGroup(
    NotationDemo2::class.java,
    Group1::class.java,
    Group3::class.java
)
```

几点必要的说明：

* API有所变化，可选用：
    * `inline fun <reified T> mockWithGroupInline(vararg groups: Class<*>): T`
    * `inline fun <reified T> mockWithGroupInline(context: MockContext, vararg groups: Class<*>): T`
    * `fun mockWithGroup(clazz: Class<T>, vararg groups: Class<*>): T`
    * `fun <T> mockWithGroup(clazz: Class<T>, context: MockContext, vararg groups: Class<*>): T`
    * `fun <T> mockWithGroup(typeToken: TypeToken<T>, vararg groups: Class<*>): T`
    * `fun <T> mockWithGroup(typeToken: TypeToken<T>, context: MockContext, vararg groups: Class<*>): T`
* Mock时指定分组的groups参数：对于任意字段，按照该groups顺序，当任意限定注解的groups被匹配时，则选用该规则

举一些例子：

> 一个冗余规则示例：

```kotlin
@MockStringDef(value = ["Leobert", "Tony"], groups = [A::class, B::class, Default::class]) // 规则1
@MockStringDef(value = ["Leobert2"], groups = [C::class, B::class]) //规则2
annotation class Name2

@field:Name2
val name: String
```

规则1和规则2中均包含分组情况： `B::class` , 当尝试匹配分组 `B::class.java` 时，即：

```kotlin
Mocker.mockWithGroup(NotationDemo2::class.java, B::class.java)
```

规则1先于规则2被匹配，所以规则2中的 `B::class` 冗余

正确示例如下：

```kotlin
@MockStringDef(value = ["Leobert", "Tony"], groups = [A::class, B::class, Default::class]) // 规则1
@MockStringDef(value = ["Leobert2"], groups = [C::class]) //规则2
annotation class Name2

@field:Name2
val name: String
```

> mock groups 参数含义示例

有些情况下，未必能依靠一个Group界定所需的POJO规则限定。 _注意，我们仍然需要努力对分组进行合理规划_

```kotlin
@MockStringDef(value = ["Leobert", "Tony"], groups = [A::class, B::class, Default::class]) // 规则1
@MockStringDef(value = ["Leobert2"], groups = [C::class]) //规则2
annotation class Name2

@MockStringDef(value = ["aaa@qq.com", "bbb@163.com"], groups = [D::class]) //规则1
annotation class Email

data class NotationDemo2(@field:Name2 val name: String, @field:Email val email: String)
```

很显然，可能在多个业务下都会使用到Email，给Email配置大量的 `groups参数值` 是反人类的，例如：

```kotlin
//反人类的反例
@MockStringDef(
    value = ["aaa@qq.com", "bbb@163.com"],
    groups = [A::class, B::class, C::class, D::class, E::class,/*...*/]
)
annotation class Email
```

可以按如下方式mock：

```kotlin
Mocker.mockWithGroup(NotationDemo2::class.java, D::class.java, B::class.java)
```

对于 `name: String` 很显然 `D::class.java` 无法命中，按序尝试 `B::class.java` 命中 规则1 对于 `email: String`
, `D::class.java` 命中，使用规则1

### 进阶4 - 使用 MockIgnore 屏蔽属性

很显然，在某些情况下，我们并不希望 Mock 干预到部分属性，注意：并不是说我们期望它们一定为 `null` ， 例如在构造器中对某些属性进行了赋值操作，又需要在 Mock 中避免被修改

```kotlin
class MockIgnoreDemo(
    @field:MockIgnore val key: String,
    @field:MockIgnore(groups = [Group3::class]) @field:Name2 val name: String
)
```

例如：

* 在所有情况下均不需要干扰到属性 `key` 时，可以将 group 设为Default
* 而 `name` 属性，在Group3 情况下，则不会干涉

### 进阶5 - 自定义注解&扩展处理器

#### 了解Adapter

在 Mocker 的设计中，对于类的 Field 的处理，均存在 Adapter 机制，您可以自由的扩展他们：

```kotlin
class MockContext {
    var intMockAdapter: FieldMockAdapterV2 =
        ComposeFieldMockAdapterV2(arrayListOf(IntRangeAdapterV2, IntDefAdapterV2))

    var longMockAdapter: FieldMockAdapterV2 =
        ComposeFieldMockAdapterV2(arrayListOf(LongRangeAdapterV2, LongDefAdapterV2))

    var shortMockAdapter: FieldMockAdapterV2 =
        ComposeFieldMockAdapterV2(arrayListOf(ShortRangeAdapterV2, ShortDefAdapterV2))

    var byteMockAdapter: FieldMockAdapterV2 =
        ComposeFieldMockAdapterV2(arrayListOf(ByteRangeAdapterV2, ByteDefAdapterV2))

    var floatMockAdapter: FieldMockAdapterV2 = FloatRangeAdapterV2

    var doubleMockAdapter: FieldMockAdapterV2 = DoubleRangeAdapterV2

    var booleanMockAdapter: FieldMockAdapterV2 = BooleanAdapterV2

    var charMockAdapter: FieldMockAdapterV2 =
        ComposeFieldMockAdapterV2(arrayListOf(CharRangeAdapterV2, CharDefAdapterV2))

    var stringMockAdapter: FieldMockAdapterV2 = StringDefAdapterV2

    var enumMockAdapter: FieldMockAdapterV2 =
        ComposeFieldMockAdapterV2(arrayListOf(IntRangeAdapterV2, IntDefAdapterV2))

    var collectionMockAdapter: FieldMockAdapterV2 = SizeAdapterV2
}
```

以StringDefAdapterV2为例：

```kotlin
object StringDefAdapterV2 : FieldMockAdapterV2 {
    override fun adapt(context: MockContext, field: Field, groups: Array<out Class<*>>) {
        field.findMockStringDefAboveNotation(groups)?.value
            ?.toMutableList()
            ?.let { values ->
                context.stringValuePool.setEnumValues(values)
            }
    }
}
```

通过反射得到内建注解约束 -> 按照 Mock 时传入的 group 策略获得可用配置 -> 设置mock取值池

后续的随机取值、赋值等流程不需要侵入。

#### 了解mock取值池

参见 `ValuePool` 和 `LimitValuePool` 

主要API：

* `fun setRange(from: T?, to: T?)` 设置范围值
* `fun setEnumValues(value: MutableList<T>)` 设置有限的值

#### 对特定类指定处理器

通过向：`MockContext#fieldMockStrategy : MutableMap<Class<*>, MockHandlerV2<*>>` 注册类和处理器的方式，可以指定Mock处理器。

例如和时间相关的类特别繁杂，内建中没有处理

您可以对 LocalDateTime 定制一套Mock处理逻辑并进行注册。






