package com.poc.edial;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.poc.edial.R;
import com.poc.edial.adapter.LogPreferenceAdapter;
import com.poc.library.SqlHandler;

public class LogPreferences extends Activity {
	
	LogPreferenceAdapter logPrefAdapter;
	ListView logPrefCustomList;
	SqlHandler sqlHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_preferences);
		
		//logPrefCustomList = (ListView) findViewById(R.id.log_pref_custom_list);
        //sqlHandler = new SqlHandler(this);        
        //showLogPrefList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_preferences, menu);
		return true;
	}	
	
	/*private void showLogPrefList()
	{
	 Log.w(toString(), "entered logpreferences");
	 ArrayList<LogPreferenceListItems> logPrefListItems = new ArrayList<LogPreferenceListItems>();  
	 logPrefListItems.clear();
	 
	  String query = "SELECT * FROM "+ SqlDBConstants.LOG_PREF_TABLE;
	  Cursor c1 = sqlHandler.selectQuery(query);
	  
	  if (c1 != null && c1.getCount() != 0) {
	   if (c1.moveToFirst()) {
	    do {
	    	LogPreferenceListItems LogPreferenceListItems = new LogPreferenceListItems();
	 
	    	LogPreferenceListItems.setSlno(c1.getString(c1
	       .getColumnIndex("slno")));
	    	LogPreferenceListItems.setName(c1.getString(c1
	       .getColumnIndex("name")));
	    	LogPreferenceListItems.setPhone(c1.getString(c1
	       .getColumnIndex("phone")));
	    	logPrefListItems.add(LogPreferenceListItems);
	 
	    } while (c1.moveToNext());
	   }
	  }
	  c1.close();
	 
	  logPrefAdapter = new LogPreferenceAdapter(
			  LogPreferences.this, logPrefListItems);
	  
	  logPrefCustomList.setAdapter(logPrefAdapter);
	}*/

}
