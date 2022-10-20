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

import osp.leobert.utils.mocker.notation.repeat.MockCharRanges;

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.notation </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> MockIntRange </p>
 * Created by leobert on 2020/11/19.
 */
@Retention(RUNTIME)
@Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE})
@Repeatable(MockCharRanges.class)
public @interface MockCharRange {
    /**
     * Smallest value, inclusive
     */
    char from() default Character.MIN_VALUE;

    /**
     * Largest value, inclusive
     */
    char to() default Character.MAX_VALUE;

    /**
     * @return the groups of the config, {} is the same effect to { Default.class}
     * @see osp.leobert.utils.mocker.notation.group.Default
     * @since 1.0.1
     */
    Class<?>[] groups() default {};
}
