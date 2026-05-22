package com.example.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;
import jakarta.validation.ConstraintValidator;        
import jakarta.validation.ConstraintValidatorContext;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPriceValidator.class)
@Documented
public @interface ValidPrice {
    String message() default "価格は100〜1000000の範囲である必要があります";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

// バリデータ
class ValidPriceValidator implements ConstraintValidator<ValidPrice, Double> {
    private static final double MIN_PRICE = 100;
    private static final double MAX_PRICE = 1000000;

    @Override
    public void initialize(ValidPrice annotation) {
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value >= MIN_PRICE && value <= MAX_PRICE;
    }
}