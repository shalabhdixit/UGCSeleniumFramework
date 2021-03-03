package com.ugc.objectRepository;

import org.openqa.selenium.By;

public class MIGNG_LoginPage {

	public static By MIGNG_textVerify = By.xpath("//*[@id='header_welcome']/a/img[contains(@alt,'Welcome to UNITED GUARANTY')]");
	public static By MIGNG_userName = By.xpath("//input[@id='login_username']");
	public static By MIGNG_password = By.xpath("//input[@id='login_password']");
	public static By MIGNG_login = By.xpath("//input[@id='login_btn']");
	

}
