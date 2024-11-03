package com.semicolon.africa.data.repository;

import com.semicolon.africa.data.model.DryCleaner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DryCleanerRepository extends JpaRepository<DryCleaner, Long> {
    boolean existsByEmail(String email);
    Optional<DryCleaner> findDryCleanerByEmail(String email);
    Optional <DryCleaner> findDryCleanerById(Long dryCleanerId);

    List<DryCleaner> findAllBy();

    List<DryCleaner> findDryCleanerByFirstName(String firstName);

    List<DryCleaner> findDryCleanersByLastName(String lastName);

    List<DryCleaner> findDryCleanersByFirstNameAndLastName(String firstName, String lastName);

    List<DryCleaner> findDryCleanerByCompanyName(String companyName);

    List<DryCleaner> findDryCleanerByPhoneNumber(String phoneNumber);

}
