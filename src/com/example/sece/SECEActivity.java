package com.example.sece;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;



public class SECEActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sece);
        LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener locListener = new MyLocationListener();
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);      
        
    }

    public class MyLocationListener implements LocationListener {
    	public void onLocationChanged(Location loc)
    	{
    		loc.getLatitude();
    		loc.getLongitude();
    		String dispLat = "Latitude = " + loc.getLatitude();
    		String dispLong = "Longitude = "+ loc.getLongitude();
    		TextView latitude = (TextView) findViewById(R.id.latitude) ;
    		TextView longitude = (TextView)findViewById(R.id.longitude);
    		latitude.setText(dispLat);
    		longitude.setText(dispLong);
    		String Locat = "Latitude = "+ loc.getLatitude() + "Longitude "+loc.getLongitude();
    		Toast.makeText(getApplicationContext(), Locat, Toast.LENGTH_LONG).show();
    		
    	}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "GPS DISABLED", Toast.LENGTH_LONG).show();
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "GPS ENABLED", Toast.LENGTH_LONG).show();
		}
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sece, menu);
        return true;
    }
    

    
}
