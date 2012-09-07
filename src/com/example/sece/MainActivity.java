package com.example.sece;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	public Button connectButton, webButton, objectNamesButton;
	public EditText urlText;
	public TextView webpageDisplay;
	public WebpageDownload myWebpage, myWebpage2;
	public JSONParser myJSONParser;
	public JSONObject jsonObject = null;
	public final static String ARRAYNAME = "", ID = "id", NAME = "name",
			TYPE = "type", URL = "url";
	public final static String LOCATION = "location",
			LOCATION_LATITUDE = "latitude", LOCATION_LONGITUDE = "longitude";
	JSONArray jsonArray = null;
	public HashMap<String, String> smartObject;
	public Iterator iterator;
	public String id, name, type, url;
	double location_longitude, location_latitude;
	public Button backButton;
	public SECEArrayList seceArrayList = new SECEArrayList();
	public ListView mainListView;
	public ArrayAdapter<String> listAdapter;
	

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
		urlText = (EditText) findViewById(R.id.urlText);
		myWebpage = new WebpageDownload();
		myWebpage2 = new WebpageDownload();
		myJSONParser = new JSONParser();
		smartObject = new HashMap<String, String>();
		backButton = (Button) findViewById(R.id.backButtonMain);
		backButton.setOnClickListener(this);
	 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_sece, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.reset:
			SharedPreferences myReset = getSharedPreferences("creds.txt", 0);
			SharedPreferences.Editor myResetEditor = myReset.edit();
			myResetEditor.clear().commit();
			Intent resetIntent = new Intent(this, SECEActivity.class);
			startActivity(resetIntent);
		}
		return true;

	}

	@Override
	public void onBackPressed() {
	}

	@Override
	public void onClick(View v) {
		/**/
		String stringUrl = null;

		if (v == connectButton) {

			try {
				stringUrl = "http://" + urlText.getText().toString();

			} finally {
				stringUrl = "https://" + urlText.getText().toString();

			}
			ConnectivityManager myConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo myNetworkInfo = myConnectivityManager
					.getActiveNetworkInfo();
			if (myNetworkInfo != null && myNetworkInfo.isConnected()) {
				try {
					String urlContent = myWebpage.getUrlContent(stringUrl);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				webpageDisplay.setText("Are u connected?");
			}

		} 
		else if (v == webButton) {
			Intent webIntent = new Intent(this, WebPage.class);
			startActivity(webIntent);
		} 
		else if (v == objectNamesButton) {
			stringUrl = "http://" + urlText.getText().toString();
			try {
				String[] s = myWebpage.getUrlContentWithStatus(stringUrl);
				if (s[0].equalsIgnoreCase("302")) {
					System.out.println("Client Error.. try HTTPS");
					stringUrl = "https://" + urlText.getText().toString();
					s = myWebpage.getUrlContentWithStatus(stringUrl);
					if (s[0].equalsIgnoreCase("302")) {
						System.out.println("Client Error in HTTPS.. BOOM .. debug !");
					}
				}	
				jsonArray = myJSONParser.getMyJSONArray(s[1]);
				seceArrayList.removeFromList();
				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						JSONObject jsonObject2 = jsonArray.getJSONObject(i);
						try {
							id = jsonObject2.getString(ID);
						} catch (Exception e) {
							e.printStackTrace();
							id = null;
						}
						try {
							name = jsonObject2.getString(NAME);
						} catch (Exception e) {
							e.printStackTrace();
							name = null;
						}

						
						try {
							url = jsonObject2.getString(URL);
						} catch (Exception e) {
							e.printStackTrace();
							url = null;
						}
						try {
							JSONObject location = jsonObject2
									.getJSONObject(LOCATION);
							location_latitude = location
									.getDouble(LOCATION_LATITUDE);
							location_longitude = location
									.getDouble(LOCATION_LONGITUDE);
						} catch (Exception e) {
							e.printStackTrace();
							location_latitude = 0;
							location_longitude = 0;
						}
						try {
							type = jsonObject2.getString(TYPE);
							if (type.equals("actuator")) {
								 seceArrayList.addtoObjectList(new SECEActuator(id, name, type, url, location_latitude, location_longitude));
								 
							} 
							else if (type.equals("sensor")) {
								 seceArrayList.addtoObjectList(new SECESensor(id, name, type, url, location_latitude, location_longitude));
							}
						} 
						catch (Exception e) {
							e.printStackTrace();
							type = null;
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				Intent listIntent = new Intent(this, ListViewActivity.class);
				startActivity(listIntent);
			} catch (IOException e) {
				System.out.println("Unable to connect to the server..");
				e.printStackTrace();
			}

		}
		
		 if (v==backButton){ 
			   
			 
		 }
		 
	}
}
