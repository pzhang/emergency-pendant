package com.android.pendant;
import android.app.Service;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.IBinder;
import android.location.*;
import android.content.Context;
import android.telephony.gsm.*;
import android.util.Log;
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
    		Log.i("OutputWrapper","In transmit");
    		sendMsg(msg);
    	}
    	public void notification(){
    		notifyUser();
    	}
    };
    
	private String[] config;
	private void sendMsg(String msg){
		Log.i("OutputWrapper", "Sending Msg : " + msg);
		//SmsManager sm = SmsManager.getDefault();
		//sm.sendTextMessage("5556", null, msg,
		 //                  null, null);

	}
	public OutputWrapper(){
		retrieveConfig();
		
	}
	private void retrieveConfig(){
		config = new String[0];
	}
	public void notifyUser(){

            NotificationManager manager = (NotificationManager)
getSystemService(Context.NOTIFICATION_SERVICE);

            Notification notification = new Notification();
            notification.ledOnMS = 1000;
            notification.ledOffMS = 500;
            notification.defaults = Notification.DEFAULT_SOUND;
            notification.vibrate = new long[] {200, 300};
            Log.i("OutputWrapper", "Notification: " + 
            	   notification.toString());
            manager.notify(1, notification);
	}
}
