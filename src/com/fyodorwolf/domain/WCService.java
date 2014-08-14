package com.fyodorwolf.domain;

import java.net.MalformedURLException;
import java.net.URL;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WCService implements ExtForecastService{

	private String units = "C";
	private static String AUTH_KEY = "6c677a834fab12d1";
	private static String BASE_URL = "http://api.wunderground.com/api/";
	
	@Override
	public Forecast getForecast(String zip, String units) {
		this.units = units;
		System.out.println("myUnits"+this.units);
		
		URL url = getUrl(zip);
		System.out.println(url);
		Forecast forecast = makeForecast( ForecastFactory.getDoc(url) );
		return forecast;
	}
	
	@Override
	public URL getUrl(String zip, String units){
		return this.getUrl(zip);
	}

	private Forecast makeForecast(Document doc) {
		Forecast forecast = new Forecast();
		try{
	        NodeList forecastList = doc.getElementsByTagName("forecast");
	        for (int a = 0; a < forecastList.getLength(); a++) {
	            Node forecastNode = forecastList.item(a);
	          
	            if (forecastNode.getNodeType() == Node.ELEMENT_NODE) {
	            	Element forecastElement = (Element) forecastNode;
	                NodeList simpleforecast = forecastElement.getElementsByTagName("simpleforecast");
	                for (int b = 0; b < simpleforecast.getLength(); b++) {
	                	Node simpleforecastNode = simpleforecast.item(b);
	                	if (simpleforecastNode.getNodeType() == Node.ELEMENT_NODE) {
	                		Element simpleforecastElement = (Element) simpleforecastNode;
	                		NodeList forecastdays = simpleforecastElement.getElementsByTagName("forecastdays");
	                        
	                		for (int c = 0; c < forecastdays.getLength(); c++) {
	                        
	                			Node forecastdaysNode = forecastdays.item(c);
	                			if (forecastdaysNode.getNodeType() == Node.ELEMENT_NODE) {
	                				Element forecastdaysElement = (Element) forecastdaysNode;
	                				NodeList forecastday = forecastdaysElement.getElementsByTagName("forecastday");
	                				for (int d = 0; d < forecastday.getLength() && d<5; d++) {
	                					Node forecastdayNode = forecastday.item(d);
	                					if (forecastdayNode.getNodeType() == Node.ELEMENT_NODE) {
	                						
	                						Element forecastdayElement = (Element) forecastdayNode;
	                						ForecastDay outlook = new ForecastDay();
	                						
											NodeList date = forecastdayElement.getElementsByTagName("date");
											for (int e = 0; e < date.getLength(); e++) {
												Node dateNode = date.item(e); 
												if (dateNode.getNodeType() == Node.ELEMENT_NODE) {
													Element dateElement = (Element) dateNode;
													NodeList weekday = dateElement.getElementsByTagName("weekday");
													outlook.setDay( ((Element) weekday.item(0)).getTextContent() );
												}
											}
											
                                            NodeList high = forecastdayElement.getElementsByTagName("high");
                                            for (int f = 0; f < high.getLength(); f++) {
                                            	Node highNode = high.item(f); 
	                                            if (highNode.getNodeType() == Node.ELEMENT_NODE) {
	                                                Element highElement = (Element) highNode;
	                                                String higT = highElement.getElementsByTagName("celsius").item(0).getTextContent();
	                                                if(this.units.equals("F")){
	                                                	higT = highElement.getElementsByTagName("fahrenheit").item(0).getTextContent();
	                                                }
	                                                outlook.highTemp = Integer.parseInt(higT);
	                                            }
                                            }
                                        
                                            NodeList low = forecastdayElement.getElementsByTagName("low");
                                            for (int g = 0; g < low.getLength(); g++) {
                                            	Node lowNode = low.item(g); 
	                                            if (lowNode.getNodeType() == Node.ELEMENT_NODE) {
	                                                Element lowElement = (Element) lowNode;
	                                                String lowT = lowElement.getElementsByTagName("celsius").item(0).getTextContent();
	                                                if(this.units.equals("F")){
	                                                	lowT = lowElement.getElementsByTagName("fahrenheit").item(0).getTextContent();
	                                                }
	                                                outlook.lowTemp = Integer.parseInt(lowT);
	                                            }
	                                        }
                                        
                                            NodeList conditions = forecastdayElement.getElementsByTagName("conditions");
                                            String con = ((Element) conditions.item(0)).getTextContent();
                                            outlook.condition = con;
                                            
                                            
                                            forecast.addDay(outlook);
                                  		}
                              		}
                          		}
                      		}
                  		}
              		}                       
        		}                 
    		}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return forecast;
	}

	private URL getUrl(String zip){
		try{
			return new URL(BASE_URL + AUTH_KEY + "/forecast10day/q/" + zip + ".xml");
		}
		catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}
}
