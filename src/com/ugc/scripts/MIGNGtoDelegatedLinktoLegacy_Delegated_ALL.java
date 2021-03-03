package com.ugc.scripts;

import org.testng.annotations.Test;

import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_RAPidLink;

public class MIGNGtoDelegatedLinktoLegacy_Delegated_ALL extends
		BusinessFunctions {

	@Test
	public void MIGNG_To_Delegated_LinktoLegacy_DelegatedALL() throws Throwable {
		try {
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG",
						"Logged_into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in MIGuide NG");
			}

			if (Rapid_Delegated_ALL()) {
				SuccessReport("Rapid_Delegated", "Rapid_Delegated_successfully");
			} else {
				failureReport("Rapid_Delegated", "Unable to do Rapid_Delegated");
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