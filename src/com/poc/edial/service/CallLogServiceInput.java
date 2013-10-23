package com.poc.edial.service;

import java.util.Date;

import android.content.Context;

//import com.poc.edial.BackGroundServices.CallLogContactsHistoryService;

public class CallLogServiceInput implements ServiceInput {
	//CallLogContactsHistoryService bgServiceContext;
	Context context;
	

	public CallLogServiceInput(Context serviceContext) {
		context = serviceContext;
		
	}
	
	public Context getcontext() {
		return context;
	}

}
