package com.android.pendant;
import android.widget.Button;
import android.view.View.*;
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
import android.view.View;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class EmergencyPendant extends Activity {
    /** Called when the activity is first created. */
	InputWrapperInterface iService = null;
	OutputWrapperInterface oService = null;
	Timer time;
	float[] xcel;
	boolean tac;
	float[] xy;
	float INERTIAL = 0;
	int count = 0;
	long systime = 0;
	long prev_time = 0;
	float velocity = 0;
	TextView myView = null;
	Context local = this;
	Button send;
	Button cancel;
	String message = "Lat: Na, Long: Na";
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pendant);
        myView = (TextView) findViewById(R.id.text);
        //send = (Button) findViewById(R.id.send);
        //cancel = (Button) findViewById(R.id.cancel);
       /* send.setVisibility(TextView.INVISIBLE);
        send.setEnabled(false);
        cancel.setVisibility(TextView.INVISIBLE);
        cancel.setEnabled(false);*/
        Intent svc = new Intent(this, InputWrapper.class);
        Intent osvc = new Intent(this, OutputWrapper.class);
        bindService(svc, mConnection, Context.BIND_AUTO_CREATE);
        bindService(osvc, oConnection, Context.BIND_AUTO_CREATE);
    /*    send.setOnClickListener(new OnClickListener() {
      	  @Override
      	  public void onClick(View v) {
      		  try{
      			Log.i("MainRunner", "Sending msg");
      		    oService.transmit(message);
      		  }
      	      catch (RemoteException ex){
    		      }
      	  }
      	});
        cancel.setOnClickListener(new OnClickListener() {
    	  @Override
    	  public void onClick(View v) {
    		  runMain();
    	  }
    	});*/
        runMain();    
    
    }

    private void runMain(){
    	velocity = 0;
    	prev_time = 0;
    	systime = 0;
    	count = 0;
    	time = new Timer();
        time.schedule(new TimerTask() {
            public void run() {
                mainRunner();
              }
            }, 0, 100);
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
        prev_time = systime;
        systime = iService.getTime();
        

        Log.i("MainRunner", "Tac: " + Boolean.toString(tac));
    	Log.i("MainRunner", "Acceleration: x: " + Float.toString(xcel[0])
				+ " y: " + Float.toString(xcel[1])
				+ " z: " + Float.toString(xcel[2]));
    	count += 1;
    	INERTIAL = (INERTIAL * (float)(count - 1) + xcel[2]) / ((float)count);
        velCalc();
        msgGate();
    	}
    	catch(RemoteException ex){
    		Log.d("MainRunner", "Service exitted unexpectedly");
    	}
       
    	
    		
    	
    }
    private void velCalc(){
    	float timed;
    	if (systime == 0 || prev_time == 0){
    		timed = 0;
    	}
    	else
    	{
    		timed = (float)(systime - prev_time);
    	}
    	Log.i("MainRunner",
        	      "Location " + Float.toString(xy[0]) + 
        	      " " + Float.toString(xy[1]));
        	Log.i("MainRunner", "timed " + Float.toString(timed));
    	velocity = velocity + ((float)(9.81) * (xcel[2]- (float)1.2) * (timed/(float)1000));
    	Log.i("MainRunner", "Velocity: " + Float.toString(velocity));
    }
    private void msgGate(){
        Log.i("MainRunner", "Testing Threshold");
    	if (tac || velocity < -1){
    		time.cancel();
    		Log.i("MainRunner", "Threshold Exceeded, Fall Detected, Sending User Notification");
    		try{
    		oService.notification();
    		}
    		catch (RemoteException ex) {
    			
    		}
    		Log.i("MainRunner", "User notified, waiting 10 seconds");
            /*send.setVisibility(TextView.VISIBLE);
            send.setEnabled(true);
            cancel.setVisibility(TextView.VISIBLE);
            cancel.setEnabled(true);*/
    		try {
    		Thread.sleep(10000);
    		}
    		catch(InterruptedException ex){
    			
    		}
            Log.i("MainRunner", "10 seconds have elapsed, sending msg");
    		message = "PERSON user distress at : Lat: " + Float.toString(xy[0]) +
            " Long: " + Float.toString(xy[1]);
            try{
            	oService.transmit(message);
            }
            catch (RemoteException ex){
            	Log.i("MainRunner", "Cannot Reach Output");
            }
            Log.i("MainRunner", "Restarting in 10 seconds");
            try {
        		Thread.sleep(10000);
        		}
        		catch(InterruptedException ex){
        			
        		}
            runMain();
    		
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