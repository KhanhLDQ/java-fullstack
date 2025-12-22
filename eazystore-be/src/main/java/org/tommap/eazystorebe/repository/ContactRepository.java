package org.tommap.eazystorebe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tommap.eazystorebe.model.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
