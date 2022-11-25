package osp.leobert.utils.mocker.adapter.impl

import osp.leobert.utils.mocker.Logger
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

    private fun isDefaultGroup(group: Collection<Class<*>>): Boolean {
        return group.isEmpty() || (group.size == 1 && group.contains(CLZ_DEFAULT))
    }

    fun Field.findMockIntRange(groups: Array<out Class<*>>): MockIntRange? {
        val notations: List<MockIntRange> =
            if (this.isAnnotationPresent(MockIntRanges::class.java)) {
                getAnnotation(MockIntRanges::class.java).value.asList()
            } else if (this.isAnnotationPresent(MockIntRange::class.java)) {
                getAnnotation(MockIntRange::class.java).run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockIntDefAboveNotation(groups: Array<out Class<*>>): MockIntDef? {
        val notations: List<MockIntDef> = annotations?.flatMap { n ->
            if (n.annotationClass.java.isAnnotationPresent(MockIntDefs::class.java)) {
                n.annotationClass.java.getAnnotation(MockIntDefs::class.java).value.asList()
            } else if (n.annotationClass.java.isAnnotationPresent(MockIntDef::class.java)) {
                n.annotationClass.java.getAnnotation(MockIntDef::class.java)
                    .run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }
        } ?: Collections.emptyList()

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockFloatRange(groups: Array<out Class<*>>): MockFloatRange? {
        val notations: List<MockFloatRange> =
            if (this.isAnnotationPresent(MockFloatRanges::class.java)) {
                getAnnotation(MockFloatRanges::class.java).value.asList()
            } else if (this.isAnnotationPresent(MockFloatRange::class.java)) {
                getAnnotation(MockFloatRange::class.java).run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockCharRange(groups: Array<out Class<*>>): MockCharRange? {
        val notations: List<MockCharRange> =
            if (this.isAnnotationPresent(MockCharRanges::class.java)) {
                getAnnotation(MockCharRanges::class.java).value.asList()
            } else if (this.isAnnotationPresent(MockCharRange::class.java)) {
                getAnnotation(MockCharRange::class.java).run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockTrue(groups: Array<out Class<*>>): MockTrue? {
        val notations: List<MockTrue> =
            if (this.isAnnotationPresent(MockTrues::class.java)) {
                getAnnotation(MockTrues::class.java).value.asList()
            } else if (this.isAnnotationPresent(MockTrue::class.java)) {
                getAnnotation(MockTrue::class.java).run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockFalse(groups: Array<out Class<*>>): MockFalse? {
        val notations: List<MockFalse> =
            if (this.isAnnotationPresent(MockFalses::class.java)) {
                getAnnotation(MockFalses::class.java).value.asList()
            } else if (this.isAnnotationPresent(MockFalse::class.java)) {
                getAnnotation(MockFalse::class.java).run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockSize(groups: Array<out Class<*>>): MockSize? {
        val notations: List<MockSize> =
            if (this.isAnnotationPresent(MockSizes::class.java)) {
                getAnnotation(MockSizes::class.java).value.asList()
            } else if (this.isAnnotationPresent(MockSize::class.java)) {
                getAnnotation(MockSize::class.java).run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockCharDefAboveNotation(groups: Array<out Class<*>>): MockCharDef? {
        val notations: List<MockCharDef> = annotations?.flatMap { n ->
            if (n.annotationClass.java.isAnnotationPresent(MockCharDefs::class.java)) {
                n.annotationClass.java.getAnnotation(MockCharDefs::class.java).value.asList()
            } else if (n.annotationClass.java.isAnnotationPresent(MockCharDef::class.java)) {
                n.annotationClass.java.getAnnotation(MockCharDef::class.java)
                    .run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }
        } ?: Collections.emptyList()

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    fun Field.findMockStringDefAboveNotation(groups: Array<out Class<*>>): MockStringDef? {
        val notations: List<MockStringDef> = annotations?.flatMap { n ->
            if (n.annotationClass.java.isAnnotationPresent(MockStringDefs::class.java)) {
                n.annotationClass.java.getAnnotation(MockStringDefs::class.java).value.asList()
            } else if (n.annotationClass.java.isAnnotationPresent(MockStringDef::class.java)) {
                n.annotationClass.java.getAnnotation(MockStringDef::class.java)
                    .run { Collections.singletonList(this) }
            } else {
                Collections.emptyList()
            }
        } ?: Collections.emptyList()

        val index = findIndex(notations.map { n -> n.groups.map { it.java }.distinct() }, groups)
        return if (index >= 0) {
            notations[index]
        } else {
            null
        }
    }

    private fun findIndex(allGroups: List<List<Class<out Any>>>, groups: Array<out Class<*>>): Int {
        val distinctGroups = groups.toMutableSet()
        val isDefaultGroup = isDefaultGroup(distinctGroups)
        return allGroups.indexOfFirst { g ->
            if (isDefaultGroup) {
                //declined group in notation: {} || {Default} || {others,Default}
                g.isEmpty() || isDefaultGroup(g) || g.contains(Default::class.java)
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

    fun Field.shouldIgnoreMock(groups: Array<out Class<*>>, logger: Logger): Boolean {

        val mockIgnore = getAnnotation(MockIgnore::class.java) ?: return false
        val groupsConfig = mockIgnore.groups.map { it.java }.toMutableSet()
        if (isDefaultGroup(groupsConfig) || groupsConfig.contains(Default::class.java)) {
            logger.log("ignore ${this.declaringClass.name} ${this.toGenericString()} will ignore mock,")
            return true
        }
        val cannotIgnore = groups.find { !groupsConfig.contains(it) } != null
        if (cannotIgnore) {
            return false
        }
        logger.log("ignore ${this.declaringClass.name} ${this.toGenericString()} will ignore mock,")
        return true
    }
}