package it.eserciziofo.pom;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import it.eserciziofo.utility.ReadFile;
import it.eserciziofo.utility.Utilities;
import it.eserciziofo.utility.checkAssert;

public class BasePage {

	Utilities util = new Utilities();

	final String ATTRIBUTI_HREF = "href";
	final String ATTRIBUTI_TARGET = "target";
	final String ATTRIBUTI_NAME = "name";
	final String ATTRIBUTI_CONTENT = "content";
	final String ATTRIBUTI_DESCRIPTION = "description";
	final By TYPE_TAG_A = By.tagName("a");
	final By TYPE_TAG_META = By.tagName("meta");
	final By TYPE_TAG_LINK = By.tagName("link");
	final String TYPE_ATTRIBUTI_REL = "rel";
	final String VALUE_OF_REL = "icon";
	final String VALUE_BLANK = "_blank";

	final String FUNCTION_JAVASCRIPT = "javascript";

	final String URL_FACEBOOK = "facebook";
	final String URL_LINKEDIN = "linkedin";
	final String URL_INSTAGRAM = "instagram";
	final String URL_MAPS = "https://goo.gl/maps";
	final String gmbiocosmetics = "gmbiocosmetics";
	final String siinfo = "siinfo";
	Map<String, Integer> listaInterni = new HashedMap<String, Integer>();
	Map<String, Integer> listaApp = new HashedMap<String, Integer>();

	List<WebElement> listaLink = util.listaElementi(TYPE_TAG_A);
	public static checkAssert soft = new checkAssert();
	int i = 0;

	public void checkLink() throws Exception {

		String valueHREF = null;
		for (i = 0; i < listaLink.size(); i++) {
			valueHREF = listaLink.get(i).getAttribute(ATTRIBUTI_HREF);
			/*
			 * report href null/blank/javaScript 1 href
			 */
			if (valueHREF == null || valueHREF.isBlank() || valueHREF.isEmpty()) {
				soft.fail("href vuoto/nullo, la riga del codice: " + util.allAttributes(listaLink.get(i)));
				listaLink.remove(i);
				i--;
				continue;
			}
			/* il risultato di questo controllo va nel report null/blank/javaScript */
			if (valueHREF.startsWith(FUNCTION_JAVASCRIPT)) {
				soft.assertTrue(true, "href contenente javaScript: " + valueHREF);
				listaLink.remove(i);
				i--;
				continue;
			}
//skjskjskjks
//			report social media 2
			if (valueHREF.contains(URL_FACEBOOK) || valueHREF.contains(URL_LINKEDIN) || valueHREF.contains(URL_MAPS)
					|| valueHREF.contains(URL_INSTAGRAM)) {
				if (checkTarget(listaLink.get(i))) {
					soft.assertTrue(true, "href contenente un url consentito : " + valueHREF + ", con un target=blank");
				} else {
					soft.fail("href contenente un url consentito : " + valueHREF + ", con un target diverso da blank");
				}
				listaLink.remove(i);
				i--;
				continue;
			}
			// report email/telefono 3
			if (util.matchEmail(valueHREF) || util.matchPhon(valueHREF)) {
				soft.assertTrue(true, "href contenente un url consentito: " + valueHREF);
				listaLink.remove(i);
				i--;
				continue;
			}

		}

	}

