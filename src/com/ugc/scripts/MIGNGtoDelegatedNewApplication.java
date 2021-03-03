package com.ugc.scripts;

import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_RAPidLink;

public class MIGNGtoDelegatedNewApplication extends BusinessFunctions {

	@Test(dataProvider = "get_TestData")
	public void MIGNG_To_Delegated_New_Application(String First, String Last, String SSN, String DOB, String NoYears,
			String Mths, String StreetAddress, String CITY, String state, String zip, String Monthly_Income,
			String Gender, String Ethnicity, String OtherMonthlyPayments, String Race, String CreditScore,
			String CreditRepository, String CreditScoreDate, String CreditKeyFactor, String PStreetAddress,
			String PCity, String PState, String PZIP, String PCounty, String OccupancyStatus, String PropertyType,
			String AppraisedValue, String SalesPrice, String LenderLoanNumber, String LoanPurposeType,
			String AmortizationType, String InitialNoteRate, String QualifyingRate, String OriginalLoanAmount,
			String AmortizationTerm, String InterestOnlyPeriod, String BalloonTerm, String Subordinate,
			String EstimatedCashtoClose, String LiquidAssets, String Hazardins, String Coverage) throws Throwable {

		String First1=null;
		String Last1=null;
		String StreetAddress1=null;
		String PStreetAddress1=null;
		String LenderLoanNumber1=null;
		String SSN1=null;
		// First Name Last Name SSN Property Address Loan Number Certificate #
		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
			char[] charsi = "12345678901234567890".toCharArray();
			StringBuilder fN = new StringBuilder();
			StringBuilder fN1 = new StringBuilder();
			Random random = new Random();
			for (int i = 0; i < 8; i++) {
				char c = chars[random.nextInt(chars.length)];
				fN.append(c);
				char c1 = chars[random.nextInt(chars.length)];
				fN1.append(c1);
			}
			First = fN.toString();
			First1 = fN1.toString();
			StringBuilder Ln = new StringBuilder();
			StringBuilder Ln1 = new StringBuilder();
			for (int i = 0; i < 5; i++) {
				char c = chars[random.nextInt(chars.length)];
				char c1 = chars[random.nextInt(chars.length)];
				Ln.append(c);
				Ln1.append(c1);
			}
			Last = Ln.toString();
			Last1 = Ln1.toString();
			StringBuilder pa = new StringBuilder();
			StringBuilder pa1 = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				char c = chars[random.nextInt(chars.length)];
				pa.append(c);
				char c1 = chars[random.nextInt(chars.length)];
				pa1.append(c1);
			}
			StreetAddress = pa.toString();
			PStreetAddress = pa.toString();
			StreetAddress1 = pa1.toString();
			PStreetAddress1 = pa1.toString();
			StringBuilder Lon = new StringBuilder();
			StringBuilder Lon1 = new StringBuilder();
			for (int i = 0; i < 3; i++) {
				char c = chars[random.nextInt(chars.length)];
				Lon.append(c);
				char c1 = chars[random.nextInt(chars.length)];
				Lon1.append(c1);
			}
			LenderLoanNumber = Lon.toString();
			LenderLoanNumber1 = Lon1.toString();
			StringBuilder Ssn = new StringBuilder();
			StringBuilder Ssn1 = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				char c = charsi[random.nextInt(charsi.length)];
				Ssn.append(c);
				char c1 = charsi[random.nextInt(charsi.length)];
				Ssn1.append(c1);
			}
			SSN = Ssn.toString();
			SSN1 = Ssn1.toString();
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_NewApplication, "NewApplication");
			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			if (MIGNG_Rapid_NewApplication(First, Last, SSN, DOB, NoYears, Mths, StreetAddress, CITY, state, zip,
					Monthly_Income, Gender, Ethnicity, OtherMonthlyPayments, Race, CreditScore, CreditRepository,
					CreditScoreDate, CreditKeyFactor, PStreetAddress, PCity, PState, PZIP, PCounty, OccupancyStatus,
					PropertyType, AppraisedValue, SalesPrice, LenderLoanNumber1, LoanPurposeType, AmortizationType,
					InitialNoteRate, QualifyingRate, OriginalLoanAmount, AmortizationTerm, InterestOnlyPeriod,
					BalloonTerm, Subordinate, EstimatedCashtoClose, LiquidAssets, Hazardins, Coverage, 1)) {

				SuccessReport("Create New Application", "New Application created successfully");
			} else {
				failureReport("Create New Application", "Unable to Create New Application");
			}

			if (logoutMIG(MIGNG_RAPidLink.MIGNG_RAPidLink_Signout)) {
				SuccessReport("Logout MIGuide NG", "Successfully logged out from MIGuide NG");
			} else {
				failureReport("Logout MIGuide NG", "Unable to log out");
			}
			
			
			
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_NewApplication, "NewApplication");
			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			if (MIGNG_Rapid_NewApplication(First1, Last1, SSN1, DOB, NoYears, Mths, StreetAddress1, CITY, state, zip,
					Monthly_Income, Gender, Ethnicity, OtherMonthlyPayments, Race, CreditScore, CreditRepository,
					CreditScoreDate, CreditKeyFactor, PStreetAddress1, PCity, PState, PZIP, PCounty, OccupancyStatus,
					PropertyType, AppraisedValue, SalesPrice, LenderLoanNumber, LoanPurposeType, AmortizationType,
					InitialNoteRate, QualifyingRate, OriginalLoanAmount, AmortizationTerm, InterestOnlyPeriod,
					BalloonTerm, Subordinate, EstimatedCashtoClose, LiquidAssets, Hazardins, Coverage, 1)) {

				SuccessReport("Create New Application", "New Application created successfully");
			} else {
				failureReport("Create New Application", "Unable to Create New Application");
			}

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