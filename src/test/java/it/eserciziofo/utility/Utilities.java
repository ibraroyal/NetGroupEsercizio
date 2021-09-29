package it.eserciziofo.utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {

	WebDriver driver = DriverFactory.getDriver();

	WebDriverWait wait;
	final String terzoLivello="www";

	

	public List<WebElement> listaElementi(By locatorValue) {
		List<WebElement> listEle = null;
		try {
			listEle = driver.findElements(locatorValue);
		} catch (Exception e) {
			throw e;
		}
		return listEle;
	}

	public boolean matchEmail(String email) {
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public boolean matchPhon(String numeroTel) {
		Pattern pattern = Pattern.compile("^(.+)[1-9]{1}[0-9]{7,11}$");
		Matcher matcher = pattern.matcher(numeroTel);
		return matcher.matches();
	}

	public void waitElement(int time, WebElement ele) {
		wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public static int verifyLinks(String linkUrl) throws Exception {
		URL url;
		HttpURLConnection httpURLConnect = null;
		try {
			url = new URL(linkUrl);
			httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.setConnectTimeout(5000);
			httpURLConnect.connect();
			httpURLConnect.disconnect();
		} catch (IOException e) {
			throw new Exception();
		}
		
		httpURLConnect.disconnect(); //sta riga potrebbe dare problemi
		return httpURLConnect.getResponseCode();

	}
	
	public String splitUrl(String url) {
		if(url.split(Pattern.quote("."))[0].contains(terzoLivello)) {
			return url.split(Pattern.quote("."))[1];
		}else {
			return url.split(Pattern.quote("."))[0];
		}
	}
	
	
	 public String allAttributes(WebElement ele) {
		 
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		 Object elementAttributes = executor.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",ele);

		
		return elementAttributes.toString();
	 }
	
	
	
	
	
	
	
	
	
}
