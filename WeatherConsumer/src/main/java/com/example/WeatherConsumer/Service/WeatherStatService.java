package com.example.WeatherConsumer.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.WeatherConsumer.DTO.WeatherDTO;

@Service
public class WeatherStatService {
    private final List<WeatherDTO> weatherData = new ArrayList<>();

    public void addWeather(WeatherDTO weatherDTO)
    {
        weatherData.add(weatherDTO);
    }

    public String getWarmCity()
    {
        String town = weatherData.stream()
                            .max(Comparator.comparingDouble(w->w.getTemperature()))
                            .map(w->w.getCity())
                            .orElse("No data");
        double temp = weatherData.stream()
                        .filter(w->town.equals(w.getCity()))
                        .mapToDouble(w->w.getTemperature())
                        .average()
                        .orElse(0.0);
        String result = town +" "+ Double.toString(Math.round(temp*10)/10.0);
        return result;
    }

    public String getColdestCity()
    {
        String town = weatherData.stream()
                            .min(Comparator.comparingDouble(w->w.getTemperature()))
                            .map(w->w.getCity())
                            .orElse("No data");
        double temp = weatherData.stream()
                        .filter(w->town.equals(w.getCity()))
                        .mapToDouble(w->w.getTemperature())
                        .average()
                        .orElse(0.0);
        String result = town + " " + Double.toString(Math.round(temp*10)/10.0);
        return result;
    }

    public String getRainyDayInCity(String city)
    {
        long result = weatherData.stream()
                        .filter(w-> city.equals(w.getCity()))
                        .filter(w->"RAIN".equals(w.getWeatherType()))
                        .count();


        String answer = MessageFormat.format("В {0} было {1} дождливых дней",city,result);
        return answer;
    }

     public String getSunnyDayInCity(String city)
    {
        long result = weatherData.stream()
                        .filter(w-> city.equals(w.getCity()))
                        .filter(w->"SUNNY".equals(w.getWeatherType()))
                        .count();


        String answer = MessageFormat.format("В {0} было {1} солнечных дней",city,result);
        return answer;
    }

    public String getAverageTemp()
    {
        String result = weatherData.stream()
                .collect(Collectors.groupingBy(
                    w->w.getCity(),
                    Collectors.averagingDouble(w->w.getTemperature())
                ))
                .entrySet().stream()
                .map(e->e.getKey() + " " + Math.round(e.getValue()*10)/10.0)
                .collect(Collectors.joining(", "));
        return result;
    }



}
