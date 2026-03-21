package org.tommap.eazystorebe.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

  private int min;
  private int max;

  @Override
  public void initialize(StrongPassword constraintAnnotation) {
    this.min = constraintAnnotation.min();
    this.max = constraintAnnotation.max();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    if (value.length() < min || value.length() > max) {
      return false;
    }

    boolean hasUpper = false;
    boolean hasLower = false;
    boolean hasDigit = false;
    boolean hasSpecial = false;

    for (int i = 0; i < value.length(); ) {
      int cp = value.codePointAt(i);
      i += Character.charCount(cp);

      if (Character.isUpperCase(cp)) {
        hasUpper = true;
      }

      if (Character.isLowerCase(cp)) {
        hasLower = true;
      }

      if (Character.isDigit(cp)) {
        hasDigit = true;
      }

      if (!Character.isLetterOrDigit(cp) && !Character.isWhitespace(cp)) {
        hasSpecial = true;
      }

      if (hasUpper && hasLower && hasDigit && hasSpecial) {
        return true;
      }
    }

    return false;
  }
}
