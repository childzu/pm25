package com.example.pm25.domain;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Serializable{

	@Setter
	@Getter
	private static final long serialVersionUID = -3725492092295234664L;
	
	@Setter
	@Getter
	@JsonProperty(value = "stationID")
	private String stationID;
	
	@Setter
	@Getter
	@JsonProperty(value = "nameTH")
	private String nameTH;

	@Setter
	@Getter
	@JsonProperty(value = "nameEN")
	private String nameEN;

	@Setter
	@Getter
	@JsonProperty(value = "areaTH")
	private String areaTH;

	@Setter
	@Getter
	@JsonProperty(value = "areaEN")
	private String areaEN;

	@Setter
	@Getter
	@JsonProperty(value = "stationType")
	private String stationType;

	@Setter
	@Getter
	@JsonProperty(value = "lat")
	private double latitude;

	@Setter
	@Getter
	@JsonProperty(value = "long")
	private double longtitude;

	@Setter
	@Getter
	@JsonProperty(value = "LastUpdate")
	private LastUpdateData LastUpdate;

	@Override
	public String toString() {
		return "Weather [stationID=" + stationID + ", nameTH=" + nameTH + ", nameEN=" + nameEN + ", areaTH=" + areaTH
				+ ", areaEN=" + areaEN + ", stationType=" + stationType + ", lat=" + latitude + ", LastUpdate=" + LastUpdate
				+ "]";
	}
	
	
}

@JsonIgnoreProperties(ignoreUnknown = true)
class LastUpdateData implements Serializable {
	
	@Setter
	@Getter
	private static final long serialVersionUID = -1515249491089419826L;
	
	@Setter
	@Getter
	@JsonProperty(value = "date")
	private String date;
	
	@Setter
	@Getter
	@JsonProperty(value = "time")
	private String time;
	
	@Setter
	@Getter
	@JsonProperty(value = "PM25")
	private PollutionPM25 pm25;
	
	@Setter
	@Getter
	@JsonProperty(value = "PM10")
	private PollutionPM10 pm10;
	
	@Setter
	@Getter
	@JsonProperty(value = "O3")
	private OxygenO3 o3;
	
	@Setter
	@Getter
	@JsonProperty(value = "CO")
	private PollutionCO co;

}

@JsonIgnoreProperties(ignoreUnknown = true)
class PollutionPM25 implements Serializable{
	
	@Setter
	@Getter
	private static final long serialVersionUID = 5514774125598250494L;
	
	@Setter
	@Getter
	@JsonProperty(value = "value")
	private String value;
	
	@Setter
	@Getter
	@JsonProperty(value = "unit")
	private String unit;

	@Override
	public String toString() {
		return "PM25 [value=" + value + ", unit=" + unit + "]";
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class PollutionPM10 implements Serializable{

	@Setter
	@Getter
	private static final long serialVersionUID = -1219894577444923369L;
	
	@Setter
	@Getter
	@JsonProperty(value = "value")
	private String value;
	
	@Setter
	@Getter
	@JsonProperty(value = "unit")
	private String unit;

	@Override
	public String toString() {
		return "PM10 [value=" + value + ", unit=" + unit + "]";
	}
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PollutionCO implements Serializable{
	
	@Setter
	@Getter
	private static final long serialVersionUID = -4743020110170918961L;
	
	@Setter
	@Getter
	@JsonProperty(value = "value")
	private String value;
	
	@Setter
	@Getter
	@JsonProperty(value = "unit")
	private String unit;

	@Override
	public String toString() {
		return "CO [value=" + value + ", unit=" + unit + "]";
	}
	
}

@JsonIgnoreProperties(ignoreUnknown = true)
class OxygenO3 implements Serializable{
	
	@Setter
	@Getter
	private static final long serialVersionUID = -4743020110170918961L;
	
	@Setter
	@Getter
	@JsonProperty(value = "value")
	private String value;
	
	@Setter
	@Getter
	@JsonProperty(value = "unit")
	private String unit;


	@Override
	public String toString() {
		return "O3 [value=" + value + ", unit=" + unit + "]";
	}
	
}
