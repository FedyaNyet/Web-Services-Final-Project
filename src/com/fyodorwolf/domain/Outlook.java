package com.fyodorwolf.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "outlook")
public class Outlook {
	
	public int highTemp;
	public int lowTemp;
	public String condition = "";
	public String day;
	
	public Outlook(){}
	public Outlook(String day, int highTemp, int lowTemp, String condition ){
		this.day = day;
		this.highTemp = highTemp;
		this.lowTemp = lowTemp;
		this.condition = condition;
	}
	
	public String toString(){
		return "("+day + ", "+lowTemp +", "+highTemp+", "+condition+")";
	}
}