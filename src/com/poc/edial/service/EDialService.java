package com.poc.edial.service;

public abstract class EDialService {
	
	public abstract ServiceOutput readCallLog(ServiceInput in);
	public abstract ServiceOutput readCallLogHistory(ServiceInput in);
	public abstract void sendSMS();
	public abstract void sendEmail();
	public abstract void setPreferences();
	public abstract void getPreferences();
	public abstract void alertReport();
}
