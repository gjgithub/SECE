package com.example.sece;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
    	credentialManager = new CredentialManager(credentials);
    	boolean credResult = credentialManager.areThereCredentials(credentials);
    	if (credResult == true){
    		System.out.println(credentials);
    		Intent proceedIntent = new Intent(this, MainActivity.class);
    		startActivity(proceedIntent);
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
	    		Toast.makeText(getApplicationContext(), "Enter credentials please.", Toast.LENGTH_SHORT).show();		
	    	}
	    	else{
	    		System.out.println("Both username and password seem correct.");
	    		credentialManager.storeCredentials(myUsername, myPassword);
		        Intent proceedIntent = new Intent(this, MainActivity.class);
	    		startActivity(proceedIntent);
	    	}
		}
    }
}


    
