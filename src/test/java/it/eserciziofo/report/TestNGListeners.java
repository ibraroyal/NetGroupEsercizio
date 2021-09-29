package it.eserciziofo.report;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import it.eserciziofo.pom.BasePage;

public class TestNGListeners implements ITestListener {

	ExtentManager ex = new ExtentManager();

	@Override
	public void onTestStart(ITestResult result) {
		BasePage.soft.getM_errors().clear();
		BasePage.soft.getSuccess().clear();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
