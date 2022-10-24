package osp.leobert.utils.mocker.utils

import osp.leobert.utils.mocker.MockException

/**
 * Classname: Exts </p>
 * Description: some extensions in project </p>
 * Created by leobert on 2022/10/24.
 */
inline fun <reified R> Any?.takeIfInstance():R? {
    if (this is R) return this
    return null
}

inline fun <reified R> Any?.isA():R {
    if (this is R) return this
    throw MockException("$this should be ${R::class.java}, but was ${this?.javaClass}")
}