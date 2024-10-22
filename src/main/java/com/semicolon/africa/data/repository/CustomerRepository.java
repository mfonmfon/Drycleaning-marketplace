package com.semicolon.africa.data.repository;

import com.semicolon.africa.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByPassword(String password);

    Customer findCustomerByEmail(String email);

    Optional<Customer> findCustomerByPassword(String password);

    boolean existsByEmail(String email);

    Customer findCustomerById(Long id);

}
