package com.fyodorwolf.domain;

import java.util.ArrayList;

public class Forecast {
	
	public ArrayList<ForecastDay> forecast = new ArrayList<ForecastDay>();
	
	public Forecast(){}
	
	public ArrayList<ForecastDay> getForecast(){
		return forecast;
	}
	
	public void addDay(ForecastDay outlook){
		forecast.add(outlook);
	}
	
	public String toString(){
		String ret = "";
		for(ForecastDay outlook : forecast){
			ret += outlook.toString() +"\n";
		}
		return ret;
	}
}
