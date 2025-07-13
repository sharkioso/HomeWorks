package com.example.WeatherProducer.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class WeatherDTO {
    private final String city;
    private final double temperature;
    private final String weatherType;
    private LocalDateTime time = LocalDateTime.now();
}
