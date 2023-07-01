package com.publicissapient.weatherapp.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class WeatherUtil {
    public static LocalDate getLocalDateFromEpoch(long epochTimestamp){
        return Instant.ofEpochSecond(epochTimestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static double getTemperatureInCelsius(double kelvin){
        return kelvin - 273.15;
    }
}
