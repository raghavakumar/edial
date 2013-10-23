package com.poc.edial;

//import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost.TabSpec;
//import android.support.v4.app.DialogFragment;
import android.widget.TabHost;
 
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	protected static final String EXTRA_ACTION = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		Log.w(toString(),"entered onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
 
		// Home tab
		Intent intentHome = new Intent().setClass(this, HomeActivity.class);
		TabSpec tabSpecHome = tabHost
		  .newTabSpec("Home")
		  .setIndicator("", ressources.getDrawable(R.drawable.icon_default_homepage))
		  .setContent(intentHome);
 
		// CallLog tab
		Intent intentCallLog = new Intent().setClass(this, ConfigCallLogs.class);
		TabSpec tabCallLog = tabHost
		  .newTabSpec("Apple")
		  .setIndicator("", ressources.getDrawable(R.drawable.icon_calllog))
		  .setContent(intentCallLog);
		
		// Preference tab
		Intent intentPreference = new Intent().setClass(this, LogPreferences.class);
		TabSpec tabSpecPreference = tabHost
		  .newTabSpec("Windows")
		  .setIndicator("", ressources.getDrawable(R.drawable.icon_pref))
		  .setContent(intentPreference);
		
		// add all tabs 
		tabHost.addTab(tabSpecHome);
		tabHost.addTab(tabCallLog);
		tabHost.addTab(tabSpecPreference);	
 
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
		
		
	}	
	
}