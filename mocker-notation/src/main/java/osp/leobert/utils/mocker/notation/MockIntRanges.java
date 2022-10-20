package osp.leobert.utils.mocker.notation;

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

/**
 * Classname: MockIntRanges </p>
 * Description: repeat for {@link MockIntRange}  </p>
 * Created by leobert on 2022/10/20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE, ANNOTATION_TYPE})
@Documented
@Inherited
public @interface MockIntRanges {
    MockIntRange[] value();
}
