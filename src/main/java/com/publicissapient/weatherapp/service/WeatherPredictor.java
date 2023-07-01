package com.publicissapient.weatherapp.service;

import com.publicissapient.weatherapp.dto.DayWeather;

import java.util.List;

public interface WeatherPredictor {
    List<DayWeather> getWeatherRecommendation(String city, int daysCount);
}
