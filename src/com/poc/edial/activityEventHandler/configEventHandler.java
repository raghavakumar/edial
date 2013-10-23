package com.poc.edial.activityEventHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.Toast;

import com.poc.edial.ConfigCallLogs;
import com.poc.edial.R;
import com.poc.library.SqlDBConstants;
import com.poc.library.util.EdialUtil;

//@SuppressLint("SimpleDateFormat")
@SuppressLint({ "ShowToast", "SimpleDateFormat" })
public class configEventHandler {
	
	private ConfigCallLogs parentActivity = null;
	static final int DATE_DIALOG_ID = 999;
	DatePickerDialog DatePickerDialog;
	String pastDateFinalStr = "";
	
	private static final String TAG = "configEventHandler";
	/**
	 * @depricated 
	 * */
	public static int cal_year;
	public static int cal_month;
	public static int cal_day;
	
	private long startDate;
	
	//eventHandler constructor	
	public configEventHandler(ConfigCallLogs parentActivity) {
		
		Log.d(TAG, "entered Constructer");		
		
		this.parentActivity = parentActivity;
		setcurDateVal();
		invokeHandler();
	}
	
	
	public void setcurDateVal()
	{
		Log.d(TAG, "entered setcurDateVal");
		
		final Calendar cal = Calendar.getInstance();
		cal_year = cal.get(Calendar.YEAR);
		cal_month = cal.get(Calendar.MONTH);
		cal_day = cal.get(Calendar.DAY_OF_MONTH);
	}
	
	
	public void invokeHandler ()
	{	
		Log.d(TAG, "entered invokeHandle");
		
		setCurrentDateOnView();   //Set Current Date for tvDisplayDate field.
		addListenerOnButton();    // Choose Date Button action		
        addListenerOnSearch();    // Filter Call log comparing between Past Date and Current Date selection
        addListenerOnReset();     // Reset Form Results        
        addListenerOnSelectAll(); //selectall checkbox action
        addListeneronincgEdit();  //call DialogFragment on outg edit button click
        addListeneronoutgEdit();  //call DialogFragment on outg edit button click        
        addListenerOnAdv();	      // disable/enable adv search 
        //addListenerOnConfigSave();
	}
	
