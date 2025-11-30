package org.tommap.eazystorebe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tommap.eazystorebe.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
