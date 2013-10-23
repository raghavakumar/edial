package com.poc.library;


public class SqlDBConstants {

    private SqlDBConstants() {
    	//null
    }    
    
    // SQL
    public static final String AUTOINCREMENT = "AUTOINCREMENT";
    public static final String PRIMARY_KEY = "PRIMARY KEY";
    public static final String SQL_IN = "%s IN (%s)";
    public static final String SQL_DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS";
    public static final String SQL_CREATE_TABLE_IF_NOT_EXIST = "CREATE TABLE IF NOT EXISTS ";
    public static final String SQL_ALTER_TABLE_ADD_COLUMN = "ALTER TABLE %s ADD COLUMN %s ";
    public static final String SQL_AVG_QUERY = "SELECT AVG(%s) FROM %s";
    public static final String SQL_AVG_QUERY_WHERE = "SELECT AVG(%s) FROM %s WHERE %s = '%s'";    
    public static final String CONTACTS_TABLE_SEL_QUERY = "SELECT * FROM "+SqlDBConstants.CONTACTS_TABLE;
    
    
    public static final String OUTG_MONTHS = "3 months";
    public static final String OUTG_MINS = "10 min";
    public static final String INCG_MONTHS = "3 months";
    public static final String INCG_MINS = "10 min";
    
    //public static final String OUTG_PREF_DESC =OUTG_MONTHS+" - More than "+OUTG_MINS+" Call duration";
    //public static final String INCG_PREF_DESC =INCG_MONTHS+" - More than "+INCG_MINS+" Call duration";
    
    public static final String OUTG_PREF_DESC =OUTG_MONTHS;
    public static final String INCG_PREF_DESC =INCG_MONTHS;
    
    
    public static final String INCG_LOG_PREF_TITLE = "Select Incoming Log Preferences";
    public static final String OUTG_LOG_PREF_TITLE = "Select Outgoing Log Preferences";
    public static final String OK_lABEL ="ok";
    public static final String CANCEL_lABEL = "cancel";
    
    // Other    
    public static final String OUTG = "Outgoing";    
    public static final String INCG = "Incoming";    
    public static final int INCOMING_CALLS = 1;
    public static final int OUTGOING_CALLS = 2;
    
    public static final int DATABASE_VERSION = 1;
    public static final int FIRST_DATABASE_VERSION = 1;   
    public static final String DATABASE_NAME = "Contacts";    
    
    public static final String CALLLOG_ARCHIVE_CONTACTS_TABLE = "CALLLOG_ARCHIVE_CONTACTS";
    public static final String CALLLOG_CONTACT_ARCHIVE_SLNO = "slno";
    public static final String CALLLOG_CONTACT_ARCHIVE_NAME = "name";
    public static final String CALLLOG_CONTACT_ARCHIVE_PHONE = "phone";
    
    public static final String NONAME = "No Name";  
    
    
    
    public static final String CALLLOG_ARCHIVE_TABLE = "CALLLOG_ARCHIVE";
    public static final String CALLLOG_ARCHIVE_SLNO = "slno";
    public static final String CALLLOG_ARCHIVE_CONTACT_REFNO = "contactrefno";    
    public static final String CALLLOG_ARCHIVE_CALL_TYPE = "calltype";
    public static final String CALLLOG_ARCHIVE_DURATION = "duration";
    
    public static final String CONTACTS_TABLE = "PHONE_CONTACTS";
    public static final String CONTACTS_SLNO = "slno";
    public static final String CONTACTS_NAME = "name";
    public static final String CONTACTS_PHONE = "phone"; 
    public static final String CONTACTS_CALL_TYPE = "callType"; 
    
    public static final String CAL_DATE_FORMAT ="M-dd-yyyy";
    
    public static final String CHOOSE_PAST_DATE_MSG = "Please choose Past date";
    public static final String EDIAL_PROB_MSG = "Problem with Edial App";   
    
    
    //CONTACTS_CALL_TYPE
    public static final String LOG_PREF_TABLE = "LOG_PREF";
    public static final String LOG_PREF_SLNO = "slno";
    public static final String LOG_PREF_NAME = "name";
    public static final String LOG_PREF_PHONE = "phone";

    public static final String EDIAL_PREF = "edial_pref";
    
    public static final String OUT_INC_STATIC_STRING = "%s - More than %s Call Duration";
    
    public static final String SPECIAL_SYMBOLS_REGEX = "[-+.^:,\"']";
    public static final String ID_COLUMN = "_id"; // if we don't make a primary key column
    public static final String DESC = "DESC";
    public static final String ASC = "ASC";
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String AUTO_ASSIGN = "AUTO_ASSIGN";
    public static final String DIVIDER = ",";
    public static final String DIVIDER_WITH_SPACE = ", ";
    public static final String FIRST_BRACKET = "(";
    public static final String LAST_BRACKET = ");";
    public static final String DOT = ".";
    public static final String UNDERSCORE = "_";

}
