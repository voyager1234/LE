package com.donga.lets_eat.adapters;

import java.io.InputStream;

import com.donga.lets_eat.R;
import com.donga.lets_eat.Restaurant;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter {
	Restaurant restaurant;
	Activity activity;
	LayoutInflater inflater;
	
	public DrawerAdapter(Activity activity,Restaurant restaurant) {
		this.restaurant=restaurant;
		this.activity=activity;
		inflater=activity.getLayoutInflater();
		// TODO Auto-generated constructor stub
		//
	}
	
	@Override
	public int getCount() {
		return restaurant.getAllFoodCategories().size();
	}

	@Override
	public Object getItem(int position) {
		return restaurant.getAllFoodCategories().get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=convertView;
		if (view==null) {
			view=inflater.inflate(R.layout.drawer_item, null);
		}
		
		TextView categoryNameTextView=(TextView) view.findViewById(R.id.drawerItemCategoryName);
		ImageView categoryImageView=(ImageView) view.findViewById(R.id.drawerItemCategoryImageView);
		
		categoryNameTextView.setText(restaurant.getAllFoodCategories().get(position).getCategoryNameString());
		if (restaurant.getAllFoodCategories().get(position).getImageBitmap()==null) {
			categoryImageView.setImageResource(R.drawable.ic_launcher);
			new ImageViewUpdater(categoryImageView, restaurant.getAllFoodCategories().get(position).getImageBitmap()).execute(restaurant.getAllFoodCategories().get(position).getCategoryImageUrl());
		}else {
			categoryImageView.setImageBitmap(restaurant.getAllFoodCategories().get(position).getImageBitmap());
		}
		
		
		return view;
	}
	
	private class ImageViewUpdater extends AsyncTask<String, Void, Bitmap>{

		ImageView imgView;
		Bitmap bitmap;
		
		public ImageViewUpdater(ImageView imgView, Bitmap bmp) {
			// TODO Auto-generated constructor stub
			this.imgView=imgView;
			this.bitmap=bmp;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		@Override
		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
	        Bitmap mIcon11 = null;
	        try {
	            InputStream in = new java.net.URL(urldisplay).openStream();
	            mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        
	        bitmap=mIcon11;
	        return mIcon11;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			imgView.setImageBitmap(result);
		}
	}
	

	
}
