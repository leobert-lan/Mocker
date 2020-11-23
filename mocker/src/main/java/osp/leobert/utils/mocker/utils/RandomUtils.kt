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

    fun nextByte(startInclusive: Byte, endInclusive: Byte): Byte {
        return (startInclusive + RANDOM.nextInt(endInclusive - startInclusive)).toByte()
    }

    fun nextInt(startInclusive: Int, endInclusive: Int): Int {
        return startInclusive + RANDOM.nextInt(endInclusive - startInclusive+1)
    }

    fun nextShort(startInclusive: Short, endInclusive: Short): Short {
        return (startInclusive + RANDOM.nextInt(endInclusive - startInclusive)).toShort()
    }

    fun nextLong(startInclusive: Long, endInclusive: Long): Long {
        return nextDouble(startInclusive.toDouble(), endInclusive.toDouble()).toLong()
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