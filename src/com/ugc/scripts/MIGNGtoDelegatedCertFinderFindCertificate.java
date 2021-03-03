package com.ugc.scripts;

import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_RAPidLink;

public class MIGNGtoDelegatedCertFinderFindCertificate extends BusinessFunctions {

	@Test(dataProvider = "get_TestData")
	public void MIGNG_To_Delegated_CertFinder_FindCertificate (String First, String Last, String SSN, 
			String DOB, String NoYears,
			String Mths, String StreetAddress, String CITY, String state, String zip, String Monthly_Income,
			String Gender, String Ethnicity, String OtherMonthlyPayments, String Race, String CreditScore,
			String CreditRepository, String CreditScoreDate, String CreditKeyFactor, String PStreetAddress,
			String PCity, String PState, String PZIP, String PCounty, String OccupancyStatus, String PropertyType,
			String AppraisedValue, String SalesPrice, String LenderLoanNumber, String LoanPurposeType,
			String AmortizationType, String InitialNoteRate, String QualifyingRate, String OriginalLoanAmount,
			String AmortizationTerm, String InterestOnlyPeriod, String BalloonTerm, String Subordinate,
			String EstimatedCashtoClose, String LiquidAssets, String Hazardins, String Coverage) throws Throwable {

		// First Name Last Name SSN Property Address Loan Number Certificate #
		try {
			// Login to MIGNG application
			if (logInMIGNGQuapper()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
			char[] charsi = "12345678901234567890".toCharArray();
			StringBuilder fN = new StringBuilder();
			Random random = new Random();
			for (int i = 0; i < 8; i++) {
				char c = chars[random.nextInt(chars.length)];
				fN.append(c);
			}
			First = fN.toString();
			StringBuilder Ln = new StringBuilder();
			for (int i = 0; i < 5; i++) {
				char c = chars[random.nextInt(chars.length)];
				Ln.append(c);
			}
			Last = Ln.toString();
			StringBuilder pa = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				char c = chars[random.nextInt(chars.length)];
				pa.append(c);
			}
			StreetAddress = pa.toString();
			PStreetAddress = pa.toString();
			StringBuilder Lon = new StringBuilder();
			for (int i = 0; i < 3; i++) {
				char c = chars[random.nextInt(chars.length)];
				Lon.append(c);
			}
			LenderLoanNumber = Lon.toString();
			StringBuilder Ssn = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				char c = charsi[random.nextInt(charsi.length)];
				Ssn.append(c);
			}
			SSN = Ssn.toString();
			click(MIGNG_HomePage.MIGNG_headerDelegated, "Delegated");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_NewApplication1, "NewApplication");
			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			if (MIGNG_Rapid_NewApplication(First, Last, SSN, DOB, NoYears, Mths, StreetAddress, CITY, state, zip,
					Monthly_Income, Gender, Ethnicity, OtherMonthlyPayments, Race, CreditScore, CreditRepository,
					CreditScoreDate, CreditKeyFactor, PStreetAddress, PCity, PState, PZIP, PCounty, OccupancyStatus,
					PropertyType, AppraisedValue, SalesPrice, LenderLoanNumber, LoanPurposeType, AmortizationType,
					InitialNoteRate, QualifyingRate, OriginalLoanAmount, AmortizationTerm, InterestOnlyPeriod,
					BalloonTerm, Subordinate, EstimatedCashtoClose, LiquidAssets, Hazardins, Coverage, 0)) {

				SuccessReport("Create New Application", "New Application created successfully");
			} else {
				failureReport("Create New Application", "Unable to Create New Application");
			}
			driver.switchTo().frame("MainFrame");
//			Thread.sleep(2000);
			if (logoutMIG(MIGNG_RAPidLink.MIGNG_RAPidLink_Signout)) {
				SuccessReport("Logout MIGuide NG", "Successfully logged out from MIGuide NG");
			} else {
				failureReport("Logout MIGuide NG", "Unable to log out");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("RapidLink");
	}
}