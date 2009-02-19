package com.android.pendant;

public class LogicHandler {
	//General 
    private String[] config;
	public LogicHandler() {
		retrieveConfig();// TODO Auto-generated constructor stub
	}
	private void retrieveConfig(){
		config = new String[0];
	}
	//Basic accelerometer logic unit
	public boolean XmLogic(float accel){
		return false;
	}
	//converts the coordinates into something sendable by
	//voice text
	public String GPSConverter(int[] coord){
		return " ";
	}
	//Logic for sending signal if when tactile signal is retrieved
	public boolean tacConverter(){
		return false;
	}

}
