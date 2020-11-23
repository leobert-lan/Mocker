package osp.leobert.utils.mocker

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> TypeToken </p>
 * Created by leobert on 2020/11/23.
 */
abstract class TypeToken<T> {
    val type: Type

    init {
        val superClass: Type = javaClass.genericSuperclass
        if (superClass is Class<*>) {
            throw MockException("不支持的类型")
        }
        type = (superClass as ParameterizedType).getActualTypeArguments().get(0)
    }
}