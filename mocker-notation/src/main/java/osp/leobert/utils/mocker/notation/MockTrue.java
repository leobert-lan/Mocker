package osp.leobert.utils.mocker.notation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import osp.leobert.utils.mocker.notation.repeat.MockTrues;

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.notation </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> MockTrue </p>
 * Created by leobert on 2020/11/20.
 */
@Retention(RUNTIME)
@Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE})
@Repeatable(MockTrues.class)
public @interface MockTrue {

    /**
     * @return the groups of the config, {} is the same effect to { Default.class}
     * @see osp.leobert.utils.mocker.notation.group.Default
     * @since 1.0.1
     */
    Class<?>[] groups() default {};
}
