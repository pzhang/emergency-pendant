package com.android.pendant;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.location.*;
import android.content.Context;
import android.telephony.gsm.*;
public class OutputWrapper extends Service {
    public void onCreate(){
    	super.onCreate();
    	_startService();
    	
    }
    public void onDestroy(){
    	super.onDestroy();

    	  _shutdownService();

    }
    private void _startService(){

    }

    private void _shutdownService(){
    	
    }
    
    public IBinder onBind(Intent i){
    	return mBinder;
    }
    private final OutputWrapperInterface.Stub mBinder = new OutputWrapperInterface.Stub(){
    	public void transmit(String msg){
    		sendMsg(msg);
    	}
    };
    
	private String[] config;
	private void sendMsg(String msg){
		LocationManager lm =
			(LocationManager) getSystemService(Context.LOCATION_SERVICE);
		SmsManager sm = SmsManager.getDefault();
		sm.sendTextMessage("18005555555", null, lm.getLastKnownLocation("gps").toString(),
		                   null, null);
	}
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
