package osp.leobert.utils.mocker.notation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p><b>Package:</b> osp.leobert.utils.mocker.notation </p>
 * <p><b>Project:</b> Mocker </p>
 * <p><b>Classname:</b> MockTrue </p>
 * Created by leobert on 2020/11/20.
 */
@Retention(RUNTIME)
@Target({ANNOTATION_TYPE})
public @interface MockTrue {
}
