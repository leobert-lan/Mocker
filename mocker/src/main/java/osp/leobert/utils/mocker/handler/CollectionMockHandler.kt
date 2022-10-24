package osp.leobert.utils.mocker.handler

import osp.leobert.utils.mocker.MockContext
import osp.leobert.utils.mocker.MockException
import osp.leobert.utils.mocker.utils.isA
import java.lang.reflect.Field
import java.lang.reflect.Type

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.handler </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> CollectionMockHandler </p>
 * Created by leobert on 2020/11/24.
 */
class CollectionMockHandler(
    private val clazz: Class<*>, private val genericTypes: Array<Type>
) : MockHandlerV2<Any?> {

    override fun mock(
        context: MockContext,
        field: Field?,
        owner: Any?,
        groups: Array<out Class<*>>
    ): Any? {
        return when {
            clazz.typeName == List::class.java.typeName ||
                    clazz.typeName == MutableList::class.java.typeName -> {
                mockArrayList(context, field, owner, groups)
            }
            List::class.java.isAssignableFrom(clazz) -> {
                mockList(context, field, owner, groups)
            }
            clazz.typeName == Set::class.java.typeName ||
                    clazz.typeName == MutableSet::class.java.typeName -> {
                mockHashSet(context, field, owner, groups)
            }
            Set::class.java.isAssignableFrom(clazz) -> {
                mockSet(context, field, owner, groups)
            }

            else -> throw MockException("not supported for ${clazz.typeName}")
        }
    }

    private fun mockArrayList(
        context: MockContext,
        field: Field?,
        owner: Any?,
        groups: Array<out Class<*>>
    ): ArrayList<Any?>? {

        return (FieldMockHandler.BeanFieldMockHandler(
            ArrayList::class.java,
            false
        ).mock(
            context = context, field = field, owner = owner, groups = groups
        ).isA<ArrayList<Any?>?>())?.apply {
            insertCollectionItem(context, field, this, groups)
        }
    }

    private fun mockList(
        context: MockContext,
        field: Field?,
        owner: Any?,
        groups: Array<out Class<*>>
    ): List<Any?>? {
        return (FieldMockHandler.BeanFieldMockHandler(clazz, false).mock(
            context = context, field = field, owner = owner, groups = groups
        ) as List<Any?>?)?.apply {
            if (this is MutableCollection<*>) {
                insertCollectionItem(context, field, this.isA(), groups)
            }
        }
    }

    private fun mockHashSet(
        context: MockContext,
        field: Field?,
        owner: Any?,
        groups: Array<out Class<*>>
    ): HashSet<Any?>? {

        return (FieldMockHandler.BeanFieldMockHandler(
            HashSet::class.java,
            false
        ).mock(
            context = context, field = field, owner = owner, groups = groups
        ).isA<HashSet<Any?>?>())?.apply {
            insertCollectionItem(context, field, this, groups)
        }
    }

    private fun mockSet(
        context: MockContext,
        field: Field?,
        owner: Any?,
        groups: Array<out Class<*>>
    ): Set<Any?>? {
        return (FieldMockHandler.BeanFieldMockHandler(clazz, false).mock(
            context = context, field = field, owner = owner, groups = groups
        ) as Set<Any?>?)?.apply {
            if (this is MutableCollection<*>) {
                insertCollectionItem(context, field, this.isA(), groups)
            }
        }
    }

    private fun insertCollectionItem(
        context: MockContext,
        field: Field?,
        collection: MutableCollection<Any?>,
        groups: Array<out Class<*>>
    ) {
        context.sizeValuePool.reset()
        field?.let {
            context.collectionMockAdapter.adapt(context, field, groups)
        }
        val size = context.sizeValuePool.randomGet(context)
        val itemMockHandler = BaseMockHandler<Any?>(genericTypes.first())
        for (i in 0 until size) {
            collection.add(
                itemMockHandler.mock(context = context, groups = groups)
            )
        }
    }
}