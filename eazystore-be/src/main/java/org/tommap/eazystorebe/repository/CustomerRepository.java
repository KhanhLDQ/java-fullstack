package org.tommap.eazystorebe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tommap.eazystorebe.model.entity.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmailOrMobileNumber(String email, String mobileNumber);
    Optional<Customer> findByEmail(String email);
}
