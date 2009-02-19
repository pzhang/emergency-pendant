package com.android.pendant;

public class InputWrapper {
    private String[] config;
	public InputWrapper() {
	   retrieveConfig();// TODO Auto-generated constructor stub
	}
	//Accelerometer wrapper stub
	public float XmHandler(){
		return 0.0;
	}
	//GPS Handler Stub
	public int[] GPSHandler(){
		int[] s;
		s = new int[0];
		return s;
	}
	//Config retrieval
	private void retrieveConfig(){
		config = new String[0];
	}
	//manual tactile signal retrieval
	public boolean tacRetrieve(){
		return false;
	}

}
