package com.publicissapient.weatherapp.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherMapData {

    @JsonProperty("dt")
    private Long dt;
    @JsonProperty("main")
    @Valid
    private Main main;
    @JsonProperty("weather")
    @Valid
    private java.util.List<Weather> weather;
    @JsonProperty("clouds")
    @Valid
    private Clouds clouds;
    @JsonProperty("wind")
    @Valid
    private Wind wind;
    @JsonProperty("visibility")
    private Integer visibility;
    @JsonProperty("pop")
    private Integer pop;
    @JsonProperty("rain")
    @Valid
    private Rain rain;
    @JsonProperty("sys")
    @Valid
    private Sys sys;
    @JsonProperty("dt_txt")
    private String dtTxt;

}
