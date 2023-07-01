package com.publicissapient.weatherapp.external.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicissapient.weatherapp.configuration.AppConfiguration;
import com.publicissapient.weatherapp.dto.WeatherApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpenWeatherMapClientTest {


    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AppConfiguration config;

    @InjectMocks
    private OpenWeatherMapClient openWeatherMapClient;

    private ObjectMapper objectMapper;
    private WeatherApiResponse weatherApiResponse;

    @BeforeEach
    void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        ResourceUtils.getFile("classpath:bombaySuccessResponse.json");
        weatherApiResponse = objectMapper
                .readValue(ResourceUtils.getFile("classpath:bombaySuccessResponse.json"), WeatherApiResponse.class);

        lenient().when(config.getOpenWeatherApiEndpoint())
                .thenReturn("https://api.openweathermap.org/data/2.5/forecast?q={city}&appid={appId}&cnt={count}");
        lenient().when(config.getOpenWeatherApiKey())
                .thenReturn("d2929e9483efc82c82c32ee7e02d563e");
    }

    @Test
    void getWeatherForecast() {
        when(restTemplate
                .getForEntity("https://api.openweathermap.org/data/2.5/forecast?q=bombay&appid=d2929e9483efc82c82c32ee7e02d563e&cnt=3",
                        WeatherApiResponse.class))
                .thenReturn(ResponseEntity.ok(weatherApiResponse));

        WeatherApiResponse bombay = openWeatherMapClient.getWeatherForecast("bombay", 3);
        assertEquals(bombay.getList().size(), 3);
    }

    @Test
    void buildURL() {
    }
}