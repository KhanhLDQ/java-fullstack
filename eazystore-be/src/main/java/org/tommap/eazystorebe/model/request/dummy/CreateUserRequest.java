package org.tommap.eazystorebe.model.request.dummy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter
@ToString
public class CreateUserRequest {
    private Long userId;
    private String name;
    private String email;
    private String mobileNumber;
}
