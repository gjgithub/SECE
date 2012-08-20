package com.example.sece;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class JSONParser {
	JSONArray jArray;
	String jsonString = "https://sece.cs.columbia.edu/static/smob.json";
	WebpageDownload myWebpage2 = new WebpageDownload();
	public JSONArray getMyJSONArray(){
	
		try {
            jArray = new JSONArray(myWebpage2.getUrlContent(jsonString));
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        } catch (IOException e) {
			e.printStackTrace();
        }
        return jArray;
	
	}
	
	
}
