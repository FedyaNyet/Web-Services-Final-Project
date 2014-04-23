package com.fyodorwolf.domain;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.InputStream;
//import java.io.StringWriter;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class ForecastFactory {

	public static final String[] providers = {"WCService", "YWService"};
	public static final int WEATHER_CHANNEL = 0;
	public static final int YAHOO_WEATHER = 1;
			
	public static Forecast getForcast(int serviceProvider, String zip){
		ForecastServiceInterface extForcastService;
		switch(serviceProvider){
		case YAHOO_WEATHER:
			extForcastService = new YWService();
			break;
		default:
			extForcastService = new WCService();
			break;
		}
		try {
			URL url = extForcastService.getUrl(zip);
			System.out.println("FETCHING:" + url.toString());
			String xml = xmlUrlToString(url);
			System.out.println("FETCHED XML: \n" + xml);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return extForcastService.getforcast(zip);
	}
	
	public static int get_provider_id(String provider){
		for(int idx = 0; idx<providers.length; idx++){
			if(provider.equals(providers[idx])) return idx;
		}
		return -1;
	}
	
	public static String xmlUrlToString(URL url){
		String resp = "";
		
        try {
        	
        	  SAXParserFactory spf = SAXParserFactory.newInstance();
              SAXParser sp = spf.newSAXParser();
              XMLReader xr = sp.getXMLReader();
              DefaultHandler handler = new DefaultHandler();
              xr.setContentHandler(handler);
              xr.parse(new InputSource(url.openStream()));
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return resp;
	}
			
	
	
}
