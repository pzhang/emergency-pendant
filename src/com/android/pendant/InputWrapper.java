package com.android.pendant;
import java.io.*;
import android.app.Service;
import android.hardware.SensorManager;
import android.content.Intent;
import android.os.Binder;
import android.util.Log;
import android.os.IBinder;
import android.os.IInterface;
import android.location.*;
import java.util.*;
import android.hardware.SensorListener;

public class InputWrapper extends Service implements SensorListener{
    private String[] config;
    LocationManager locMan;
    BufferedReader input;
    String locPro;
    long systime;
    SensorManager sensors;
    List<String> proList;
    private long lastUpdate = -1;
    Timer time = new Timer();
    float[] s = new float[2];
    float[] location;
    float[] accel = new float[3];
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
    	public long getTime(){
    		return systime;
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
		sensors = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensors.registerListener(this,
		        SensorManager.SENSOR_ACCELEROMETER,
		        SensorManager.SENSOR_DELAY_UI);
	 	//proList = locMan.getProviders(true);
		
		//Just grab the first member of the list. It's name will be "gps"
		//locPro = proList.get(0);
		accel[0] = 0;
		accel[1] = 0;
		accel[2] = 0;

		try {
		FileInputStream fIn = openFileInput("data.txt");
        InputStreamReader isr = new InputStreamReader(fIn); 
		input = new BufferedReader(isr);
		}
		catch (IOException ex){
			
		}
		time.schedule(new TimerTask() {
            public void run() {
                setGPS();
                setXcel();
              }
            }, 0, 40);

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
		return accel;
		//return foo;
	}
	public void setXcel(){
		String line = null;
		try {
		if ((line = input.readLine()) != null){
			systime = Long.parseLong(line);
			accel[Integer.parseInt(input.readLine())] = 
				 (float) (Double.parseDouble(input.readLine()));
		}
		else{
			input.close();
			input = null;
			try {
				FileInputStream fIn = openFileInput("data.txt");
		        InputStreamReader isr = new InputStreamReader(fIn); 
				input = new BufferedReader(isr);
				}
				catch (IOException ex){
					
				}
		}
		}
		catch (IOException ex){
			
		}
	}
	public void onAccuracyChanged(int sensor, int accuracy) {
	    // this method is called very rarely, so we don't have to
	    // limit our updates as we do in onSensorChanged(...)
	   
	  }
	 
	  // from the android.hardware.SensorListener interface
	  public void onSensorChanged(int sensor, float[] values) {
	    if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
	      long curTime = System.currentTimeMillis();
	      // only allow one update every 100ms, otherwise updates
	      // come way too fast and the phone gets bogged down
	      // with garbage collection
	      if (lastUpdate == -1 || (curTime - lastUpdate) > 100) {
	        lastUpdate = curTime;
	        
	        accel[0] = values[SensorManager.DATA_X];
	        accel[1] = values[SensorManager.DATA_Y];
	        accel[2] = values[SensorManager.DATA_Z];
	        
	      }
	    }
	  }
	//GPS Handler Stub
	private void setGPS(){
		Location loc;
		//Log.i("InputWrapper", "in setGPS");
		//Log.i("InputWrapper", Float.toString(s[0]) + " " + Float.toString(s[1]));
		//loc = locMan.getLastKnownLocation("gps");
		//float Lat =  (float)loc.getLatitude();
		//float Lon =  (float)loc.getLongitude();
        s[0] = (float)40.1763889;
        s[1] = (float)-88.3563889;
	}
	public float[] GPSHandler(){
		//Log.i("InputWrapper", "in GPSHandler");
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
