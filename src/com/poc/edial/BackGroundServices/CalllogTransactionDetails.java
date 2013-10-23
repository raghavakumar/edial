package com.poc.edial.BackGroundServices;

public class CalllogTransactionDetails {	
	 public static String name;
	 public static  String phone;
	 public static  String callType;
	/**
	 * @return the name
	 */
	public static String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public static void setName(String contactName) {
		name = contactName;
	}
	/**
	 * @return the phone
	 */
	public static String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public static void setPhone(String contactPhone) {
		phone = contactPhone;
	}
	/**
	 * @return the callType
	 */
	public static String getCallType() {
		return callType;
	}
	/**
	 * @param callType the callType to set
	 */
	public static void setCallType(String phoneCallerType) {
		callType = phoneCallerType;
	}

}
