package com.example.WeatherConsumer.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.WeatherConsumer.Service.WeatherStatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stats")
public class WeatherConsumerController {
    private final WeatherStatService weatherStatService;

    @GetMapping("/coldest")
    public ResponseEntity<String> getColdCity()
    {
        return ResponseEntity.ok(weatherStatService.getColdestCity());
    }

    @GetMapping("/warmest")
    public ResponseEntity<String> getWarmCity()
    {
        return ResponseEntity.ok(weatherStatService.getWarmCity());
    }

    @GetMapping("/rain/{city}")
    public ResponseEntity<String> getRainyDays(@RequestParam (name = "city", defaultValue = "SAINT_PETERSBURG") String city)
    {
        return ResponseEntity.ok(weatherStatService.getRainyDayInCity(city));
    }

    @GetMapping("/sunny/{city}")
    public ResponseEntity<String> getSunnyDays(@RequestParam (name = "city", defaultValue = "SAINT_PETERSBURG") String city)
    {
        return ResponseEntity.ok(weatherStatService.getSunnyDayInCity(city));
    }

    @GetMapping("/average")
    public ResponseEntity<String> getAverageTemperature()
    {
        return ResponseEntity.ok(weatherStatService.getAverageTemp());
    }
}
