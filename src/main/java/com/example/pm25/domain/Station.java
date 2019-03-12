package com.example.pm25.domain;

import java.io.Serializable;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Station implements Serializable{
	
	@Setter
	@Getter
	private static final long serialVersionUID = 1L;
	
	@Setter
	@Getter
	@JsonProperty(value = "stations")
	private ArrayList<Weather> stations;


}
