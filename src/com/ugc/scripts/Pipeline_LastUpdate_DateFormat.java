package com.ugc.scripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ugc.businessLibrary.BusinessFunctions;

public class Pipeline_LastUpdate_DateFormat extends BusinessFunctions {
	@Test
	public void Pipeline_Last_Update_Date_Format () throws Throwable {
		try {
			boolean flag = true;
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG",
						"Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			
			List<WebElement> elements = driver.findElements(By.xpath("//div[@id='pipeline']//tbody/tr/td[1]"));
			for (WebElement ele : elements) {
				if(!isValidDateFormat("MM/dd/yyyy", ele.getText())){
					flag = false;
				}
			}
			
			if (flag == true) {
				SuccessReport("Validate Last Update Column format", "Last Update Column is in format MM/dd/yyyy");
			}else{
				failureReport("Validate Last Update Column format", "Last Update Column is not in format MM/dd/yyyy");
			}
			
			logoutMIGNG();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}