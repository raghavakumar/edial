package com.poc.edial.service;

import android.database.Cursor;
import android.provider.CallLog;
import android.util.Log;

import com.poc.library.util.EdialUtil;

public class EDialServiceImpl extends EDialService {
	public void alertReport() {
		// TODO Auto-generated method stub
		
	}

	public void getPreferences() {
		// TODO Auto-generated method stub
		
	}


	public void sendEmail() {
		// TODO Auto-generated method stub
		
	}

	public void sendSMS() {
		// TODO Auto-generated method stub
		
	}

	public void setPreferences() {
		// TODO Auto-generated method stub
		
	}
	
	public ServiceOutput readCallLogArchive(ServiceInput in) {
		// TODO Auto-generated method stub		
		CallLogArchiveInput archiveInput = (CallLogArchiveInput)in;
		CallLogArchiveOutput archiveOutput = new CallLogArchiveOutput();
		CallLogArchiveReport CallLogArchiveReport = new CallLogArchiveReport();
		//CallLogArchiveReportRecords()
		
		return archiveOutput;		
	}
	
	public ServiceOutput readCallLogHistory (ServiceInput in)
	{
		CallLogServiceInput serviceInput = (CallLogServiceInput)in;
		CallLogOutput output = new CallLogOutput();
		
		Cursor callLogCursor = serviceInput.getcontext().getContentResolver().query(
				android.provider.CallLog.Calls.CONTENT_URI, null, null, null,
				android.provider.CallLog.Calls.DEFAULT_SORT_ORDER);
		
		if (callLogCursor != null) {
			while (callLogCursor.moveToNext()) {
				
				String id = callLogCursor.getString(callLogCursor
						.getColumnIndex(CallLog.Calls._ID));
				
				Log.d("calllog", "id value===>"+id);
				
				String name = callLogCursor.getString(callLogCursor
						.getColumnIndex(CallLog.Calls.CACHED_NAME));
				String cacheNumber = callLogCursor.getString(callLogCursor
						.getColumnIndex(CallLog.Calls.CACHED_NUMBER_LABEL));
				String number = callLogCursor.getString(callLogCursor
						.getColumnIndex(CallLog.Calls.NUMBER));
				long dateTimeMillis = callLogCursor.getLong(callLogCursor
						.getColumnIndex(CallLog.Calls.DATE));
				//@SuppressWarnings("unused")
				long durationMillis = callLogCursor.getLong(callLogCursor
						.getColumnIndex(CallLog.Calls.DURATION));
				int callType = callLogCursor.getInt(callLogCursor
						.getColumnIndex(CallLog.Calls.TYPE));				

				if (cacheNumber == null)
					cacheNumber = number;
				if (name == null)
					name = "No Name";
				
				Log.d("calllog", "name==>"+name);
				Log.d("calllog", "cacheNumber==>"+cacheNumber);
				Log.d("calllog", "number==>"+number);
				Log.d("calllog", "dateTimeMillis==>"+dateTimeMillis);				
				
				String dateString = EdialUtil.getDateTime(dateTimeMillis);
				
				Log.d("calllog", "dateString==>"+dateString);				
				
				CallData CallData = new CallData();
				CallData.setName(name);
				CallData.setNumber(cacheNumber);
				CallData.setCallType(callType);
				CallData.setDuration(durationMillis);
				
				output.addCallData(CallData);
			}
		}
		return output;
	}

	@Override
	public ServiceOutput readCallLog(ServiceInput in) {
		CallLogInput input = (CallLogInput)in;
		CallLogOutput output = new CallLogOutput();
				
		output = (CallLogOutput) readCallLogHistory (new CallLogServiceInput(input.getActivity()));
		
		return output;
	}

	@Override
	public ServiceOutput readCallLogArchive(ServiceInput in) {
		// TODO Auto-generated method stub
		return null;
	}
}
