package osp.leobert.utils.mocker.utils

import java.util.*


/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.utils </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> RandomUtils </p>
 * Created by leobert on 2020/11/18.
 */
object RandomUtils {
    private val RANDOM: Random = Random()

    fun nextBoolean(): Boolean {
        return RANDOM.nextBoolean()
    }

    fun nextByte(startInclusive: Byte, endExclusive: Byte): Byte {
        return (startInclusive + RANDOM.nextInt(endExclusive - startInclusive)).toByte()
    }

    fun nextInt(startInclusive: Int, endExclusive: Int): Int {
        return startInclusive + RANDOM.nextInt(endExclusive - startInclusive)
    }

    fun nextShort(startInclusive: Short, endExclusive: Short): Short {
        return (startInclusive + RANDOM.nextInt(endExclusive - startInclusive)).toShort()
    }

    fun nextLong(startInclusive: Long, endExclusive: Long): Long {
        return nextDouble(startInclusive.toDouble(), endExclusive.toDouble()).toLong()
    }

    fun nextFloat(startInclusive: Float, endInclusive: Float): Float {
        return startInclusive + (endInclusive - startInclusive) * RANDOM.nextFloat()
    }

    fun nextDouble(startInclusive: Double, endInclusive: Double): Double {
        return startInclusive + (endInclusive - startInclusive) * RANDOM.nextDouble()
    }

    fun nextSize(startInclusive: Int, endInclusive: Int): Int {
        return startInclusive + RANDOM.nextInt(endInclusive - startInclusive + 1)
    }

}