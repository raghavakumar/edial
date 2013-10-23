package com.poc.edial;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Dialog;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


public class GeoMap extends FragmentActivity implements LocationListener {

	/**
     * Note that this may be null if the Google Play services APK is not available.
     */
    private GoogleMap mMap;
    private LocationManager locationManager;
    private String provider;
    private TextView myAddress;
    double LATITUDE;
    double LONGITUDE;    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_map);
        
        myAddress = (TextView)findViewById(R.id.myAddress);        
        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        if(status!=ConnectionResult.SUCCESS)
        { 
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
        }
        else
        {  
        	setLocationParam();
        	setUpMapIfNeeded();
        }
    }
    
    
    public void setLocationParam()
    {
    	// Get the location manager   
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
        	
        	 Toast.makeText(this, "Provider " + provider + " has been selected.",
        		        Toast.LENGTH_SHORT).show();
        	 
          onLocationChanged(location);
        } else {
          //latituteField.setText("Location not available");
          //longitudeField.setText("Location not available");
        }
        
        getGeoLocationInfo(location);
    }
    
    
    protected boolean verifyGoogleUtilService()
    {
  	  
  	  int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
    
  	  if(status!=ConnectionResult.SUCCESS)
        { 
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
            return false;
        }
  	  return true;
    }
    
    protected void getGeoLocationInfo(Location location) 
    {
  	  
  	  boolean googleUtilstatus = verifyGoogleUtilService();
  	  
  	  if(googleUtilstatus)
  	  {
  	  
  	  String result;
  	  Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
      	  
      	  if(Geocoder.isPresent())
      	  {   	  
  	    	  List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
  	          if (addresses.size() > 0)
  	          {
  	        	  Address mobileLocationAddress = addresses.get(0);  	              
  	              result = getMobileLocationAddress(mobileLocationAddress);  	              
  	              myAddress.setText(result);
        	 }
  			 else
  			 {
  				 myAddress.setText("Cannot get Address!");
  			 }
      	  }
      	  else
      	   {
      		  myAddress.setText("NoGEOCoder Exist!"); 
      	   }      		  
      	  
  		} catch (IOException e) {
	  		 // TODO Auto-generated catch block
	  		 e.printStackTrace();
	  		 myAddress.setText("Cannot get Address!");
  		}
        
  	  }
    }
    
    
    protected String getMobileLocationAddress(Address mobileLocationAddress)
    {    	
    	 StringBuilder result = new StringBuilder(); 
    	 
    	 if(mobileLocationAddress.getAddressLine(0)!=null) result.append(mobileLocationAddress.getAddressLine(0)).append("\n");
    	 if(mobileLocationAddress.getAddressLine(1)!=null) result.append(mobileLocationAddress.getAddressLine(1)).append("\n");	              
    	 if(mobileLocationAddress.getPostalCode()!=null)   result.append(mobileLocationAddress.getPostalCode()).append("\n");
    	 if(mobileLocationAddress.getSubLocality()!=null)  result.append(mobileLocationAddress.getSubLocality()).append("\n");
    	 if(mobileLocationAddress.getLocality()!=null)     result.append(mobileLocationAddress.getLocality()).append("\n");
    	 if(mobileLocationAddress.getCountryName()!=null)  result.append(mobileLocationAddress.getCountryName());
    	 
    	 if(result.toString()!="")
    		 return result.toString();
    	 else
    		 return "Cannot get Address!";
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
        setUpMapIfNeeded();
    }
    
    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
      super.onPause();
      locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
  	  LATITUDE = (double) (location.getLatitude());
      LONGITUDE = (double) (location.getLongitude());
      //latituteField.setText(String.valueOf(LATITUDE));
      //longitudeField.setText(String.valueOf(LONGITUDE));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
      // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
      Toast.makeText(this, "Enabled new provider " + provider,
          Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
      Toast.makeText(this, "Disabled provider " + provider,
          Toast.LENGTH_SHORT).show();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not have been
     * completely destroyed during this process (it is likely that it would only be stopped or
     * paused), {@link #onCreate(Bundle)} may not be called again so we should call this method in
     * {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {    	
    	
        LatLng ll = new LatLng(LATITUDE, LONGITUDE);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 17));
        //mMap.addMarker(new MarkerOptions().position(new LatLng(78.437428500000010000,17.410632100000000000)).title("Marker"));        
        //double LATITUDE = 17.410632100000000000;
        //double LONGITUDE = 78.437428500000010000;        
    }
}

