package com.fyodorwolf.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/services")
public class WeatherApplication extends Application {
   private Set<Object> singletons = new HashSet<Object>();

   public WeatherApplication() {
      singletons.add(new ForecastService());   
   }

   @Override
   public Set<Object> getSingletons() {
      return singletons;
   }
}
