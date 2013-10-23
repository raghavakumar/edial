package com.poc.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
 
public class SqlHandler {
 Context context;
 SQLiteDatabase sqlDatabase;
 SqlDbhelper dbHelper;
 
 public SqlHandler(Context context) { 
 
  dbHelper = new SqlDbhelper(context, SqlDBConstants.DATABASE_NAME, null,
		  SqlDBConstants.DATABASE_VERSION);  
  sqlDatabase = dbHelper.getWritableDatabase();
 }
 
 public void executeQuery(String query) {
  try {
 
   if (sqlDatabase.isOpen()) {
    sqlDatabase.close();
   }   
   //Log.w(toString(), query); 
   sqlDatabase = dbHelper.getWritableDatabase();
   sqlDatabase.execSQL(query); 
  } catch (Exception e) {
	  
   Log.w(toString(), "DATABASE ERROR " + e); 
   System.out.println("DATABASE ERROR " + e);
  } 
 }
 
 public Cursor selectQuery(String query) {
  Cursor edialCursor = null;
  try {
 
   if (sqlDatabase.isOpen()) {
    sqlDatabase.close();
 
   }
   sqlDatabase = dbHelper.getWritableDatabase();
   edialCursor = sqlDatabase.rawQuery(query, null);
 
  } catch (Exception e) {
 
   System.out.println("DATABASE ERROR " + e);
   Log.w(toString(), "DATABASE ERROR " + e);
 
  }
  return edialCursor; 
 } 


public int updateQuery (String table, ContentValues cv, String where)
{
	//db.update(customerTable, cv, colID+"=?", new String []{String.valueOf(b.id)});	
	int edialCursor = 0;
	try {
		 
	   if (sqlDatabase.isOpen()) {
	    sqlDatabase.close();
	 
	   }
	   sqlDatabase = dbHelper.getWritableDatabase();
	   edialCursor = sqlDatabase.update(table, cv, where, null);
	   
	   Log.d(toString(), "update edialCursor==> " + edialCursor);
	   
	   
	  } catch (Exception e) {
	 
	   System.out.println("DATABASE ERROR " + e);
	   Log.w(toString(), "DATABASE ERROR " + e);
	 
	  }
	return edialCursor; 
  } 
		  
}
