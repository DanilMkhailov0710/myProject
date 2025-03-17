package org.travel.insurance.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.travel.insurance.core.domain.MedicalRiskLimitLevel;

import java.util.Optional;

public interface MedicalRiskLimitLevelRepository extends JpaRepository<MedicalRiskLimitLevel, Long> {

    Optional<MedicalRiskLimitLevel> findByMedicalRiskLimitLevelIc(String medicalRiskLimitLevelIc);

}
