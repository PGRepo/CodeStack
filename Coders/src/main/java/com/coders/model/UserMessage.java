package com.coders.model;

import java.util.ArrayList;

public class UserMessage {
	
	Users user;
	String message;
	ArrayList<String> listOfDefaultImages;
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<String> getListOfDefaultImages() {
		return listOfDefaultImages;
	}
	public void setListOfDefaultImages(ArrayList<String> listOfSefaultImages) {
		this.listOfDefaultImages = listOfSefaultImages;
	}
	
	
	
	

}
