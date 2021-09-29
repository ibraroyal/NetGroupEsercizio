package it.eserciziofo.test;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.IAssert;

import com.aventstack.extentreports.ExtentTest;

import it.eserciziofo.pom.BasePage;
import it.eserciziofo.report.ExtentManager;
import it.eserciziofo.report.TestNGListeners;
import it.eserciziofo.utility.DriverFactory;
import it.eserciziofo.utility.ReadFile;

@Listeners(TestNGListeners.class)

public class BasePageTest {

	WebDriver driver;
	BasePage bp;
	ExtentTest report;
	ExtentManager ex;

	@DataProvider(name = "link")
	public Object[] report() throws Exception {
		return ReadFile.letturaFile().toArray();

	}

	@BeforeTest
	public void start() {
		ExtentManager.createIstance();
	}

	@Test(dataProvider = "link")
	public void checkSite(String link) throws Exception {
		driver = DriverFactory.runBrowser(link);
		bp = new BasePage();
		bp.checkMetaFavicon();
		bp.checkUrlTitle(driver);
		bp.checkDescrizione();
		bp.checkLink();
		bp.checkLinkInterni(driver);
		bp.checkAllPages(driver);
		bp.checkAll();
	}

	@AfterMethod(alwaysRun = true)
	public void finishTest(ITestResult it) {
		ex = new ExtentManager();

		for (String message : BasePage.soft.getSuccess()) {
			ex.writeMessage(driver, "info", message, "info");
		}

		if (it.getStatus() == ITestResult.FAILURE) {

			for (IAssert<?> error : BasePage.soft.getM_errors().keySet()) {
				ex.writeMessage(driver, "error", error.getMessage(), "error");
			}

		}
//		ExtentManager.getIstance().flush();
		bp.closeDriver(driver);
	}

	@AfterSuite(alwaysRun = true)
	public void flushReport() throws Exception {

	}

}
