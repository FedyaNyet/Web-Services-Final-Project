package com.fyodorwolf.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "outlook")
public class ForecastDay {

	public enum Day {
		Monday,
		Tuesday,
		Wednesday,
		Thursday,
		Friday,
		Saturday,
		Sunday
	};
	
	public int highTemp;
	public int lowTemp;
	public String condition = "";
	public Day day;
	
	public ForecastDay(){}
	
	public void setDay(String day){
		switch(day){
			case "Mon":
			case "Monday":
				this.day = Day.Monday;
				break;
			case "Tue":
			case "Tuesday":
				this.day = Day.Tuesday;
				break;
			case "Wed":
			case "Wednesday":
				this.day = Day.Wednesday;
				break;
			case "Thu":
			case "Thursday":
				this.day = Day.Thursday;
				break;
			case "Fri":
			case "Friday":
				this.day = Day.Friday;
				break;
			case "Sat":
			case "Saturday":
				this.day = Day.Saturday;
				break;
			case "Sun":
			case "Sunday":
				this.day = Day.Sunday;
				break;
			}
	}
	
	public String toString(){
		return "("+day + ", "+lowTemp +", "+highTemp+", "+condition+")";
	}
}