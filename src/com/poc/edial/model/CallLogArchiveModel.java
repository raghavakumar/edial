package com.poc.edial.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.poc.edial.CallLogArchive;
import com.poc.edial.R;

public class CallLogArchiveModel {
	
	public CallLogArchive parentActivity = null;
	View callLogArchiveView;    
	private static final String TAG = "CallLogArchiveModel";	
	
	//CallLogArchiveModel constructor	
	public CallLogArchiveModel(CallLogArchive parentActivity) 
	{	
		Log.d(TAG, "entered Constructor");
		
		LayoutInflater inflater = (LayoutInflater) parentActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		callLogArchiveView = inflater.inflate(R.layout.activity_call_log_archive, null);
		
		parentActivity.currentDate = (TextView)callLogArchiveView.findViewById(R.id.currentDate);
		parentActivity.setContentView(callLogArchiveView);
	}
}
