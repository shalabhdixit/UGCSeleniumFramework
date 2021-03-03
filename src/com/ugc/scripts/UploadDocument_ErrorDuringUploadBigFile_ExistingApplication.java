package com.ugc.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewApplication;
import com.ugc.objectRepository.MIGNG_Validation;

public class UploadDocument_ErrorDuringUploadBigFile_ExistingApplication extends
		BusinessFunctions {
	@Test(dataProvider = "get_TestData")
	public void Upload_Document_Error_During_Upload_BigFile_Existing_Application (
			String uploadDocument, String uploadBigFile, String duplicateFile,
			String multipleFile, String uploadDocument2) throws Throwable {

		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG",
						"Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			if (openExistingApplication()) {
				SuccessReport("Create New Application",
						"New Application created successfully");
			} else {
				failureReport("Create New Application",
						"Unable to Create New Application");
			}

			if (!uploadDocument.equals("")) {
				String[] files = uploadDocument.split(";");
				for (int i = 0; i < files.length; i++) {
					// call upload document method
					uploadDocument(files[i], i + 1);
				}
			}

			if (!uploadBigFile.equals("")) {
				String[] files = uploadBigFile.split(";");
				for (int i = 0; i < files.length; i++) {
					// call upload document method
					uploadDocument(files[i], i + 1);
				}
			}

			if (!duplicateFile.equals("")) {
				// call upload document method
				uploadDocument(duplicateFile, 0);		//	0 for duplicate file validation
			}
						
			if (!multipleFile.equals("")) {
				String[] files = multipleFile.split(";");
				// call upload document method
				uploadDocument(files[0], 1);	// upload big file
				uploadDocument(files[1], 0);	// upload duplicate file
			}

			if (!uploadDocument2.equals("")) {
				String[] files = uploadDocument2.split(";");
				for (int i = 0; i < files.length; i++) {
					// call upload document method
					uploadDocument(files[i], i + 1);
				}
			}

			// Click on ReSubmit button
			click(MIGNG_NewApplication.MIGNG_reSubmit, "ReSubmit Button");

			// Wait for the availability of Pipeline table
			waitForVisibilityOfElement(MIGNG_HomePage.MIGNG_pipeline,
					"Pipeline page");

			// verify success text
			String successText = getText(
					MIGNG_NewApplication.MIGNG_successText, "Success Message");
			if (!successText.equals("")
					&& successText
							.contains(MIGNG_Validation.msgSuccessApplicationSubmitted)) {
				SuccessReport("New Application Created", "Success Message : "
						+ successText);
				String applicationNo = successText.replaceAll("[^0-9]", "");
				SuccessReport("New Application Created", "Application No : "
						+ applicationNo);
			}

			logoutMIGNG();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("UD_BigFile_ExistingApp");
	}
}
