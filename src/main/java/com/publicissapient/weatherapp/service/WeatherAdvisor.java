package com.publicissapient.weatherapp.service;

import com.publicissapient.weatherapp.dto.WeatherMapData;

import java.util.List;
import java.util.Map;

public interface WeatherAdvisor {
    Map<Long, List<String>> getRecommendations(List<WeatherMapData> data);
}
