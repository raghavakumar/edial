package com.poc.edial;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.poc.edial.BO.ContactPhoneLog;
import com.poc.edial.activityEventHandler.configEventHandler;
import com.poc.edial.model.ContactListItems;
import com.poc.edial.model.LogPreferences;
import com.poc.edial.model.configCallLogModel;
import com.poc.edial.service.CallData;
import com.poc.edial.service.EDialServiceImpl;
import com.poc.library.SqlDBConstants;
import com.poc.library.SqlHandler;
import com.poc.library.Service.LoggingService;
import com.poc.library.Service.Trace;
import com.poc.library.util.EdialUtil;

//import com.example.library.util.dateUtil;
 
@SuppressWarnings("unused")
public class ConfigCallLogs extends Activity { 
	
    public TextView currentDate;     // Capture Current Date Field Value
    public TextView startDate;          // Capture Start Date Field Value
    public TextView advSearch;
    public TextView outgPrefDesc;
	public TextView incgPrefDesc;
	private TextView incgSelectMonths;	
    
	public Button searchResult;        // Search Button
	public Button btnChangeDate;       // Trigger button for Date widget
	public Button btnClearRes;	
	public Button btnoutgSettings;
	public Button btnincgSettings;
	public Button saveConfigBtn;
	
	public CheckBox selectall;
	
	public boolean checkAll;	
	public boolean OptMenuStatus = false;
	public boolean callDetStatus = false;
		
	public String outgMonths = SqlDBConstants.OUTG_MONTHS;
	public String outgMins = SqlDBConstants.OUTG_MINS;
	public String incgMonths = SqlDBConstants.INCG_MONTHS;
	public String incgMins = SqlDBConstants.INCG_MINS;	
	
	public static final String TAG = "ConfigCallLogs";
	
	public static final String EDIAL_PREF = SqlDBConstants.EDIAL_PREF;  //Preference file
	
	public ContactPhoneLog PhoneLogTrans;
	public LogPreferences LogPref;	
	public ListView lvCustomList;
	public SqlHandler sqlHandler;
	public EdialUtil edialUtil;
	public LoggingService edialService;
	public Trace log;
	//public dateUtil dateUtil;
	
	configEventHandler configEventHandler;	
	public configCallLogModel configCallLogModel;	
	public LinearLayout configCalloutgPref;
	
	public EDialServiceImpl EDialSerImpl;
	public CallData callDataRet;
	
	public Cursor callLogCursor;	
	public HashMap<String,String> settMonMinDetailsMap = new HashMap<String,String>();
	public ArrayList<CallData> contactList = new ArrayList<CallData>(); 
	    
    /** Called when the activity is first created. */    
	@SuppressLint("SimpleDateFormat")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		
		Log.d(TAG, "entered oncreate");	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_call_logs); 
        
        configCallLogModel = new configCallLogModel(this);
        configEventHandler = new configEventHandler(this);
        PhoneLogTrans = new ContactPhoneLog(this);
        LogPref = new LogPreferences(this);
        sqlHandler = new SqlHandler(this);        
                
        edialUtil = new EdialUtil();                
        configCalloutgPref.setVisibility(View.GONE);
        
              
        //log = new edialService().getTrace();        
        //log.setDebug(true);        
        //logMsg("message here in configCallLogs check here");
        //log.debug(TAG, "message here in configCallLogs modddd");
    }	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d(TAG, "entered onCreateOptionsMenu check here");
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.options_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu (Menu menu) {
		
		Log.d(TAG, "entered onPrepareOptionsMenu");
		super.onPrepareOptionsMenu(menu);
		return OptMenuStatus;
		//return true;		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d(TAG, "entered onOptionsItemSelected");
		switch (item.getItemId()) 
		{
			case R.id.menu_outg_filter:
	    		Log.w(toString(), "onOptionsItemSelected");
	    		//Toast.makeText(ConfigCallLogs.this, "Preferences is Selected", Toast.LENGTH_SHORT).show();
	    		//showincgpopup();
	    		return true;
	    	default:
	            return super.onOptionsItemSelected(item);
		}
	}
	
} 
