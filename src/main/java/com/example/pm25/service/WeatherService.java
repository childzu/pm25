package com.example.pm25.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.pm25.domain.Station;
import com.example.pm25.domain.Weather;

@Service
public class WeatherService {
	
	RestTemplate restTemplate;
	
	@Value("${weather.endpoint}")
	private String weather_endpoint;
	
	public WeatherService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public Station getWeatherFromAir4Thai() {
		return this.restTemplate.getForObject(this.weather_endpoint, Station.class);
	}
	
	public Weather getWeatherFromAir4ThaiForStationId(String stationId) {
		return this.restTemplate.getForObject(this.weather_endpoint + "?stationID=" + stationId, Weather.class);
	}

}
