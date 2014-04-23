package com.fyodorwolf.domain;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class YWService implements ForecastServiceInterface{

	public static String BASE_URL = "http://weather.yahooapis.com/forecastrss?p=";
	
	@Override
	public Forecast getforcast(String zip) {
		System.out.println("Get YWService forecast");
		HashMap<String,String> foreCast= new HashMap<String, String>();
		foreCast.put("monday","20C");
		Forecast myForcast = new Forecast();
		myForcast.setForcast(foreCast);
		return myForcast;
	}

	@Override
	public URL getUrl(String zip) throws MalformedURLException {
		return new URL(BASE_URL+zip);
	}
	
}
