package osp.leobert.utils.mocker.notation.repeat;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import osp.leobert.utils.mocker.notation.MockFloatRange;

/**
 * Classname: MockFloatRanges </p>
 * Description: repeat for {@link MockFloatRange} </p>
 * Created by leobert on 2022/10/20.
 *
 * @since 1.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE})
@Documented
@Inherited
public @interface MockFloatRanges {
    MockFloatRange[] value();
}
