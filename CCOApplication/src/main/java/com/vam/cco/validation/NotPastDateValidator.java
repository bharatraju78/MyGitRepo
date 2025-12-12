package com.vam.cco.validation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotPastDateValidator implements ConstraintValidator<NotPastDate, Date> {

    @Override
    public void initialize(NotPastDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (value == null) return true; // leave null checks to @NotNull if needed
        LocalDate asLocal = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return !asLocal.isBefore(LocalDate.now());
    }
}
