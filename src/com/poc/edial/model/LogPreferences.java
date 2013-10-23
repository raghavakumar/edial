package com.poc.edial.model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.poc.library.SqlDBConstants;
import com.poc.library.SqlHandler;

public class LogPreferences {
	SqlHandler dbHelper;
	
	  public LogPreferences(Context context) {
		dbHelper = new SqlHandler(context);	
		
	  }
	  
	  public void PrefCallLogRecInsert(String name, String phone) {
		  
	    ContentValues values = new ContentValues();
	    
	    values.put(SqlDBConstants.LOG_PREF_NAME, name);
	    values.put(SqlDBConstants.LOG_PREF_PHONE, phone);	    
	    
	    Log.w(toString(), "PrefCallLogRecInset name"+name);
	    Log.w(toString(), "PrefCallLogRecInset number"+phone);	    
	    
	    String query = "INSERT INTO "+SqlDBConstants.LOG_PREF_TABLE+"("+SqlDBConstants.LOG_PREF_NAME+","+SqlDBConstants.LOG_PREF_PHONE+") values ('"
	    	      + name + "','" + phone + "')";
	    
	    dbHelper.executeQuery(query);
	  }
	  
	  public void PrefCallLogRecDelete()
	  {			  
		  Log.w(toString(), "Delete LogPreference Table");
		  
		  dbHelper.executeQuery("delete from "+ SqlDBConstants.LOG_PREF_TABLE);
		  dbHelper.executeQuery("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + SqlDBConstants.LOG_PREF_TABLE+"'");
		  
		  Log.w(toString(), "Delete sqlite sequence table for LogPreference");
	  }
} 
