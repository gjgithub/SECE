package com.example.sece;

import java.util.*;


public class SECELinkedList {
	LinkedList<SECESensor> sensorList = new LinkedList<SECESensor>();
	LinkedList<SECEActuator> actuatorList = new LinkedList<SECEActuator>();
	
	Iterator iterator;
	public SECELinkedList(){
		
		
		
	}
	LinkedList<SECESensor> addtoSensorList(SECESensor sensor){
		sensorList.add(sensor);
		return (LinkedList<SECESensor>) sensorList;
	}
	SECESensor getSensorFromList(SECESensor sensor){
		iterator = sensorList.iterator();
		for (int i=0;i<sensorList.size(); i++){
			if (sensorList.get(i)== sensor){
				return sensorList.get(i);
			}
					
		}
		return null;
		
	}
	LinkedList<SECEActuator> addtoActuatorList(SECEActuator actuator){
		actuatorList.add(actuator);
		return actuatorList;
	}
	SECEActuator getActuatorFromList(SECEActuator actuator){
		iterator = actuatorList.iterator();
		for (int i=0;i<actuatorList.size(); i++){
			if (actuatorList.get(i)== actuator){
				return actuatorList.get(i);
			}
					
		}
		return null;
		
	}
}