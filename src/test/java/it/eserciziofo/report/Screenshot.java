package it.eserciziofo.report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import it.eserciziofo.utility.DriverFactory;

public class Screenshot {
	static WebDriver driver= DriverFactory.getDriver();
	 public static String getScreen() throws IOException {
         
	       String screenShot = System.getProperty("user.dir") + "/Screenshot/file.png";
	        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	         try {
	        	 FileUtils.copyFile(scrFile, new File(screenShot));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	         return screenShot;
	         }
	 
	 
}
