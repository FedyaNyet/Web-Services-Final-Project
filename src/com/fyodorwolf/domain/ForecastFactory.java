package com.fyodorwolf.domain;

import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class ForecastFactory {

	public static final int WEATHER_CHANNEL = 0;
	public static final int YAHOO_WEATHER = 1;
	public static final String[] providers = {"WCService", "YWService"};
			
	public static Forecast getForecast(int serviceProvider, String zip){
		ForecastServiceInterface extForcastService;
		switch(serviceProvider){
		case YAHOO_WEATHER:
			extForcastService = new YWService();
			break;
		default:
			extForcastService = new WCService();
			break;
		}
		return extForcastService.getForecast(zip);
	}

	public static Document getDoc(URL url) {
		try {
			URLConnection uc = url.openConnection();
	        uc.connect();
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.parse(uc.getInputStream());
	        doc.getDocumentElement().normalize();
	        return doc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static int get_provider_id(String provider){
		for(int idx = 0; idx<providers.length; idx++){
			if(provider.equals(providers[idx])) return idx;
		}
		return -1;
	}
	
}
