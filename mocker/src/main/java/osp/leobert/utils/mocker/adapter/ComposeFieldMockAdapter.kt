package osp.leobert.utils.mocker.adapter

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.adapter </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> ComposeFieldMockAdapter </p>
 * Created by leobert on 2020/11/19.
 */
@Deprecated(
    message = "cannot support different configurations",
    replaceWith = ReplaceWith(
        expression = "ComposeFieldMockAdapterV2",
        imports = ["osp.leobert.utils.mocker.adapter.ComposeFieldMockAdapterV2"]
    )
)
class ComposeFieldMockAdapter constructor(val leaf: MutableList<FieldMockAdapter>) :
    FieldMockAdapter {

    override fun adapt(context: MockContext, field: Field) {
        leaf.forEach { it.adapt(context, field) }
    }
}