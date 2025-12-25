package org.tommap.eazystorebe.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactRequest {
    private String name;
    private String email;
    private String mobileNumber;
    private String message;
}
