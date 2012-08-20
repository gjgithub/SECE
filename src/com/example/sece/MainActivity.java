package com.example.sece;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	public Button connectButton, webButton, objectNamesButton;
	public EditText urlText;
	public TextView webpageDisplay;
	public WebpageDownload myWebpage;
	public JSONParser myJSONParser;
	public JSONObject jsonObject = null;
	public final static String ARRAYNAME="", ID="id", NAME = "name", TYPE="type", URL = "url";
	public final static String LOCATION = "location", LOCATION_LATITUDE="latitude", LOCATION_LONGITUDE = "longitude";
    JSONArray jsonArray=null;
    public HashMap<String, String> smartObject;
    public Iterator iterator;
    public String id, name, type, url;
	double location_longitude, location_latitude;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondscreen);
        connectButton = (Button) findViewById(R.id.connectButton);
    	connectButton.setOnClickListener(this);
    	webButton = (Button) findViewById(R.id.webButton);
    	webButton.setOnClickListener(this);
    	objectNamesButton = (Button) findViewById(R.id.readObjectNames);
    	objectNamesButton.setOnClickListener(this);
    	urlText=(EditText) findViewById(R.id.urlText);
    	webpageDisplay = (TextView) findViewById(R.id.webpageDisplay);
    	myWebpage = new WebpageDownload();
    	myJSONParser = new JSONParser();
    	smartObject = new HashMap<String, String>();

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sece, menu);
        
        
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.reset:
			SharedPreferences myReset = getSharedPreferences("creds.txt",0);
			SharedPreferences.Editor myResetEditor = myReset.edit();
			myResetEditor.clear().commit();
			Intent resetIntent = new Intent(this, SECEActivity.class);
			startActivity(resetIntent);
		}
    	
    	return true;
    
    }

	@Override
	public void onClick(View v) {
		String stringUrl = null;
		if (v == connectButton){
			
			
			try {stringUrl = "http://"+urlText.getText().toString();
			
			}
			finally {stringUrl = "https://" + urlText.getText().toString();
				
			}
			ConnectivityManager myConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        	NetworkInfo myNetworkInfo = myConnectivityManager.getActiveNetworkInfo();
        	if (myNetworkInfo!=null && myNetworkInfo.isConnected()){
        		try {
					webpageDisplay.setText(myWebpage.getUrlContent(stringUrl));
				} catch (IOException e) {
					e.printStackTrace();
				}        	
        	}
        	else{
        		webpageDisplay.setText("Are u connected?");
        	}
        	
		}
		else if (v == webButton){
			Intent webIntent = new Intent(this, WebPage.class);
			startActivity(webIntent);
		}
		else if (v == objectNamesButton){
			jsonArray = myJSONParser.getMyJSONArray();
			try {
				for (int i=0;i<jsonArray.length(); i++){
					JSONObject jsonObject2 = jsonArray.getJSONObject(i);
					id = jsonObject2.getString(ID);
					name = jsonObject2.getString(NAME);
					type = jsonObject2.getString(TYPE);
					url = jsonObject2.getString(URL);
					
					JSONObject location = jsonObject2.getJSONObject(LOCATION);
					location_latitude = location.getDouble(LOCATION_LATITUDE);
					location_longitude = location.getDouble(LOCATION_LONGITUDE);
					
					if (type.equals("sensor")){
						new SECESensor(id, name, url, location_latitude, location_longitude);
						System.out.println("Sensor created.");
					}
					else if (type.equals("actuator")){
						new SECEActuator(id, name, url, location_latitude, location_longitude);
					System.out.println("Actuator created.");
					}
				}
			}
			catch (JSONException e){
				e.printStackTrace();
			}

		}
	} 
}

