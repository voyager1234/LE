package com.donga.lets_eat.helper_classes;

import com.donga.lets_eat.FoodItem;
import com.donga.lets_eat.R;
import com.donga.lets_eat.R.id;
import com.donga.lets_eat.R.layout;
import com.donga.lets_eat.Restaurant;

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
	static String demoImageAddress="http://t3.gstatic.com/images?q=tbn:ANd9GcRgneebjG4VcK4sBqVonvvLhQvyWlE2l4Fw3wDOm_J-rkKwszFESQ";

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
	
	public static Restaurant getInstanceOfDemoRestaurant(){
		FoodItem f1=new FoodItem("Food Name 1", "Description of Food 1", "", 111.0f);
		Restaurant res=null;
		return res;
	}
	
}
