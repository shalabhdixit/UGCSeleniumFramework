package com.ugc.scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ugc.businessLibrary.BusinessFunctions;

public class Pipeline_LastUpdate_ReverseSorting extends BusinessFunctions{
	@Test
	public void Pipeline_LastUpdate_ReverseSorting_ () throws Throwable {
		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG",
						"Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			WebElement element = driver.findElement(By.xpath("//div[@id='pipeline']//thead/tr/th[1]"));
			String className = element.getAttribute("class");
			if (className.equalsIgnoreCase("sorting-desc")) {
				SuccessReport("Validate Last Update Column sorting", "Last Update Column is reverse sorted");
			}
			
			click(By.xpath("//div[@id='pipeline']//thead/tr/th[1]"), "Last Update");
			className = element.getAttribute("class");
			if (className.equalsIgnoreCase("sorting-asc")) {
				SuccessReport("Validate Last Update Column sorting", "Last Update Column sorting is in ascending order");
			}

			click(By.xpath("//div[@id='pipeline']//thead/tr/th[1]"), "Last Update");
			className = element.getAttribute("class");
			if (className.equalsIgnoreCase("sorting-desc")) {
				SuccessReport("Validate Last Update Column sorting", "Last Update Column is reverse sorted");
			}

			logoutMIGNG();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
