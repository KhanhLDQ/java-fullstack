package org.tommap.eazystorebe.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(
    name = "customers",
    uniqueConstraints = {
        @UniqueConstraint(name = "unq_email", columnNames = "email"),
        @UniqueConstraint(name = "unq_mobile_number", columnNames = "mobile_number")
    }
)
public class Customer extends BaseAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, length = 100)
  private String email;

  @Column(nullable = false, length = 10)
  private String mobileNumber;

  @Column(nullable = false, length = 500)
  private String passwordHash;
}
