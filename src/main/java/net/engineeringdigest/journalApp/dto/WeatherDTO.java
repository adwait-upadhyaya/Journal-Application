package net.engineeringdigest.journalApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("temperature")
    private int temperature;
}
