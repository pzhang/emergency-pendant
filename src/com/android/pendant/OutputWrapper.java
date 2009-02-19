package com.android.pendant;

public class OutputWrapper {
	private String[] config;
	public OutputWrapper(){
		retrieveConfig();
	}
	private void retrieveConfig(){
		config = new String[0];
	}
	public void transmit(String content){
		
	}
	public void notifyUser(){
		
	}
}
