package org.tommap.eazystorebe.exception;

import lombok.Getter;

import java.io.Serial;
import java.util.Map;

@Getter
public class CustomerAlreadyExistsException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 8268352400683195104L;

  private final Map<String, String> errors;

  public CustomerAlreadyExistsException(Map<String, String> errors) {
      super("Customer already exists!");
      this.errors = errors;
  }
}
