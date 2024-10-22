package com.semicolon.africa.data.repository;

import com.semicolon.africa.data.model.DryCleaner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DryCleanerRepository extends JpaRepository<DryCleaner, Long> {
    DryCleaner findDryCleanerByFullName(String fullName);

}
