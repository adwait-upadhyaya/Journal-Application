package net.engineeringdigest.journalApp.exception;

public class WeatherServiceException extends RuntimeException{

    public WeatherServiceException(String message){
        super(message);
    }
}
