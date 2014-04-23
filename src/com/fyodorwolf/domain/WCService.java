package com.fyodorwolf.domain;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WCService implements ForecastServiceInterface{

	private String AUTH_KEY = "6c677a834fab12d1";
	private String BASE_URL = "http://api.wunderground.com/api/";
	
	@Override
	public Forecast getforcast(String zip) {
		// TODO Auto-generated method stub
		System.out.println("Get WCService forecast");
		HashMap<String,String> foreCast= new HashMap<String, String>();
		foreCast.put("monday","10C");
		Forecast myForcast = new Forecast();
		myForcast.setForcast(foreCast);
		return myForcast;
	}

	@Override
	public URL getUrl(String zip) throws MalformedURLException{
		return new URL(BASE_URL + AUTH_KEY + "/forecast/q/" + zip + ".xml");
	}
	

	public static void main(String[] args) throws MalformedURLException{

		String AUTH_KEY = "6c677a834fab12d1";
		String BASE_URL = "http://api.wunderground.com/api/";
		URL googleWeatherXml = new URL(BASE_URL + AUTH_KEY + "/forecast/q/02478.xml");

		try{
				
	        URLConnection uc = googleWeatherXml.openConnection();
	        uc.connect();
	      
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.parse(uc.getInputStream());
	        doc.getDocumentElement().normalize();
	      
	      
	        NodeList forecast = doc.getElementsByTagName("forecast");
	        for (int a = 0; a < forecast.getLength(); a++) {
	            Node forecastNode = forecast.item(a);
	          
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
	                                        
	                                          NodeList date = forecastdayElement.getElementsByTagName("date");
	                                        
	                                          for (int e = 0; e < date.getLength(); e++) {
	                                            Node dateNode = date.item(e); 
	                                          
	                                            if (dateNode.getNodeType() == Node.ELEMENT_NODE) {
	                                                Element dateElement = (Element) dateNode;
	                                              
	                                                NodeList weekday = dateElement.getElementsByTagName("weekday");
	                                                Element day = (Element) weekday.item(0);
	                                                System.out.println("Day of weekday: " + day.getTextContent());
	                                            }
	                                          }
	                                        
	                                          NodeList high = forecastdayElement.getElementsByTagName("high");
	                                        
	                                          for (int f = 0; f < high.getLength(); f++) {
	                                            Node highNode = high.item(f); 
	                                          
	                                            if (highNode.getNodeType() == Node.ELEMENT_NODE) {
	                                                Element highElement = (Element) highNode;
	                                                NodeList celsius = highElement.getElementsByTagName("celsius");
	                                                Element cel = (Element) celsius.item(0);
	                                                System.out.println("High cel: " + cel.getTextContent());
	                                            }
	                                          }
	                                        
	                                          NodeList low = forecastdayElement.getElementsByTagName("low");
	                                        
	                                          for (int g = 0; g < low.getLength(); g++) {
	                                            Node lowNode = low.item(g); 
	                                          
	                                            if (lowNode.getNodeType() == Node.ELEMENT_NODE) {
	                                                Element lowElement = (Element) lowNode;
	                                                NodeList celsius = lowElement.getElementsByTagName("celsius");
	                                                Element cel = (Element) celsius.item(0);
	                                                System.out.println("Low cel: " + cel.getTextContent());
	                                            }
	                                          }
	                                        
	                                          NodeList avewind = forecastdayElement.getElementsByTagName("avewind");
	                                        
	                                          for (int h = 0; h < avewind.getLength(); h++) {
	                                            Node avewindNode = avewind.item(h); 
	                                          
	                                            if (avewindNode.getNodeType() == Node.ELEMENT_NODE) {
	                                                Element avewindElement = (Element) avewindNode;
	                                                NodeList mph = avewindElement.getElementsByTagName("mph");
	                                                Element mp = (Element) mph.item(0);
	                                                System.out.println("mph: " + mp.getTextContent());
	                                              
	                                                NodeList kph = avewindElement.getElementsByTagName("kph");
	                                                Element kp = (Element) kph.item(0);
	                                                System.out.println("kph: " + kp.getTextContent());
	                                              
	                                                NodeList dir = avewindElement.getElementsByTagName("dir");
	                                                Element dr = (Element) dir.item(0);
	                                                System.out.println("Dir: " + dr.getTextContent());
	                                              
	                                                NodeList degrees = avewindElement.getElementsByTagName("degrees");
	                                                Element deg = (Element) degrees.item(0);
	                                                System.out.println("Degree: " + deg.getTextContent());
	                                            }
	                                          }
	                                        
	                                          NodeList conditions = forecastdayElement.getElementsByTagName("conditions");
	                                          Element con = (Element) conditions.item(0);
	                                          System.out.println("conditions: " + con.getTextContent());
	                                        
	                                          NodeList avehumidity = forecastdayElement.getElementsByTagName("avehumidity");
	                                          Element ave = (Element) avehumidity.item(0);
	                                          System.out.println("avehumidity: " + ave.getTextContent());
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
	}

}
