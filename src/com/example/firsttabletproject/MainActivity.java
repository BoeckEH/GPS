package com.example.firsttabletproject;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ToggleButton;
import android.widget.TextView;

public class MainActivity extends Activity {

    public GetGPSInformation myGPS;
    public Timer myTimer = new Timer();

//    private TimerTask myTimerTask = new TimerTask() {
//		@Override
//		public void run() {
//			DoOnTimer();
//		}
//	};
    
	private class aTask extends TimerTask {
		@Override
		public void run() {
			DoOnTimer();
		}
	}
	
	private aTask myTimerTask = new aTask();
	
	
	
	private void DoOnTimer(){
		this.runOnUiThread(Timer_Tick);
	    //This method runs in the same thread as the timer.               

	
	}
	
	
	private Runnable Timer_Tick = new Runnable() {
	    public void run() {

	    //This method runs in the same thread as the UI.               

	    UpdateGPS();

	    }
	};
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myGPS = new GetGPSInformation(getApplicationContext());
    }
	
	

	@Override
    public void onDestroy() {
        super.onDestroy();
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onClick_EricsButton(View view){

    	UpdateGPS();
    	
    }
    
    public void UpdateGPS() {
    	
	    if (myGPS.canGetLocation())
	    {
	    	double lat = myGPS.getLatitude();
	    	double lon = myGPS.getLongitude();
	    	
	    	Location myLocation = myGPS.getLocation();
	    	long tme = myLocation.getTime();
	    	
	    	
	    	TextView myLoc = (TextView) findViewById(R.id.tv_Latitude);
	    	myLoc.setText(String.format("%f", lat));
	    	myLoc = (TextView) findViewById(R.id.tv_Longitude);
	    	myLoc.setText(String.format("%f", lon));
	    	myLoc = (TextView) findViewById(R.id.tv_Time);
	    	myLoc.setText(String.format("%tc", tme));
	    	
	    }
    	
    }
    
    public void onClick_ssGPSThread(View view){
        boolean on = ((ToggleButton) view).isChecked();

        ProgressBar  myProg = (ProgressBar) findViewById(R.id.progressBar1); 
        
        if (on) {
        	myProg.setVisibility(ProgressBar.VISIBLE);
            myTimer.schedule(myTimerTask, 0, 5000);
        } else {
        	myProg.setVisibility(ProgressBar.INVISIBLE);
        	myTimer.cancel();
        }

    	
    }
    
}
