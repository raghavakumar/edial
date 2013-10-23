package com.poc.edial.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.poc.edial.R;
import com.poc.edial.model.ContactListItems;
import com.poc.edial.service.CallData;
import com.poc.library.SqlDBConstants;
import com.poc.library.util.QuickContactHelper;
 
public class ContactListAdapter extends BaseAdapter {
 
    Context context;
    ArrayList<CallData> contactList;
    static boolean[] checkboxstate;
    private static final String TAG = "ContactListAdapter";
 
    public ContactListAdapter(Context context, ArrayList<CallData> contactList2) {
    	
    	Log.d(TAG, "entered Constructer"); 
        this.context = context;
        contactList = contactList2;
        checkboxstate=new boolean[getCount()];
    }
    
    private class ViewHolder {    	   
    	   //TextView calltype;
    	   TextView name;
    	   TextView phone;
    	   TextView desc;
    	   CheckBox check;
    	   ImageView icon;
    	  }
    
 
    @Override
    public int getCount() { 
        return contactList.size();
    }
 
    @Override
    public Object getItem(int position) { 
        return contactList.get(position);
    }
 
    @Override
    public long getItemId(int position) { 
        return position;
    }    
  
 
    
	@SuppressLint("CutPasteId")
	@Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        CallData contactListItems = contactList.get(position);
        ViewHolder holder = null;
        Log.d(TAG, "entered getView");
        
        Log.d(TAG, "convertView in custom adapter==>"+convertView);
 
        if (convertView == null) {
        	
        	Log.v(TAG, "convertView is NULL");
        	
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contact_list_row, null);
            
            holder = new ViewHolder();                                
            holder.name = (TextView) convertView.findViewById(R.id.title);
            holder.desc = (TextView) convertView.findViewById(R.id.desc);
            holder.phone = (TextView) convertView.findViewById(R.id.phone);   
            holder.check = (CheckBox) convertView.findViewById(R.id.checkBoxSelect);
            
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
            
            holder.check.setOnClickListener( new View.OnClickListener() { 
                public void onClick(View v) { 
                 CheckBox cb = (CheckBox) v ; 
                 ContactListItems contactListItems = (ContactListItems) cb.getTag(); 
                /* Toast.makeText(context,
                  "Clicked on Checkbox: " + cb.getText() +
                  " is " + cb.isChecked(),
                  Toast.LENGTH_LONG).show();*/
                 contactListItems.setSelected(cb.isChecked());
                } 
               });        
           

        }
        else 
        {        	
        	 SharedPreferences settings =  context.getSharedPreferences(SqlDBConstants.EDIAL_PREF, 0);
             String isChecked = settings.getString("checkStatus", "initialstring");
             
             Log.d(TAG, "isChecked status in custom adapter===>"+isChecked);
             
             if(isChecked=="true")
             {            
             	Log.d(TAG, "silent here in if cond===>"+isChecked);
             	contactListItems.setSelected(true);
             	checkboxstate[position]=true;
             	
             }
             else
             {
             	Log.d(TAG, "silent here in else cond check===>"+isChecked);
             	contactListItems.setSelected(false);
             	checkboxstate[position]=false;
             }
             
            holder = (ViewHolder) convertView.getTag();
        }
        
        final Bitmap b = new QuickContactHelper(context, contactListItems.getNumber()).addThumbnail();
        holder.icon.setImageBitmap(b);        
        
        //TextView tvSlNo = (TextView) convertView.findViewById(R.id.tv_slno);
        //tvSlNo.setText(contactListItems.getSlno());
        /*TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        tvName.setText(contactListItems.getName());
        TextView tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);
        tvPhone.setText(contactListItems.getPhone());*/
       
        holder.phone.setText(contactListItems.getNumber());
        holder.name.setText(contactListItems.getName());
        holder.desc.setText(contactListItems.getCallType());        
        holder.check.setChecked(contactListItems.isSelected());
        holder.check.setTag(contactListItems);
        return convertView;
    }
}