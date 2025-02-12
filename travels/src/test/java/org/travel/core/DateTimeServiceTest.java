package org.travel.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeServiceTest {

    private final DateTimeService function = new DateTimeService();

    @Test
    public void checkByZero(){
        LocalDate from = LocalDate.of(2024, 2, 1);
        LocalDate to = LocalDate.of(2024, 2, 1);
        assertEquals(0, function.computeAmountOfDays(from, to));
    }

    @Test
    public void firstTest(){
        LocalDate from = LocalDate.of(2024, 2, 1);
        LocalDate to = LocalDate.of(2024, 2, 7);
        assertEquals(6, function.computeAmountOfDays(from, to));
    }

    @Test
    public void secondTest(){
        LocalDate from = LocalDate.of(2024, 2, 7);
        LocalDate to = LocalDate.of(2024, 2, 1);
        assertEquals(-6, function.computeAmountOfDays(from, to));
    }

}