package it.eserciziofo.classiplus;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import it.eserciziofo.utility.Utilities;

public class HomePageTest {

	PageHome ph = new PageHome();
	WebDriverWait wait;

	@BeforeTest
	public void setUp() throws Exception {
		Utilities runlLink = new Utilities();
		try {
			runlLink.runBrowser();
		} catch (Exception e) {
			throw e;
		}
	}

	@Test(priority = 1)
	public void checkOrarioAndContatto() throws Exception {
		ph.checkListaElementVisibility("xpath", "//*[@class='d-flex justify-content-between']", "elementi non presenti",
				1);
	}

	@Test(priority = 2)
	public void checkMenu() throws Exception {
		ph.checkListaElementVisibility("xpath", "//*[@class='main-bar']", "descrizione servizione non è visibile", 1);

	}

	@Test(priority = 3)
	public void subMenu() throws Exception {
		ph.checkListaElementVisibility("xpath", "//*[@id='menu-item-2809']", "descrizione servizione non è visibile",
				30);
		ph.checkListaElementVisibility("xpath", "//*[@id='menu-item-2832']", "descrizione servizione non è visibile",
				1);
	}

	@Test(priority = 4)
	public void checkTestoDescrizioneServizi() throws Exception {
		ph.checkListaElementVisibility("xpath", "//*[@class='tp-tab-content']", "descrizione servizione non è visibile",
				30);
	}

	@Test(priority = 5)
	public void checkTitoloPagina() throws Exception {
		ph.checkListaElementVisibility("xpath", "//*[@class='elementor-heading-title elementor-size-default']",
				"descrizione servizione non è visibile", 1);
	}

	@Test(priority = 6)
	public void checkTitoloPaginaLevel2() throws Exception {
		ph.checkListaElementVisibility("xpath",
				"//*[@class='section-head center wt-small-separator-outer text-center']",
				"descrizione servizione non è visibile", 1);
	}
	@Test(priority = 7)
	public void checkDescrizioneInColonna() throws Exception {
		ph.checkListaElementVisibility("xpath",
				"//*[@class='services-style-new owl-btn-vertical-center row justify-content-center']",
				"descrizione servizione non è visibile", 1);
	}
	
	/*da aggiungere un attesa per il counter*/
	@Test(priority = 8)
	public void checkCounterWork() throws Exception {
		ph.checkListaElementVisibility("xpath",
				"//*[@class='section-head center wt-small-separator-outer text-center text-white']",
				"descrizione servizione non è visibile", 1);
		
		ph.checkListaElementVisibility("xpath",
				"//*[@class='counter-outer']",
				"descrizione servizione non è visibile", 1);
	}
	

}
