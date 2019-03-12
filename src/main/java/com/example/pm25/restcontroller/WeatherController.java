package com.example.pm25.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import com.example.pm25.domain.Station;
import com.example.pm25.domain.Weather;
import com.example.pm25.service.WeatherService;

@RestController
@RequestMapping("api")
public class WeatherController {
	
	@Autowired
	WeatherService weatherService;
	
	@GetMapping("")
	@ResponseBody
	public RedirectView index() {
		return new RedirectView("api/weather");
	}
	
	@GetMapping("/")
	@ResponseBody
	public RedirectView index2() {
		return new RedirectView("weather");
	}
	
	@GetMapping(value = "weather/{stationid}")
	@ResponseBody
	public Weather getWeatherBiId(@PathVariable("stationid") String stationid) {
		return this.weatherService.getWeatherFromAir4ThaiForStationId(stationid);
	}
	
	@GetMapping("weather")
	@ResponseBody
	public Station getWeather() {
		return this.weatherService.getWeatherFromAir4Thai();
	}

}
