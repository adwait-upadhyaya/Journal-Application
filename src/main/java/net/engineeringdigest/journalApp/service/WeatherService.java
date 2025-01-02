package net.engineeringdigest.journalApp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.engineeringdigest.journalApp.config.WeatherApiProperties;
import net.engineeringdigest.journalApp.dto.WeatherDTO;
import net.engineeringdigest.journalApp.exception.WeatherServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService  implements IWeatherService {
    private final RestTemplate restTemplate;
    private final WeatherApiProperties weatherApiProperties;

    public WeatherService(RestTemplate restTemplate, WeatherApiProperties weatherApiProperties) {
        this.restTemplate = restTemplate;
        this.weatherApiProperties = weatherApiProperties;
    }

    @Override
    public WeatherDTO fetchWeather(String city){
        String url = weatherApiProperties.getUrl() + "?access_key=" + weatherApiProperties.getKey() + "&query=" + city;
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        WeatherDTO weatherDTO = new WeatherDTO();

        try {
            JsonNode root = objectMapper.readTree(response);
            weatherDTO.setTemperature(root.path("current").path("temperature").asInt());
            weatherDTO.setName(root.path("location").path("name").asText());
        } catch (Exception e) {
            throw new RuntimeException("Error parsing weather data", e);
        }

        return weatherDTO;
    }
}
