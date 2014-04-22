package com.fyodorwolf.services;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.FormParam;

import com.fyodorwolf.domain.ForecastFactory;
import com.fyodorwolf.domain.Forecast;


@Path("/forecast")
public class ForecastService {

	private String location = "Boston Ma 02134";
	private int provider = ForecastFactory.WEATHER_CHANNEL;

	
	@GET
   	@Produces(MediaType.APPLICATION_JSON)
	public Forecast getForcast(){
		return ForecastFactory.getForcast(provider, location);
	}
	
	@GET
	@Path("/config")
   	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> getConfig(){
		HashMap<String, String> json = new HashMap<String, String>();
		json.put("location",location);
		json.put("provider", ForecastFactory.providers[provider]);
		return json;
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/config")
	public void setConfig(@FormParam("provider") String provider, @FormParam("location") String location){
		int providerId = ForecastFactory.get_provider_id(provider);
		if(providerId < 0) return;
		this.location = location;
		this.provider = providerId;
		System.out.println("setConfig "+ ForecastFactory.providers[providerId] + "," + this.location);
	}
	
}
