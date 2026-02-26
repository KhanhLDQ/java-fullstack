package org.tommap.eazystorebe.exception;

import lombok.Getter;

import java.io.Serial;
import java.util.Map;

@Getter
public class PasswordCompromisedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 6715218591561151532L;

    private final Map<String, String> errors;

    public PasswordCompromisedException(Map<String, String> errors) {
        super("Password compromised!");
        this.errors = errors;
    }
}
