package com.fyodorwolf.domain;

import java.net.MalformedURLException;
import java.net.URL;

public interface ForecastServiceInterface {

	public Forecast getforcast(String Location);
	public URL getUrl(String zip) throws MalformedURLException ;
}
