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

	private String zip = "02134";
	private int provider = ForecastFactory.WEATHER_CHANNEL;

	
	@GET
   	@Produces(MediaType.APPLICATION_JSON)
	public Forecast getForcast(){
		Forecast forcast = ForecastFactory.getForecast(this.provider, this.zip);
		System.out.println("GOT"+forcast);
		return forcast;
	}
	
	@GET
	@Path("/config")
   	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> getConfig(){
		HashMap<String, String> json = new HashMap<String, String>();
		json.put("zip",this.zip);
		json.put("provider", ForecastFactory.providers[this.provider]);
		return json;
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/config")
	public void setConfig(@FormParam("provider") String provider, @FormParam("zip") String zip){
		int providerId = ForecastFactory.get_provider_id(provider);
		if(providerId < 0) return;
		this.zip = zip;
		this.provider = providerId;
		System.out.println("setConfig "+ ForecastFactory.providers[providerId] + "," + this.zip);
	}
	
}
