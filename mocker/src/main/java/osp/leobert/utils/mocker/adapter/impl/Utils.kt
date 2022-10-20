package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.notation.*
import osp.leobert.utils.mocker.notation.group.Default
import osp.leobert.utils.mocker.notation.repeat.*
import java.lang.reflect.Field
import java.util.*

/**
 * Classname: Utils </p>
 * Description: utils for fetch target annotation </p>
 * Created by leobert on 2022/10/20.
 */
internal object Utils {

    private val CLZ_DEFAULT = Default::class.java

    fun isDefaultGroup(group: Collection<Class<*>>): Boolean {
        return group.isEmpty() || (group.size == 1 && group.contains(CLZ_DEFAULT))
    }

    fun Field.findMockIntRange(vararg groups: Class<*>): MockIntRange? {
        val notations: List<MockIntRange> =
            if (this.isAnnotationPresent(MockIntRanges::class.java)) {
                getAnnotation(MockIntRanges::class.java).value.asList()
            } else if (this.isAnnotationPresent(MockIntRange::class.java)) {
                getAnnotation(MockIntRange::class.java).run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, *groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockIntDef(vararg groups: Class<*>): MockIntDef? {
        val notations: List<MockIntDef> =
            if (this.isAnnotationPresent(MockIntDefs::class.java)) {
                getAnnotation(MockIntDefs::class.java).value.asList()
            } else if (this.isAnnotationPresent(MockIntDef::class.java)) {
                getAnnotation(MockIntDef::class.java).run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, *groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockFloatRange(vararg groups: Class<*>): MockFloatRange? {
        val notations: List<MockFloatRange> =
            if (this.isAnnotationPresent(MockFloatRanges::class.java)) {
                getAnnotation(MockFloatRanges::class.java).value.asList()
            } else if (this.isAnnotationPresent(MockFloatRange::class.java)) {
                getAnnotation(MockFloatRange::class.java).run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, *groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockCharRange(vararg groups: Class<*>): MockCharRange? {
        val notations: List<MockCharRange> =
            if (this.isAnnotationPresent(MockCharRanges::class.java)) {
                getAnnotation(MockCharRanges::class.java).value.asList()
            } else if (this.isAnnotationPresent(MockCharRange::class.java)) {
                getAnnotation(MockCharRange::class.java).run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, *groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockTrue(vararg groups: Class<*>): MockTrue? {
        val notations: List<MockTrue> =
            if (this.isAnnotationPresent(MockTrues::class.java)) {
                getAnnotation(MockTrues::class.java).value.asList()
            } else if (this.isAnnotationPresent(MockTrue::class.java)) {
                getAnnotation(MockTrue::class.java).run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, *groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockFalse(vararg groups: Class<*>): MockFalse? {
        val notations: List<MockFalse> =
            if (this.isAnnotationPresent(MockFalses::class.java)) {
                getAnnotation(MockFalses::class.java).value.asList()
            } else if (this.isAnnotationPresent(MockFalse::class.java)) {
                getAnnotation(MockFalse::class.java).run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, *groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockSize(vararg groups: Class<*>): MockSize? {
        val notations: List<MockSize> =
            if (this.isAnnotationPresent(MockSizes::class.java)) {
                getAnnotation(MockSizes::class.java).value.asList()
            } else if (this.isAnnotationPresent(MockSize::class.java)) {
                getAnnotation(MockSize::class.java).run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, *groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    private fun findIndex(allGroups: List<List<Class<out Any>>>, vararg groups: Class<*>): Int {
        val distinctGroups = groups.toMutableSet()
        val isDefaultGroup = isDefaultGroup(distinctGroups)
        return allGroups.indexOfFirst { g ->
            if (isDefaultGroup) {
                g.isEmpty() || isDefaultGroup(g)
            } else {
                val firstMatchedConfig = distinctGroups.find {
                    if (it == Default::class.java) {
                        isDefaultGroup(g) || g.contains(it)
                    } else {
                        g.contains(it)
                    }
                }
                //todo log??

                firstMatchedConfig != null
            }
        }
    }
}