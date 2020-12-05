package osp.leobert.utils.mocker.notation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

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
}
