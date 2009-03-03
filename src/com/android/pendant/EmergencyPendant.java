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
	InputWrapperInterface mService = null;
	Timer time = new Timer();
	float xcel;
	boolean tac;
	float[] xy;
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent svc = new Intent(this, InputWrapper.class);
        bindService(svc, mConnection, Context.BIND_AUTO_CREATE);
        time.schedule(new TimerTask() {
            public void run() {
                mainRunner();
              }
            }, 0, 2);
        setContentView(R.layout.main);
    }
    private void mainRunner(){
    	try{
    		xy = mService.location();
    		xcel = mService.xcel();
    		tac = mService.tacResponse();
    	}
    	catch (RemoteException ex){
    		
    	}
    }
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className,
                IBinder service) {
        	mService = InputWrapperInterface.Stub.asInterface(service);
        }
        public void onServiceDisconnected(ComponentName className) {
            mService = null;
        }
    };
}