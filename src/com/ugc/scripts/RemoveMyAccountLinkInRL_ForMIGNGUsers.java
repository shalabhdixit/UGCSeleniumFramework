package com.ugc.scripts;

import org.testng.annotations.Test;

import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_RAPidLink;

public class RemoveMyAccountLinkInRL_ForMIGNGUsers extends
		BusinessFunctions {

	@Test
	public void Remove_MyAccount_Link_InRL_For_MIGNG_User() throws Throwable {
		try {
//			boolean flag = false;
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			MIGNG_Rapid_SignOut();
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Resubmit, "Resubmit Existing Loan");
//			flag = assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_Verifyall, "Resubmit verified");

			if (assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_Verifyall, "Select Loan")) {
				SuccessReport("Verify_MyAccount","Verify_MyAccount successfully");
			} else {
				failureReport("Verify_MyAccount","Unable to do Verify_MyAccount");
			}
			if (logoutMIG(MIGNG_RAPidLink.MIGNG_RAPidLink_Signout)) {
				SuccessReport("Logout MIGuide NG", "Successfully logged out from MIGuide NG ");
			} else {
				failureReport("Logout MIGuide NG RAPID lINK", "Unable to log out");
			}
			if (logInMIG("RL_userName", "RL_Password") & verifyHomePageRR()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			if (verifyRlMyAccount()) {
				SuccessReport("Verify_MyAccount", "Verify_MyAccount successfully");
			} else {
				failureReport("Verify_MyAccount", "Unable to do Verify_MyAccount");
			}
			if (logoutMIG(MIGNG_RAPidLink.MIGNG_RAPidLink_Signout)) {
				SuccessReport("Logout MIGuide NG", "Successfully logged out from MIGuide NG ");
			} else {
				failureReport("Logout MIGuide NG RAPID lINK", "Unable to log out");
			}
			if (logInMIG("RR_userName", "RR_password") & verifyHomePageRR()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			if (verifyRRMyAccount()) {
				SuccessReport("Verify_MyAccount", "Verify_MyAccount successfully");
			} else {
				failureReport("Verify_MyAccount", "Unable to do Verify_MyAccount");
			}
			
			if (logoutMIG(MIGNG_RAPidLink.MIGNG_RRSignout)) {
				SuccessReport("Logout MIGuide NG", "Successfully logged out from MIGuide NG ");
			} else {
				failureReport("Logout MIGuide NG RAPID lINK", "Unable to log out");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}