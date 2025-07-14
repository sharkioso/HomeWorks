package com.example.WeatherConsumer.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDTO {
    private String city;
    private double temperature;
    private String weatherType;
    private LocalDateTime time;
}
