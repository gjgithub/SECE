package com.example.sece;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard.Row;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends Activity{
	public Button refresh;
	public final String URL = "objecturl", TYPE="objecttype";
	public ListView mainListView;
	public ArrayAdapter<String> listAdapter;
	public String[] stringurl = new String[SECEArrayList.objectList.size()];
	public String urls = null;
	public String[] type= new String[SECEArrayList.objectList.size()];
	public String[] names = new String[SECEArrayList.objectList.size()];
	double [] latitude, longitude = new double[SECEArrayList.objectList.size()];
	ArrayList<String> namesList;
	ArrayList<Row> rows = new ArrayList<Row>();
	public Iterator iterator;
	String s[] = null;
	public WebpageDownload download = new WebpageDownload();
	  
	  /* Called when the activity is first created. */
	
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_listview2);
	    
	    // Find the ListView resource. 
	    mainListView = (ListView) findViewById(R.id.myList);

	    for (int i=0;i<SECEArrayList.objectList.size();i++){
	    	type[i] = SECEArrayList.objectList.get(i).type;
	    	names[i] = SECEArrayList.objectList.get(i).name + ": "+ type[i];
	    	stringurl[i] = SECEArrayList.objectList.get(i).url;
	    	System.out.println("Name: " + names[i] + ", Type: " + type[i]);
	    	System.out.println(stringurl[i]);
	    	
	    	
	    	namesList = new ArrayList<String>();
	    	namesList.addAll(Arrays.asList(names));
	    } 
	    	
	    refresh = (Button) findViewById(R.id.refresh);
	    refresh.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		        LocationListener locListener = new MyLocationListener();
		        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 10, locListener);      
		        Toast.makeText(getApplicationContext(), "GETTING LOCATION", Toast.LENGTH_SHORT).show();
		        
		        
			}
	    	
	    });
	    
	    // Create ArrayAdapter.
	    listAdapter = new ArrayAdapter<String>(this, R.layout.activity_simplerow, R.id.objectNames, namesList);
	    
    	
	    //iterator = SECEArrayList.objectList.iterator();
	    
	    mainListView.setAdapter(listAdapter);      
	 
	    mainListView.setOnItemClickListener(new OnItemClickListener (){
			
	    	
	    	@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				String url2;
				urls = SECEArrayList.objectList.get(position).url;
				Log.d("URL String", urls);
				
				
				/*Intent newIntent = new Intent(getApplicationContext(), ListViewOnClick.class);
				newIntent.putExtra(URL, urls);
				newIntent.putExtra(TYPE, type[position]);
				System.out.println(position);
				startActivity(newIntent);	*/			
				String[] s = null;
				try {
					s = download.getUrlContentWithStatus(urls);
					if (s[0].equalsIgnoreCase("302")) {
						System.out.println("Client Error.. try HTTPS");
						url2 = urls.replace("http", "https");
						s = download.getUrlContentWithStatus(url2);
						if (s[0].equalsIgnoreCase("302")) {
							System.out.println("Client Error in HTTPS.. BOOM .. debug !");
						}
					}
				}
				catch (IOException e) {
					System.out.println("Unable to connect to the server..");
					e.printStackTrace();
				}
				
				int pressed = 0;
				pressed+=1;
				if (SECEArrayList.objectList.get(position).type.equalsIgnoreCase("actuator")){
					if (pressed % 2 !=0){
						Toast.makeText(getApplicationContext(), "ON", Toast.LENGTH_SHORT).show();
					}
					else {
						Toast.makeText(getApplicationContext(), "OFF", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					Toast.makeText(getApplicationContext(), s[1], Toast.LENGTH_SHORT).show();
				}
			}
	    	
	    	
	    	
	    });
	    
	  }

	
	  
	  
}
