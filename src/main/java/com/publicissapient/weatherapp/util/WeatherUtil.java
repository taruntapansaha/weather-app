package com.publicissapient.weatherapp.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static com.publicissapient.weatherapp.util.Constants.CELSIUS_CONVERTER_FACTOR;

public class WeatherUtil {

    public static LocalDate getLocalDateFromEpoch(long epochTimestamp) {
        return Instant.ofEpochSecond(epochTimestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static double getTemperatureInCelsius(double kelvin) {
        return kelvin - CELSIUS_CONVERTER_FACTOR;
    }
}
