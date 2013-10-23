package com.poc.edial.activityEventHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.util.Log;
import com.poc.edial.CallLogArchive;
import com.poc.edial.service.EDialConstants;
import com.poc.library.SqlDBConstants;

public class CallLogArchiveEventHandler {
	
	private CallLogArchive parentActivity = null;
	static final int DATE_DIALOG_ID = 999;
	DatePickerDialog DatePickerDialog;
	String pastDateFinalStr = "";
	private static final String TAG = "CallLogArchiveEventHandler";
	
	public static int cal_year;
	public static int cal_month;
	public static int cal_day;
	
	private long startDate;
	
	//eventHandler constructor	
	public CallLogArchiveEventHandler(CallLogArchive parentActivity) {
		
		Log.d(TAG, "entered Constructer");
		this.parentActivity = parentActivity;
		setcurDateVal();
		invokeHandler();
	}	
	
	public void setcurDateVal()
	{
		Log.d(TAG, "entered setcurDateVal");
		
		final Calendar cal = Calendar.getInstance();
		cal_year = cal.get(Calendar.YEAR);
		cal_month = cal.get(Calendar.MONTH);
		cal_day = cal.get(Calendar.DAY_OF_MONTH);
	}
	
	public void invokeHandler ()
	{	
		Log.d(TAG, "entered invokeHandle");		
		setCurrentDateOnView();   //Set Current Date for tvDisplayDate field.		
	}
	
	// display current date	
	@SuppressLint("SimpleDateFormat")
	public void setCurrentDateOnView() {		
		Log.d(TAG, "entered setCurrentDateOnView");		
		Date date = Calendar.getInstance().getTime();		
		SimpleDateFormat formatter = new SimpleDateFormat(SqlDBConstants.CAL_DATE_FORMAT);
	    String currentDate = formatter.format(date);		
		parentActivity.currentDate.setText(EDialConstants.CALLLOG_ARCHIVE_REPORT_AS_ON + currentDate);
	}
}
