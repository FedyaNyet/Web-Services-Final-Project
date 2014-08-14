package com.fyodorwolf.domain;

import java.net.MalformedURLException;
import java.net.URL;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class GWService implements ExtForecastService{

	public static String BASE_URL = "http://weather.yahooapis.com/forecastrss?p=";
	
	@Override
	public Forecast getForecast(String zip, String units){
		URL url = getUrl(zip, units);
		System.out.println(url);
		Forecast forecast = makeForecast(ForecastFactory.getDoc(url));
		return forecast;
	}

	private Forecast makeForecast(Document doc) {
		Forecast forecast = new Forecast();
        NodeList forecastList = doc.getElementsByTagName("yweather:forecast");
        for (int a = 0; a < forecastList.getLength(); a++) {
        	NamedNodeMap attributes = forecastList.item(a).getAttributes();
        	ForecastDay outlook = new ForecastDay();
			outlook.setDay( attributes.getNamedItem("day").getNodeValue() );
        	outlook.condition = attributes.getNamedItem("text").getNodeValue();
        	outlook.highTemp = Integer.parseInt(attributes.getNamedItem("high").getNodeValue());
        	outlook.lowTemp = Integer.parseInt(attributes.getNamedItem("low").getNodeValue());
    		forecast.addDay(outlook);
        }
		return forecast;
	}

	@Override
	public URL getUrl(String zip, String units){
		try {
			return new URL(BASE_URL+zip+"&u="+units.toLowerCase());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
