package com.android.pendant;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.location.*;
import java.util.*;

public class InputWrapper extends Service {
    private String[] config;
    private Timer timer = new Timer();
    public IBinder onBind(Intent i){
    	return null;
    }
    public void onCreate(){
    	super.onCreate();
    	_startService();
    	
    }
    public void onDestroy(){
    	super.onDestroy();

    	  _shutdownService();

    }
    private void _startService(){
    	timer.scheduleAtFixedRate(
    		      new TimerTask() {
    		        public void run() {
    		          mainRunner();
    		        }
    		      },
    		      0,
    		      1);

    }
    private void mainRunner(){
    	float xcel;
    	boolean tac;
    	tac = tacRetrieve();
    	xcel = XmHandler();
    	if (xcel > 9.8){
    		
    	}
    	if (tac){
    		
    	}
    }
    private void _shutdownService(){
    	
    }
	public InputWrapper() {
	   retrieveConfig();// TODO Auto-generated constructor stub
	}
	//Accelerometer wrapper stub
	public float XmHandler(){
		return 0;
	}
	//GPS Handler Stub
	public float[] GPSHandler(){
		float[] s;
		s = new float[2];
		Location loc;
		LocationManager locMan;
		String locPro;
		List<String> proList;

		//Get the location manager from the server
		locMan = (LocationManager) getSystemService(LOCATION_SERVICE);

	 	proList = locMan.getProviders(true);
		
		//Just grab the first member of the list. It's name will be "gps"
		locPro = proList.get(0);
		loc = locMan.getLastKnownLocation(locPro);

		float Lat =  (float)loc.getLatitude();
		float Lon =  (float)loc.getLongitude();
        s[0] = Lat;
        s[1] = Lon;
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
