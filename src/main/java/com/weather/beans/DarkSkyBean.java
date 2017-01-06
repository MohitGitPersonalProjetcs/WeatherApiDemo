package com.weather.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class DarkSkyBean {

	TimelyDarkSkyBean hourly;

	public TimelyDarkSkyBean getHourly() {
		return hourly;
	}

	public void setHourly(TimelyDarkSkyBean hourly) {
		this.hourly = hourly;
	}

}
