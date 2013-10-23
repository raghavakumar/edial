package com.poc.edial.BackGroundServices;

import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.IBinder;
import android.provider.CallLog;
import android.util.Log;
import android.widget.Toast;

import com.poc.edial.ConfigCallLogs;
import com.poc.edial.service.CallData;
import com.poc.edial.service.CallLogInput;
import com.poc.edial.service.CallLogOutput;
import com.poc.edial.service.CallLogServiceInput;
import com.poc.edial.service.EDialService;
import com.poc.edial.service.EDialServiceImpl;
import com.poc.library.SqlDBConstants;

public class CallLogContactsHistoryService extends Service {
	  private final IBinder mBinder = new MyBinder();
	  private ArrayList<String> list = new ArrayList<String>();
	  private static final String TAG = "EDialInstallService";
	  CallLogTransactionSqlDBHandler ServiceSqlDBHandler;	  
	  ConfigCallLogs parentActivity;
	  private EDialService service =  new EDialServiceImpl();
	  
	  @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
		  
		  ServiceSqlDBHandler = new CallLogTransactionSqlDBHandler(this.getApplicationContext());
		  
		  CallLogOutput contactList = (CallLogOutput) service.readCallLogHistory(new CallLogServiceInput(this));
		  
		  if(contactList.getCallDataList().size() > 0) {
			  
			  ArrayList<CallData> contactListItems = contactList.getCallDataList();			  
			  for (int i = 0; i < contactListItems.size(); i++) {
				  
				  String name = contactListItems.get(i).getName();
				  String number = contactListItems.get(i).getNumber();
				  String callType = contactListItems.get(i).getCallType();
				  long durationMillis = contactListItems.get(i).getDuration();				  
				  
				  Log.d(TAG, "name==>"+name);
				  Log.d(TAG, "number==>"+number);
				  Log.d(TAG, "callType==>"+callType);
				  Log.d(TAG, "durationMillis==>"+durationMillis);
				  
				 if(name!="" && number!="" && durationMillis>0 && (callType.equalsIgnoreCase(SqlDBConstants.OUTG) || callType.equalsIgnoreCase(SqlDBConstants.INCG))) 
		    	 {
		    	   Log.d(TAG, "call log inserted in DB for outgoing");
		    	   ServiceSqlDBHandler.CallLogArchiveRecInsert(name, number, callType, durationMillis);					    	
		    	 }
			}
		}
		return Service.START_NOT_STICKY;
	  }

	  //@Override
	  public int onStartCommandOld(Intent intent, int flags, int startId) {
		  
		  ServiceSqlDBHandler = new CallLogTransactionSqlDBHandler(this.getApplicationContext());
		  
		  CallLogOutput contactList = (CallLogOutput) service.readCallLog(new CallLogInput(parentActivity));
		  
		  if(contactList.getCallDataList().size() > 0) {
			  
		  }
		  
		  Cursor callLogCursor = this.getContentResolver().query(
					android.provider.CallLog.Calls.CONTENT_URI, null, null, null,
					android.provider.CallLog.Calls.DEFAULT_SORT_ORDER);
			
			if (callLogCursor != null) {
				
				Log.d("calllog", "entered getCallDetails ifcond");				
				Log.d("calllog", "entered in calllog cursor");				
				
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
					
					Log.w("calllog", "name==>"+name);
					Log.w("calllog", "cacheNumber==>"+cacheNumber);
					Log.w("calllog", "number==>"+number);
					Log.w("calllog", "dateTimeMillis==>"+dateTimeMillis);
					Log.w("calllog", "durationMillis==>"+durationMillis);
					
					if (callType == SqlDBConstants.OUTGOING_CALLS)
					{
						Log.d(TAG, "entered OUTGOING_CALLS==>"+SqlDBConstants.OUTGOING_CALLS);
						if(name!="" && number!="" && durationMillis>0) 
				    	{
				    	  Log.d(TAG, "call log inserted in DB for outgoing");
				    	  ServiceSqlDBHandler.CallLogArchiveRecInsert(name, number, SqlDBConstants.OUTG, durationMillis);					    	
				    	}
					}
					else if (callType == SqlDBConstants.INCOMING_CALLS)
					{	
						Log.d(TAG, "entered getCallDetails incg");						
						if(name!="" && number!="" && durationMillis>0)
						{
							Log.d(TAG, "call log inserted in DB for outgoing");
							ServiceSqlDBHandler.CallLogArchiveRecInsert(name, number, SqlDBConstants.INCG, durationMillis);
						}						
					}				
			    }
		      }	    
	       return Service.START_NOT_STICKY;
	     }		  
	  

	  @Override
	  public IBinder onBind(Intent arg0) {
	    return mBinder;
	  }

	  public class MyBinder extends Binder {
		  CallLogContactsHistoryService getService() {
			  
			Log.d("EDialInstallService", "EDialInstallService getCallLog binder test");	    	
	    	Toast.makeText(CallLogContactsHistoryService.this, "EDialInstallService getWordList", Toast.LENGTH_SHORT).show();
	    	
	      return CallLogContactsHistoryService.this;
	    }
	  } 

	} 