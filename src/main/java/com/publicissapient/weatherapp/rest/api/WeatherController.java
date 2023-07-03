package com.publicissapient.weatherapp.rest.api;

import com.publicissapient.weatherapp.dto.DayWeather;
import com.publicissapient.weatherapp.service.WeatherPredictor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

import static com.publicissapient.weatherapp.util.Constants.DEFAULT_DAY_COUNT;

@RestController(value = "v1")
@Validated
public class WeatherController {

    private final WeatherPredictor weatherPredictor;

    public WeatherController(WeatherPredictor weatherPredictor) {
        this.weatherPredictor = weatherPredictor;
    }

    @GetMapping(value = "weather/forecast/city")
    public ResponseEntity<List<DayWeather>> getWeatherForecast(
            @RequestParam @NotBlank String city,
            @RequestParam(required = false) Optional<Integer> count) {

        List<DayWeather> dayWeatherList = weatherPredictor.getWeatherRecommendation(city,
                count.orElse(DEFAULT_DAY_COUNT));
        return ResponseEntity.ok().body(dayWeatherList);
    }

}
