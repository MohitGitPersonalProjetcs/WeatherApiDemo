package com.weather.beans;

import java.util.List;

public class OpenWeatherMapBean {

	private int count;
	private List<OwmCityDetailBean> list;
	
	public List<OwmCityDetailBean> getList() {
		return list;
	}
	public void setList(List<OwmCityDetailBean> list) {
		this.list = list;
	}
}
