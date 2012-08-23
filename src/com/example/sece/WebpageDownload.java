package com.example.sece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;

public class WebpageDownload {

	public URL url, url2;
	public String result;

	public String getUrlContent(String myUrl) throws IOException {
		InputStream myInputStream = null;
		try {
			url = new URL(myUrl);
			HttpURLConnection myConnection = (HttpURLConnection) url
					.openConnection();
			myConnection.setReadTimeout(10000);
			myConnection.setConnectTimeout(10000);
			myConnection.setRequestMethod("GET");
			myConnection.setDoInput(true);
			myConnection.connect();

			Integer responseCode = myConnection.getResponseCode();
			System.out.println("Response code: " + responseCode);
			myInputStream = myConnection.getInputStream();
			String webpageString = inputStreamToString(myInputStream);
			return webpageString;
		} finally {
			if (myInputStream != null) {
				myInputStream.close();
			}
		}
	}

	public String[] getUrlContentWithStatus(String myUrl) throws IOException {
		InputStream myInputStream = null;
		try {
			url = new URL(myUrl);
			HttpURLConnection myConnection = (HttpURLConnection) url
					.openConnection();
			try {
				TrustModifier.relaxHostChecking(myConnection);
			} catch (KeyManagementException e) {
				
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
			} catch (KeyStoreException e) {
				
				e.printStackTrace();
			}
			myConnection.setReadTimeout(10000);
			myConnection.setConnectTimeout(10000);
			myConnection.setRequestMethod("GET");
			myConnection.setDoInput(true);
			myConnection.connect();

			Integer responseCode = myConnection.getResponseCode();
			System.out.println("Response code: " + responseCode);
			
			myInputStream = myConnection.getInputStream();
			String webpageString = inputStreamToString(myInputStream);
			String[] sArr = new String[2];
			sArr[0] = responseCode.toString();
			sArr[1] = webpageString;
			System.out.println("Web page Content: " + webpageString);
			return sArr;
		} finally {
			if (myInputStream != null) {
				myInputStream.close();
			}
		}
	}

	public String inputStreamToString(InputStream inputStream)
			throws IOException, UnsupportedEncodingException {
		BufferedReader reader = null;
		reader = new BufferedReader(new InputStreamReader(inputStream));
		String content = "";
		String c = "";
		while ((c = reader.readLine()) != null) {
			content += c;
		}
		reader.close();
		return content;
	}

	public String getObjectNames(String myUrl) throws IOException {
		InputStream myInputStream = null;
		try {
			url2 = new URL(myUrl);
			HttpURLConnection myConnection = (HttpURLConnection) url2
					.openConnection();
			myConnection.setReadTimeout(10000);
			myConnection.setConnectTimeout(10000);
			myConnection.setRequestMethod("GET");
			myConnection.setDoInput(true);
			myConnection.connect();
			int ResponseCode = myConnection.getResponseCode();
			System.out.println("Response code: " + ResponseCode);
			myInputStream = myConnection.getInputStream();
			String webpageString = objectNamestoString(myInputStream);
			return webpageString;
		} finally {
			if (myInputStream != null) {
				myInputStream.close();
			}
		}
	}

	public String objectNamestoString(InputStream inputStream)
			throws IOException, UnsupportedEncodingException {
		BufferedReader reader = null;
		reader = new BufferedReader(new InputStreamReader(inputStream));
		String content = "";
		String s = "";
		while ((s = reader.readLine()) != null) {
			if ((s.indexOf("name")) != -1) {
				System.out.println(s);
			}
		}
		reader.close();
		return content;
	}
}
