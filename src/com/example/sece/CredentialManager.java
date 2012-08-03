package com.example.sece;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;


public class CredentialManager {
	public String myUsername, myPassword, SECEUsername, SECEPassword;
	public SharedPreferences mySharedPreferences;
	public SharedPreferences.Editor mySPEditor;
	public String username;
	public String password;
	
	
	public CredentialManager(SharedPreferences preferences) {	
		System.out.println("I am in credential manager ");
		this.mySharedPreferences = preferences;
	}
	
	public Boolean areThereCredentials(SharedPreferences sharedpreferences){
		
		String username = sharedpreferences.getString(SECEUsername, "");
		String password = sharedpreferences.getString(SECEPassword, "");
		if(!username.equals("") && !password.equals("")){
			return true;
		}
		else{return false;}
	
		
	}
		
	
	public Boolean noBlanks(String username, String password){
	if (username.equals("") || password.equals("")){
		return false;
	}
 
	else{return true;}
	}
	public void resetCredentials(String username, String password){
		mySPEditor = mySharedPreferences.edit();
		mySPEditor.putString(SECEUsername, username);
		mySPEditor.putString(SECEPassword, password);
		mySPEditor.clear().commit();
		;
	}
	public void storeCredentials(String username, String password){
		
		mySPEditor = mySharedPreferences.edit();
		mySPEditor.putString(SECEUsername, username);
		mySPEditor.putString(SECEPassword, password);
		mySPEditor.commit();
	}
	
	
	/**
	 * public String getCredentials(String enterUsername, String enterPassword){
	 * 	if (enterUsername == this.username & enterPassword == this.password){........}
	 * else{.......}
	 * 
	 * }
	 */
}