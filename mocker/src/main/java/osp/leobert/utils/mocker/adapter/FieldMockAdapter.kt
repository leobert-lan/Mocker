package osp.leobert.utils.mocker.adapter

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.adapter </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> FieldMockAdapter </p>
 * Created by leobert on 2020/11/18.
 */
@Deprecated(
    message = "cannot support different configurations",
    replaceWith = ReplaceWith("FieldMockAdapterV2")
)
interface FieldMockAdapter {
    @Deprecated(message = "cannot support different configurations")
    fun adapt(context: MockContext, field: Field)
}