package tagarde.core.utility.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })

@Retention(RetentionPolicy.RUNTIME)
public @interface CustomValidation {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean notNull() default false;

    boolean notBlank() default false;

    String regex() default "";

    int minLength() default 0;

    int maxLength() default 255;
}