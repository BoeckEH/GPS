package com.example.firsttabletproject;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onClick_EricsButton(View view){
    	
    GetGPSInformation myGPS = new GetGPSInformation(getApplicationContext());
    
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
    
    
}
