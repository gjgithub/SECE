package com.example.sece;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewOnClick extends Activity {
	public final String LURL = "objecturl", TYPE= "objecttype";
	public TextView nameDisplay;
	public WebpageDownload downloader = new WebpageDownload();
	public String url, type, url2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_on_click);
        Intent in =  getIntent();
        url = in.getStringExtra(LURL);
        nameDisplay = (TextView) findViewById(R.id.objectType);
		
		System.out.println(url);
		type = in.getStringExtra(TYPE);
        System.out.println(type);
        if (type.equalsIgnoreCase("actuator")){
        	Toast.makeText(getApplicationContext(), type, Toast.LENGTH_SHORT).show();
        }
            
		try {
			String[] s = downloader.getUrlContentWithStatus(url);
			nameDisplay.setText(s[1]);
			if (s[0].equalsIgnoreCase("302")) {
				System.out.println("Client Error.. try HTTPS");
				url2 = url.replace("http", "https");
				s = downloader.getUrlContentWithStatus(url2);
				nameDisplay.setText(s[1]);
				if (s[0].equalsIgnoreCase("302")) {
					System.out.println("Client Error in HTTPS.. BOOM .. debug !");
				}
			}
		}
		catch (IOException e) {
			System.out.println("Unable to connect to the server..");
			e.printStackTrace();
		}
        
        
        
        
        
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_view_on_click, menu);
        return true;
    }

    
}
