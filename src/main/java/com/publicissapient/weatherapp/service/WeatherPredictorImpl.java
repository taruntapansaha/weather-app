package com.publicissapient.weatherapp.service;

import com.publicissapient.weatherapp.dto.DayWeather;
import com.publicissapient.weatherapp.dto.WeatherApiResponse;
import com.publicissapient.weatherapp.dto.WeatherMapData;
import com.publicissapient.weatherapp.external.client.WeatherClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.publicissapient.weatherapp.util.WeatherUtil.getLocalDateFromEpoch;
import static com.publicissapient.weatherapp.util.WeatherUtil.getTemperatureInCelsius;

@Service
public class WeatherPredictorImpl implements WeatherPredictor {

    private final WeatherClient weatherClient;
    private final WeatherAdvisor weatherAdvisor;

    public WeatherPredictorImpl(WeatherClient weatherClient, WeatherAdvisor weatherAdvisor) {
        this.weatherClient = weatherClient;
        this.weatherAdvisor = weatherAdvisor;
    }

    @Override
    public List<DayWeather> getWeatherRecommendation(String city, int daysCount) {
        WeatherApiResponse weatherForecast = weatherClient.getWeatherForecast(city, daysCount);

        Map<Long, DayWeather> dayWiseWeatherData = new HashMap<>();
        List<DayWeather> days = new LinkedList<>();

        Map<Long, List<String>> recommendations = weatherAdvisor.getRecommendations(weatherForecast.getList());

        for(WeatherMapData data : weatherForecast.getList()){
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
