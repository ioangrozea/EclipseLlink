package edu.msg.ro.persistence.user.entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<CustomEmailValidator, String> {
    @Override
    public void initialize(CustomEmailValidator CustomEmailValidator) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email.contains("@");
    }
}
