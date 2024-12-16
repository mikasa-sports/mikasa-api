package com.mikasa.validations.constraint;

import com.mikasa.validations.validator.PhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Phone {

  String message() default """
      Invalid phone number! Should contain one '+' character 
      in the start and then 10 up to 14 numbers!""";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
