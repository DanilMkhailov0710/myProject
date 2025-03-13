package org.travel.insurance.core.repositories;

import org.travel.insurance.core.domain.AgeCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface AgeCoefficientRepository extends JpaRepository<AgeCoefficient, Long> {

    @Query("SELECT c from AgeCoefficient c " +
            "where c.ageFrom <= :age " +
            "and c.ageTo >= :age")
    Optional<AgeCoefficient> findAgeCoefficient(
            @Param("age") Integer age
    );

}
