package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.MockException
import java.lang.reflect.Field
import java.lang.reflect.Type

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> CollectionMockHandler </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2020/11/24.
 */
class CollectionMockHandler(
    private val clazz: Class<*>, val genericTypes: Array<Type>
) : MockHandler<Any?> {
    override fun mock(context: MockContext, field: Field?, owner: Any?): Any? {
        return when {
            clazz.typeName == List::class.java.typeName ||
                    clazz.typeName == MutableList::class.java.typeName -> {
                mockArrayList(context, field, owner)
            }
            List::class.java.isAssignableFrom(clazz) -> {
                mockList(context, field, owner)
            }

            else -> throw MockException("not supported for ${clazz.typeName}")
        }

    }

    private fun mockArrayList(context: MockContext, field: Field?, owner: Any?): ArrayList<Any?>? {

        return (FieldMockHandler.BeanFieldMockHandler(
            ArrayList::class.java,
            false
        ).mock(
            context, field, owner
        ) as ArrayList<Any?>?)?.apply {
            insertCollectionItem(context, field, this)
        }
    }

    private fun mockList(context: MockContext, field: Field?, owner: Any?): List<Any?>? {
        return (FieldMockHandler.BeanFieldMockHandler(clazz, false).mock(
            context, field, owner
        ) as List<Any?>?)?.apply {
            if (this is MutableCollection<*>) {
                insertCollectionItem(context, field, this as MutableCollection<Any?>)
            }
        }
    }

    private fun insertCollectionItem(
        context: MockContext,
        field: Field?,
        collection: MutableCollection<Any?>
    ) {
        context.sizeValuePool.reset()
        field?.let {
            context.collectionMockAdapter.adapt(context, field)
        }
        val size = context.sizeValuePool.randomGet(context)
        val itemMockHandler = BaseMockHandler<Any?>(genericTypes.first())
        for (i in 0 until size) {
            collection.add(
                itemMockHandler.mock(context)
            )
        }
    }
}