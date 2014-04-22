package com.fyodorwolf.domain;

public class ForecastFactory {

	public static final String[] providers = {"WCService", "YWService"};
	public static final int WEATHER_CHANNEL = 0;
	public static final int YAHOO_WEATHER = 1;
			
	public static Forecast getForcast(int serviceProvider, String location){
		ForecastServiceInterface extForcastService;
		switch(serviceProvider){
		case YAHOO_WEATHER:
			extForcastService = new YWService();
			break;
		default:
			extForcastService = new WCService();
			break;
		}
		return extForcastService.getforcast(location);
	}
			
}
