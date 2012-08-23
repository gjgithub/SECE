package com.example.sece;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class WebPage extends Activity implements OnClickListener {
	public Button back;
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView myWebView = (WebView) findViewById(R.id.googleweb);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("http://sece.cs.columbia.edu/static/smob.json");
        back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		if (v== back) {
			Intent backIntent  = new Intent(this, MainActivity.class);
			startActivity(backIntent);
		}
		
	}
}
