package com.donga.lets_eat.adapters;

import com.donga.lets_eat.Restaurant;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

	Restaurant mRestaurant;
	
	public ViewPagerAdapter(FragmentManager fm,Restaurant mRestaurant) {
		super(fm);
		this.mRestaurant=mRestaurant;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
