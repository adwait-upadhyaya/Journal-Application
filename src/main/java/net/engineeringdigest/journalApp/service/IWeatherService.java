package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.dto.WeatherDTO;

public interface IWeatherService {
    WeatherDTO fetchWeather(String city);
}
