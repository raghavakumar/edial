package com.poc.edial.model;

public class ContactListItems {
	 
	 String slno;
	 String name;
	 String phone;
	 String callType;
	 boolean selected = false;
	 
	 public String getSlno() {
	  return slno;
	 }
	 
	 public void setSlno(String slno) {
	  this.slno = slno;
	 }
	 
	 public String getName() {
	  return name;
	 }
	 
	 public void setName(String name) {
	  this.name = name;
	 }
	 
	 public String getPhone() {
	  return phone;
	 }
	 
	 public void setPhone(String phone) {
	  this.phone = phone;
	 }
	 
	 public String getcallType() {
		  return callType;
	 }
		 
	 public void setcallType(String callType) {
		  this.callType = callType;
	 }
	 
	 public boolean isSelected() {
		  return selected;
	 }
	 
	 public void setSelected(boolean selected) {
	  this.selected = selected;
	 }
	 
	 @Override
	    public String toString() {
	        return name + "\n<font size='2'>" + callType +"</font>";
	    }
	 
	}