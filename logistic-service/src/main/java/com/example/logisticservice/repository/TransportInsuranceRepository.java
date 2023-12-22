package com.example.logisticservice.repository;

import com.example.logisticservice.model.TransportInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportInsuranceRepository extends JpaRepository<TransportInsurance, Long> {
}
