package com.android.pendant;
import android.content.ServiceConnection;
import android.util.Log;
import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.content.ComponentName;
import android.os.*;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class EmergencyPendant extends Activity {
    /** Called when the activity is first created. */
	InputWrapperInterface iService = null;
	OutputWrapperInterface oService = null;
	InputWrapperDumb iwrapper;
	Timer time = new Timer();
	float[] xcel;
	boolean tac;
	float[] xy;
	TextView myView = null;
	Context local = this;
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pendant);
        myView = (TextView) findViewById(R.id.text);
        Intent svc = new Intent(this, InputWrapper.class);
        Intent osvc = new Intent(this, OutputWrapper.class);
        bindService(svc, mConnection, Context.BIND_AUTO_CREATE);
        bindService(osvc, oConnection, Context.BIND_AUTO_CREATE);
        runMain();    
    }
    private void runMain(){

        time.schedule(new TimerTask() {
            public void run() {
                mainRunner();
              }
            }, 0, 1000);
    }
    private void mainRunner(){
    	Log.i("MainRunner", "Running");
        if (iService == null) {
        	Log.i("iService", "NULL");
        }
        if (oService == null) {
        	Log.i("oService", "NULL");
        }
    	try {
    	xy = iService.location();
    	xcel = iService.xcel();
    	tac = iService.tacResponse();

    	Log.i("MainRunner",
    	      "Location " + Float.toString(xy[0]) + 
    	      " " + Float.toString(xy[1]));
    	
        Log.i("MainRunner", "Tac: " + Boolean.toString(tac));
    	Log.i("MainRunner", "Acceleration: x: " + Float.toString(xcel[0])
				+ " y: " + Float.toString(xcel[1])
				+ " z: " + Float.toString(xcel[2]));
        msgGate();
    	}
    	catch(RemoteException ex){
    		Log.d("MainRunner", "Service exitted unexpectedly");
    	}
       
    	
    		
    	
    }
    private void msgGate(){
    
    	if (tac || (xcel[0] >= 9.8 || 
    			    xcel[1] >= 9.8 ||
    			    xcel[2] >= 9.8)){
    		try{
    		String message;
    		message = "Lat: " + Float.toString(xy[0]) +
    		          "Long: " + Float.toString(xy[1]);
    		Log.i("MainRunner", "Sending msg");
    		oService.transmit(message);
    		}
    		catch (RemoteException ex){
    			
    		}
    	}
    }
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className,
                IBinder service1) {
        	iService = InputWrapperInterface.Stub.asInterface(service1);
        }
        public void onServiceDisconnected(ComponentName className) {
            iService = null;
        }
    };
    private ServiceConnection oConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className,
                IBinder service2) {
        	oService = OutputWrapperInterface.Stub.asInterface(service2);
        }
        public void onServiceDisconnected(ComponentName className) {
            oService = null;
        }
    };
}