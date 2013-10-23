package com.poc.edial.BackGroundServices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.poc.library.SQLiteQueryBuilder;
import com.poc.library.SqlDBConstants;
import com.poc.library.SqlHandler;

public class CallLogTransactionSqlDBHandler {
	
	Context context;
	SqlHandler dbHelper;
	private static final String TAG = "ServiceSqlDBHandler";	
	
	 public CallLogTransactionSqlDBHandler(Context context) {
		 dbHelper = new SqlHandler(context);		 
	 }
	 
	 public long CallLogContactRec(String name, String phone)
	 {
		 long contactRefNum = -1;
		 //select record if exists
		 String tables = SqlDBConstants.CALLLOG_ARCHIVE_CONTACTS_TABLE;		 
		 String[] columns = {SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_SLNO, SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_NAME, SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_PHONE};
		 String where = SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_PHONE+"='"+phone+"'";
		 
		 String selectQueryString = SQLiteQueryBuilder.buildQueryString(false, tables, columns, where, null, null, null, null);		 
		
		 Log.d(TAG, "query verify here ==> "+selectQueryString);
		 
		 Cursor selectedRowCursor = dbHelper.selectQuery(selectQueryString);
		 if (selectedRowCursor != null && selectedRowCursor.moveToFirst())
		 {
			 Log.d(TAG, "record exists verify==> "+selectedRowCursor); 
			 contactRefNum = selectedRowCursor.getLong(selectedRowCursor
						.getColumnIndex(SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_SLNO));
			 String rowName = selectedRowCursor.getString(selectedRowCursor
						.getColumnIndex(SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_NAME));
			 String rowPhone = selectedRowCursor.getString(selectedRowCursor
						.getColumnIndex(SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_PHONE));
			 
			 Log.d(TAG, "contactRefNum check ==> "+contactRefNum);
			 Log.d(TAG, "rowName==> "+rowName);
			 Log.d(TAG, "rowName==> "+rowPhone);
			 
			 if(contactRefNum!=-1 && rowName!="" && rowPhone!="")
			 {
				 if(name != rowName && phone==rowPhone)
				 {				 
					 ContentValues cv = new ContentValues();
				     cv.put(SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_NAME, name); 
				     String whereClause = SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_SLNO+"="+contactRefNum;		     
				     
					 int updateNameColumnStatus = dbHelper.updateQuery(tables, cv, whereClause);				 
					 Log.d(TAG, "updateStatus==> "+updateNameColumnStatus);
				 
				 }
				 /*else if(name == rowName && phone!=rowPhone)
				 {				 
					 ContentValues cv = new ContentValues();
				     cv.put(SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_PHONE, phone); 
				     String whereClause = SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_SLNO+"="+contactRefNum;	
				     //String[] wherCols= {"+contactRefNum+"};
				     
					 int updatePhoneRowStatus = dbHelper.updateQuery(tables, cv, whereClause);					 
					 Log.d(TAG, "updateStatus==> "+updatePhoneRowStatus);
				 }*/ 	
		   }
	     }
	     else
	     {	 
		  //insert record if not exists		 
		  contactRefNum = CallLogInsertRec(name, phone);
		 }
	     return contactRefNum;		 
	 }
	 
	 
	 public long CallLogInsertRec(String name, String phone)
	 {
		long contactRefInsertNum = -1;
			
		ContentValues values = new ContentValues();
	    values.put(SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_NAME, name);
	    values.put(SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_PHONE, phone);
	    
	    Log.d(TAG, "CallLogRecInset callType"+name);
	    Log.d(TAG, "CallLogRecInset duration"+phone);	    
	    
	    String query = "INSERT INTO "+SqlDBConstants.CALLLOG_ARCHIVE_CONTACTS_TABLE+"("+SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_NAME+","+SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_PHONE+") values ('"
	    	      + name + "','"+ phone + "')";		    
	    
	    Log.d(TAG, "query==> "+query);
	    
	    dbHelper.executeQuery(query);
	    
	    String queryLastId = "SELECT "+SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_SLNO+" from "+SqlDBConstants.CALLLOG_ARCHIVE_CONTACTS_TABLE+" order by "+SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_SLNO+" DESC limit 1";
	    Cursor lastIdCursor = dbHelper.selectQuery(queryLastId);
	    if (lastIdCursor != null && lastIdCursor.moveToFirst()) {
	    	
	    	contactRefInsertNum = lastIdCursor.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
	    	
	    	Log.d(TAG, "contactRefInsertNum in insertfunction ==> "+contactRefInsertNum);
	    }
		return contactRefInsertNum;		 
	 }
	 
	 public void CallLogArchiveRecInsert(String name, String phone, String callType, long duration) {
			Log.d(TAG, "entered CallLogRecInsert");
			
			long contactRefNum = CallLogContactRec(name, phone);		
			
			if(contactRefNum!=-1)
			{			  
			    ContentValues values = new ContentValues();
			    values.put(SqlDBConstants.CALLLOG_ARCHIVE_CONTACT_REFNO, contactRefNum);
			    values.put(SqlDBConstants.CALLLOG_ARCHIVE_CALL_TYPE, callType);		    
			    values.put(SqlDBConstants.CALLLOG_ARCHIVE_DURATION, duration);
			    
			    Log.d(TAG, "CallLogRecInset callType"+callType);
			    Log.d(TAG, "CallLogRecInset duration"+duration);	    
			    
			    String query = "INSERT INTO "+SqlDBConstants.CALLLOG_ARCHIVE_TABLE+"("+SqlDBConstants.CALLLOG_ARCHIVE_CONTACT_REFNO+","+SqlDBConstants.CALLLOG_ARCHIVE_CALL_TYPE+","+SqlDBConstants.CALLLOG_ARCHIVE_DURATION+") values ('"
			    	      + contactRefNum + "','"+ callType + "','" + duration + "')";		    
			    
			    Log.d(TAG, "query==> "+query);
			    dbHelper.executeQuery(query);
			}
	 }
}
