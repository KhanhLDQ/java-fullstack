package org.tommap.eazystorebe.service;

import org.tommap.eazystorebe.model.request.RegisterRequest;

public interface ICustomerService {
  void register(RegisterRequest request);
}
