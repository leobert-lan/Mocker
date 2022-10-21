package osp.leobert.utils.mocker

import osp.leobert.utils.mocker.handler.BaseMockHandler

object Mocker {

    inline fun <reified T> mock(vararg groups: Class<*>): T {
        return mock(context = MockContext(), groups = groups)
    }

    inline fun <reified T> mock(context: MockContext, vararg groups: Class<*>): T {
        return mock(clazz = T::class.java, context = context, groups = groups)
    }

    fun <T> mock(clazz: Class<T>, vararg groups: Class<*>): T {
        return mock(clazz = clazz, context = MockContext(), groups = groups)
    }

    fun <T> mock(clazz: Class<T>, context: MockContext, vararg groups: Class<*>): T {
        return BaseMockHandler<T>(clazz).mock(context = context, groups = groups)
    }

    fun <T> mock(typeToken: TypeToken<T>, vararg groups: Class<*>): T {
        return mock(typeToken = typeToken, context = MockContext(), groups = groups)
    }

    fun <T> mock(typeToken: TypeToken<T>, context: MockContext, vararg groups: Class<*>): T {
        return BaseMockHandler<T>(typeToken.type).mock(
            context = context.apply { this.parseParameterizedType(typeToken.type) },
            groups = groups
        )
    }
}