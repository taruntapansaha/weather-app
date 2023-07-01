package com.publicissapient.weatherapp.service;

import com.publicissapient.weatherapp.dto.DayWeather;
import com.publicissapient.weatherapp.dto.Weather;
import com.publicissapient.weatherapp.dto.WeatherMapData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.publicissapient.weatherapp.util.WeatherUtil.getTemperatureInCelsius;

@Service
public class WeatherAdvisorImpl implements WeatherAdvisor{
    @Override
    public Map<Long, List<String>> getRecommendations(List<WeatherMapData> data){
        Map<Long, List<String>> recommendations = new HashMap<>();
        for(WeatherMapData dayWiseData: data){
            long day = dayWiseData.getDt();
            List<String> recommendation = new LinkedList<>();

            if(40 > getTemperatureInCelsius(dayWiseData.getMain().getTempMax())){
                recommendation.add("Use sunscreen lotion");
            }

            /*
                additional checks can be done on other data points
                recommendations can be added without affecting other classes
             */

            for(Weather weather : dayWiseData.getWeather()){
                if("rain".equalsIgnoreCase(weather.getMain())){
                    recommendation.add("Carry an Umbrella");
                }
            }
            recommendations.put(day, recommendation);
        }
        return recommendations;
    }

    public List<String> getRecommendations(Map<Long, DayWeather> dayWiseWeatherData, WeatherMapData data){

        List<String> recommendations = new LinkedList<>();
        for(Weather weather: data.getWeather()){
            if("rain".equalsIgnoreCase(weather.getMain())){
                recommendations.add("Carry an Umbrella");
            }
        }

        return null;
    }
}
