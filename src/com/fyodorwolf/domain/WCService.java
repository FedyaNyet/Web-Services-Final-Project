package com.fyodorwolf.domain;

import java.net.MalformedURLException;
import java.net.URL;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WCService implements ForecastServiceInterface{

	private static String AUTH_KEY = "6c677a834fab12d1";
	private static String BASE_URL = "http://api.wunderground.com/api/";
	
	@Override
	public Forecast getForecast(String zip) {
		System.out.println(getUrl(zip));
		Forecast forecast = makeForecast(ForecastFactory.getDoc(getUrl(zip)));
		return forecast;
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
	                				for (int d = 0; d < forecastday.getLength(); d++) {
	                					Node forecastdayNode = forecastday.item(d);
	                					if (forecastdayNode.getNodeType() == Node.ELEMENT_NODE) {
	                						
	                						Element forecastdayElement = (Element) forecastdayNode;
	                						Outlook outlook = new Outlook();
	                						
											NodeList date = forecastdayElement.getElementsByTagName("date");
											for (int e = 0; e < date.getLength(); e++) {
												Node dateNode = date.item(e); 
												if (dateNode.getNodeType() == Node.ELEMENT_NODE) {
													Element dateElement = (Element) dateNode;
													NodeList weekday = dateElement.getElementsByTagName("weekday");
													outlook.day = ((Element) weekday.item(0)).getTextContent();
												}
											}
											
                                            NodeList high = forecastdayElement.getElementsByTagName("high");
                                            for (int f = 0; f < high.getLength(); f++) {
                                            	Node highNode = high.item(f); 
	                                            if (highNode.getNodeType() == Node.ELEMENT_NODE) {
	                                                Element highElement = (Element) highNode;
	                                                NodeList celsius = highElement.getElementsByTagName("celsius");
	                                                String higT = ((Element) celsius.item(0)).getTextContent();
	                                                outlook.highTemp = Integer.parseInt(higT);
	                                            }
                                            }
                                        
                                            NodeList low = forecastdayElement.getElementsByTagName("low");
                                            for (int g = 0; g < low.getLength(); g++) {
                                            	Node lowNode = low.item(g); 
	                                            if (lowNode.getNodeType() == Node.ELEMENT_NODE) {
	                                                Element lowElement = (Element) lowNode;
	                                                NodeList celsius = lowElement.getElementsByTagName("celsius");
	                                                String lowT = ((Element) celsius.item(0)).getTextContent();
	                                                outlook.lowTemp = Integer.parseInt(lowT);
	                                            }
	                                        }
                                        
                                            NodeList conditions = forecastdayElement.getElementsByTagName("conditions");
                                            String con = ((Element) conditions.item(0)).getTextContent();
                                            outlook.condition = con;
//                                        
//                                            NodeList avehumidity = forecastdayElement.getElementsByTagName("avehumidity");
//                                            Element ave = (Element) avehumidity.item(0);
//                                            System.out.println("avehumidity: " + ave.getTextContent());
                                            
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

	@Override
	public URL getUrl(String zip){
		try{
			return new URL(BASE_URL + AUTH_KEY + "/forecast/q/" + zip + ".xml");
		}
		catch(MalformedURLException e){
			e.printStackTrace();
			return null;
		}
	}
}
