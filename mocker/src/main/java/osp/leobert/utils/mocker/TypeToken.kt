package osp.leobert.utils.mocker

import osp.leobert.utils.mocker.constructor.TypeUtils
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> TypeToken </p>
 * Created by leobert on 2020/11/23.
 */
open class TypeToken<T> {

    companion object {
        /**
         * Gets type literal for the given `Type` instance.
         */
        operator fun get(type: Type): TypeToken<*> {
            return TypeToken<Any>(type)
        }

        /**
         * Gets type literal for the given `Class` instance.
         */
        operator fun <T> get(type: Class<T>): TypeToken<T> {
            return TypeToken(type)
        }

        /**
         * Gets type literal for the parameterized type represented by applying `typeArguments` to
         * `rawType`.
         */
        fun getParameterized(rawType: Type?, vararg typeArguments: Type?): TypeToken<*>? {
            return TypeToken<Any>(
                TypeUtils.newParameterizedTypeWithOwner(
                    null,
                    rawType,
                    *typeArguments
                )
            )
        }

        /**
         * Gets type literal for the array type whose elements are all instances of `componentType`.
         */
        fun getArray(componentType: Type?): TypeToken<*>? {
            return TypeToken<Any>(TypeUtils.arrayOf(componentType))
        }
    }

    val type: Type
    val rawType: Class<in T>

    protected constructor() {
        val superClass: Type = javaClass.genericSuperclass
        if (superClass is Class<*>) {
            throw MockException("不支持的类型")
        }
        type = (superClass as ParameterizedType).actualTypeArguments[0]
        rawType = TypeUtils.getRawType(type) as Class<in T>
    }

    protected constructor(type: Type) {
        this.type = type
        rawType = TypeUtils.getRawType(type) as Class<in T>
    }

}