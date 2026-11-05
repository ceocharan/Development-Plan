package com.ssolution.cab.service.repository;

import com.ssolution.cab.service.entity.Cab;
import com.ssolution.dtos.enums.CommonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CabRepository extends JpaRepository<Cab, UUID> {
    boolean existsByRegistrationNumberAndStatus(String registrationNumber, CommonStatus commonStatus);
}
