package org.tommap.eazystorebe.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tommap.eazystorebe.model.dto.UserDto;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
  private String message;
  private UserDto user;
  private String jwt;
}
