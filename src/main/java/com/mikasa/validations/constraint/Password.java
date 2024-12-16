package com.mikasa.validations.constraint;

import com.mikasa.validations.validator.UserPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = UserPasswordValidator.class)
public @interface Password {

  String message() default
      """
          Invalid password! The password should have 8 up to 32 characters at least one
          uppercase character, one lowercase character, one digit,
          one special symbol and no whitespaces!""";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
