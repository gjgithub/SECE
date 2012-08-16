package com.example.sece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	public Button connectButton, webButton;
	public EditText urlText;
	public TextView webpageDisplay;
	public URL url;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondscreen);
        connectButton = (Button) findViewById(R.id.connectButton);
    	connectButton.setOnClickListener(this);
    	webButton = (Button) findViewById(R.id.webButton);
    	webButton.setOnClickListener(this);
    	urlText=(EditText) findViewById(R.id.urlText);
    	webpageDisplay = (TextView) findViewById(R.id.webpageDisplay);
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
		if (v == connectButton){
			
			String stringUrl = "http://"+urlText.getText().toString();
			ConnectivityManager myConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        	NetworkInfo myNetworkInfo = myConnectivityManager.getActiveNetworkInfo();
        	if (myNetworkInfo!=null && myNetworkInfo.isConnected()){
        		new WebpageDownload().execute(stringUrl);
        	}
        	else{
        		webpageDisplay.setText("Are u connected?");
        	}
        	
		}
		else if (v == webButton){
			Intent webIntent = new Intent(this, WebPage.class);
			startActivity(webIntent);
		}
	}
 
	private class WebpageDownload extends AsyncTask <String, Void, String>{

		@Override
		protected String doInBackground(String... url) {
			try {
				return getUrlContent(url[0]);
			} catch (IOException e) {
				e.printStackTrace();
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}
		protected void onPostExecute(String result) {
	        webpageDisplay.setText(result);
		}
		public String inputToString(InputStream inputStream) throws IOException, UnsupportedEncodingException {
			BufferedReader reader = null;
			reader =  new BufferedReader(new InputStreamReader(inputStream));
			String content = null;
			int c;
			do {
				c = reader.read();
				if (c!=-1){
					content+= (char) c;
				}
			}
			while(c !=-1);
			return content;
		}
		
		public String getUrlContent(String myUrl) throws IOException {
			InputStream myInputStream = null;
			try {
				url = new URL(myUrl);
				HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
				myConnection.setReadTimeout(10000);
				myConnection.setConnectTimeout(10000);
				myConnection.setRequestMethod("GET");
				myConnection.setDoInput(true);
				myConnection.connect();
				int ResponseCode = myConnection.getResponseCode();
				Log.d("HTTP EXAMPLE", "The response is: " + ResponseCode);
				myInputStream = myConnection.getInputStream();
				String webpageString = inputToString(myInputStream);
				return webpageString;
			}
			finally {
				if (myInputStream !=null) {
					myInputStream.close();
				}
		}
		
			
		}

	}

	
}

