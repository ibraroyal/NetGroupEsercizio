package it.eserciziofo.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
	final static String NOME_DRIVE = "webdriver.chrome.driver";
	final static String PATH_DRIVE = ".\\src\\main\\resources\\driver\\chromedriver.exe";
	private static WebDriver driver;

	public static WebDriver getDriver() {
		return driver;
	}

	public static WebDriver runBrowser(String url) throws Exception {
		System.setProperty(NOME_DRIVE, PATH_DRIVE);
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--lang=en");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.get(url);
			Thread.sleep(3000);
		} catch (Exception e) {
			throw e;
		}
		return driver;
	}

}
