package com.poc.library.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.poc.edial.ConfigCallLogs;
import com.poc.edial.service.EDialConstants;
import com.poc.library.SqlDBConstants;

@SuppressLint("SimpleDateFormat")
public class EdialUtil {

	private static final String TAG = "edialUtil";

	public static int converMinutestoSeconds(String minutes) {
		int minutesInteger = Integer.parseInt(minutes.replaceAll("\\D+", ""));
		return minutesInteger * 60;
	}

	/** Convert milliseconds to Seconds format. */
	public static String getDuration(long milliseconds) {
		Log.d(TAG, "entered getDuration");

		int seconds = (int) (milliseconds / 1000) % 60;
		int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
		int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
		if (hours < 1)
			return minutes + ":" + seconds;
		return hours + ":" + minutes + ":" + seconds;
	}

	public static String getDefaultImage() {

		Log.d(TAG, "entered getDefaultImage");
		return null;
	}

	/** Convert Timestamp to DateTime Format. */
	@SuppressLint("SimpleDateFormat")
	public static String getDateTime(long milliseconds) {

		Log.d(TAG, "entered getDateTime");

		Log.d(TAG, "milliseconds===>" + milliseconds);
		SimpleDateFormat formatter = new SimpleDateFormat(EDialConstants.CAL_DATE_FORMAT_M_DD_YYYY);
		String dateString = formatter.format(new Date(milliseconds));
		return dateString;
	}

	// private method of your class
	public static int getIndex(View dialogView, int monthsId, String myString) {

		Log.d(TAG, "entered getIndex");
		int index = 0;
		Spinner spinSelect = (Spinner) dialogView.findViewById(monthsId);

		for (int i = 0; i < spinSelect.getCount(); i++) {
			if (spinSelect.getItemAtPosition(i).toString()
					.equalsIgnoreCase(myString)) {
				index = i;
			}
		}
		spinSelect.setSelection(index);
		return index;
	}

	public static String getPastDate(String pastMonth) {

		Log.d(TAG, "entered getPastDate");

		int pastMonthInt = Integer.parseInt(pastMonth.replaceAll("\\D+", ""));
		// Log.w(toString(), "pastMonth Value===>"+pastMonth);

		String pastDate = "";
		SimpleDateFormat format = new SimpleDateFormat("M-dd-yyyy"); // yyyy.MM.dd
																		// HH:mm
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -pastMonthInt);
		pastDate = format.format(cal.getTime());

		// Log.w(toString(),
		// "past date in edialutil.getPastDate===>"+format.format(cal.getTime()));
		return pastDate;
	}

	public static HashMap<String, String> getMonMinVal(View dialogView, int monthsId,
			int minId) {
		Log.d(TAG, "entered getMonMinVal");

		HashMap<String, String> getMonMinDetailsMap = new HashMap<String, String>();
		String MonMinVal = "";

		Spinner spinMonth = (Spinner) dialogView.findViewById(monthsId);
		String months = spinMonth.getSelectedItem().toString();

		Spinner spinMin = (Spinner) dialogView.findViewById(minId);
		String mins = spinMin.getSelectedItem().toString();

		// Log.w(toString(), "outgMonths in getMonMinVal===> "+outgMonths);

		if (months != "" && mins != "") {
			getMonMinDetailsMap.put("months", months);
			getMonMinDetailsMap.put("mins", mins);

			// Log.w(toString(), "entered ok button outgMonths "+months);
			// Log.w(toString(), "entered ok button outgMins"+mins);
			MonMinVal = String.format(SqlDBConstants.OUT_INC_STATIC_STRING,
					months, mins);

			getMonMinDetailsMap.put("monMinsString", MonMinVal);

			if (MonMinVal != "") {
				return getMonMinDetailsMap;
			} else {
				Log.w(TAG, "No Values Set in getMonMinVal()");
			}
			// Log.w(toString(), "format==>"+MonMinVal);
		}
		return getMonMinDetailsMap;
	}

	/**
	 * @deprecated
	 * @param activityContext
	 * @return
	 */
	public Cursor createcallLogCursor(ConfigCallLogs activityContext) {
		Log.d(TAG, "entered createcallLogCursor");
		Cursor createallLogCursor;

		createallLogCursor = activityContext.getContentResolver().query(
				android.provider.CallLog.Calls.CONTENT_URI, null, null, null,
				android.provider.CallLog.Calls.DEFAULT_SORT_ORDER);

		return createallLogCursor;
	}

	public static HashMap<Object, String> createHashMap(String[] elementLabels,
			String[] elementValues) {
		Log.d(TAG, "entered createHashMap");
		// String[] elementLabels = { "pastDate",
		// "curDate","outgPrefMon","outgPrefMin", "incgPrefMon", "incgPrefMin"
		// };
		// String[] elementValues = {
		// activityContext.pastDate.getText().toString(),
		// activityContext.tvDisplayDate.getText().toString(),getPastDate(activityContext.outgMonths),activityContext.outgMins,
		// getPastDate(activityContext.incgMonths), activityContext.incgMins };

		HashMap<Object, String> mapSet = new HashMap<Object, String>();
		for (int i = 0; i < elementLabels.length - 1; i++) {
			mapSet.put(elementLabels[i], elementValues[i]);
			Log.d(TAG, elementLabels[i] + "====" + elementValues[i]);
		}

		return mapSet;
	}
	
	public static Button createButton(Activity activityName, String Label) {
		Log.d(TAG, "entered createButton");
		Log.d(TAG, "activityName===>" + activityName);
		Log.d(TAG, "Label===>" + Label);

		Button myButton = new Button(activityName);
		// myButton.setMaxWidth(10);
		// myButton.setHeight(5);
		myButton.setId(1);
		myButton.setText(Label);
		return myButton;
	}
}
