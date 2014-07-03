package com.donga.lets_eat;

import java.io.Serializable;
import java.util.Date;

public class TempClassSingleton implements Serializable{
	
	//temp values
	private String temp_date_selector;
	private Date temp_reminder_dateDate;

	//singleton object
	
	TempClassSingleton singleton=null;
	
	public TempClassSingleton getInstance() {
		if (singleton==null) {
			singleton=new TempClassSingleton();
		}		
		return singleton;
	}
	
	private TempClassSingleton(){
		super();
	}
	
	
	
}
