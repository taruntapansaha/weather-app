package com.publicissapient.weatherapp.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

    @Value("${openWeather.api.endpoint}")
    private String openWeatherApiEndpoint;

    @Value("${openWeather.api.key}")
    private String openWeatherApiKey;

    @Value("${openWeather.api.connection.timeout}")
    private int readTimeOut;

    @Value("${openWeather.api.connection.timeout}")
    private int connectionTimeOut;
}

