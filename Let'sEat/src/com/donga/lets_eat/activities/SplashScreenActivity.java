package com.donga.lets_eat.activities;


import com.donga.lets_eat.R;
import com.donga.lets_eat.R.id;
import com.donga.lets_eat.R.layout;
import com.donga.lets_eat.helper_classes.Constants;

import android.os.Bundle;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends Activity {

	Intent mIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mIntent=getIntent();
		
		final String intentExtraString=mIntent.getStringExtra(Constants.INTENT_EXTRA_KEY);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash_screen);

		ImageView splashImageView = (ImageView) findViewById(R.id.splashImageView);
		TextView splashTextView = (TextView) findViewById(R.id.splashTextView);
		TextView splastZixoftTextView=(TextView) findViewById(R.id.splashZixoftTextView);
		
//		ObjectAnimator mover = ObjectAnimator.ofFloat(splashImageView,
//				"translationY", -300f, 0f);
		ObjectAnimator mover = ObjectAnimator.ofFloat(splashImageView, "alpha",
				0f, 1f);
		mover.setDuration(1000);
		
		ObjectAnimator mover2 = ObjectAnimator.ofFloat(splashTextView,
				"y", 200f, 0f);
		mover.setDuration(1000);
		
		ObjectAnimator chexFadeIn = ObjectAnimator.ofFloat(splashTextView, "alpha",
				0f, 1f);
		chexFadeIn.setDuration(1000);
		
		ObjectAnimator ziXoftFadeIn = ObjectAnimator.ofFloat(splastZixoftTextView, "alpha",
				0f, 1f);
		ziXoftFadeIn.setDuration(1000);
		
		ObjectAnimator imageFadeIn = ObjectAnimator.ofFloat(splashImageView, "alpha",
				0f, 1f);
		imageFadeIn.setDuration(1000);
		
		AnimatorSet animatorSet1 = new AnimatorSet();
		animatorSet1.playTogether(mover,imageFadeIn);
		
		AnimatorSet animatorSet2=new AnimatorSet();
		animatorSet2.playTogether(chexFadeIn,ziXoftFadeIn);
		
		AnimatorSet allAnimatorSet=new AnimatorSet();
		allAnimatorSet.playSequentially(animatorSet1,animatorSet2);
		allAnimatorSet.start();

		Runnable waiterRunnable = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2500);
					Intent intent = new Intent(getApplicationContext(),
							MainActivity.class);
					intent.putExtra(Constants.INTENT_EXTRA_KEY, intentExtraString);
					startActivity(intent);
					SplashScreenActivity.this.finish();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		Thread waiterThread = new Thread(waiterRunnable);
		waiterThread.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.splash_screen, menu);
		return false;
	}

}
