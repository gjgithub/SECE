package com.example.sece;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocationListener implements LocationListener {
	public void onLocationChanged(Location loc)
	{
		double latitude = loc.getLatitude();
		double longitude = loc.getLongitude();
	}

	@Override
	public void onProviderDisabled(String provider) {
		String gpsDisabled = "GPS Disabled";
	}

	@Override
	public void onProviderEnabled(String provider) {
		String gpsEnabled = "GPS Enabled";
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}
}