package com.example.sece;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebPage extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView myWebView = (WebView) findViewById(R.id.googleweb);
        myWebView.getSettings().setJavaScriptEnabled(true);
     myWebView.loadUrl("http://sece.cs.columbia.edu/static/smob.json");
	}
}
