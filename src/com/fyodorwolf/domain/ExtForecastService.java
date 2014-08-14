package com.fyodorwolf.domain;

import java.net.MalformedURLException;
import java.net.URL;

public interface ExtForecastService {

	public Forecast getForecast(String Location, String units);
	public URL getUrl(String zip, String units) throws MalformedURLException;
}
