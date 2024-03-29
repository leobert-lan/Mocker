package osp.leobert.utils.mocker

import osp.leobert.utils.mocker.handler.BaseMockHandler
import osp.leobert.utils.mocker.notation.group.Default
import java.lang.reflect.Type

object Mocker {

    val DEFAULT_GROUP: Array<Class<*>> = arrayOf(Default::class.java)


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
        return mockWithGroup(clazz, context, Default::class.java)
    }

    fun <T> mock(typeToken: TypeToken<T>): T {
        return mock(typeToken, MockContext())
    }

    fun <T> mock(typeToken: TypeToken<T>, context: MockContext): T {
        return mockWithGroup(typeToken, context, Default::class.java)
    }

    fun <T> mock(type: Type): T {
        return mockWithGroup(type, MockContext(), Default::class.java)
    }

    fun <T> mock(type: Type, context: MockContext): T {
        return mockWithGroup(type, context, Default::class.java)
    }

    inline fun <reified T> mockWithGroupInline(vararg groups: Class<*>): T {
        return mockWithGroupInline(context = MockContext(), groups = groups)
    }

    inline fun <reified T> mockWithGroupInline(context: MockContext, vararg groups: Class<*>): T {
        return mockWithGroup(clazz = T::class.java, context = context, groups = groups)
    }

    fun <T> mockWithGroup(clazz: Class<T>, vararg groups: Class<*>): T {
        return mockWithGroup(clazz = clazz, context = MockContext(), groups = groups)
    }

    fun <T> mockWithGroup(clazz: Class<T>, context: MockContext, vararg groups: Class<*>): T {
        return BaseMockHandler<T>(clazz).mock(context = context, groups = groups)
    }

    fun <T> mockWithGroup(typeToken: TypeToken<T>, vararg groups: Class<*>): T {
        return mockWithGroup(typeToken = typeToken, context = MockContext(), groups = groups)
    }

    fun <T> mockWithGroup(
        typeToken: TypeToken<T>,
        context: MockContext,
        vararg groups: Class<*>
    ): T {
        return BaseMockHandler<T>(typeToken.type).mock(
            context = context.apply { this.parseParameterizedType(typeToken.type) },
            groups = groups
        )
    }

    fun <T> mockWithGroup(
        type: Type,
        context: MockContext,
        vararg groups: Class<*>
    ): T {
        return BaseMockHandler<T>(type).mock(
            context = context.apply { this.parseParameterizedType(type) },
            groups = groups
        )
    }
}