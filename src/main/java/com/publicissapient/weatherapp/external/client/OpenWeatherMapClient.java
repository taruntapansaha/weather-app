package com.publicissapient.weatherapp.external.client;

import com.publicissapient.weatherapp.configuration.AppConfiguration;
import com.publicissapient.weatherapp.dto.WeatherApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OpenWeatherMapClient implements WeatherClient {
    private final RestTemplate restTemplate;

    private final AppConfiguration config;

    public OpenWeatherMapClient(RestTemplate restTemplate, AppConfiguration config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @Override
    public WeatherApiResponse getWeatherForecast(String city, int count) {
//        String uri = "openWeather.api.endpoint=https://api.openweathermap.org/data/2.5/forecast?q={city}&appid={appId}&cnt={count}";

        String buildURL = buildURL(config.getOpenWeatherApiEndpoint(),
                city,
                config.getOpenWeatherApiKey(),
                count);

        ResponseEntity<WeatherApiResponse> response = restTemplate.getForEntity(buildURL, WeatherApiResponse.class);

        return response.getBody();
    }

    public static String buildURL(String url, String city, String appId, int count) {

//        String uriString = "openWeather.api.endpoint=https://api.openweathermap.org/data/2.5/forecast?q={city}&appid={appId}&cnt={count}";
//        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(url);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        builder.replaceQueryParam("q",city)
                .replaceQueryParam( "appid",appId)
                .replaceQueryParam("cnt", count);

        return builder.toUriString();
    }

}
