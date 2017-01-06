package com.weather.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DarkSkyData {

	private String time;
	private String summary;
	private int temperature;
	

	public String getTime() {
		return time;
	}
	
	
	public void setTime(String time) {
		long longTime = Long.parseLong(time);
		long unixSeconds = longTime;
		Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
		int dayDefault = date.getDay();
		Date currentDate = new Date();
		int currentDateDay = currentDate.getDay();
		if(dayDefault <= currentDateDay){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
		//sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate);
		String properDate = formattedDate.substring(0, 20);
		this.time = properDate;
	}}
		
	
	
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}


	public int getTemperature() {
		return temperature;
	}


	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
	
}
