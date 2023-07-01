package com.publicissapient.weatherapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class DayWeather {

    private LocalDate day;
    private double minTemperature;
    private double maxTemperature;
    private List<String> recommendations;
}
