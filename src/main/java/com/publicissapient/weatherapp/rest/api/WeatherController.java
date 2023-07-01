package com.publicissapient.weatherapp.rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "v1")
public class WeatherController {

    @GetMapping(value = "weather/forecast/city")
    public void getWeatherForecast(@RequestParam String city, @RequestParam int count){

    }

}
