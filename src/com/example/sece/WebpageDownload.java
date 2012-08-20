package com.example.sece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebpageDownload {

	public URL url, url2;
	public String result;

	
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
			System.out.println("Response code: " +ResponseCode);
			myInputStream = myConnection.getInputStream();
			String webpageString = inputStreamToString(myInputStream);
			return webpageString;
		}
		finally {
			if (myInputStream !=null) {
				myInputStream.close();
			}
	}	
	}
	public String inputStreamToString(InputStream inputStream) throws IOException, UnsupportedEncodingException {
		BufferedReader reader = null;
		reader =  new BufferedReader(new InputStreamReader(inputStream));
		String content = "";
		String c = "";
		while((c = reader.readLine()) != null) { 
			content+=c;
			} 
		reader.close();
		return content;
	}
	public String getObjectNames(String myUrl) throws IOException {
		InputStream myInputStream = null;
		try {
			url2 = new URL(myUrl);
			HttpURLConnection myConnection = (HttpURLConnection) url2.openConnection();
			myConnection.setReadTimeout(10000);
			myConnection.setConnectTimeout(10000);
			myConnection.setRequestMethod("GET");
			myConnection.setDoInput(true);
			myConnection.connect();
			int ResponseCode = myConnection.getResponseCode();
			System.out.println("Response code: " +ResponseCode);
			myInputStream = myConnection.getInputStream();
			String webpageString = objectNamestoString(myInputStream);
			return webpageString;
		}
		finally {
			if (myInputStream !=null) {
				myInputStream.close();
			}
	}	
	}
	public String objectNamestoString(InputStream inputStream) throws IOException, UnsupportedEncodingException {
		BufferedReader reader = null;
		reader =  new BufferedReader(new InputStreamReader(inputStream));
		String content = "";
		String s = "";
		int c;
		while((s = reader.readLine()) != null) { 
			if ((s.indexOf("name"))!=-1){
				System.out.println(s);
					
				
		}
			
			/*if (s.equals("name")){
				content+=s;
				System.out.println(content);
			}*/
		
		}
		reader.close();
		return content;
	}
}


