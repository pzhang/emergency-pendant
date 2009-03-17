package com.android.pendant;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.util.Log;
import android.os.IBinder;
import android.os.IInterface;
import android.location.*;
import java.util.*;

public class InputWrapper extends Service {
    private String[] config;
    LocationManager locMan;
    String locPro;
    List<String> proList;
    Timer time = new Timer();
    float[] s = new float[2];
    float[] location;
    public IBinder onBind(Intent i){
    	return mBinder;
    }
    private final InputWrapperInterface.Stub mBinder = new InputWrapperInterface.Stub(){
    	public float[] location(){
    		return GPSHandler();
    	}
    	public boolean tacResponse(){
    		return tacRetrieve();
    	}
    	public float[] xcel(){
    	    return XmHandler();	
    	}
    };
    public void onCreate(){
    	super.onCreate();
    	_startService();
    	
    }
    public void onDestroy(){
    	super.onDestroy();

    	  _shutdownService();

    }
    private void _startService(){
		//Get the location manager from the server
		locMan = (LocationManager) getSystemService(LOCATION_SERVICE);
	 	//proList = locMan.getProviders(true);
		
		//Just grab the first member of the list. It's name will be "gps"
		//locPro = proList.get(0);
		time.schedule(new TimerTask() {
            public void run() {
                setGPS();
              }
            }, 0, 1000);

    }

    private void _shutdownService(){
    	
    }
	public InputWrapper() {
	   retrieveConfig();// TODO Auto-generated constructor stub
	}
	//Accelerometer wrapper stub
	public float[] XmHandler(){
		Random rand = new Random();
		float[] foo = new float[3];
		foo[0] = rand.nextFloat() * 12;
		foo[1] = rand.nextFloat() * 12;
		foo[2] = rand.nextFloat() * 12;
		return foo;
	}
	//GPS Handler Stub
	private void setGPS(){
		Location loc;
		Log.i("InputWrapper", "in setGPS");
		Log.i("InputWrapper", Float.toString(s[0]) + " " + Float.toString(s[1]));
		loc = locMan.getLastKnownLocation("gps");
		float Lat =  (float)loc.getLatitude();
		float Lon =  (float)loc.getLongitude();
        s[0] = Lat;
        s[1] = Lon;
	}
	public float[] GPSHandler(){
		Log.i("InputWrapper", "in GPSHandler");
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
