package com.android.pendant;
import android.content.ServiceConnection;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.content.ComponentName;
import android.os.*;
import java.util.Timer;
import java.util.TimerTask;
public class EmergencyPendant extends Activity {
    /** Called when the activity is first created. */
	InputWrapperInterface iService = null;
	OutputWrapperInterface oService = null;
	Timer time = new Timer();
	float xcel;
	boolean tac;
	float[] xy;
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent svc = new Intent(this, InputWrapper.class);
        Intent osvc = new Intent(this, OutputWrapper.class);
        bindService(svc, mConnection, Context.BIND_AUTO_CREATE);
        bindService(osvc, oConnection, Context.BIND_AUTO_CREATE);
        time.schedule(new TimerTask() {
            public void run() {
                mainRunner();
              }
            }, 0, 2);
        setContentView(R.layout.main);
    }
    private void mainRunner(){
    	try{
    		xy = iService.location();
    		xcel = iService.xcel();
    		tac = iService.tacResponse();
    		msgGate();
    	}
    	catch (RemoteException ex){
    		
    	}
    }
    private void msgGate(){
    	if (tac && xcel != 0){
    		oService.transmit("boom")
    	}
    }
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className,
                IBinder service) {
        	iService = InputWrapperInterface.Stub.asInterface(service);
        }
        public void onServiceDisconnected(ComponentName className) {
            iService = null;
        }
    };
    private ServiceConnection oConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className,
                IBinder service) {
        	oService = OutputWrapperInterface.Stub.asInterface(service);
        }
        public void onServiceDisconnected(ComponentName className) {
            oService = null;
        }
    };
}