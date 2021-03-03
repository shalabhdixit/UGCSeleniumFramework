package com.ugc.objectRepository;

import org.openqa.selenium.By;

public class MIGNG_HomePage {


	public static By MIGNG_pipeline = By.xpath("//div[@id='pipeline']");
//	public static By MIGNG_headerPipeline = By.xpath("//ul[@class='nav navbar-nav navbar-left']/li[1]/a");
	public static By MIGNG_headerPipeline = By.xpath("//ul[@class='nav navbar-nav navbar-left']/li[1]/a[contains(text(),'Pipeline')]");
	public static By MIGNG_headerNewQuote = By.xpath("//ul[@class='nav navbar-nav navbar-left']/li[2]/a[contains(text(),'New Quote')]");
	public static By MIGNG_headerNewFullFile = By.xpath("//ul[@class='nav navbar-nav navbar-left']/li[3]/a");
	public static By MIGNG_headerDelegated = By.xpath("//ul[@class='nav navbar-nav navbar-left']/li[4]/a");
	
//	public static By MIGNG_headerReports = By.xpath("//ul[@class='nav navbar-nav navbar-left']/li[5]/a");
	public static By MIGNG_headerPortfolio = By.xpath("//ul[@class='nav navbar-nav navbar-left']/li/a[contains(text(),'Portfolio')]");
	public static By MIGNG_headerReports = By.xpath("//ul[@class='nav navbar-nav navbar-left']/li/a[contains(text(),'Reports')]");
	
	public static By MIGNG_pipelineQuote = By.xpath("//table[@id='entities']/tbody/tr[1]/td[text()='Quote' or 'Application']");
	public static By MIGNG_pipelineData = By.xpath("// table[@id='entities']//tbody/tr");
	public static By MIGNG_portfolioHeader = By.xpath("// table[@id='entities']/thead/tr/th");
	public static By MIGNG_logout = By.xpath("//button[@id='logout']");
	
	public static By MIGNG_portfolio = By.xpath("//div[@id='reports']//h1[contains(text(),'Portfolio')]");
	public static By MIGNG_reportsHeader = By.xpath("//div[@id='reports']//h1[contains(text(),'Reports')]");
	public static By MIGNG_applicationHeader = By.xpath("//h1[@class='entity-title'][contains(text(),'New Full-file Application')]");
	public static By MIGNG_searchField	= By.xpath("//div[contains(@class,'search')]//input");
	public static By MIGNG_searchButton	= By.xpath("//div[contains(@class,'search')]//button");
	public static By MIGNG_applicationStatus = By.xpath("//h4[@class='entity-status']");
	public static By MIGNG_showMe = By.xpath("//select[@id='showMe']");
	public static By MIGNG_emptySearch = By.xpath("//table[@id='entities']/tbody/tr/td[@class='dataTables_empty']");
	
	
	public static By MIGNG_delegated = By.xpath("//a[@name='rapidlinklink']");

}
