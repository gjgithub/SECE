package com.example.sece;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class JSONParser {
	JSONArray jArray;
	public JSONArray getMyJSONArray(String jsonString){
		try {
            jArray = new JSONArray(jsonString);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        } 
        return jArray;
	
	}
	
	
}
