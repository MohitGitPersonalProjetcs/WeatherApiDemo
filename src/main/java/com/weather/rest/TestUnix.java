package com.weather.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TestUnix {
	
	public static void main(String[] args) {
		long unixSeconds = 1483639200;
		Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate);
		
		long unixSeconds2 = 1483642800;
		Date date1 = new Date(unixSeconds2*1000L); // *1000 is to convert seconds to milliseconds
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
		sdf1.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
		String formattedDate1 = sdf1.format(date1);
		System.out.println(formattedDate1);
	}

}
