package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.notation.group.Default
import java.lang.reflect.Field
import java.lang.reflect.Type

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> MapMockHandler </p>
 * Created by leobert on 2020/11/29.
 */
class MapMockHandler(
    private val clazz: Class<*>, private val genericTypes: Array<Type>
) : MockHandlerV2<Map<Any?, Any?>?> {
    override fun mock(context: MockContext, field: Field?, owner: Any?): Map<Any?, Any?>? {
        return mock(context, field, owner, Default::class.java)
    }

    override fun mock(
        context: MockContext,
        field: Field?,
        owner: Any?,
        vararg groups: Class<*>
    ): Map<Any?, Any?>? {
        return when {
            Map::class.java == clazz || MutableMap::class.java == clazz -> {
                mockHashMap(context, field, owner, *groups)
            }
            else -> {
                mockMap(context, field, owner, *groups)
            }
        }.apply {
            context.applyField(this, field, owner)
        }
    }

    private fun mockHashMap(
        context: MockContext,
        field: Field?,
        owner: Any?,
        vararg groups: Class<*>
    ): HashMap<Any?, Any?>? {
        return (FieldMockHandler.BeanFieldMockHandler(HashMap::class.java, false).mock(
            context, field, owner
        ) as HashMap<Any?, Any?>?)
            ?.apply {
                insertMapItem(context, field, this, *groups)
            }
    }

    private fun mockMap(
        context: MockContext,
        field: Field?,
        owner: Any?,
        vararg groups: Class<*>
    ): Map<Any?, Any?>? {
        return (FieldMockHandler.BeanFieldMockHandler(clazz, false).mock(
            context, field, owner
        ) as Map<Any?, Any?>?)?.apply {
            if (this is MutableMap<*, *>) {
                insertMapItem(context, field, this as MutableMap<Any?, Any?>, *groups)
            }
        }
    }

    private fun insertMapItem(
        context: MockContext,
        field: Field?,
        map: MutableMap<Any?, Any?>,
        vararg groups: Class<*>

    ) {
        context.sizeValuePool.reset()
        field?.let {
            context.collectionMockAdapter.adapt(context, field, *groups)
        }
        val size = context.sizeValuePool.randomGet(context)
        val keyMockHandler = BaseMockHandler<Any?>(genericTypes.first())
        val valueMockHandler = BaseMockHandler<Any?>(genericTypes[1])
        for (i in 0 until size) {
            map[keyMockHandler.mock(context)] = valueMockHandler.mock(context)
        }
    }
}