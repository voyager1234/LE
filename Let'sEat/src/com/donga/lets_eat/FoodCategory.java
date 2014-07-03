package com.donga.lets_eat;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.media.Image;

public class FoodCategory {
	String categoryNameString;
	String categoryImageUrl;
	Bitmap imageBitmap;
	
	ArrayList<FoodItem> itemsInCategoryArrayList;
	
	public FoodCategory(String categoryNameString, String categoryImageUrl,
			ArrayList<FoodItem> itemsInCategoryArrayList) {
		super();
		this.categoryNameString = categoryNameString;
		this.categoryImageUrl = categoryImageUrl;
		this.itemsInCategoryArrayList = itemsInCategoryArrayList;
		imageBitmap=null;
		//updated
		//updated 2nd time
		//ss
	}
	
	public Bitmap getImageBitmap() {
		return imageBitmap;
	}
	public void setImageBitmap(Bitmap imageBitmap) {
		this.imageBitmap = imageBitmap;
	}
	
	
	public String getCategoryNameString() {
		return categoryNameString;
	}
	public void setCategoryNameString(String categoryNameString) {
		this.categoryNameString = categoryNameString;
	}
	public String getCategoryImageUrl() {
		return categoryImageUrl;
	}
	public void setCategoryImageUrl(String categoryImageUrl) {
		this.categoryImageUrl = categoryImageUrl;
	}
	public ArrayList<FoodItem> getItemsInCategoryArrayList() {
		return itemsInCategoryArrayList;
	}
	public void setItemsInCategoryArrayList(
			ArrayList<FoodItem> itemsInCategoryArrayList) {
		this.itemsInCategoryArrayList = itemsInCategoryArrayList;
	}
	

}
