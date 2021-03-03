package com.ugc.objectRepository;

import org.openqa.selenium.By;

public class MIGNG_Documentum {

	public static By documentum_loginScreen = By.
			xpath("//span[@class='defaultLabelStyle' and contains(text(),'Documentum Administrator')]");
	public static By documentum_Username = By.xpath("//input[@id='LoginUsername']");
	public static By documentum_Password = By.xpath("//input[@id='LoginPassword']");
	public static By documentum_btnLogin = By.xpath("//button[@title='Login']");
	public static By documentum_btnLoginAgain = By.xpath("//button[@title='Login Again']");
	
	public static By documentum_Cabinets = By.xpath("//span[contains(text(),'Cabinets')]");
	public static By documentum_CabinetsExpand = By.
			xpath("//span[contains(text(),'Cabinets')]/ancestor::nobr/a[1]/img");

	public static By documentum_RateQuote = By.xpath("//span[contains(text(),'Rate Quote')]");
	public static By documentum_RateQuoteExpand = By.
			xpath("//span[contains(text(),'Rate Quote')]/ancestor::nobr/a[1]/img");
	
	public static By documentum_RateQuotes = By.xpath("//span[contains(text(),'Rate Quotes')]");
	public static By documentum_RateQuotesExpand = By.
			xpath("//span[contains(text(),'Rate Quotes')]/ancestor::nobr/a[1]/img");
	
	public static By documentum_txtSearch = By.xpath("//input[@title='Search By Name']");
	public static By documentum_btnSearch = By.xpath("//button[@title='Search By Name']");
	
//	public static By documentum_lstDocument = By.xpath("//span[contains(@title,'pdf')]");
	public static By documentum_lstDocument = By.xpath("//div[@id='ObjectList_doclist_grid_0_data']//table//tr");
	public static By documentum_lstDocumentNotFound = By.
			xpath("//div[@id='ObjectList_doclist_grid_0_data']//table//tr/td/span[contains(text(),'No items found.')]");
	public static By documentum_lstDocumentSize = By.
			xpath("//div[@id='ObjectList_doclist_grid_0_data']//table//tr/td[3]/div");
	public static By documentum_lstDocumentItem = By.
			xpath("//div[@id='ObjectList_doclist_grid_0_data']//table//tr/td[3]/div//span[contains(text(),'pdf')]");
	public static By documentum_btnLogout = By.xpath("//button[@title='Logout']");
	

}
