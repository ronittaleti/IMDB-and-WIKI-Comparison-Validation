package com.ronittaleti;


import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {

	@Override
	public void onTestStart(ITestResult tr) {
		System.out.println("Test '" + tr.getName() + "' started.");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {

		System.out.println("========================");
		System.out.println("Test '" + tr.getName() + "' PASSED");
		System.out.println("Test Class: " + tr.getTestClass().getName());
		System.out.println("========================");
	}

	@Override
	public void onTestFailure(ITestResult tr) {

		System.out.println("========================");
		System.out.println("Test '" + tr.getName() + "' FAILED");
		System.out.println("Test Class: " + tr.getTestClass().getName());
		System.out.println("========================");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		System.out.println("========================");
		System.out.println("Test '" + tr.getName() + "' SKIPPED");
		System.out.println("Test Class: " + tr.getTestClass().getName());
		System.out.println("========================");
	}
}