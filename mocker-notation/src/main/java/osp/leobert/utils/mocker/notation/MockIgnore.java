package osp.leobert.utils.mocker.notation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import osp.leobert.utils.mocker.notation.group.Default;

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.notation </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> MockIgnore </p>
 * ignore the filed when mock
 * Created by leobert on 2020/12/5.
 */
@Retention(RUNTIME)
@Target({PARAMETER, FIELD, LOCAL_VARIABLE})
public @interface MockIgnore {
    /**
     *
     * if it's a default group or contains {@link Default}, always ignore
     *
     * otherwise, only ignore when all given groups within this
     *
     * @return the groups of the config, {} is the same effect to { Default.class}
     * @see osp.leobert.utils.mocker.notation.group.Default
     * @since 1.0.1
     */
    Class<?>[] groups() default {};
}
