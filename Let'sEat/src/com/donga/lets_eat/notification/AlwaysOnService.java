package com.donga.lets_eat.notification;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;

public class AlwaysOnService extends Service {
	PowerManager mPM;
	PowerManager.WakeLock mWakeLock;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mPM = (PowerManager) getSystemService(Context.POWER_SERVICE);
		mWakeLock = mPM.newWakeLock(PowerManager.FULL_WAKE_LOCK,
				"MY ALWAYS ON TAG");
		mWakeLock.acquire();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mWakeLock.isHeld()) {
			mWakeLock.release();
		}
	}

}
