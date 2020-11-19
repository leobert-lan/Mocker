package osp.leobert.utils.mocker.adapter

import osp.leobert.utils.mocker.MockContext
import java.lang.reflect.Field

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.adapter </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> ComposeFieldMockAdapter </p>
 * Created by leobert on 2020/11/19.
 */
class ComposeFieldMockAdapter(val leaf: MutableList<FieldMockAdapter>) : FieldMockAdapter {

    override fun adapt(context: MockContext, field: Field) {
        leaf.forEach { it.adapt(context, field) }
    }
}