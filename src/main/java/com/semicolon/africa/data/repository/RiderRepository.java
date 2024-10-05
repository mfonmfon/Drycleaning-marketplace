package com.semicolon.africa.data.repository;

import com.semicolon.africa.data.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Long> {
}
