package osp.leobert.utils.mocker.notation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.notation </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> MockSize </p>
 * Created by leobert on 2020/11/28.
 */
@Retention(RUNTIME)
@Target({PARAMETER, LOCAL_VARIABLE, METHOD, FIELD, ANNOTATION_TYPE})
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
}