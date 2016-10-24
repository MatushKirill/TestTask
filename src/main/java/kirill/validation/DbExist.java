package kirill.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by kirill on 14/10/16.
 */
@Documented
@Constraint(validatedBy = DbExistValid.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DbExist {
    String message() default "{DbExist}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
