package com.publicissapient.weatherapp.external.client;

import com.publicissapient.weatherapp.dto.WeatherApiResponse;

public interface WeatherClient {
    WeatherApiResponse getWeatherForecast(String city, int count);
}