	// display current date	
	public void setCurrentDateOnView() {
		
		Log.d(TAG, "entered setCurrentDateOnView");
		
		Date date = Calendar.getInstance().getTime();		
		SimpleDateFormat formatter = new SimpleDateFormat(SqlDBConstants.CAL_DATE_FORMAT);
	    String today = formatter.format(date);		
		parentActivity.currentDate.setText(today);
	}
	
	
	/*public void addListenerOnConfigSave()
	{
		
		saveConfigBtn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {				        	
        	PhoneLogTrans.insertUpdateContactLogs(contactListAdapter);
            Log.d("gm", "Tapped here ");
        }
    	});
	
	}*/
	
	
	public void addListeneronincgEdit() {
	
		Log.d(TAG, "entered addListeneronincgEdit in configEventHandler");
	    parentActivity.btnincgSettings.setOnClickListener(new OnClickListener() {		
		@Override
		public void onClick(View v) {
			
			 Log.w(toString(), "entered onclick listener second");			 
			 AlertDialog.Builder alertDialogIncg = alertDialogFrag(SqlDBConstants.INCG_LOG_PREF_TITLE, R.layout.incg_custom_dialog, SqlDBConstants.INCG);
			 alertDialogIncg.show();
			}
		});
	}
	
	
	public void addListeneronoutgEdit() {
		
		Log.d(TAG, "entered addListeneronoutgEdit");
		
		parentActivity.btnoutgSettings.setOnClickListener(new OnClickListener() {
			//@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				
				 //Log.w(toString(), "entered onclick listener second");						 
				 AlertDialog.Builder alertDialogOutg = alertDialogFrag(SqlDBConstants.OUTG_LOG_PREF_TITLE, R.layout.outg_custom_dialog, SqlDBConstants.OUTG);
				 alertDialogOutg.show(); 
			}
		});
	}
	
	
	
	public void doPositiveClickforIncg (View dialogView)
	{
		Log.d(TAG, "entered doPositiveClickforIncg");
		HashMap<String,String> outgMonthMinString = parentActivity.edialUtil.getMonMinVal(dialogView, R.id.incgSelectMonthsNum, R.id.incgSelectMin);
   	 	parentActivity.incgMonths = outgMonthMinString.get("months");
   	 	parentActivity.incgMins = outgMonthMinString.get("mins");
   	 	//String incgpref = outgMonthMinString.get("monMinsString");	
   	 	parentActivity.incgPrefDesc.setText(outgMonthMinString.get("monMinsString"));
	}
	
	public void doPositiveClickforOutg (View dialogView)
	{		
		Log.d(TAG, "entered doPositiveClickforOutg");
		HashMap<String,String> outgMonthMinString = parentActivity.edialUtil.getMonMinVal(dialogView, R.id.outgSelectMonthsNum, R.id.outgSelectMin);
   	 	parentActivity.outgMonths = outgMonthMinString.get("months");
   	 	parentActivity.outgMins = outgMonthMinString.get("mins");
   	 	//String outgpref = outgMonthMinString.get("monMinsString");
   	 	parentActivity.outgPrefDesc.setText(outgMonthMinString.get("monMinsString"));
	}
	
	
	public AlertDialog.Builder alertDialogFrag(String title, int LayoutPopup, final String type)
	{			
		Log.d(TAG, "entered alertDialogFrag");
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(parentActivity);
		alertDialog.setTitle(title);

	    LayoutInflater li = (LayoutInflater) parentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    final View dialogView = li.inflate(LayoutPopup, null);
	    alertDialog.setView(dialogView);
	    
	    alertDialog.setPositiveButton(SqlDBConstants.OK_lABEL, new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog, int which) {	
	        	 
	        	 if(type ==SqlDBConstants.INCG) doPositiveClickforIncg(dialogView);
	        	 else if(type ==SqlDBConstants.OUTG) doPositiveClickforOutg(dialogView);
	        	 
	       } });

		 alertDialog.setNegativeButton(SqlDBConstants.CANCEL_lABEL, new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog, int which) {	        	 
	        	 //Log.w(toString(), "entered else button - Close Dialog");			             
	        	 dialog.cancel();
	       } });
	    return alertDialog;
	}
	
	public void addListenerOnAdv() { 
		
		Log.d(TAG, "entered addListenerOnAdv");

		parentActivity.advSearch.setOnClickListener(new OnClickListener() {
		//@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {			
			
			if(parentActivity.configCalloutgPref.isShown()) parentActivity.configCalloutgPref.setVisibility(View.GONE);
			else parentActivity.configCalloutgPref.setVisibility(View.VISIBLE);
		}
	});
 }
	
	
	private void addListenerOnReset() { 
		
		Log.d(TAG, "entered addListenerOnReset");

		parentActivity.btnClearRes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				parentActivity.startDate.setText("");              // set Pastdate to null			
				//parentActivity.PhoneLogTrans.CallLogRecDelete();  //delete existing Phone log records
				setcurDateVal(); 								  //reset Past Date Picker window to current date
				
				//empty custom adapter only if Listrow is already set				
				if(parentActivity.OptMenuStatus == true) 
				{								
					parentActivity.lvCustomList.setAdapter(null);
					View btView = parentActivity.lvCustomList.findViewById(1);
					
	                if(btView != null) 	parentActivity.lvCustomList.removeFooterView(btView); //remove save button from adapter footer view
	                
	                parentActivity.PhoneLogTrans.contactListAdapter.notifyDataSetChanged();
				}
				
                parentActivity.searchResult.setEnabled(false);  //Disable both Search and reset buttons
                parentActivity.btnClearRes.setEnabled(false);
			}
		});		
	}
	
	public void addListenerOnSelectAll() {
		
		Log.d(TAG, "entered addListenerOnSelectAll");
		
		parentActivity.selectall.setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	            // TODO Auto-generated method stub	            
	            boolean isChecked = parentActivity.selectall.isChecked();
	            
	            if (isChecked == true) {
	            	//size = contactListAdapter.getCount();
	            	Log.w(toString(), "entered if condition==>"+isChecked);	            	
	            	setSharedPref("true");
	            } else if(isChecked==false) {	            	
	            	Log.w(toString(), "entered else condition==>"+isChecked);
	            	setSharedPref("false");
	            }
	        }
	    });
	  }
	
	public void setSharedPref (String status)
	{	
		Log.d(TAG, "entered setSharedPref");
		
		SharedPreferences settings = parentActivity.getSharedPreferences(ConfigCallLogs.EDIAL_PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("checkStatus", status);              
        editor.commit(); 
        
        parentActivity.PhoneLogTrans.contactListAdapter.notifyDataSetChanged();		
	}		
		
	public void addListenerOnSearch() { 
		
		Log.d(TAG, "entered addListenerOnSearch");

		parentActivity.searchResult.setOnClickListener(new OnClickListener() {

			//@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {				
				parentActivity.PhoneLogTrans.showCallDetails();
				//parentActivity.showCallDetails();
			}
		});
	}
	
	
	public void addListenerOnButton() {	
		
		Log.d(TAG, "entered addListenerOnButton");

		parentActivity.btnChangeDate.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				Log.w(toString(), "edateUtil==>addListenerOnButton==>onClick");
			    DatePickerDialog=new DatePickerDialog(parentActivity, datePickerListener, cal_year, cal_month,
						cal_day);
			    DatePickerDialog.updateDate(cal_year, cal_month, cal_day);
			    DatePickerDialog.show();
			}
		});
	}
	
	public DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {	
			
			if( (selectedYear > 0) && (selectedMonth > 0) && (selectedDay > 0) )
			{
				Log.w(toString(), "entered ondatesetlisterner");
				
				if (selectedYear > cal_year) 
				{
					Toast.makeText(parentActivity,SqlDBConstants.CHOOSE_PAST_DATE_MSG, 1).show();
					parentActivity.startDate.setText(" ");
				}
				else if(selectedMonth > cal_month && cal_year == selectedYear)
				{
					Toast.makeText(parentActivity,SqlDBConstants.CHOOSE_PAST_DATE_MSG, 1).show();
					parentActivity.startDate.setText(" ");
				
				}
				else if (selectedDay > cal_day && cal_year == selectedYear && selectedMonth == cal_month)
				{
					Toast.makeText(parentActivity,SqlDBConstants.CHOOSE_PAST_DATE_MSG, 1).show();
					parentActivity.startDate.setText(" ");
				}
				else
				{	
					cal_year = selectedYear;
					cal_month = selectedMonth;
					cal_day = selectedDay;	
					
					Calendar c = Calendar.getInstance();
					c.set(selectedYear, selectedMonth, selectedDay);
					startDate =  c.getTimeInMillis();
					parentActivity.startDate.setText( EdialUtil.getDateTime(startDate));
				
					parentActivity.searchResult.setEnabled(true);
					parentActivity.btnClearRes.setEnabled(true);
			   }
			}                
		}
	};
}