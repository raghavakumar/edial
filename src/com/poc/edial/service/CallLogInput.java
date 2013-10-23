package com.poc.edial.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.util.Log;

import com.poc.edial.ConfigCallLogs;
import com.poc.library.SqlHandler;

public class CallLogInput extends ActivityInput implements ServiceInput {
	private Date startDate;
	private Date endDate;
	private int callType;	
	//SqlHandler dbHelper;

	@SuppressLint("SimpleDateFormat")
	public CallLogInput(ConfigCallLogs activity) {
		super(activity);		
		
		//dbHelper = new SqlHandler(activity);
		
		SimpleDateFormat sdf = new SimpleDateFormat(EDialConstants.CAL_DATE_FORMAT_M_DD_YYYY);
		try {
			startDate = sdf.parse(activity.startDate.getText().toString());
			endDate = sdf.parse(activity.currentDate.getText().toString());
					
		} catch (ParseException e) {
			Log.e(ConfigCallLogs.TAG, "Error parsing " + activity.startDate.getText().toString() + " or " + activity.currentDate.getText().toString(), e);
			//TODO: add a InvalidInputException and throw
		}
	}	
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getCallType() {
		return callType;
	}
	public void setCallType(int callType) {
		this.callType = callType;
	}	
}
