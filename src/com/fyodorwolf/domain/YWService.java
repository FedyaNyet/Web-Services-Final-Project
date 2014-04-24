package com.fyodorwolf.domain;

import java.net.MalformedURLException;
import java.net.URL;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class YWService implements ForecastServiceInterface{

	public static String BASE_URL = "http://weather.yahooapis.com/forecastrss?p=";
	
	@Override
	public Forecast getForecast(String zip) {
		System.out.println(getUrl(zip));
		System.out.println("Get YWService forecast");
		Forecast forecast = makeForecast(ForecastFactory.getDoc(getUrl(zip)));
		forecast.units = "F";
		return forecast;
	}

	private Forecast makeForecast(Document doc) {
		Forecast forecast = new Forecast();
        NodeList forecastList = doc.getElementsByTagName("yweather:forecast");
        for (int a = 0; a < forecastList.getLength(); a++) {
        	NamedNodeMap attributes = forecastList.item(a).getAttributes();
        	Outlook outlook = new Outlook();
        	outlook.day = attributes.getNamedItem("day").getNodeValue();
        	outlook.condition = attributes.getNamedItem("text").getNodeValue();
        	outlook.highTemp = Integer.parseInt(attributes.getNamedItem("high").getNodeValue());
        	outlook.highTemp = Integer.parseInt(attributes.getNamedItem("low").getNodeValue());
    		forecast.addDay(outlook);
        }
		return forecast;
	}

	@Override
	public URL getUrl(String zip){
		try {
			return new URL(BASE_URL+zip);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
