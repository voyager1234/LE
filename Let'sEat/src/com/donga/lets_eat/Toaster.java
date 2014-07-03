package com.donga.lets_eat;

import android.content.Context;
import android.widget.Toast;

public class Toaster {
	
	public static void showToast(Context context,String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	
	public static void showLongToast(Context context,String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

}
