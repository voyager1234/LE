package com.donga.lets_eat;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.media.Image;

public class Restaurant {

	String restaurantNameString;
	String restaurantDescriptionString;
	String restaurantImageUrl;
	Bitmap imageBitmap;
	
	ArrayList<FoodCategory> allFoodCategories;
	
	public Bitmap getImageBitmap() {
		return imageBitmap;
	}

	public void setImageBitmap(Bitmap imageBitmap) {
		this.imageBitmap = imageBitmap;
	}

	public String getRestaurantNameString() {
		return restaurantNameString;
	}

	public void setRestaurantNameString(String restaurantNameString) {
		this.restaurantNameString = restaurantNameString;
	}

	public String getRestaurantDescriptionString() {
		return restaurantDescriptionString;
	}

	public void setRestaurantDescriptionString(String restaurantDescriptionString) {
		this.restaurantDescriptionString = restaurantDescriptionString;
	}

	public String getRestaurantImageUrl() {
		return restaurantImageUrl;
	}

	public void setRestaurantImageUrl(String restaurantImageUrl) {
		this.restaurantImageUrl = restaurantImageUrl;
	}

	public ArrayList<FoodCategory> getAllFoodCategories() {
		return allFoodCategories;
	}

	public void setAllFoodCategories(ArrayList<FoodCategory> allFoodCategories) {
		this.allFoodCategories = allFoodCategories;
	}

	public Restaurant(String restaurantNameString,
			String restaurantDescriptionString, String restaurantImageUrl,
			ArrayList<FoodCategory> allFoodCategories) {
		super();
		this.restaurantNameString = restaurantNameString;
		this.restaurantDescriptionString = restaurantDescriptionString;
		this.restaurantImageUrl = restaurantImageUrl;
		this.allFoodCategories = allFoodCategories;
		imageBitmap=null;
	}
}
