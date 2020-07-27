package store.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = CategoriesConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoriesConstraint {
    String message() default "The input categories list consist of categories which aren't in the system";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

