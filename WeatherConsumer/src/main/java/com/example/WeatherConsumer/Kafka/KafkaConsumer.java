package com.example.WeatherConsumer.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.WeatherConsumer.DTO.WeatherDTO;
import com.example.WeatherConsumer.Service.WeatherStatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final WeatherStatService weatherStatService;

    @KafkaListener(
        topics = "weather-topic",
        groupId = "weather-group",
        containerFactory =  "KafkaListenerContainerFactory")
    public void consumeWeatherData(WeatherDTO weatherDTO)
    {
        weatherStatService.addWeather(weatherDTO);
    }
}
