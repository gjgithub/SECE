package com.example.sece;

public class SECESmartObject {
	String id, name, type, url;
	double location_latitude, location_longitude;
	public SECESmartObject(String id, String name, String type, String url, double location_latitude2, double location_longitude2){
		this.id = id;
		this.name = name;
		this.type = type;
		this.url = url;
		this.location_latitude = location_latitude2;
		this.location_longitude = location_longitude2;
	}
	boolean withinDistance(){
		if (Math.sqrt(location_latitude*location_latitude+location_longitude*location_longitude)<10){
			return true;
		}
		return false;
	}
}
