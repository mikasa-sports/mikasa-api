package com.mikasa.validations.validator;

import com.mikasa.validations.constraint.Phone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (value == null) {
      return true;
    }

    Matcher matcher = Pattern.compile("^[+]\\d{10,14}$").matcher(value);

    return matcher.matches();
  }

  @Override
  public void initialize(Phone constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }
}
