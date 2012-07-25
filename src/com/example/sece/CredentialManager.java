package com.example.sece;

public class CredentialManager {
public String username, password;
	
	public CredentialManager(){
		
	}



	public String storeCredentials(String username, String password){
	this.username = username;
	this.password = password;;
	return username+password;
	}
	
	/**
	 * public String getCredentials(String enterUsername, String enterPassword){
	 * 	if (enterUsername == this.username & enterPassword == this.password){........}
	 * else{.......}
	 * 
	 * }
	 */
}
