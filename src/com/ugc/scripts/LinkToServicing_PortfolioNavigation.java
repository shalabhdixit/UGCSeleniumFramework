package com.ugc.scripts;

import org.testng.annotations.Test;

import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewQuote;
import com.ugc.objectRepository.MIGNG_RAPidLink;

public class LinkToServicing_PortfolioNavigation extends BusinessFunctions {
	@Test
	public void Link_To_Servicing_Portfolio_Navigation() throws Throwable {
		try {
			// Login to MIGNG application
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

			// The user navigates back to the pipeline
			click(MIGNG_HomePage.MIGNG_headerPortfolio, "Portfolio");
			//	verify Portfolio page
			verifyPortfolioPage();

			// The user navigates back to the pipeline
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			// validate Pipeline page
			validatePipeline();

			//	Create New Quote
			click(MIGNG_HomePage.MIGNG_headerNewQuote, "New Quote");
			Thread.sleep(2000);
			assertText(MIGNG_NewQuote.MIGNG_quoteHeader, "New Quote");
			// The user navigates back to the pipeline
			click(MIGNG_HomePage.MIGNG_headerPortfolio, "Portfolio");
			//	verify Portfolio page
			verifyPortfolioPage();

			// The user navigates back to the pipeline
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			// validate Pipeline page
			validatePipeline();

			//	Create New Application
			click(MIGNG_HomePage.MIGNG_headerNewFullFile, "New Full-file Application");
			Thread.sleep(2000);
			assertText(MIGNG_HomePage.MIGNG_applicationHeader, "New Full-file Application");
			
			// The user navigates back to the Portfolio
			click(MIGNG_HomePage.MIGNG_headerPortfolio, "Portfolio");
			//	verify Portfolio page
			verifyPortfolioPage();

			// The user navigates back to the pipeline
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			// validate Pipeline page
			validatePipeline();

			click(MIGNG_HomePage.MIGNG_headerDelegated, "Delegated Rapid Link");
			Thread.sleep(2000);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_NewApplication, "NewApplication");
			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			Thread.sleep(2000);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Pipeline, "Pipeline");
			// validate Pipeline page
			validatePipeline();
//			driver.switchTo().defaultContent();

			// The user navigates back to the Portfolio
			click(MIGNG_HomePage.MIGNG_headerPortfolio, "Portfolio");
			//	verify Portfolio page
			verifyPortfolioPage();

			// The user navigates back to the pipeline
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			// validate Pipeline page
			validatePipeline();

			click(MIGNG_HomePage.MIGNG_headerDelegated, "Delegated Rapid Link");
			Thread.sleep(2000);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_PipeUpload, "Upload");
//			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			Thread.sleep(1000);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Pipeline, "Pipeline");
			Thread.sleep(1000);
//			driver.switchTo().defaultContent();

			// The user navigates back to the Portfolio
			click(MIGNG_HomePage.MIGNG_headerPortfolio, "Portfolio");
			//	verify Portfolio page
			verifyPortfolioPage();
			
			// The user navigates back to the pipeline
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			// validate Pipeline page
			validatePipeline();

			click(MIGNG_HomePage.MIGNG_headerDelegated, "Delegated Rapid Link");
			Thread.sleep(2000);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_PipeResubmit, "Resubmit");
//			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			Thread.sleep(2000);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Pipeline, "Pipeline");
			Thread.sleep(2000);
//			driver.switchTo().defaultContent();

			// The user navigates back to the Portfolio
			click(MIGNG_HomePage.MIGNG_headerPortfolio, "Portfolio");
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
			
			// The user navigates back to the Portfolio
			click(MIGNG_HomePage.MIGNG_headerPortfolio, "Portfolio");
			//	verify Portfolio page
			verifyPortfolioPage();
			
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
