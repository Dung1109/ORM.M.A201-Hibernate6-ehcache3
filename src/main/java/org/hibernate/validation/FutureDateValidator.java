package org.hibernate.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class FutureDateValidator implements ConstraintValidator<FutureDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate movieDate, ConstraintValidatorContext context) {
        if (movieDate == null) {
            return true;
        }

        return LocalDate.now().compareTo(movieDate) <= 0;
    }
}
