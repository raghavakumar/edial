package com.poc.edial.service;

public class EDialConstants {
	//call type masks
	public static final int OUTGOING_CALL = 0x1;
	public static final int INCOMMING_CALL = 0x2;
	public static final int MISSED_CALLS =  0x4;
	
	public static final int IN_OUT_CALLS =  OUTGOING_CALL | INCOMMING_CALL;
	public static final int ALL_CALLS =  OUTGOING_CALL | INCOMMING_CALL| MISSED_CALLS;
	
	public static final String CAL_DATE_FORMAT_M_DD_YYYY ="M-dd-yyyy";
	//public static final String CAL_DATE_FORMAT_M_DD_YYYY ="M-dd-yyyy";
	
	// Other    
    public static final String OUTG = "Outgoing";    
    public static final String INCG = "Incoming";    
    public static final int INCOMING_CALLS = 1;
    public static final int OUTGOING_CALLS = 2;
    
    
    public static final String CALLLOG_ARCHIVE_REPORT_AS_ON = "CallLog Archive Report As on : "; 
}
