package com.fcctech.tournament.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static int getAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public static boolean inRange(int min, int max, LocalDate birthDate) {
        int age = getAge(birthDate);
        return age >= min && age <= max;
    }

    public static boolean isNotInRange(int min, int max, LocalDate birthDate) {
        return !inRange(min, max, birthDate);
    }

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
}
