package osp.leobert.utils.mocker.adapter

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.adapter </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> FieldMockAdapter </p>
 * Created by leobert on 2020/11/18.
 */
interface FieldMockAdapter {
    fun adapt(context: MockContext, field: Field)
}