package com.poc.edial;


import android.app.Application;
import android.util.Log;

public class MainApplication extends Application {
	//SqlHandler sqlHandler;
	
    @Override
    public void onCreate() {
        super.onCreate();
        Log.w(toString(), "mainapplication edial test");
        //sqlHandler = new SqlHandler(this);
    }    
}
