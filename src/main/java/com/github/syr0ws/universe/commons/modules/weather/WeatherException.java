package com.github.syr0ws.universe.commons.modules.weather;

public class WeatherException extends Exception {

    public WeatherException(String message) {
        super(message);
    }

    public WeatherException(String message, Throwable cause) {
        super(message, cause);
    }
}
