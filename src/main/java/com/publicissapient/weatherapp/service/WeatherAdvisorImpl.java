package com.publicissapient.weatherapp.service;

import com.publicissapient.weatherapp.dto.Weather;
import com.publicissapient.weatherapp.dto.WeatherMapData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.publicissapient.weatherapp.util.Constants.*;
import static com.publicissapient.weatherapp.util.WeatherUtil.getTemperatureInCelsius;

/**
 * Makes recommendation of several weather data points; the source data is from retrieve from openweathermap Api
 * additional checks can be done on other data points
 * and recommendations will be added without affecting other classes
 */
@Service
public class WeatherAdvisorImpl implements WeatherAdvisor {

    /**
     * @param data day-wise weather data retrieved from open openweathermap Api
     * @return Map of day-wise recommendations: date is the key, recommendations are value
     */

    @Override
    public Map<Long, List<String>> getRecommendations(List<WeatherMapData> data) {
        Map<Long, List<String>> recommendations = new HashMap<>();
        for (WeatherMapData dayWiseData : data) {
            long day = dayWiseData.getDt();
            List<String> recommendation = new LinkedList<>();

            if (THRESHOLD_TEMPERATURE > getTemperatureInCelsius(dayWiseData.getMain().getTempMax())) {
                recommendation.add(TEMPERATURE_RECOMMENDATION);
            }

            for (Weather weather : dayWiseData.getWeather()) {
                if (RAIN.equalsIgnoreCase(weather.getMain())) {
                    recommendation.add(RAIN_RECOMMENDATION);
                }
            }
            recommendations.put(day, recommendation);
        }
        return recommendations;
    }

}
