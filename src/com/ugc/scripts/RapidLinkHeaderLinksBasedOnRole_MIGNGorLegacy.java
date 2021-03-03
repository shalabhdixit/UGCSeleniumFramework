package com.ugc.scripts;

import org.testng.annotations.Test;

import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_RAPidLink;

public class RapidLinkHeaderLinksBasedOnRole_MIGNGorLegacy extends
		BusinessFunctions {

	@Test
	public void RapidLinkHeaderLinksBasedOnRoleMIGNGorLegacy() throws Throwable {
		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG",
						"Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			if (Rapid_Delegated()) {
				SuccessReport("Rapid_Delegated",
						"Rapid_Delegated successfully");
			} else {
				failureReport("Rapid_Delegated",
						"Unable to do Rapid_Delegated");
			}
			if (logoutMIG(MIGNG_RAPidLink.MIGNG_RAPidLink_Signout)) {
				SuccessReport("Logout MIGuide NG",
						"Successfully logged out from MIGuide NG RAPID lINK");
			} else {
				failureReport("Logout MIGuide NG RAPID lINK", "Unable to log outRAPID lINK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}