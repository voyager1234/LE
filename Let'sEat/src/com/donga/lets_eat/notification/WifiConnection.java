package com.donga.lets_eat.notification;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;

public class WifiConnection {
	public static void setWifiConnection(ScanResult result, WifiConfiguration wfc){
		wfc.SSID = "\"".concat(result.SSID.toString()).concat("\"");
		wfc.status = WifiConfiguration.Status.DISABLED; // CURRENT 0, DISABLED 1, ENABLED 2
		wfc.priority = 40;
		
		// Capabilites가 Open일 때 설정	
		wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
		wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		wfc.allowedAuthAlgorithms.clear();
		wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		 
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);

		
		// WEP 방식
		wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
		wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
		wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
		wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		 
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
		wfc.wepKeys[0] = "\"".concat("password").concat("\""); // password에는 해당 암호 입력
		wfc.wepTxKeyIndex = 0;

		
		// WPA, WPA2 방식
		wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
		wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
		wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
		wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		wfc.preSharedKey = "\"".concat("password").concat("\""); // password에는 해당 암호 입력

		
		
	}
}
