package com.example.sece;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SECEActivity extends Activity implements OnClickListener {
	public Button login;
	public EditText usernameText, passwordText;
	public SharedPreferences credentials;
	public SharedPreferences.Editor credentialEditor;
    public String creds, SECEusername, SECEpassword, myUsername, myPassword;    
    public CredentialManager credentialManager;  

	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sece);
    	login = (Button) findViewById(R.id.login);
    	login.setOnClickListener(this);
    	usernameText = (EditText) findViewById(R.id.usernameText);
    	passwordText = (EditText) findViewById(R.id.passwordText);
    	credentials = getSharedPreferences("creds.txt", 0);
    	credentialManager = new CredentialManager(getSharedPreferences("creds.txt",0));
    	boolean credResult = credentialManager.areThereCredentials(credentials);
    	if (credResult == true){
    		Intent proceedIntent = new Intent(this, MainActivity.class);
    		startActivity(proceedIntent);
    	}
	}

    public class MyLocationListener implements LocationListener {
    	public void onLocationChanged(Location loc)
    	{
    		//loc.getLatitude();
    		//loc.getLongitude();
    		String dispLat = "Latitude = " + loc.getLatitude();
    		String dispLong = "Longitude = "+ loc.getLongitude();
    		//TextView latitude = (TextView) findViewById(R.id.latitude) ;
    		//TextView longitude = (TextView)findViewById(R.id.longitude);
    		//latitude.setText(dispLat);
    		//longitude.setText(dispLong);
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
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.reset:
			
			credentialManager.resetCredentials(SECEusername, SECEpassword);
			Intent resetIntent = new Intent(this, SECEActivity.class);
			startActivity(resetIntent);
		}
    	
    	return true;
    
    }
    
    @Override
	public void onClick(View v) {
    	if (v == login) {
			
	        myUsername = usernameText.getText().toString();
	    	myPassword = passwordText.getText().toString();
	    	System.out.println("Username is '" + myUsername + "'");
	    	System.out.println("Password is '" + myPassword + "'");
	    	if (!credentialManager.noBlanks(myUsername, myPassword)){
	    		System.out.println("Username or password is blank");
	    		Toast.makeText(getApplicationContext(), "Enter credentials please.", Toast.LENGTH_LONG).show();		
	    	}
	    	else{
	    		System.out.println("Both username and password seem correct.");
	    		credentialManager.storeCredentials(myUsername, myPassword);
	    		//LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		        //LocationListener locListener = new MyLocationListener();
		        //locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);      
		        Toast.makeText(getApplicationContext(), "GETTING LOCATION", Toast.LENGTH_LONG).show();
		        Intent proceedIntent = new Intent(this, MainActivity.class);
	    		startActivity(proceedIntent);
	    	}
		}
    }
}


    
