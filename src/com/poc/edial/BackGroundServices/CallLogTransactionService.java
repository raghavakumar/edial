package com.poc.edial.BackGroundServices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CallLogTransactionService extends Service {
	 private final IBinder mBinder = new MyBinder();
	 private static long timeStarted = -1L;
     private static long timeEnded;     
     private static long timeStartedRinging;     
     private static Long durationMillis;     
     private static String number;     
     private static String contactName;
     private static String callType;
     private static boolean noCallListenerYet = true;
     public static final int SUCCESS = 1;
     public static final int FAILURE = 0;
     private static final String TAG = "CallHistoryUpdate";     
     CallLogTransactionSqlDBHandler ServiceSqlDBHandler;           
     
	  @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
		  
	    Log.d(TAG, "CallLogOutgoingService===>onStartCommand");		
	    ServiceSqlDBHandler = new CallLogTransactionSqlDBHandler(this.getApplicationContext());				 
		  
		number = CalllogTransactionDetails.getPhone();
	    contactName = CalllogTransactionDetails.getName();
	    callType = CalllogTransactionDetails.getCallType();
		  
		Log.d(TAG, "CallLogOutgoingService===>number***==>"+number);
		Log.d(TAG, "CallLogOutgoingService===>contactName===>"+contactName);		  
		  
        if (noCallListenerYet) {
        	
        	Log.d(TAG, "CallLogOutgoingService===>noCallListenerYet");
        	
            final TelephonyManager tm = (TelephonyManager) this.getBaseContext().getSystemService(
            		this.getBaseContext().TELEPHONY_SERVICE);
            
            tm.listen(new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                	
                	Log.d(TAG, "CallLogOutgoingService===>onCallStateChanged");
                	
                	 if (state == TelephonyManager.CALL_STATE_RINGING) {
                		 timeStartedRinging = System.currentTimeMillis();
             	         Log.d(TAG, "callLogIncomingService==>timeStarted: " + timeStartedRinging);             	        
	                    }                	 
                	 
                    if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                    	Log.d(TAG, "CallLogOutgoingService===>calstatehook");
                        timeStarted = System.currentTimeMillis();
                        Log.d(TAG, "timeStarted: " + timeStarted);
                        Log.d(TAG, "number: " + number);
                    }
                     
                    if (state == TelephonyManager.CALL_STATE_IDLE && timeStarted != -1L) {                    	
                    	Log.d(TAG, "CallLogOutgoingService===>callstateidle");
                        timeEnded = System.currentTimeMillis();
                        Log.d(TAG, "timeEnded: " + timeEnded);	                         
                        
                        if(timeStarted!=-1 && timeEnded>0)
                        {
                        	durationMillis = timeEnded - timeStarted;
                        	
                        	Log.d(TAG, "callLogOutgoingService==>contactName==>"+contactName);
                        	Log.d(TAG, "callLogOutgoingService==>number==>"+number);
                        	Log.d(TAG, "callLogOutgoingService==>durationMillis==>"+durationMillis);
                        	
                        	ServiceSqlDBHandler.CallLogArchiveRecInsert(contactName, number, callType, durationMillis);
                        }
                        // reset to -1
                        timeStarted = -1L;
                    }
                }
            }, PhoneStateListener.LISTEN_CALL_STATE);
            noCallListenerYet = false;
	      }
			return SUCCESS;
	    }	
	  
	  @Override
	  public IBinder onBind(Intent arg0) {
	    return mBinder;
	  }

	  public class MyBinder extends Binder {		  
		  CallLogTransactionService getService() {			  
			  Log.d(TAG, "CalLogBGService getWordList binder test");	    	
	    	  Toast.makeText(CallLogTransactionService.this, "CallLogOutgoingService getWordList", Toast.LENGTH_SHORT).show();
	      return CallLogTransactionService.this;
	    }
	  }	  
}