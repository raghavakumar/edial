package com.poc.edial.service;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class CallLogOutput extends BaseServiceOutput {
	ArrayList<CallData> callDataList;
	Page page;	
	
	public ArrayList<CallData> getCallDataList() {
		return callDataList;
	}
	public void setCallDataList(ArrayList<CallData> callDataList) {
		this.callDataList = callDataList;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	public void addCallData(CallData callData) {
		if (callDataList == null) {
			callDataList =  new ArrayList<CallData>();
		}
		callDataList.add(callData);
	}
}
