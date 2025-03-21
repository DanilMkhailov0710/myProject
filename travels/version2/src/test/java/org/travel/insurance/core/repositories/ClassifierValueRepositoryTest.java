package org.travel.insurance.core.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.travel.insurance.core.domain.ClassifierValue;

import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClassifierValueRepositoryTest {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(classifierValueRepository);
    }

    @ParameterizedTest
    @MethodSource("riskTypeValues")
    public void FindRiskByIcForRiskType(String ic){
        checkByCorrectly("RISK_TYPE", ic);
    }

    @Test
    public void shouldNotFind_RiskType_FAKE() {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", "FAKE");
        assertTrue(valueOpt.isEmpty());
    }

    private static List<String> riskTypeValues() {
        return List.of(
                "TRAVEL_MEDICAL",
                "TRAVEL_CANCELLATION",
                "TRAVEL_LOSS_BAGGAGE",
                "TRAVEL_THIRD_PARTY_LIABILITY",
                "TRAVEL_EVACUATION",
                "TRAVEL_SPORT_ACTIVITIES"
        );
    }

    private void checkByCorrectly(String classifierTitle, String ic) {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                classifierTitle, ic);
        assertTrue(valueOpt.isPresent());
        String actualIc = ic;
        assertEquals(valueOpt.get().getIc(), actualIc);
        String actualClassifierTitle = classifierTitle;
        assertEquals(valueOpt.get().getClassifierId().getTitle(), actualClassifierTitle);
    }

}