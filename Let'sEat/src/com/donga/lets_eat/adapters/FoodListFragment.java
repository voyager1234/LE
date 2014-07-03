package com.donga.lets_eat.adapters;

import com.donga.lets_eat.FoodCategory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FoodListFragment extends Fragment {
	FoodCategory mFoodCategory;
	
	public FoodListFragment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static FoodListFragment getInstance(FoodCategory foodCategory) {
		FoodListFragment mFrag=new FoodListFragment();
		mFrag.mFoodCategory=foodCategory;
		return mFrag;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
}
