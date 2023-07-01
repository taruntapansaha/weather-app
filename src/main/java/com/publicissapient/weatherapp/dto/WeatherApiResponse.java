package com.publicissapient.weatherapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherApiResponse {

    @JsonProperty("cod")
    private String status;
    @JsonProperty("message")
    private Integer message;
    @JsonProperty("cnt")
    private Integer count;
    @JsonProperty("list")
    private java.util.List<WeatherMapData> list;
    @JsonProperty("city")
    private City city;

}
