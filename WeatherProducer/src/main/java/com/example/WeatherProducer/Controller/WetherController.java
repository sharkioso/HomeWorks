package com.example.WeatherProducer.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.WeatherProducer.Service.WeatherProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
public class WetherController {
    private final WeatherProducerService weatherProducerService;

    @GetMapping
    public String start(@RequestParam(name = "count" ,defaultValue = "10") int counter)
    {
        weatherProducerService.startGeneration(counter);
        return "Работаем братья";
    }
    
}
