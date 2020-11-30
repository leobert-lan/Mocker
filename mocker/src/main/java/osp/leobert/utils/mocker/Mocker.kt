package osp.leobert.utils.mocker

import osp.leobert.utils.mocker.handler.BaseMockHandler

object Mocker {

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
}