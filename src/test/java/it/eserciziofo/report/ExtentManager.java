package it.eserciziofo.report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

	private static ExtentReports extent;
	ExtentTest test;
	ExtentTest testnode1;
	ExtentTest testnode2;

	public static ExtentReports createIstance() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			Date date = new Date();
			String dt = dateFormat.format(date);
			String reportPath = System.getProperty("user.dir") + "\\src\\main\\resources\\output\\file_report_" + dt
					+ ".html";
			ExtentHtmlReporter Htmlextent = new ExtentHtmlReporter(reportPath);
			Htmlextent.config().setDocumentTitle("Test automation siinfo");
			Htmlextent.config().setReportName("report siti siinfo");
			extent = new ExtentReports();
			extent.attachReporter(Htmlextent);
			extent.setSystemInfo("Host name", "localhost");
			extent.setSystemInfo("Environemnt", "QA");
			extent.setSystemInfo("TESTER NAME", "Ibra");

		} catch (Exception e) {
			throw e;
		}
		return extent;

	}

	public ExtentTest createTestSite(WebDriver driver) {

		if (test == null) {
			test = getIstance().createTest(driver.getCurrentUrl(),
					"in questa sezione viene mostrato tutto il report del sito indicato sopra");
		}
		return test;
	}
 
	
	
	public ExtentTest createTestByPageName(WebDriver driver) {
		int i = 0;
		List<ExtentTest> testPage = new ArrayList<ExtentTest>();
		testPage.add(createTestSite(driver).createNode(driver.getTitle(),
				"in questa sezione sono riportati report per ciascun pagina"));
		return testPage.get(i);
	}

	public void createTestTypeErrore(WebDriver driver, String nameTest) {

		if (nameTest.contains("error")) {
			if (testnode1 == null) {
				testnode1 = createTestByPageName(driver).createNode(nameTest,
						"in questa sezione sono riportati tutti i test falliti");
			}
		} else if (nameTest.contains("info")) {
			if (testnode2 == null) {
				testnode2 = createTestByPageName(driver).createNode(nameTest,
						"in questa sezione sono riportati tutti i test passati");
			}
		}

	}

	public void writeMessage(WebDriver driver, String nameTest, String message, String typeMessage) {
		createTestTypeErrore(driver, nameTest);
		if (typeMessage.contains("error")) {
			testnode1.error(message);
		} else if (typeMessage.contains("info")) {
			testnode2.info(message);
		}
		ExtentManager.getIstance().flush();

	}

	public static ExtentReports getIstance() {
		return extent;
	}
}
