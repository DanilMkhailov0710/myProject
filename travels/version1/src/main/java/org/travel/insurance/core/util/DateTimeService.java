package org.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeService {
    public long computeAmountOfDays(LocalDate from, LocalDate to){
        return ChronoUnit.DAYS.between(from, to);
    }
}
