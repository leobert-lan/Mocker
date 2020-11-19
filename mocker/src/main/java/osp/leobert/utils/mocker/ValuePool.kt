package osp.leobert.utils.mocker

import osp.leobert.utils.mocker.utils.RandomUtils

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> ValuePool </p>
 * <p><b>Description:</b> a pool contains all the appropriate values, by range or enum </p>
 * Created by leobert on 2020/11/18.
 */
sealed class ValuePool<T>(private val comparator: Filter<T>) {
    interface Filter<T> {
        fun inRange(target: T, from: T?, to: T?): Boolean
    }

    companion object {
        const val TYPE_RANGE = 1
        const val TYPE_ENUM = 2
    }

    protected var type: Int = TYPE_RANGE

    protected var from: T? = null
    protected var to: T? = null

    protected val enumValues: MutableList<T> by lazy { ArrayList() }

    fun setRange(from: T?, to: T?) {
        when (type) {
            TYPE_ENUM -> {
                enumValues.removeAll { !comparator.inRange(it, from, to) }
                if (enumValues.isEmpty()) throw MockException("pool is empty")
            }
            else -> { //only the latest invocation make effect
                this.from = from
                this.to = to
            }
        }
    }

    fun setEnumValues(value: MutableList<T>) {
        when (type) {
            TYPE_ENUM -> { //only the latest invocation make effect
                enumValues.clear()
                enumValues.addAll(value)
                if (enumValues.isEmpty()) throw MockException("pool is empty")
            }
            else -> {
                enumValues.clear()
                enumValues.addAll(value)
                type = TYPE_ENUM
                setRange(from, to)
            }
        }
    }


    @Throws(MockException::class)
    abstract fun randomGet(context: MockContext): T

    class ByteValuePool : ValuePool<Byte>(comparator = object : Filter<Byte> {
        override fun inRange(target: Byte, from: Byte?, to: Byte?): Boolean {
            return (from?.run { target >= this } ?: true) && (to?.run { target <= to } ?: true)
        }
    }) {
        override fun randomGet(context: MockContext): Byte {
            return when (type) {
                TYPE_ENUM -> {
                    enumValues.getOrNull(RandomUtils.nextInt(0, enumValues.size))
                        ?: throw MockException("无法获取目标值")
                }
                else -> {
                    RandomUtils.nextByte(from ?: context.byteRange[0], to ?: context.byteRange[1])
                }
            }
        }
    }

    class IntValuePool : ValuePool<Int>(comparator = object : Filter<Int> {
        override fun inRange(target: Int, from: Int?, to: Int?): Boolean {
            return (from?.run { target >= this } ?: true) && (to?.run { target <= to } ?: true)
        }
    }) {
        override fun randomGet(context: MockContext): Int {
            return when (type) {
                TYPE_ENUM -> {
                    enumValues.getOrNull(RandomUtils.nextInt(0, enumValues.size))
                        ?: throw MockException("无法获取目标值")
                }
                else -> {
                    RandomUtils.nextInt(from ?: context.intRange[0], to ?: context.intRange[1])
                }
            }
        }
    }

    class LongValuePool : ValuePool<Long>(comparator = object : Filter<Long> {
        override fun inRange(target: Long, from: Long?, to: Long?): Boolean {
            return (from?.run { target >= this } ?: true) && (to?.run { target <= to } ?: true)
        }
    }) {
        override fun randomGet(context: MockContext): Long {
            return when (type) {
                TYPE_ENUM -> {
                    enumValues.getOrNull(RandomUtils.nextInt(0, enumValues.size))
                        ?: throw MockException("无法获取目标值")
                }
                else -> {
                    RandomUtils.nextLong(from ?: context.longRange[0], to ?: context.longRange[1])
                }
            }
        }
    }

    class ShortValuePool : ValuePool<Short>(comparator = object : Filter<Short> {
        override fun inRange(target: Short, from: Short?, to: Short?): Boolean {
            return (from?.run { target >= this } ?: true) && (to?.run { target <= to } ?: true)
        }
    }) {
        override fun randomGet(context: MockContext): Short {
            return when (type) {
                TYPE_ENUM -> {
                    enumValues.getOrNull(RandomUtils.nextInt(0, enumValues.size))
                        ?: throw MockException("无法获取目标值")
                }
                else -> {
                    RandomUtils.nextShort(
                        from ?: context.shortRange[0],
                        to ?: context.shortRange[1]
                    )
                }
            }
        }
    }

    class FloatValuePool : ValuePool<Float>(comparator = object : Filter<Float> {
        override fun inRange(target: Float, from: Float?, to: Float?): Boolean {
            return (from?.run { target >= this } ?: true) && (to?.run { target <= to } ?: true)
        }
    }) {
        override fun randomGet(context: MockContext): Float {
            return when (type) {
                TYPE_ENUM -> {
                    enumValues.getOrNull(RandomUtils.nextInt(0, enumValues.size))
                        ?: throw MockException("无法获取目标值")
                }
                else -> {
                    RandomUtils.nextFloat(
                        from ?: context.floatRange[0],
                        to ?: context.floatRange[1]
                    )
                }
            }
        }
    }

    class DoubleValuePool : ValuePool<Double>(comparator = object : Filter<Double> {
        override fun inRange(target: Double, from: Double?, to: Double?): Boolean {
            return (from?.run { target >= this } ?: true) && (to?.run { target <= to } ?: true)
        }
    }) {
        override fun randomGet(context: MockContext): Double {
            return when (type) {
                TYPE_ENUM -> {
                    enumValues.getOrNull(RandomUtils.nextInt(0, enumValues.size))
                        ?: throw MockException("无法获取目标值")
                }
                else -> {
                    RandomUtils.nextDouble(
                        from ?: context.doubleRange[0],
                        to ?: context.doubleRange[1]
                    )
                }
            }
        }
    }

    //todo 其他基本类型
}