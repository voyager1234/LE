package com.donga.lets_eat;

import android.R.integer;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelperMethods {

	public static String removeBlankSpaces(final String string) {
		if (string==null) {
			return "";
		}
		return string.replaceAll("\\s", "");
	}
	
	public static String removeBlankSpacesAndMakeLowerCase(final String string) {
		if (string==null) {
			return "";
		}
		return string.replaceAll("\\s", "").toLowerCase();
	}
	
	public static String removeSpecialCharacters(final String string) {
		if (string==null) {
			return "";
		}
		return string.replaceAll("[-+.^:,]","");
	}
	
	public static boolean isBlankString(final String string) {
		if (string==null) {
			return true;
		}
		
		String tempString=removeBlankSpaces(string);
		if (tempString.equals(null)||tempString.length()==0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static AlertDialog.Builder returnSimpleAlertDialogBuilder(Context context,int backGroundColorResourceID,int imageResourceID, String dialogText) {
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
		LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view=layoutInflater.inflate(R.layout.confirmation_alert, null);
		ImageView imageView=(ImageView) view.findViewById(R.id.confirmationImage);
		imageView.setImageResource(imageResourceID);
		LinearLayout linearLayout=(LinearLayout) view.findViewById(R.id.coloredlayout);
		linearLayout.setBackgroundResource(backGroundColorResourceID);
		TextView textView=(TextView) view.findViewById(R.id.confirmationMessage);
		textView.setText(dialogText);	
		builder.setView(view);
		return builder;		
	}
	
}
