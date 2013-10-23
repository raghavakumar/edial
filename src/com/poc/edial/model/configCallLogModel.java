package com.poc.edial.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.poc.edial.ConfigCallLogs;
import com.poc.edial.R;
import com.poc.library.SqlDBConstants;

public class configCallLogModel {
	
	public ConfigCallLogs parentActivity = null;
	View configCallLogView;
    //public static TextView tvDisplayDate;     // Capture Current Date Field Value
    //public static TextView pastDate;
	private static final String TAG = "configCallLogModel";	
    
	//ConfigCallLogModel constructor	
	public configCallLogModel(ConfigCallLogs parentActivity) 
	{		
		
		Log.d(TAG, "entered Constructor"); 
		
		LayoutInflater inflater = (LayoutInflater) parentActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
        configCallLogView = inflater.inflate(R.layout.activity_config_call_logs, null);		
		
        parentActivity.currentDate = (TextView)configCallLogView.findViewById(R.id.tvDate);
        parentActivity.startDate = (TextView)configCallLogView.findViewById(R.id.pastDateentry);
        parentActivity.outgPrefDesc = (TextView) configCallLogView.findViewById(R.id.outgPrefDesc);        
        parentActivity.incgPrefDesc = (TextView) configCallLogView.findViewById(R.id.incgPrefDesc);
            
        parentActivity.searchResult = (Button) configCallLogView.findViewById(R.id.searchQuery);
        parentActivity.btnClearRes = (Button) configCallLogView.findViewById(R.id.btnClearRes);
        parentActivity.btnoutgSettings = (Button) configCallLogView.findViewById(R.id.btnoutgSettings);
        parentActivity.btnincgSettings = (Button) configCallLogView.findViewById(R.id.btnincgSettings);
        parentActivity.btnChangeDate = (Button) configCallLogView.findViewById(R.id.btnChangeDate);
        
        
        parentActivity.lvCustomList = (ListView) configCallLogView.findViewById(R.id.lv_custom_list);
        parentActivity.configCalloutgPref = (LinearLayout) configCallLogView.findViewById(R.id.configCalloutgPref);
        parentActivity.advSearch = (TextView) configCallLogView.findViewById(R.id.adv);
        parentActivity.selectall = (CheckBox) configCallLogView.findViewById(R.id.selectall);
        
        setPreDefText(parentActivity);
        setKeyListener(parentActivity);        
        setDisabled(parentActivity);
        setEnabled(parentActivity);
        
        parentActivity.setContentView(configCallLogView);
    }
	
	public void setKeyListener(ConfigCallLogs parentActivity)
	{
		Log.d(TAG, "entered setKeyListener"); 
		
		parentActivity.startDate.setKeyListener(null);
        parentActivity.currentDate.setKeyListener(null);	
	}
	
	public void setDisabled (ConfigCallLogs parentActivity)
	{
		Log.d(TAG, "entered setDisabled");
		
		parentActivity.searchResult.setEnabled(false);
        parentActivity.btnClearRes.setEnabled(false);		
	}
	
	public void setEnabled (ConfigCallLogs parentActivity)
	{
		Log.d(TAG, "entered setEnabled");
		// Set Button Enabled state here		
	}	
	
	public void setPreDefText (ConfigCallLogs parentActivity)
	{
		Log.d(TAG, "entered setPreDefText");
		parentActivity.outgPrefDesc.setText(SqlDBConstants.OUTG_PREF_DESC);
        parentActivity.incgPrefDesc.setText(SqlDBConstants.INCG_PREF_DESC);
	}

	public ConfigCallLogs getParentActivity() {
		Log.d(TAG, "entered getParentActivity");
		return parentActivity;
	}

	public void setParentActivity(ConfigCallLogs parentActivity) {
		Log.d(TAG, "entered setParentActivity");
		this.parentActivity = parentActivity;
	}
}
