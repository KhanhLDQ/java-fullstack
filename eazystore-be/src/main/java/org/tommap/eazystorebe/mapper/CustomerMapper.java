package org.tommap.eazystorebe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tommap.eazystorebe.model.entity.Customer;
import org.tommap.eazystorebe.model.request.RegisterRequest;

import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR)
public abstract class CustomerMapper {
  @Autowired
  protected PasswordEncoder passwordEncoder;

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "lastModifiedAt", ignore = true)
  @Mapping(target = "lastModifiedBy", ignore = true)
  @Mapping(target = "passwordHash", expression = "java(passwordEncoder.encode(request.getPassword()))")
  public abstract Customer toCustomer(RegisterRequest request);
}
