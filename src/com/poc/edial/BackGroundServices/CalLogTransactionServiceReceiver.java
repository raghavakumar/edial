package com.poc.edial.BackGroundServices;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.poc.library.SqlDBConstants;
import com.poc.library.SqlHandler;

public class CalLogTransactionServiceReceiver extends BroadcastReceiver {
	
	  Context context;
	  String contactName = "";
	  String outgoingNumber = "";
	  String incomingNumber = "";
	  SqlHandler sqlHandler;

	  @Override
	  public void onReceive(Context context, Intent intent) {
		 sqlHandler = new SqlHandler(context);
		  
		  Log.d("CalLogStartServiceReceiver", "CalLogStartServiceReceiver onReceive check");		  
		  this.context = context; 
		  String action=intent.getAction();
		  
		  if(action.equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)){
			  
			  Log.d("CalLogStartServiceReceiver", "Package Installed first time");
			  Intent callLogBackgroundService = new Intent(context, CallLogContactsHistoryService.class);
			  context.startService(callLogBackgroundService);
		  }		  
		  else {			  
			  Log.d("CalLogStartServiceReceiver", "Record Call Transaction details");
			  
			  outgoingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			  incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
			  
			  if(outgoingNumber!=null) setCallDetails (outgoingNumber, SqlDBConstants.OUTG);
			  if(incomingNumber!=null) setCallDetails (incomingNumber, SqlDBConstants.INCG);
			  
			  Intent callLogOutgoingService = new Intent(context, CallLogTransactionService.class);			  
			  context.startService(callLogOutgoingService);
			  
			  //Intent callLogBackgroundService = new Intent(context, CallLogContactsHistoryService.class);
			  //context.startService(callLogBackgroundService);			  
		  }		 
	  }	
	  
	  //set call Details information	  
	  public void setCallDetails (String dialledNumber, String callLogType)
	  { 
		  if(dialledNumber!="" && callLogType!="")
		  {
			  Log.d("CalLogStartServiceReceiver", "setCallDetails");
			  contactName = getContactDisplayNameByNumber(dialledNumber);
			  
			  CalllogTransactionDetails.setPhone(dialledNumber);
			  if(contactName!="") CalllogTransactionDetails.setName(contactName);
			  CalllogTransactionDetails.setCallType(callLogType);
		  }
		  else
		  {
			  Log.e("CalLogStartServiceReceiver", "Error in setting values.");
		  }
	  }
	  
	  //get contactname based on Number from Contacts Provider	  
	  public String getContactDisplayNameByNumber(String contactNumber) {
		    Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(contactNumber));
		    String contactName = SqlDBConstants.NONAME;

		    ContentResolver contentResolver = this.context.getContentResolver();
		    Cursor contactLookup = contentResolver.query(uri, new String[] {BaseColumns._ID,
		            ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null);

		    try {
		        if (contactLookup != null && contactLookup.getCount() > 0) {
		            contactLookup.moveToNext();
		            contactName = contactLookup.getString(contactLookup.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));		            
		        }
		    } finally {
		        if (contactLookup != null) {
		            contactLookup.close();
		        }
		    }
		    return contactName;
		}
	} 
