package org.travel.core;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
class DateTimeService {
    public long computeAmountOfDays(LocalDate from, LocalDate to){
        return ChronoUnit.DAYS.between(from, to);
    }
}
