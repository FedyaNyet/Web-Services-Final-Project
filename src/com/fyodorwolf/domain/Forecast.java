package com.fyodorwolf.domain;

import java.util.ArrayList;

public class Forecast {
	
	public String units = "C";
	public ArrayList<Outlook> forecast = new ArrayList<Outlook>();
	
	public Forecast(){}
	
	public ArrayList<Outlook> getForecast(){
		return forecast;
	}
	
	public void addDay(Outlook outlook){
		forecast.add(outlook);
	}
	
	public String toString(){
		String ret = "units:"+units+"\n";
		for(Outlook outlook : forecast){
			ret += outlook.toString() +"\n";
		}
		return ret;
	}
}
