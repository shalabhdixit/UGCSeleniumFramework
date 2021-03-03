package com.ugc.scripts;

import org.testng.annotations.Test;

import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewQuote;

public class LinkToServicing_ReportsLink extends BusinessFunctions {
	@Test
	public void Link_To_Servicing_Reports_Link () throws Throwable {
		try {
			// Login to MIGNG application
//			if (MIG_logIn("uid", "pwd")) {
			if (logInMIG("cert_finder_Uname", "cert_finder_Password")) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			
			//	verify Portfolio page
			verifyPortfolioPage();

			// The user navigates back to the pipeline
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			// validate Pipeline page
			validatePipeline();

			//	Click on Reports
			click(MIGNG_HomePage.MIGNG_headerReports, "Reports");
			Thread.sleep(2000);
			if(isElementPresent(MIGNG_HomePage.MIGNG_reportsHeader, "Report Header")){
				SuccessReport("Verify Reports page is displayed", "Reports page is displayed");
			}else{
				failureReport("Verify Reports page is displayed", "Reports page is not displayed");
			}				

			// The user navigates back to the pipeline
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			// validate Pipeline page
			validatePipeline();

			//	Create New Quote
			click(MIGNG_HomePage.MIGNG_headerNewQuote, "New Quote");
			Thread.sleep(2000);
			assertText(MIGNG_NewQuote.MIGNG_quoteHeader, "New Quote");

			// The user navigates back to the pipeline
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			// validate Pipeline page
			validatePipeline();

			//	Click on Reports
			click(MIGNG_HomePage.MIGNG_headerReports, "Reports");
			Thread.sleep(2000);
			if(isElementPresent(MIGNG_HomePage.MIGNG_reportsHeader, "Report Header")){
				SuccessReport("Verify Reports page is displayed", "Reports page is displayed");
			}else{
				failureReport("Verify Reports page is displayed", "Reports page is not displayed");
			}				

			//	Create New Application
			click(MIGNG_HomePage.MIGNG_headerNewFullFile, "New Full-file Application");
			Thread.sleep(2000);
			assertText(MIGNG_HomePage.MIGNG_applicationHeader, "New Full-file Application");
			
			// The user navigates back to the pipeline
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			// validate Pipeline page
			validatePipeline();

			//	Click on Reports
			click(MIGNG_HomePage.MIGNG_headerReports, "Reports");
			Thread.sleep(2000);
			if(isElementPresent(MIGNG_HomePage.MIGNG_reportsHeader, "Report Header")){
				SuccessReport("Verify Reports page is displayed", "Reports page is displayed");
			}else{
				failureReport("Verify Reports page is displayed", "Reports page is not displayed");
			}				

			// The user navigates back to the pipeline
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			// validate Pipeline page
			validatePipeline();

			logoutMIGNG();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
