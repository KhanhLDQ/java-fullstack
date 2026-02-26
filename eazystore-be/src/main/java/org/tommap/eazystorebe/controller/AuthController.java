package org.tommap.eazystorebe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommap.eazystorebe.model.dto.UserDto;
import org.tommap.eazystorebe.model.request.LoginRequest;
import org.tommap.eazystorebe.model.request.RegisterRequest;
import org.tommap.eazystorebe.model.response.LoginResponse;
import org.tommap.eazystorebe.service.ICustomerService;
import org.tommap.eazystorebe.util.JwtUtils;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final ICustomerService customerService;

//  private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
//  private final PasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
      );

      String jwt = jwtUtils.generateToken(authentication);
      var userDto = new UserDto();
      userDto.setName(authentication.getName());

      return ResponseEntity.ok(new LoginResponse("Successful authentication", userDto, jwt));
    } catch (BadCredentialsException ex) {
      return buildErrorResponse(UNAUTHORIZED, "Invalid username or password");
    } catch (AuthenticationException ex) {
      return buildErrorResponse(UNAUTHORIZED, "Authentication failed");
    } catch (Exception ex) {
      return buildErrorResponse(INTERNAL_SERVER_ERROR, "An unexpected error occurred");
    }
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
//    var user = User.builder()
//        .username(request.getEmail())
//        .password(passwordEncoder.encode(request.getPassword()))
//        .roles("USER")
//        .build();
//    inMemoryUserDetailsManager.createUser(user);

    customerService.register(request);

    return ResponseEntity
        .status(CREATED)
        .body("Registration successful");
  }

  private ResponseEntity<LoginResponse> buildErrorResponse(HttpStatus httpStatus, String message) {
    return ResponseEntity
        .status(httpStatus)
        .body(
            new LoginResponse(message, null, null)
        );
  }
}
