package osp.leobert.utils.mocker.utils

import java.lang.reflect.Method

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.utils </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> UnsafeUtils </p>
 * Created by leobert on 2020/11/19.
 */
object UnsafeUtils {

    private val unsafe: Any = try {
        Class.forName("sun.misc.Unsafe")
    } catch (e: ClassNotFoundException) {
        throw  UnsupportedOperationException("can't find sun.misc.Unsafe. " + e.message, e)
    }.run {
        try {
            this.getDeclaredField("theUnsafe")
        } catch (e: NoSuchFieldException) {
            throw  UnsupportedOperationException(
                "can't find the field theUnsafe in sun.misc.Unsafe." + e.message,
                e
            )
        }.apply {
            this.isAccessible = true
        }
    }.run {
        try {
            this.get(null)
        } catch (e: IllegalAccessException) {
            throw UnsupportedOperationException("get Unsafe instance failed: " + e.message, e)
        }
    }

    private val allocateInstance: Method = try {
        Class.forName("sun.misc.Unsafe")
    } catch (e: ClassNotFoundException) {
        throw  UnsupportedOperationException("can't find sun.misc.Unsafe. " + e.message, e)
    }.run {
        try {
            this.getMethod("allocateInstance", Class::class.java)
        } catch (e: NoSuchMethodException) {
            throw  UnsupportedOperationException(
                "can't find the method allocateInstance in sun.misc.Unsafe : " + e.message,
                e
            )
        }
    }


    fun <T> newInstance(clazz: Class<T>): T {
        return try {
            clazz.cast(allocateInstance.invoke(unsafe, clazz))
        } catch (e: Exception) {
            throw UnsupportedOperationException(
                "create instance for " + clazz + " failed. " + e.message,
                e
            )
        }
    }
}