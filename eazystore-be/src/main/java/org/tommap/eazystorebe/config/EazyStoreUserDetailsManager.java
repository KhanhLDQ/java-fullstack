package org.tommap.eazystorebe.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.tommap.eazystorebe.model.entity.Customer;
import org.tommap.eazystorebe.repository.CustomerRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EazyStoreUserDetailsManager implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));

        return new User(username, customer.getPasswordHash(), List.of());
    }
}
