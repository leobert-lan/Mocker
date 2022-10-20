package osp.leobert.utils.mocker.notation.repeat;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import osp.leobert.utils.mocker.notation.MockIntDef;

/**
 * Classname: MockIntDefs </p>
 * Description: repeat for {@link MockIntDef} </p>
 * Created by leobert on 2022/10/20.
 *
 * @since 1.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ANNOTATION_TYPE})
@Documented
@Inherited
public @interface MockIntDefs {
    MockIntDef[] value();
}
