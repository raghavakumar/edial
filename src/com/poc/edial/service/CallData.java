package com.poc.edial.service;

import java.util.Date;

public class CallData {
	private String name;
	private String number;
	private long duration;
	private String callType;
	private Date date;
	boolean selected = false;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(int callType) {
		
		if(callType == EDialConstants.OUTGOING_CALLS) this.callType = EDialConstants.OUTG;
		if(callType == EDialConstants.INCOMING_CALLS) this.callType = EDialConstants.INCG;
		
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	 public boolean isSelected() {
		  return selected;
	 }
	 
	 public void setSelected(boolean selected) {
	  this.selected = selected;
	 }
}
