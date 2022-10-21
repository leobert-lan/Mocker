package osp.leobert.utils.mocker.notation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

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
     * @return the groups of the config, {} is the same effect to { Default.class}
     * @see osp.leobert.utils.mocker.notation.group.Default
     * @since 1.0.1
     */
    // TODO: 2022/10/20 思考下如何最恰当的ignore
    Class<?>[] groups() default {};
}
