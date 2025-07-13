package com.example.WeatherProducer.Service;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.WeatherProducer.DTO.WeatherDTO;
import com.example.WeatherProducer.Enum.City;
import com.example.WeatherProducer.Enum.WeatherType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final Random random = new Random();


    public void startGeneration(int counter)
    {
        for (int i =0 ; i<counter; i++){
            try{
                City randomCity = City.values()[random.nextInt(City.values().length)];
                WeatherType randomType = WeatherType.values()[random.nextInt(WeatherType.values().length)];
                double randomTemp = random.nextDouble()*35;
                double temp = Math.round(randomTemp*10)/10.0;
                
                WeatherDTO weather = new WeatherDTO(
                    randomCity.name(),
                    temp,
                    randomType.name()
                );

                String message = objectMapper.writeValueAsString(weather);
                kafkaTemplate.send("weather-topic",message);

            }
            catch(JsonProcessingException e)
            {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
