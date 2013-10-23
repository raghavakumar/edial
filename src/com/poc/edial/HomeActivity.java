package com.poc.edial;

//import android.app.Activity;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
//import android.support.v4.app.DialogFragment;
 
public class HomeActivity extends FragmentActivity {
	protected static final String EXTRA_ACTION = null;
	//private Button btnCreateDB;
	//private Button btnLogPref;
	public EditText myAddress;
	public CheckBox geoLocationCheckBox;
	public static final String TAG = "HomeActivity";	

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		Log.w(toString(),"entered onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		myAddress = (EditText) findViewById(R.id.compMsg);
		showGeoLocation();
	}	
	
	
	
	public void showGeoLocation() {
		
		geoLocationCheckBox = (CheckBox) findViewById(R.id.geolocation);

		Log.d(TAG, "entered addListenerOnSelectAll");
		
		geoLocationCheckBox.setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	            // TODO Auto-generated method stub	            
	            boolean isChecked = geoLocationCheckBox.isChecked();
	            
	            if (isChecked == true) {	            	
	            	Log.w(toString(), "entered if condition==>"+isChecked);
	            	
	            	Intent intent = new Intent(HomeActivity.this, GeoMap.class);
					intent.putExtra(EXTRA_ACTION, "showGeoMap");
		            startActivity(intent); 
	            	
	            } else if(isChecked==false) {	            	
	            	Log.w(toString(), "entered else condition==>"+isChecked);
	            	
	            }
	        }
	    });
	}
	
	
	/*public void addListenerOnLogPref(){
		
		btnLogPref = (Button) findViewById(R.id.CallLogPreferences);

		btnLogPref.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.w(toString(),"entered onClick");

				Intent intent = new Intent(HomeActivity.this, LogPreferences.class);
				intent.putExtra(EXTRA_ACTION, "createLogPref");
	            startActivity(intent); 
			}

		});
		
	}*/
	
	
}