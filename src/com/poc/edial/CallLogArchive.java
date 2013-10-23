package com.poc.edial;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.poc.edial.BO.CallLogArchiveReport;
import com.poc.edial.activityEventHandler.CallLogArchiveEventHandler;
import com.poc.edial.model.CallLogArchiveModel;

public class CallLogArchive extends Activity {
	
	public TextView currentDate;
	CallLogArchiveEventHandler CallLogArchiveEventHandler;
	CallLogArchiveModel CallLogArchiveModel;
	CallLogArchiveReport CallLogArchiveReport;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call_log_archive);
		
		CallLogArchiveModel = new CallLogArchiveModel(this);
		CallLogArchiveEventHandler = new CallLogArchiveEventHandler(this);
		CallLogArchiveReport = new CallLogArchiveReport(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.call_log_archive, menu);
		return true;
	}
}
