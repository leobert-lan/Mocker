package osp.leobert.utils.mocker.notation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.notation </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> MockIntDef </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2020/11/19.
 */
@Retention(RUNTIME)
@Target({ANNOTATION_TYPE})
public @interface MockIntDef {
    /**
     * Defines the allowed constants for this element
     */
    int[] value() default {};

    /**
     * Defines whether the constants can be used as a flag, or just as an enum (the default)
     */
    boolean flag() default false;

    /**
     * Whether any other values are allowed. Normally this is
     * not the case, but this allows you to specify a set of
     * expected constants, which helps code completion in the IDE
     * and documentation generation and so on, but without
     * flagging compilation warnings if other values are specified.
     */
    boolean open() default false;
}