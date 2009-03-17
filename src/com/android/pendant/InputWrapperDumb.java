package com.android.pendant;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.location.*;
import java.util.*;

public class InputWrapperDumb {
	    private String[] config;
	    LocationManager locMan;
	    public void onCreate(){
	    	_startService();
	    	
	    }
	    public void onDestroy(){

	    	  _shutdownService();

	    }
	    private void _startService(){
	      
	    }

	    private void _shutdownService(){
	    	
	    }
		public InputWrapperDumb(LocationManager loc) {
		   retrieveConfig();// TODO Auto-generated constructor stub
		   locMan = loc;
		}
		//Accelerometer wrapper stub
		public float XmHandler(){
			Random rand = new Random();
			return rand.nextFloat();
		}
		//GPS Handler Stub
		public float[] GPSHandler(){
			float[] s;
			s = new float[2];
			Location loc;
			String locPro;
			List<String> proList;

			//Get the location manager from the server
			

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
