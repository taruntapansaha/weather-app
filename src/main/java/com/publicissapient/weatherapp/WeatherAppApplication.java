package com.publicissapient.weatherapp;

import com.publicissapient.weatherapp.configuration.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class WeatherAppApplication {

    @Autowired
    private AppConfiguration config;

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.setConnectTimeout(Duration.ofMillis(config.getConnectionTimeOut()))
                .setReadTimeout(Duration.ofMillis(config.getReadTimeOut()))
                .build();
    }
}
