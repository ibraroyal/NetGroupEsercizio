package it.eserciziofo.pom;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import it.eserciziofo.utility.Utilities;

public class PageHome extends Utilities {

	public PageHome(WebDriver driverBase) {
		super(driverBase);
		// TODO Auto-generated constructor stub
	}

	final String ATTRIBUTI_HREF = "href";
	final String ATTRIBUTI_TARGET = "target";
	final String ATTRIBUTI_SIZE = "size";
	final String ATTRIBUTI_NAME = "name";
	final String ATTRIBUTI_CONTENT = "content";
	final String ATTRIBUTI_DESCRIPTION = "description";
	final String TYPE_LOCATOR = "a";
	final String TYPE_LOCATOR2 = "link";
	final String TYPE_LOCATOR3 = "meta";
	final String VALUE_ATTRIBUTO = "_blank";
	final String SIZE_FAVICON = "32x32";
	final String FUNCTION_JAVASCRIPT = "javascript";

	public void checkLink() throws Exception {
		try {
			List<WebElement> listaLink = util.listaElementi(TYPE_LOCATOR);
			for (int i = 0; i < listaLink.size(); i++) {
				WebElement e1 = listaLink.get(i);
				String url = e1.getAttribute(ATTRIBUTI_HREF);

				if (url == null || url.isEmpty()) {
//					log.log.warning("link null/vuoto");
					continue;
				}
				if (url.startsWith(FUNCTION_JAVASCRIPT)) {
					continue;
				}
//				verica se link è un email attraverso le regex
				if (util.matchEmail(url)) {
//					log.log.info("questo elemento contiene un email: " + url);
					continue;
				}
//				verica se link è un numero di telefono attraverso le regex
				if (util.matchPhon(url)) {
//					log.log.info("questo elemento contiene un numero di telefono: " + url);
					continue;
				}
//				verifica se link è interno e se ha un attributo target=_blank
				if (url.startsWith(runlLink.getUrl())) {
					if (e1.getAttribute(ATTRIBUTI_TARGET).equals(VALUE_ATTRIBUTO)) {
						it.eserciziofo.log.log.warning(runlLink.getUrl() + "- url: " + url + ": link interno blank");
					}
					continue;
				}
//				verifica se link è esterno e se ha un attributo target !=_blank
				if (!url.startsWith(runlLink.getUrl())) {
					if (!e1.getAttribute(ATTRIBUTI_TARGET).equals(VALUE_ATTRIBUTO)) {
						it.eserciziofo.log.log.warning(runlLink.getUrl() + "- link: " + url + ": link esterno no blank");
					}
					continue;
				}

			}
		} catch (Exception e) {
			throw e;
		}

	}

	public void checkMetaFavicon() throws Exception {
//		inizializza una lista di tipo webElement con il tag Link
		List<WebElement> listaElementiMeta = util.listaElementi(TYPE_LOCATOR2);
		try {
//			fa un loop 
			for (int i = 0; i < listaElementiMeta.size(); i++) {
//				inizializza un oggetto webElement con elemento iesimo della lista 
				WebElement webElement = listaElementiMeta.get(i);
//				inizializza la string con il valore del attributo size dell'elemento istanziato sopra 
				String value1 = webElement.getAttribute(ATTRIBUTI_SIZE);
//				inizializza la string con il valore del attributo href dell'elemento istanziato sopra 
				String value2 = webElement.getAttribute(ATTRIBUTI_HREF);
				// salta il ciclo l'attributo size è nullo
				if (value1 == null) {
					continue;
				}
//				mi confronta il valore size con le misure preimpostati 32*32
				if (value1.equals(SIZE_FAVICON)) {
//					se la condizione risulta vera, verifica che l'attributo href non è vuoto all'interno di un assertue
//					altrimenti mi lancia un errore di tipo assert
					assertTrue(!value2.isEmpty());
					it.eserciziofo.log.log.info(runlLink.getUrl() + ": favicon ok");
				}
			}
		} catch (Exception e) {
			throw e;
		} catch (AssertionError e) {
//			in caso si verificasse un errore, viene scritto nel file di log
			it.eserciziofo.log.log.warning(runlLink.getUrl() + ": favicon ko");
		}
	}

	public void checkUrlTitle() throws Exception {
		try {
			assertTrue(!Utilities.getWebDrive().getTitle().isEmpty());
			it.eserciziofo.log.log.info("title ok");
		} catch (Exception e) {
			throw e;
		} catch (AssertionError e) {
			it.eserciziofo.log.log.warning(runlLink.getUrl() + ": title ko");
		}
	}

	public void checkDescrizione() throws Exception {
		List<WebElement> listaElementiMeta = util.listaElementi(TYPE_LOCATOR3);
		try {
			for (int i = 0; i < listaElementiMeta.size(); i++) {
				WebElement webElement = listaElementiMeta.get(i);
				String name = webElement.getAttribute(ATTRIBUTI_NAME);
				String content = webElement.getAttribute(ATTRIBUTI_CONTENT);
				if (name.equals(ATTRIBUTI_DESCRIPTION)) {
					assertTrue(!content.isEmpty());
					it.eserciziofo.log.log.info(runlLink.getUrl() + ": campo descrizione è inizializzato");
				}

			}
		} catch (Exception e) {
			throw e;
		} catch (AssertionError e) {
			it.eserciziofo.log.log.warning(runlLink.getUrl() + ": campo descrizione è vuoto");
		}

	}

	public void closeDriver() {
		Utilities.getWebDrive().close();

	}
}
