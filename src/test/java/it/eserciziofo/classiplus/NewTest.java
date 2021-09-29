package it.eserciziofo.classiplus;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewTest {

	public static void main(String[] args) throws IOException {
		HttpURLConnection httpURLConnect = null;
		String url = "https://siinfo.eu/";
		URL linkUrl = new URL(url);
		httpURLConnect = (HttpURLConnection) linkUrl.openConnection();
		httpURLConnect.setConnectTimeout(5000);
		httpURLConnect.connect();
		System.out.println(linkUrl.getHost());
		httpURLConnect.disconnect();

	}
}