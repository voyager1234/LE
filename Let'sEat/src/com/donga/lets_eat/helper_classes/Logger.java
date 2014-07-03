package com.donga.lets_eat.helper_classes;

import java.util.ArrayList;


import android.util.Log;

public class Logger {
	//web commmit
	final static String logTag="LetsEat";
	
	public static void log(String message) {
		Log.d(logTag, message);
	}
	
	
	public static void log(boolean[] allChecklistItems) {
		StringBuilder stringBuilder=new StringBuilder();
		stringBuilder.append("[");
		for (boolean checklistItem : allChecklistItems) {
			stringBuilder.append((checklistItem? "1":"0")+" ");
		}
		stringBuilder.append("]");
		
		Log.d(logTag, stringBuilder.toString());
	}
}
