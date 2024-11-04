package com.semicolon.africa.data.repository;

import com.semicolon.africa.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomersById(Long id);

    List<Customer> findCustomersByFirstName(String firstName);

    boolean existsByEmail(String email);

   Optional<Customer> findCustomerByEmail(String email);

}
