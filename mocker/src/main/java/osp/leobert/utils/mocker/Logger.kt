package osp.leobert.utils.mocker

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> Logger </p>
 * Created by leobert on 2020/12/5.
 */
interface Logger {
    fun log(any: Any, thr: Throwable? = null)
}