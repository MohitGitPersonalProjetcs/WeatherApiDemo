package com.weather.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OwmCityDetailBean {

	private String id;
	private String name;
	private OwmCoord coord;
	private OwmMainBean main;
	private String dt; 
	
	public OwmMainBean getMain() {
		return main;
	}
	public void setMain(OwmMainBean main) {
		this.main = main;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		long longTime = Long.parseLong(id);
		long unixSeconds = longTime;
		Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
		int dayDefault = date.getDay();
		Date currentDate = new Date();
		int currentDateDay = currentDate.getDay();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
		//sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate);
		String properDate = formattedDate.substring(0, 20);
		this.dt = properDate;
	
	}
	
	public OwmCoord getCoord() {
		return coord;
	}
	public void setCoord(OwmCoord coord) {
		this.coord = coord;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
