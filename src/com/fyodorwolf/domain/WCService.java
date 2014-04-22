package com.fyodorwolf.domain;

import java.util.HashMap;

public class WCService implements ForecastServiceInterface{

	
	@Override
	public Forecast getforcast(String Location) {
		// TODO Auto-generated method stub
		System.out.println("Get WCService forecast");
		HashMap<String,String> foreCast= new HashMap<String, String>();
		foreCast.put("Monday", "26");
		foreCast.put("Tuesday", "28");
		foreCast.put("Wednesday", "30");
		foreCast.put("Thursday", "29");
		foreCast.put("Friday", "27");
		foreCast.put("Saterday", "25");
		foreCast.put("Sunday", "32");
		return new Forecast(foreCast);
	}

}
