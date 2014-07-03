package com.donga.lets_eat;

import android.graphics.Bitmap;
import android.media.Image;

public class FoodItem {
	String foodName;
	String foodDescription;
	String foodImageUrl;
	Bitmap imageBitmap;
	
	float foodPrice;
	
	
	public FoodItem(String foodName, String foodDescription, String foodImageUrl,
			float foodPrice) {
		super();
		this.foodName = foodName;
		this.foodDescription = foodDescription;
		this.foodImageUrl = foodImageUrl;
		this.foodPrice = foodPrice;
		imageBitmap=null;
	}
	public String getFoodImageUrl() {
		return foodImageUrl;
	}
	
	public Bitmap getImageBitmap() {
		return imageBitmap;
	}


	public void setImageBitmap(Bitmap imageBitmap) {
		this.imageBitmap = imageBitmap;
	}


	public void setFoodImageUrl(String foodImageUrl) {
		this.foodImageUrl = foodImageUrl;
	}


	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodDescription() {
		return foodDescription;
	}
	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}
	
	
	public float getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(float foo) {
		this.foodPrice = foodPrice;
	}
	

}
