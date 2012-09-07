package com.example.sece;

import java.util.ArrayList;
import java.util.ArrayList;

public class SECEArrayList {
	public static ArrayList<SECESmartObject> objectList = new ArrayList<SECESmartObject>();
	public SECEArrayList() {
		
	}
	ArrayList<SECESmartObject> addtoObjectList(SECESensor sensor){
		objectList.add(sensor);
		return objectList;
	}
	ArrayList<SECESmartObject> addtoObjectList(SECEActuator actuator){
		objectList.add(actuator);
		return objectList;
	}
	/*void getObjectFromList(){
		
			for (int i=0;i<objectList.size();i++){
				System.out.println(objectList.get(i));
			}

		
	}*/
	ArrayList<SECESmartObject> removeFromList() {
		objectList.removeAll(objectList);
		return objectList;
	}
	
}
