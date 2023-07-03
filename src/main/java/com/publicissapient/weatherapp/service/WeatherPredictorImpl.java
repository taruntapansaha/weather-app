package com.publicissapient.weatherapp.service;

import com.publicissapient.weatherapp.dto.DayWeather;
import com.publicissapient.weatherapp.dto.WeatherApiResponse;
import com.publicissapient.weatherapp.dto.WeatherMapData;
import com.publicissapient.weatherapp.external.client.WeatherClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.publicissapient.weatherapp.util.WeatherUtil.getLocalDateFromEpoch;
import static com.publicissapient.weatherapp.util.WeatherUtil.getTemperatureInCelsius;

@Service
public class WeatherPredictorImpl implements WeatherPredictor {

    private final WeatherClient weatherClient;
    private final WeatherAdvisor weatherAdvisor;

    private final CacheService cacheService;

    public WeatherPredictorImpl(WeatherClient weatherClient, WeatherAdvisor weatherAdvisor, CacheService cacheService) {
        this.weatherClient = weatherClient;
        this.weatherAdvisor = weatherAdvisor;
        this.cacheService = cacheService;
    }

    @Override
    public List<DayWeather> getWeatherRecommendation(String city, int daysCount) throws Exception {
        WeatherApiResponse weatherForecast = null;
        try {
            weatherForecast = weatherClient.getWeatherForecast(city, daysCount);
        } catch (Exception exception) {
            throw exception;
        }

        if (weatherForecast == null) {
            List<DayWeather> dayWeathers = cacheService.get(city);
            if (dayWeathers == null || dayWeathers.isEmpty()) {
                throw new RuntimeException("Unable to serve the request at the moment");
            }
            return dayWeathers;
        }


        Map<Long, DayWeather> dayWiseWeatherData = new HashMap<>();
        Map<Long, List<String>> recommendations =
                weatherAdvisor.getRecommendations(weatherForecast.getList());

        for (WeatherMapData data : weatherForecast.getList()) {
            DayWeather dayWeather = recordTemperature(data);
            Long date = data.getDt();
            dayWeather.getRecommendations().addAll(recommendations.get(date));
            dayWiseWeatherData.put(date, dayWeather);
        }

        return new ArrayList<>(dayWiseWeatherData.values());
    }

    private DayWeather recordTemperature(WeatherMapData data) {
        DayWeather weather = new DayWeather();

        weather.setDay(getLocalDateFromEpoch(data.getDt()));
        weather.setMinTemperature(getTemperatureInCelsius(data.getMain().getTempMin()));
        weather.setMaxTemperature(getTemperatureInCelsius(data.getMain().getTempMax()));

        return weather;
    }


}
