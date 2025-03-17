package org.travel.insurance.core.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AgeCoefficientRepositoryTest {
    @Autowired
    private AgeCoefficientRepository repository;

    @Test
    public void correctlyValue(){
        var coefficient = repository.findAgeCoefficient(10);
        assertTrue(coefficient.isPresent());
    }

    @Test
    public void incorrectlyValue(){
        var coefficient = repository.findAgeCoefficient(1000);
        assertTrue(coefficient.isEmpty());
    }

}