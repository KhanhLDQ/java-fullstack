package org.tommap.eazystorebe.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactRequest {
    @NotBlank(message = "name cannot be empty!")
    @Size(min = 5, max = 30, message = "name must be between 5 and 30 characters!")
    private String name;

    @NotBlank(message = "email cannot be empty!")
    @Email(message = "invalid email format!")
    private String email;

    @NotBlank(message = "mobile number cannot be empty!")
    @Pattern(regexp = "^\\d{10}$", message = "mobile number must be 10 digits!")
    private String mobileNumber;

    @NotBlank(message = "message cannot be empty!")
    @Size(min = 5, max = 500, message = "message must be between 5 and 500 characters!")
    private String message;
}
