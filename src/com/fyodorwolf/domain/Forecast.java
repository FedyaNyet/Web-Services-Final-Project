package com.fyodorwolf.domain;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forecast")
public class Forecast {

	HashMap<String, String> forecast = new HashMap<String, String>();
	
	public Forecast(){}
	
	public Forecast(HashMap<String, String> forecast){
		this.forecast = forecast;
	}
	
}
