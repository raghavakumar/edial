package com.poc.edial.model;

public class LogPreferenceListItems {
	 
	 String slno;
	 String name;
	 String phone;	 
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
	 
	 public boolean isSelected() {
		  return selected;
	 }
	 
	 public void setSelected(boolean selected) {
	  this.selected = selected;
	 }
	 
	}
