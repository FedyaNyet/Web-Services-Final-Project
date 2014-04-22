package com.fyodorwolf.services;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONValue;

import com.fyodorwolf.domain.ForecastFactory;
import com.fyodorwolf.domain.Forecast;


@Path("/forecast")
public class ForecastService {

	private String location = "Boston Ma 02134";
	private int provider = ForecastFactory.WEATHER_CHANNEL;

	
	@GET
   	@Produces("application/xml")
	public Forecast getForcast(){
		return ForecastFactory.getForcast(provider, location);
	}
	
	@GET
	@Path("/config")
    @Produces("text/plain")
	public String getConfig(){
		HashMap<String, String> json = new HashMap<String, String>();
		json.put("location",location);
		json.put("provider", ForecastFactory.providers[provider]);
		return JSONValue.toJSONString(json);
	}

	@POST
    @Consumes("text/plain")
    @Produces("text/plain")
	public Forecast setProvider(){
		return ForecastFactory.getForcast(provider, location);
	}
	
	@POST
    @Consumes("text/plain")
    @Produces("text/plain")
	public Forecast setLocation(){
		return ForecastFactory.getForcast(provider, location);
	}
	
}
