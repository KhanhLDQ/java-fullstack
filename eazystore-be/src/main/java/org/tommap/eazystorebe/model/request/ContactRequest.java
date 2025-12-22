package org.tommap.eazystorebe.model.request;

import lombok.Data;

@Data
public class ContactRequest {
    private String name;
    private String email;
    private String mobileNumber;
    private String message;
}
