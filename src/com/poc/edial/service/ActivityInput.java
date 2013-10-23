package com.poc.edial.service;

import android.app.Activity;

public abstract class ActivityInput extends Page implements ServiceInput {
	private Activity activity;
	
	public ActivityInput(Activity activity) {
		this.activity = activity;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}
