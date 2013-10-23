package com.poc.edial.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.poc.edial.R;
//import com.example.edial.model.ContactListItems;
import com.poc.edial.model.LogPreferenceListItems;
import com.poc.library.SqlDBConstants;
 
public class LogPreferenceAdapter extends BaseAdapter {
 
    Context context;
    ArrayList<LogPreferenceListItems> logPrefList;
    static boolean[] checkboxstate;
    private static final String TAG = "LogPreferenceAdapter";
 
    public LogPreferenceAdapter(Context context, ArrayList<LogPreferenceListItems> list) {
    	
    	Log.d(TAG, "entered Constructor"); 
        this.context = context;
        logPrefList = list;
        checkboxstate=new boolean[getCount()];
    }
    
    private class ViewHolder {
    	   TextView phone;
    	   CheckBox name;
    	  }
 
    @Override
    public int getCount() {
 
        return logPrefList.size();
    }
 
    @Override
    public Object getItem(int position) {
 
        return logPrefList.get(position);
    }
 
    @Override
    public long getItemId(int position) {
 
        return position;
    }    
  
 
    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
    	
    	View view;
    	LogPreferenceListItems logPreferenceListItems = logPrefList.get(position);
        
    	Log.d(TAG, String.valueOf(position));        
        Log.d(TAG, "convertView in custom adapter==>"+convertView);        
        
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.log_pref_list_row, null);
        }
        else 
        {	        	 
        	view = convertView;
        }       
        
        Log.w(toString(), "Name in LogPrefList===>"+logPreferenceListItems.getName());
        
        TextView log_pref_slNo = (TextView) convertView.findViewById(R.id.log_pref_slno);
        log_pref_slNo.setText(logPreferenceListItems.getSlno());
        
        TextView log_pref_name = (TextView) convertView.findViewById(R.id.log_pref_name);
        log_pref_name.setText(logPreferenceListItems.getName());
        
        TextView log_pref_phone = (TextView) convertView.findViewById(R.id.log_pref_phone);
        log_pref_phone.setText(logPreferenceListItems.getPhone());
       
        return convertView;
    }
}