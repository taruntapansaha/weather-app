package com.publicissapient.weatherapp.external.client;

import com.publicissapient.weatherapp.configuration.AppConfiguration;
import com.publicissapient.weatherapp.dto.WeatherApiResponse;
import com.publicissapient.weatherapp.external.client.exception.BadRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class OpenWeatherMapClient implements WeatherClient {
    private static final String Q = "q";
    private static final String APP_ID = "appid";
    private static final String CNT = "cnt";
    private final RestTemplate restTemplate;

    private final AppConfiguration config;

    public OpenWeatherMapClient(RestTemplate restTemplate, AppConfiguration config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @Override
    public WeatherApiResponse getWeatherForecast(String city, int count) throws BadRequest {

        String buildURL = buildURL(config.getOpenWeatherApiEndpoint(),
                city,
                config.getOpenWeatherApiKey(),
                count);

        ResponseEntity<WeatherApiResponse> response = null;

        try {
            response = restTemplate.getForEntity(buildURL, WeatherApiResponse.class);
        } catch (RestClientResponseException exception) {

            if (exception.getStatusCode().is4xxClientError()) {
                log.error("Bad Request", exception);
                throw new BadRequest("Bad Request", exception);
            } else if (exception.getStatusCode().is5xxServerError()) {
                // TODO: read response cached data
                log.error(exception.getResponseBodyAsString(), exception);
            }
        }


        return response.getBody();
    }

    public static String buildURL(String url, String city, String appId, int count) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        builder.replaceQueryParam(Q, city)
                .replaceQueryParam(APP_ID, appId)
                .replaceQueryParam(CNT, count);

        return builder.toUriString();
    }

}
