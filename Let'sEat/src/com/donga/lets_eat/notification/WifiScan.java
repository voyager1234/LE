// RSSI : WIFI 수신 감도, -100에 가까울수록 안좋음 0 에 가까울수록 좋음.

package com.donga.lets_eat.notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.donga.lets_eat.R;

public class WifiScan extends Activity {
	private static final String TAG = "WIFIScanner";
	private static final String TAG_SON = "WIFIWifiRank";

	/****************************************************
	 * Notification
	 ***************************************************/
	private boolean isServiceOn = false;
	private NotificationManager mNM;
	private Notification mNoti;

	public static boolean ck;
	private TextView mTxtView;
	//

	// WifiManager variable
	WifiManager wifimanager;
	List<WifiRank> wifilistrank = new ArrayList<WifiRank>();

	// UI variable
	TextView textStatus;

	private int scanCount = 0;
	String text = "";
	String result = "";

	// Additional UI
	Button btnTop1, btnTop2, btnTop3;
	int topCount = -1;
	int forlimit;

	// WifiConnection
	WifiConfiguration wfc = new WifiConfiguration();

	TextView notification;

	private List<ScanResult> mScanResult; // ScanResult List
											// 여기에 Scan 결과를 저장할 것임.

	private BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			final String action = intent.getAction();
			if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
				getWIFIScanResult(); // get WIFISCanResult
				wifimanager.startScan(); // for refresh
			} else if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
				sendBroadcast(new Intent("wifi.ON_NETWORK_STATE_CHANGED"));
			}
		}
	};

	public void getWIFIScanResult() {
		/**********************************************************
		 * Notification
		 **********************************************************/
		mNM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent mPendingIntent = PendingIntent.getActivity(
				getApplicationContext(), 0, new Intent(getApplicationContext(),
						WifiScan.class), PendingIntent.FLAG_UPDATE_CURRENT);

		mNoti = new NotificationCompat.Builder(getApplicationContext())
				.setContentTitle("^^" + setInformation.notificationString1).setContentText("Let's eat")
				.setSmallIcon(R.drawable.ic_launcher).setTicker("Let's eat! " + setInformation.notificationString1 + "!")
				.setAutoCancel(true).setContentIntent(mPendingIntent).build();
		if (ck == true) {
			startService(new Intent(this, AlwaysOnService.class));
			mTxtView.setText("Always Screen On : On");
			isServiceOn = true;
		}

		else if (ck == false) {
			stopService(new Intent(this, AlwaysOnService.class));
			mTxtView.setText("Always SCreen On : Off");
			isServiceOn = false;
		}
		//
		
		
		mScanResult = wifimanager.getScanResults(); // ScanResult
		// Scan count
		textStatus.setText("Scan count is \t" + ++scanCount + " times \n");

		topCount = -1;
		wifilistrank.clear();
		// 이 부분을 이용해서 fiwi에 접속할 수 있도록 만들어야 함.
		textStatus.append("=======================================\n");
		forlimit = 0;
		for (int i = 0; i < mScanResult.size(); i++) {
			ScanResult result = mScanResult.get(i);

			// 암호 존재 여부에 따라서 다른 리스트를 만들어보자
			// 미리 만들어 놓은 wifilistrank_password 사용
			WifiRank wifilist = new WifiRank(result.SSID.toString(),
					result.level);

			// WifiConnection 이라는게 새로 추가한 부분들이다.
			// WifiConnection
			// 아래 메소드 수정해줘야 함!!
			WifiConnection.setWifiConnection(result, wfc);
			int networkId = wifimanager.addNetwork(wfc);
			Log.d("aaa", "networkId(" + Integer.toString(networkId) + ")");
			if (networkId != -1) {
				// success, can call wfMgr.enableNetwork(networkId, true) to
				// connect
			}

			wifilistrank.add(wifilist);

			// "1. SSID : 와이파이이름	RSSI : 측정RSSI dBm" 이렇게 출력됨

			// textStatus.append((i + 1) + ". SSID : " + result.SSID.toString()
			// + "\t\t RSSI : " + result.level + " dBm\n");
			textStatus.append((i + 1) + ". " + result.SSID.toString() + "\t\t"
					+ result.level + "dBm" + "\n    "
					+ result.capabilities.toString() + "\t\t"
					+ result.frequency + "\n    wfc(" + wfc.SSID + " / "
					+ wfc.status + " / " + wfc.priority + ")\n");
			forlimit++;

			if (forlimit >= 10) {
				forlimit = 0;
				break;
			}

			/*
			 * // top 3 Log.d(TAG_SON,"WIFIWifiRank"); if (i == 0) {
			 * btnTop1.setText(result.SSID.toString()); } else if (i == 1) {
			 * btnTop2.setText(result.SSID.toString()); } else if (i == 2) {
			 * btnTop3.setText(result.SSID.toString()); }
			 */
			topCount++;
		}
		textStatus.append("=======================================\n");
		Collections.sort(wifilistrank);
		// Collections.reverse(wifilistrank);

		// 나중에는 RSSI 감도를 평균으로 하여서, 랭킹을 구하는 것이 좋겠다.
		int rankCount = 0;

		/******************************************************
		 * 인접한 와이파이 해당하는 음식점 탐색
		 ******************************************************/
		int ticCount = 0;
		for (WifiRank r : wifilistrank) {
			Log.d(TAG_SON, r.wifi_name);
			if (rankCount > wifilistrank.size())
				break;
			if (ticCount == 0) {
				if (r.wifi_name.equalsIgnoreCase("app2.4g_1")) {
					setInformation.notificationString1 = "맥도날드";
					notification.setText(setInformation.notificationString1);
					btnTop1.setText("맥도날드");
					ticCount++;
				} else if (r.wifi_name.equalsIgnoreCase("app2.4g_2")) {
					setInformation.notificationString1 = "설빙";
					btnTop1.setText("설빙");
					ticCount++;
					notification.setText(setInformation.notificationString1);
				} else if (r.wifi_name.equalsIgnoreCase("app2.4g_3")) {
					setInformation.notificationString1 = "스타벅스";
					btnTop1.setText("스타벅스");
					ticCount++;
					notification.setText(setInformation.notificationString1);
				} else if (r.wifi_name.equalsIgnoreCase("app2.4g_4")) {
					setInformation.notificationString1 = "한솥도시락";
					btnTop1.setText("한솥도시락");
					ticCount++;
					notification.setText(setInformation.notificationString1);
				}
			} else if (ticCount == 1) {
				if (r.wifi_name.equalsIgnoreCase("app2.4g_1")) {
					setInformation.notificationString2 = "맥도날드";
					btnTop2.setText("맥도날드");
					ticCount++;
					notification.setText(setInformation.notificationString1);
				} else if (r.wifi_name.equalsIgnoreCase("app2.4g_2")) {
					setInformation.notificationString2 = "설빙";
					btnTop2.setText("설빙");
					ticCount++;
					notification.setText(setInformation.notificationString1);
				} else if (r.wifi_name.equalsIgnoreCase("app2.4g_3")) {
					setInformation.notificationString2 = "스타벅스";
					btnTop2.setText("스타벅스");
					ticCount++;
					notification.setText(setInformation.notificationString1);
				} else if (r.wifi_name.equalsIgnoreCase("app2.4g_4")) {
					setInformation.notificationString2 = "한솥도시락";
					btnTop2.setText("한솥도시락");
					ticCount++;
					notification.setText(setInformation.notificationString1);
				}
			} else if (ticCount == 2) {
				if (r.wifi_name.equalsIgnoreCase("app2.4g_1")) {
					setInformation.notificationString3 = "맥도날드";
					btnTop3.setText("맥도날드");
					ticCount++;
					notification.setText(setInformation.notificationString1);
					break;
				} else if (r.wifi_name.equalsIgnoreCase("app2.4g_2")) {
					setInformation.notificationString3 = "설빙";
					btnTop3.setText("설빙");
					ticCount++;
					notification.setText(setInformation.notificationString1);
					break;
				} else if (r.wifi_name.equalsIgnoreCase("app2.4g_3")) {
					setInformation.notificationString3 = "스타벅스";
					btnTop3.setText("스타벅스");
					ticCount++;
					notification.setText(setInformation.notificationString1);
					break;
				} else if (r.wifi_name.equalsIgnoreCase("app2.4g_4")) {
					setInformation.notificationString3 = "한솥도시락";
					btnTop3.setText("한솥도시락");
					ticCount++;
					notification.setText(setInformation.notificationString1);
					break;
				}
			}
			rankCount++;
		}
		Log.d(TAG_SON, "topCount : " + Integer.toString(topCount));

	}

	public void initWIFIScan() {
		// init WIFISCAN
		scanCount = 0; // "WIFI SCAN" 버튼을 눌리면 scanCount = 0 부터 다시 센다.
		text = ""; // 더불어 String text를 ""로 초기화 시킨다.

		// 여기서 이것이 실행되면 위에 BroadCastReceiver에서 받아서 사용한다.
		final IntentFilter filter = new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
		/*
		 * public static final String SCAN_RESULTS_AVAILABLE_ACTION Added in API
		 * level 1 An access point scan has completed, and results are available
		 * from the supplicant. Call getScanResults() to obtain the results.
		 * Constant Value: "android.net.wifi.SCAN_RESULTS"
		 */
		filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		/*
		 * String NETWORK_STATE_CHANGED_ACTION Broadcast intent action
		 * indicating that the state of Wi-Fi connectivity has changed.
		 */
		registerReceiver(mReceiver, filter);
		wifimanager.startScan();
		Log.d(TAG, "initWIFIScan()");
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wifi_scan);

		/*************************************************************
		 * Notification
		 *************************************************************/
		mTxtView = (TextView) findViewById(R.id.textView1);
		

		Switch onOffSwitch = (Switch) findViewById(R.id.on_off_switch);
		onOffSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				ck = isChecked;
				Log.v("Switch State=", "" + isChecked);
				Log.v("Switch State=", "" + ck);
			}
		});

		
		// Setup UI
		textStatus = (TextView) findViewById(R.id.textStatus);
		Log.d("msg1", "h");
		// Setup OnClickListener

		// Setup WIFI
		wifimanager = (WifiManager) getSystemService(WIFI_SERVICE);
		Log.d(TAG, "Setup WIfiManager getSystemService");

		// Additional UI
		btnTop1 = (Button) findViewById(R.id.btntop1);
		btnTop2 = (Button) findViewById(R.id.btntop2);
		btnTop3 = (Button) findViewById(R.id.btntop3);
		notification = (TextView) findViewById(R.id.notification);

		// if WIFIEnabled
		if (wifimanager.isWifiEnabled() == false) // isWifiEnabled() : Return
													// whether Wi-Fi is enabled
													// or disabled.
													// boolean 형으로 return 함.
			wifimanager.setWifiEnabled(true); // setWifiEnabled(boolean enabled)
												// : Enable or disable Wi-Fi.
		initWIFIScan();
	}

	public void printToast(String messageToast) {
		Toast.makeText(this, messageToast, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (isServiceOn == true) {
			mNM.notify(7777, mNoti);
		}
	}
}