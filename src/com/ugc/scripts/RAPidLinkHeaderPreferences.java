package com.ugc.scripts;

import org.testng.annotations.Test;

import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_RAPidLink;

public class RAPidLinkHeaderPreferences extends BusinessFunctions {
	@Test
	public void RAPidLinkHeaderPreference() throws Throwable {
		// First Name Last Name SSN Property Address Loan Number Certificate #
		try {
			if (logInMIG("RR_userName", "RR_password") & verifyHomePageRR()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			
			if (MIGNG_Preferen()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			if (logoutMIG(MIGNG_RAPidLink.MIGNG_RAPidLink_Signout)) {
				SuccessReport("Logout MIGuide NG",
						"Successfully logged out from MIGuide NG ");
			} else {
				failureReport("Logout MIGuide NG RAPID lINK", "Unable to log out");
			}
			if (logInMIG("RL_userName", "RL_Password") & verifyHomePageRR()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			
			if (MIGNG_Preferenll()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			if (logoutMIG(MIGNG_RAPidLink.MIGNG_RAPidLink_Signout)) {
				SuccessReport("Logout MIGuide NG", "Successfully logged out from MIGuide NG ");
			} else {
				failureReport("Logout MIGuide NG RAPID lINK", "Unable to log out");
			}
			
			if (logInMIG("MIGNG_userName", "MIGNG_passWord") & verifyHomePageMIGNG()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			if(RAPidLinkPreference()){
				SuccessReport("RAPidLinkPreference", "Successfully  verified RAPidLinkPreference");
			} else {
				failureReport("RAPidLinkPreference", "Unable verified RAPidLinkPreference");
			}
			
			if (logoutMIG(MIGNG_RAPidLink.MIGNG_RAPidLink_Signout)) {
				SuccessReport("Logout MIGuide NG", "Successfully logged out from MIGuide NG ");
			} else {
				failureReport("Logout MIGuide NG RAPID lINK", "Unable to log out");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
