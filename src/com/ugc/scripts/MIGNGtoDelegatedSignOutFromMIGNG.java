package com.ugc.scripts;

import org.testng.annotations.Test;

import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_RAPidLink;

public class MIGNGtoDelegatedSignOutFromMIGNG extends BusinessFunctions {
	@Test
	public void MIGNG_To_Delegated_SignOut_From_MIGNG() throws Throwable {
		try {
			boolean flag = false;
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG","Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in MIGuide NG");
			}

			MIGNG_Rapid_SignOut();
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Pipeline, "Pipeline");
			waitForVisibilityOfElement(MIGNG_RAPidLink.MIGNG_RAPidLink_PipelineVerify, "Pipeline");
			flag = assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_PipelineVerify, "Pipeline");
			if (flag) {
				SuccessReport("MIGNG_migng_SignOut","MIGNG_migng_SignOut successfully");
			} else {
				failureReport("MIGNG_migng_SignOut","Unable to Signout from MIGNG_Rapid");
			}
			logoutMIGNG();
			
			if (launchUrl(configProps.getProperty("Migng_pipeline"))) {
				SuccessReport("launchUrl", "MIGNG_Pipline_launchUrl successfully");
			} else {
				failureReport("Create New Application",	"Unable launchUrl MIGNG_Pipline_launchUrl");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}