package com.poc.edial.BO;

import android.util.Log;
import android.view.View;

import com.poc.edial.ConfigCallLogs;
import com.poc.edial.adapter.ContactListAdapter;
import com.poc.edial.model.LogPreferences;
import com.poc.edial.service.CallLogInput;
import com.poc.edial.service.CallLogOutput;
import com.poc.edial.service.EDialService;
import com.poc.edial.service.EDialServiceImpl;
import com.poc.library.SqlHandler;
import com.poc.library.util.EdialUtil;

public class ContactPhoneLog {
	
	SqlHandler dbHelper;
	private ConfigCallLogs parentActivity = null;	
	public ContactListAdapter contactListAdapter;	
	public LogPreferences LogPref;	
	private EDialService service =  new EDialServiceImpl();
	
	private static final String TAG = "ContactPhoneLog";	
	
    public ContactPhoneLog(ConfigCallLogs parentActivity) {
    	Log.d(TAG, "entered Constructor");
		dbHelper = new SqlHandler(parentActivity);	
		this.parentActivity = parentActivity;		
	  }
    
	  public void insertUpdateContactLogs (ContactListAdapter contactListAdapter) 
	  {		  
		  
		    Log.d(TAG, "entered in insertUpdateContactLogs");
			int cntChoice = contactListAdapter.getCount();
			
			/*if(cntChoice > 0)  LogPref.PrefCallLogRecDelete();
			
			for(int i = 0;i<cntChoice;i++)
	        {			
				ContactListItems contactListItems = (ContactListItems) contactListAdapter.getItem(i);			
				Log.d("insertDb", "entered in db"+contactListItems.getName());
				
				LogPref.PrefCallLogRecInsert(contactListItems.getName(), contactListItems.getPhone());
	        }*/
	  }	
	
	public void showCallDetails()
	{		
		Log.d(TAG, "entered in showCallDetails ** Phonelogtrans");
		CallLogOutput contactList = (CallLogOutput) service.readCallLog(new CallLogInput(parentActivity));
		
		Log.d(TAG, "contactList===>"+contactList.getCallDataList());		
		parentActivity.contactList.clear();			 
		contactListAdapter = new ContactListAdapter(
			  parentActivity, contactList.getCallDataList());
		  
		  if(contactList.getCallDataList().size() > 0) {
			   Log.d(TAG, "Adding 'save to db' button");
			  
			   parentActivity.OptMenuStatus = true;
			   parentActivity.saveConfigBtn = EdialUtil.createButton(parentActivity, "save");				
			   parentActivity.lvCustomList.addFooterView(parentActivity.saveConfigBtn);				 
				 
			   parentActivity.saveConfigBtn.setOnClickListener(new View.OnClickListener() {
				        public void onClick(View v) {				        	
				        	insertUpdateContactLogs(contactListAdapter);
				            Log.d(TAG, "Tapped here ");
				        }
				    });
			 }		  
		  parentActivity.lvCustomList.setAdapter(contactListAdapter);
		 }			
	
	
	/** Read call logs from Call History and display in CustomAdapter ListView.**/	
	
} 