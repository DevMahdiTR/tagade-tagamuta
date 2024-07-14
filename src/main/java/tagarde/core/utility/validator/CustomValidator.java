package tagarde.core.utility.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class CustomValidator implements ConstraintValidator<CustomValidation, String>  {

    private String message;
    private boolean notNull;
    private boolean notBlank;
    private String regex;
    private int minLength;
    private int maxLength;



    @Override
    public void initialize(CustomValidation constraintAnnotation) {
        message = constraintAnnotation.message();
        notNull = constraintAnnotation.notNull();
        notBlank = constraintAnnotation.notBlank();
        regex = constraintAnnotation.regex();
        minLength = constraintAnnotation.minLength();
        maxLength = constraintAnnotation.maxLength();

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        StringBuilder compositeMessage = new StringBuilder();

        if (notNull && value == null) {
            compositeMessage.append("The value cannot be null. ");
        }

        if (notBlank && (value == null || value.trim().isEmpty())) {
            compositeMessage.append("The value cannot be blank or empty. ");
        }

        if (value != null && value.length() > maxLength) {
            compositeMessage.append("The value must be no more than ").append(maxLength).append(" characters long. ");
        }

        if (value != null && value.length() < minLength) {
            compositeMessage.append("The value must be at least ").append(minLength).append(" characters long. ");
        }

        if (!regex.isEmpty() && value != null && !value.matches(regex)) {
            compositeMessage.append("The value does not match the required pattern. ");
        }


        if (compositeMessage.length() > 0) {
            compositeMessage.insert(0, message + " ");
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(compositeMessage.toString()).addConstraintViolation();
            return false;
        }

        return true;
    }
}