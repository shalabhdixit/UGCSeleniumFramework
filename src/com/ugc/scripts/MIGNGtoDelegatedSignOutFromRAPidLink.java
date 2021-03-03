package com.ugc.scripts;

import org.testng.annotations.Test;

import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_RAPidLink;

public class MIGNGtoDelegatedSignOutFromRAPidLink extends
		BusinessFunctions {
	@Test
	public void MIGNG_To_Delegated_SignOut_From_RAPidLink() throws Throwable {
		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG",
						"Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			if (MIGNG_Rapid_SignOut()) {
				SuccessReport("MIGNG_Rapid_SignOut", "MIGNG_Rapid_SignOut successfully");
			} else {
				failureReport("MIGNG_Rapid_SignOut", "Unable to Signout from MIGNG_Rapid");
			}
			
			if (logoutMIG(MIGNG_RAPidLink.MIGNG_RAPidLink_Signout)) {
				SuccessReport("Logout MIGuide NG",	"Successfully logged out from MIGuide NG RAPID lINK");
			} else {
				failureReport("Logout MIGuide NG RAPID lINK", "Unable to log outRAPID lINK");
			}
			
			if (launchUrl(configProps.getProperty("Migng_pipeline"))) {
				SuccessReport("launchUrl", "MIGNG_Rapid_launchUrl successfully");
			} else {
				failureReport("Create New Application", "Unable launchUrl MIGNG_Rapid");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}