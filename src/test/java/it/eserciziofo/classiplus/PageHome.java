package it.eserciziofo.classiplus;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import it.eserciziofo.utility.Utilities;
import it.eserciziofo.utility.FileLog;
import it.eserciziofo.utility.Utility;

public class PageHome {
	FileLog log = new FileLog("Check_home.txt");
	WebDriverWait wait;
	Utility util = new Utility();

	


	public void checkListaElementVisibility(String locatorType,String locator, String messLog, int time) throws Exception {
		
		try {
			List<WebElement> listElementi = util.listaElementi(locator);
			for (WebElement webElement : listElementi) {
				util.moveMouseSuElemento(webElement);
				wait = new WebDriverWait(Utilities.getWebDrive(), time);
				wait.until(ExpectedConditions.visibilityOf(webElement));
				
				System.out.println(webElement.getText() + " : " + webElement.isDisplayed());
				Assert.assertTrue(webElement.isDisplayed());
			}
			System.out.println("totale elementi: " + listElementi.size());
		} catch (Exception e) {
			throw e;
		} catch (AssertionError e) {
			log.log.warning(e.getMessage());
		}

	}
	public void checkElementoVisibility(String locator, String messLog, int time) throws Exception {

		try {
			WebElement webElement = util.creaElemento(locator, time);
			System.out.println(webElement.getText() + " : " + webElement.isDisplayed());
			Assert.assertTrue(webElement.isDisplayed());
		} catch (Exception e) {
			throw e;
		} catch (AssertionError e) {
			log.log.warning(e.getMessage());
		}
	}
	public void checkLink(String locator, String url ) throws Exception  {
		try {
			List<WebElement> listElementi = util.listaElementi(locator);
			for (WebElement webElement : listElementi) {
				if (webElement.getAttribute("href").equals(url)) {
					log.log.info("Favico icon ok del sito:" + Utilities.getWebDrive().getCurrentUrl());
				}

			}
			System.out.println("totale elementi: " + listElementi.size());
		} catch (Exception e) {
			throw e;
		} catch (AssertionError e) {
			log.log.warning(e.getMessage());
		}

	}
	
	

}
