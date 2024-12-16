package com.mikasa.validations.constraint;

import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "Can't be empty!")
@Pattern(regexp = "^[A-Za-z]*$", message = "Should contain only letters!")
@Size(min = 2, max = 20, message = "The length should be between 2 and 20 characters!")
public @interface Name {

  String message() default "Invalid name!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
