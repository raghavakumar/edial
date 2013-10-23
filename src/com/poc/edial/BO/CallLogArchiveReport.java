package com.poc.edial.BO;

import android.database.Cursor;
import android.provider.CallLog;
import android.util.Log;

import com.poc.edial.CallLogArchive;
import com.poc.edial.activityEventHandler.CallLogArchiveEventHandler;
import com.poc.edial.adapter.CallLogArchiveAdapter;
import com.poc.library.SQLiteQueryBuilder;
import com.poc.library.SqlDBConstants;
import com.poc.library.SqlHandler;

public class CallLogArchiveReport {
	
	SqlHandler dbHelper;
	private CallLogArchive parentActivity = null;	
	public CallLogArchiveAdapter callLogArchiveAdapter;
	private static final String TAG = "ContactPhoneLog";
		
	
    public CallLogArchiveReport(CallLogArchive parentActivity) {
    	Log.d(TAG, "entered Constructor");
		dbHelper = new SqlHandler(parentActivity);	
		this.parentActivity = parentActivity;
		
		//CallLogArchiveReportRecords();
		
	  }    
    
    public void CallLogArchiveReportRecords()
	{
		 String tables = SqlDBConstants.CALLLOG_ARCHIVE_CONTACTS_TABLE;		 
		 String[] columns = {SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_SLNO, SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_NAME, SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_PHONE};
		 //String where = SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_PHONE+"='"+phone+"'";
		 
		 String selectQueryString = SQLiteQueryBuilder.buildQueryString(false, tables, columns, null, null, null, null, null);
		 Log.d(TAG, "query verify here ==> "+selectQueryString);		 
		 Cursor contactsArchiveRecords = dbHelper.selectQuery(selectQueryString);
		 
		 if (contactsArchiveRecords != null) {
			 
				/*while (contactsArchiveRecords.moveToNext()) {
					
					String slNo = contactsArchiveRecords.getString(contactsArchiveRecords
							.getColumnIndex(SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_SLNO));
					
					String contactName = contactsArchiveRecords.getString(contactsArchiveRecords
							.getColumnIndex(SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_NAME));
					
					String contactPhone = contactsArchiveRecords.getString(contactsArchiveRecords
							.getColumnIndex(SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_PHONE));
					
					
					Log.d(TAG, "slNo===>"+slNo);
					Log.d(TAG, "contactName===>"+contactName);
					Log.d(TAG, "contactPhone===>"+contactPhone);					
				}*/
		 }
	 }					
}
