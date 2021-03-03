package com.ugc.scripts;

import org.testng.annotations.Test;

import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_RAPidLink;

public class MIGNGtoDelegatedCertFinderLink extends BusinessFunctions {

	@Test
	public void MIGNG_To_Delegated_CertFinder_Link() throws Throwable {
		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_CertFinder, "CertFinder");
//			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_CertFinderVerify, "CertFinder Verified");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_MiGuideHome, "MiGuideHome");

			if (assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_PipelineVerify, "Pipeline")) {
				SuccessReport("CertFinder", "Rapid_DelegatedCertFinder successfully");
			} else {
				failureReport("Rapid_DelegatedCertFinder",	"Unable to do Rapid_DelegatedCertFinder");
			}
			logoutMIGNG();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}