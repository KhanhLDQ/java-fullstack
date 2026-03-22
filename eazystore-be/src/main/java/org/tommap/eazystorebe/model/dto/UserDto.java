package org.tommap.eazystorebe.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@NoArgsConstructor
public class UserDto {
  private Long userId;
  private String name;
  private String email;
  private String mobileNumber;
}