	public void checkLinkInterni(WebDriver driver) throws Exception {

		String valueHREF = null;

		for (i = 0; i < listaLink.size(); i++) {
			valueHREF = listaLink.get(i).getAttribute(ATTRIBUTI_HREF);
			URL url1 = new URL(driver.getCurrentUrl());
			if (valueHREF.equals(driver.getCurrentUrl().replace("www.", ""))) {
				listaLink.remove(i);
				i--;
				continue;
			}
			if (valueHREF.contains(util.splitUrl(url1.getHost()))) {
				if (valueHREF.contains("#") || valueHREF.contains("?")) {
					listaLink.remove(i);
					i--;
					continue;
				}
				/*
				 * report check href interno 4
				 */
				if (checkLinkRespose(valueHREF)) {

					if (checkTarget(listaLink.get(i))) {
						soft.fail("href interno: " + valueHREF
								+ ": response diversa da 404, con attributo uguale a blank ");
					} else {
						soft.assertTrue(true, "href interno: " + valueHREF
								+ ": response diversa da 404, con un attributo diverso da blank ");
					}

				} else {
					soft.fail("href interno: " + valueHREF + ": response 404");
				}
			} else {
				/* report check href esterno 5 */
				if (driver.getCurrentUrl().contains(gmbiocosmetics) && valueHREF.contains(siinfo)) {
					soft.assertTrue(true, "href contenente un url consentito: " + valueHREF);
					listaLink.remove(i);
					i--;
					continue;
				} else {
					soft.fail("href esterno: " + valueHREF);
				}
			}
		}
	}

	public boolean checkLinkRespose(String valueHREF) throws Exception {

		if (Utilities.verifyLinks(valueHREF) == 404) {
			return false;
		}

		return true;
	}

	public boolean checkTarget(WebElement element) {
		if (element.getAttribute(ATTRIBUTI_TARGET).equals(VALUE_BLANK)) {
			return true;
		} else {
			return false;
		}
	}

	public void checkMetaFavicon() throws Exception {
		/* report check favicon 6 */
		List<WebElement> listaElementiMeta = util.listaElementi(TYPE_TAG_LINK);
		for (WebElement webElement : listaElementiMeta) {
			if (webElement.getAttribute(TYPE_ATTRIBUTI_REL).equals(VALUE_OF_REL)) {

				if (webElement.getAttribute(ATTRIBUTI_HREF) == null
						|| webElement.getAttribute(ATTRIBUTI_HREF).isBlank()) {
					soft.fail("favicon null/blank");
				} else {
					soft.assertTrue(true, "favicon trovata: " + webElement.getAttribute(ATTRIBUTI_HREF));
				}

			}
		}
	}

	/**
	 * @param driver
	 * @throws Exception
	 */
	public void checkUrlTitle(WebDriver driver) throws Exception {
		/*
		 * report check Title 7
		 */
		if (driver.getTitle() == null || driver.getTitle().isBlank()) {
			soft.fail("Title null/blank");
		} else {
			soft.assertTrue(true, "titolo trovato: " + driver.getTitle());
		}
	}

	public void checkDescrizione() throws Exception {
		/*
		 * report check descrizione 8
		 */
		List<WebElement> listaElementiMeta = util.listaElementi(TYPE_TAG_META);
		for (WebElement webElement : listaElementiMeta) {
			if (webElement.getAttribute(ATTRIBUTI_NAME).equals(ATTRIBUTI_DESCRIPTION)) {
				if (webElement.getAttribute(ATTRIBUTI_CONTENT).isBlank()
						|| webElement.getAttribute(ATTRIBUTI_CONTENT) == null) {
					soft.fail("descrizione null/blank");
				} else {
					soft.assertTrue(true, "descrizione trovata: " + webElement.getAttribute(ATTRIBUTI_CONTENT));
				}
			}
		}

	}

	public void checkAllPages(WebDriver driver) throws Exception {
		String valueHREF;
		for (Object link : ReadFile.letturaFile().toArray()) {
			listaApp.put((String) link, 1);
			listaApp.put(driver.getCurrentUrl(), 1);
		}

		for (i = 0; i < listaLink.size(); i++) {
			valueHREF = listaLink.get(i).getAttribute(ATTRIBUTI_HREF);
			if (!listaApp.containsKey(valueHREF)) {
				listaInterni.put(valueHREF, i);
			}
		}
		for (String url : listaInterni.keySet()) {

			System.out.println("url: " + url);

			driver.get(url);
			checkUrlTitle(driver);
//			checkDescrizione();
//			checkLink();
//			checkLinkInterni(driver);

		}
	}

	public void checkAll() {
		soft.assertAll();
	}

	public void closeDriver(WebDriver driver) {
		driver.quit();

	}
}
