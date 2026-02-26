package org.tommap.eazystorebe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.stereotype.Service;
import org.tommap.eazystorebe.exception.CustomerAlreadyExistsException;
import org.tommap.eazystorebe.exception.PasswordCompromisedException;
import org.tommap.eazystorebe.mapper.CustomerMapper;
import org.tommap.eazystorebe.model.request.RegisterRequest;
import org.tommap.eazystorebe.repository.CustomerRepository;
import org.tommap.eazystorebe.service.ICustomerService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;
  private final CompromisedPasswordChecker compromisedPasswordChecker;

  @Override
  public void register(RegisterRequest request) {
      CompromisedPasswordDecision compromisedPasswordDecision = compromisedPasswordChecker.check(request.getPassword());
      if (compromisedPasswordDecision.isCompromised()) {
          throw new PasswordCompromisedException(
              Map.of("password", "Password is compromised, please choose a stronger one!")
          );
      }

      customerRepository
          .findByEmailOrMobileNumber(request.getEmail(), request.getMobileNumber())
          .ifPresent(existingCustomer -> {
              Map<String, String> errors = new HashMap<>();
              boolean emailExists = existingCustomer.getEmail().equalsIgnoreCase(request.getEmail());
              boolean mobileExists = existingCustomer.getMobileNumber().equalsIgnoreCase(request.getMobileNumber());

              if (emailExists) {
                  errors.put("email", String.format("Email %s already registered", request.getEmail()));
              }

              if (mobileExists) {
                  errors.put("mobileNumber", String.format("Mobile number %s already registered", request.getMobileNumber()));
              }

              throw new CustomerAlreadyExistsException(errors);
          });

      customerRepository.save(customerMapper.toCustomer(request));
  }
}
