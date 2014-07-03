package com.donga.lets_eat.notification;
import android.widget.ImageView;

public class WifiRank implements Comparable<WifiRank> {
	String wifi_name;
	int weight;

	WifiRank(String wifi_name, int weight) {
		this.wifi_name = wifi_name;
		this.weight = weight;
	}

	@Override
	public int compareTo(WifiRank o) {
		WifiRank bean = (WifiRank) o;

		if (bean.weight >= this.weight)
			return 1;
		else if (bean.weight < this.weight)
			return -1;
		else
			return 0;
	}
}
