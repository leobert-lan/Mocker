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

import osp.leobert.utils.mocker.notation.repeat.MockSizes;

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.notation </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> MockSize </p>
 * Created by leobert on 2020/11/28.
 */
@Retention(RUNTIME)
@Target({PARAMETER, LOCAL_VARIABLE, METHOD, FIELD, ANNOTATION_TYPE})
@Repeatable(MockSizes.class)
public @interface MockSize {

    /**
     * An exact size (or -1 if not specified)
     */
    int value() default -1;

    /**
     * A minimum size, inclusive
     */
    int min() default 1;

    /**
     * A maximum size, inclusive
     */
    int max() default 5;

//    /**
//     * The size must be a multiple of this factor
//     */
//    long multiple() default 1;

    /**
     * @return the groups of the config, {} is the same effect to { Default.class}
     * @see osp.leobert.utils.mocker.notation.group.Default
     * @since 1.0.1
     */
    Class<?>[] groups() default {};
}