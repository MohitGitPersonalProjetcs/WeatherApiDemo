package com.weather.beans;

import java.util.List;

public class TimelyDarkSkyBean {

	private String summary;

	private List<DarkSkyData> data;

	public List<DarkSkyData> getData() {
		return data;
	}

	public void setData(List<DarkSkyData> data) {
		this.data = data;

	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
