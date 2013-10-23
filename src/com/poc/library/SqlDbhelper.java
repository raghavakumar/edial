package com.poc.library;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 
public class SqlDbhelper extends SQLiteOpenHelper {
	
private static String SCRIPT_CREATE_CONTACTS_TABLE =null;
private static String  SCRIPT_CREATE_CALLLOG_ARCHIVE_RECORD_TABLE =null;
private static String  SCRIPT_CREATE_CALLLOG_ARCHIVE_CONTACTS_TABLE =null;
private static String SCRIPT_CREATE_LOG_PREF_TABLE =null;
 
public SqlDbhelper(Context context, String name, CursorFactory factory,
   int version) {
  super(context, name, factory, version);
  // TODO Auto-generated constructor stub 
 }
 
 @Override
 public void onCreate(SQLiteDatabase db) {
  // TODO Auto-generated method stub  
  createContactsTable(db);
  createCallLogArchiveContactsTable(db);
  createCallLogArchiveRecTable(db);
  createLogPreferenceTable(db); 
 }
 
 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  // TODO Auto-generated method stub
  db.execSQL(SqlDBConstants.SQL_DROP_TABLE_IF_EXISTS + SqlDBConstants.CONTACTS_TABLE);
  onCreate(db);
 } 
 
private static void createCallLogArchiveContactsTable(SQLiteDatabase db) {
	 
	SCRIPT_CREATE_CALLLOG_ARCHIVE_CONTACTS_TABLE = SqlDBConstants.SQL_CREATE_TABLE_IF_NOT_EXIST
			   + SqlDBConstants.CALLLOG_ARCHIVE_CONTACTS_TABLE + " (" + SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_SLNO
			   + " integer "+SqlDBConstants.PRIMARY_KEY+" "+ SqlDBConstants.AUTOINCREMENT+", " + SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_NAME
			   + " text not null," + SqlDBConstants.CALLLOG_CONTACT_ARCHIVE_PHONE + " text not null);";
	 db.execSQL(SCRIPT_CREATE_CALLLOG_ARCHIVE_CONTACTS_TABLE);
 }
 
private static void createCallLogArchiveRecTable(SQLiteDatabase db) {
	 
	 SCRIPT_CREATE_CALLLOG_ARCHIVE_RECORD_TABLE = SqlDBConstants.SQL_CREATE_TABLE_IF_NOT_EXIST
			   + SqlDBConstants.CALLLOG_ARCHIVE_TABLE + " (" + SqlDBConstants.CALLLOG_ARCHIVE_SLNO
			   + " integer "+SqlDBConstants.PRIMARY_KEY+" "+ SqlDBConstants.AUTOINCREMENT+", " + SqlDBConstants.CALLLOG_ARCHIVE_CONTACT_REFNO
			   + " text not null," + SqlDBConstants.CALLLOG_ARCHIVE_CALL_TYPE + " text not null," + SqlDBConstants.CALLLOG_ARCHIVE_DURATION + " text not null);";
	 db.execSQL(SCRIPT_CREATE_CALLLOG_ARCHIVE_RECORD_TABLE);
 }
 
 
 private static void createContactsTable(SQLiteDatabase db) {
	 
	 SCRIPT_CREATE_CONTACTS_TABLE = SqlDBConstants.SQL_CREATE_TABLE_IF_NOT_EXIST
			   + SqlDBConstants.CONTACTS_TABLE + " (" + SqlDBConstants.CONTACTS_SLNO
			   + " integer "+SqlDBConstants.PRIMARY_KEY+" "+ SqlDBConstants.AUTOINCREMENT+", " + SqlDBConstants.CONTACTS_NAME
			   + " text not null, " + SqlDBConstants.CONTACTS_PHONE + " text not null," + SqlDBConstants.CONTACTS_CALL_TYPE + " text not null);";
	 db.execSQL(SCRIPT_CREATE_CONTACTS_TABLE);
 }
 
 private static void createLogPreferenceTable(SQLiteDatabase db) {
	 
	 SCRIPT_CREATE_LOG_PREF_TABLE = SqlDBConstants.SQL_CREATE_TABLE_IF_NOT_EXIST
			   + SqlDBConstants.LOG_PREF_TABLE + " (" + SqlDBConstants.LOG_PREF_SLNO
			   + " integer "+SqlDBConstants.PRIMARY_KEY+" "+ SqlDBConstants.AUTOINCREMENT+", " + SqlDBConstants.LOG_PREF_NAME
			   + " text not null, " + SqlDBConstants.LOG_PREF_PHONE + " text not null);";
	 db.execSQL(SCRIPT_CREATE_LOG_PREF_TABLE); 
 }
     
}
