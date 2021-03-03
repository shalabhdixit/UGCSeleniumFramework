/**
 * com.ctaf is a group of Selenium accelerators  
 */
package com.cigniti.accelerators;

import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import java.text.ParseException;


/**
 * ActionEngine is a wrapper class of Selenium actions
 */

public class ActionEngine extends TestEngine {
	public WebDriverWait wait;
	public Alert newAlert;
	String bool = configProps.getProperty("OnSuccessReports");

	boolean b = true; // /Boolean.parseBoolean(bool);

	// public boolean flag=false;

	/**
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * @return --boolean (true or false)
	 * @throws Throwable
	 */

	public boolean click(By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			waitForVisibilityOfElement(locator, locatorName);
			driver.findElement(locator).click();
			flag = true;
		} catch (Exception e) {

		} finally {
			if (!flag) {
				failureReport("Click " + locatorName, "Unable to click on "	+ locatorName);
				return true;
			} else if (b && flag) {
				SuccessReport("Click " + locatorName, "Successfully clicked on " + locatorName);
			}
		}
		return flag;
	}

	/**
	 * This method returns check existence of element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Textbox, checkbox etc)
	 * @return: Boolean value(True or False)
	 * @throws NoSuchElementException
	 */
	public boolean isElementPresent(By by, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(by);
			flag = true;
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			if (!flag) {
				failureReport("Verify presence of '" + locatorName + "'", "Unable to verify presence of '" + locatorName + "'");
			} else if (b && flag) {
//				SuccessReport("Verify presence of '" + locatorName + "'", "Successfully verified presence of '" + locatorName	+ "'");
			}
		}
	}

	public boolean isElementPresentdisc(By by, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(by);
			flag = true;
			return true;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		} finally {
			if (b && flag) {
				SuccessReport("IsElementPresent ", "Able to locate element "
						+ locatorName);
			}

		}
	}

	public boolean isPopUpElementPresent(By by, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			if (driver.findElement(by).isDisplayed())
				flag = true;
			else
				flag = false;
			return flag;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		} finally {
			if (!flag) {
				// failureReport("check IsElementPresent", locatorName
				// + " Element is not present on the page");
			} else if (b && flag) {
				SuccessReport("IsElementPresent ", "Able to locate element "
						+ locatorName);
			}

		}
	}

	/**
	 * This method used type value in to text box or text area
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param testdata
	 *            : Value wish to type in text box / text area
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Textbox,Text Area etc..)
	 * 
	 * @throws NoSuchElementException
	 */
	public void type(By locator, String testdata, String locatorName)
			throws Throwable {
		boolean flag = false;
		if (!testdata.trim().equals("")) {
			try {
				waitForVisibilityOfElement(locator, locatorName);
				driver.findElement(locator).clear();
				driver.findElement(locator).sendKeys("");
				driver.findElement(locator).sendKeys(testdata);
				flag = true;
			} catch (Exception e) {

			} finally {
				if (!flag) {
					failureReport(locatorName, "Data could not be entered. " + locatorName + " : " + testdata);
					// return true;
				} else if (b && flag) {
					if(locatorName.contains("Password")){
						SuccessReport(locatorName, "Data has been entered. " + locatorName + " : **************");
					}else{
						SuccessReport(locatorName, "Data has been entered. " + locatorName + " : " + testdata);
					}

				}
			}
			// return flag;
		}
	}

	/**
	 * Moves the mouse to the middle of the element. The element is scrolled
	 * into view and its location is calculated using getBoundingClientRect.
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:link,menus etc..)
	 * 
	 */
	public boolean mouseover(By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			new Actions(driver).moveToElement(mo).build().perform();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				failureReport("MouseOver", "MouseOver action could not be performed on " + locatorName);
			} else if (b && flag) {
				SuccessReport("MouseOver ",	"MouserOver Action is performed on " + locatorName);
			}
		}
	}

	/**
	 * A convenience method that performs click-and-hold at the location of the
	 * source element, moves by a given offset, then releases the mouse.
	 * 
	 * @param source
	 *            : Element to emulate button down at.
	 * 
	 * @param xOffset
	 *            : Horizontal move offset.
	 * 
	 * @param yOffset
	 *            : Vertical move offset.
	 * 
	 */
	public boolean draggable(By source, int x, int y, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {

			WebElement dragitem = driver.findElement(source);
			new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();
			Thread.sleep(5000);
			flag = true;
			return true;

		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				failureReport("Draggable ",
						"Draggable action is not performed on " + locatorName);

			} else if (b && flag) {

				SuccessReport("Draggable ", "Draggable Action is Done on "
						+ locatorName);
			}
		}
	}

	/**
	 * A convenience method that performs click-and-hold at the location of the
	 * source element, moves to the location of the target element, then
	 * releases the mouse.
	 * 
	 * @param source
	 *            : Element to emulate button down at.
	 * 
	 * @param target
	 *            : Element to move to and release the mouse at.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Button,image etc..)
	 * 
	 */
	public boolean draganddrop(By source, By target, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement from = driver.findElement(source);
			WebElement to = driver.findElement(target);
			new Actions(driver).dragAndDrop(from, to).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				failureReport("DragAndDrop ",
						"DragAndDrop action is not perform on " + locatorName);

			} else if (b && flag) {

				SuccessReport("DragAndDrop ", "DragAndDrop Action is Done on "
						+ locatorName);
			}
		}
	}

	/**
	 * To slide an object to some distance
	 * 
	 * @param slider
	 *            : Action to be performed on element
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 */
	public boolean slider(By slider, int x, int y, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {
			WebElement dragitem = driver.findElement(slider);
			// new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()
			// .perform();
			new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();// 150,0
			Thread.sleep(5000);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				failureReport("Slider ", "Slider action is not perform on "
						+ locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else if (b && flag) {
				SuccessReport("Slider ", "Slider Action is Done on "
						+ locatorName);
			}
		}
	}

	/**
	 * To right click on an element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 * @throws Throwable
	 */

	public boolean rightclick(By by, String locatorName) throws Throwable {

		boolean flag = false;
		try {
			WebElement elementToRightClick = driver.findElement(by);
			Actions clicker = new Actions(driver);
			clicker.contextClick(elementToRightClick).perform();
			flag = true;
			return true;
			// driver.findElement(by1).sendKeys(Keys.DOWN);
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				failureReport("RightClick ", "RightClick action is not perform on " + locatorName);

			} else if (b && flag) {
				SuccessReport("RightClick ", "RightClick Action is Done on " + locatorName);
			}
		}
	}

	public boolean waitUntilinvisibilityOfElementWithText(By by,
			String expectedText, String locator) throws Throwable {
		wait = new WebDriverWait(driver, 120);
		boolean flag = false;

		try {
			wait.until(ExpectedConditions.invisibilityOfElementWithText(by,
					expectedText));

			flag = true;
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				failureReport("WaitUntilinvisibilityOfElementWithText ",
						" Falied to locate element " + locator + " with text "
								+ expectedText);
			} else if (b && flag) {
				SuccessReport(" WaitUntilinvisibilityOfElementWithText ",
						" Successfully located element " + locator
								+ " with text " + expectedText);
			}

		}

	}

	/**
	 * Wait for an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 */

	public boolean waitForTitlePresent(By locator) throws Throwable {

		boolean flag = false;
		boolean bValue = false;

		try {
			for (int i = 0; i < 200; i++) {
				if (driver.findElements(locator).size() > 0) {
					flag = true;
					bValue = true;
					break;
				} else {
					driver.wait(50);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				failureReport("WaitForTitlePresent ", "Title is wrong");

			} else if (b && flag) {
				SuccessReport("WaitForTitlePresent ",
						"Launched successfully expected Title ");
			}
		}
		return bValue;
	}

	/**
	 * Wait for an ElementPresent
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return Whether or not the element is displayed
	 */
	public boolean waitForElementPresent(By by, String locator)
			throws Throwable {
		boolean flag = false;
		try {
			for (int i = 0; i < 300; i++) {
				if (driver.findElement(by).isDisplayed()) {
					flag = true;
					return true;

				} else {
					Thread.sleep(100);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			if (!flag) {
				failureReport("WaitForElementPresent ",
						"Falied to locate element " + locator);
			} else if (b && flag) {
				SuccessReport("WaitForElementPresent ",
						"Successfully located element " + locator);
			}
		}

		return flag;

	}

	/**
	 * This method Click on element and wait for an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param waitElement
	 *            : Element name wish to wait for that (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 */
	public boolean clickAndWaitForElementPresent(By locator, By waitElement,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			click(locator, locatorName);
			waitForElementPresent(waitElement, locatorName);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				failureReport("ClickAndWaitForElementPresent ",
						"Failed to perform clickAndWaitForElementPresent action");
			} else if (b && flag) {
				SuccessReport("ClickAndWaitForElementPresent ",
						"successfully performed clickAndWaitForElementPresent action");
			}
		}
	}

	/**
	 * Select a value from Dropdown using send keys
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param value
	 *            : Value wish type in dropdown list
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 * 
	 */
	public boolean selectBySendkeys(By locator, String value, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				failureReport("Select ", value
						+ "is Not Select from the DropDown " + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else if (b && flag) {
				SuccessReport("Select ", value
						+ " is Selected from the DropDown " + locatorName);
			}
		}
	}

	/**
	 * select value from DropDown by using selectByIndex
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param index
	 *            : Index of value wish to select from dropdown list.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 * 
	 */
	public boolean selectByIndex(By locator, int index, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				failureReport("Select ", "Option at index " + index
						+ " is Not Select from the DropDown" + locatorName);

			} else if (b && flag) {
				SuccessReport("Select ", "Option at index " + index
						+ " is Selected from the DropDown" + locatorName);
			}
		}
	}

	/**
	 * select value from DD by using value
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param value
	 *            : Value wish to select from dropdown list.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 */

	public boolean selectByValue(By locator, String value, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				failureReport("Select", value
						+ " could not be Selected from the DropDown "
						+ locatorName);

			} else if (b && flag) {
				SuccessReport("Select ", value
						+ " is Selected from the DropDown " + locatorName);
			}
		}
	}

	/**
	 * select value from DropDown by using selectByVisibleText
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param visibletext
	 *            : VisibleText wish to select from dropdown list.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 */

	public boolean selectByVisibleText(By locator, String visibletext,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				failureReport("Select ", visibletext + " is Not Select from the DropDown " + locatorName);

			} else if (b && flag) {
				SuccessReport("Select ", visibletext + "  is Selected from the DropDown " + locatorName);
			}
		}
	}

	/**
	 * SWITCH TO WINDOW BY USING TITLE
	 * 
	 * @param windowTitle
	 *            : Title of window wish to switch
	 * 
	 * @param count
	 *            : Selenium launched Window id (integer no)
	 * 
	 * @return: Boolean value(true or false)
	 * 
	 */
	//
	public boolean switchWindowByTitle(String windowTitle, int count)
			throws Throwable {
		boolean flag = false;
		try {
			// Set<String> windowList = driver.getWindowHandles();
			// int windowCount = windowList.size();
			// Calendar calendar = new GregorianCalendar();
			// int second = calendar.get(Calendar.SECOND); // /to get current
			// time
			// int timeout = second + 40;
			/*
			 * while (windowCount != count && second < timeout) {
			 * Thread.sleep(500); windowList = driver.getWindowHandles();
			 * windowCount = windowList.size();
			 * 
			 * }
			 */

			// String[] array = windowList.toArray(new String[0]);

			// for (int i = 0; i <= windowCount; i++) {
			//
			// driver.switchTo().window(array[count - 1]);
			//
			// // if (driver.getTitle().contains(windowTitle))
			// flag = true;
			// return true;
			// }
			return false;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				failureReport("SelectWindow ", "The Window with title "
						+ windowTitle + " is not Selected");

			} else if (b && flag) {
				SuccessReport("SelectWindow ",
						"Focus navigated to the window with title "
								+ windowTitle);
			}
		}
	}

	/**
	 * Function To get column count and print data in Columns
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return: Returns no of columns.
	 * 
	 */
	public int getColumncount(By locator) throws Exception {

		WebElement tr = driver.findElement(locator);
		List<WebElement> columns = tr.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;

	}

	/**
	 * Function To get row count and print data in rows
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return: returns no of rows.
	 */
	public int getRowCount(By locator) throws Exception {

		WebElement table = driver.findElement(locator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}

	/**
	 * Verify alert present or not
	 * 
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 * 
	 */
	public boolean Alert() throws Throwable {
		boolean flag = false;
		boolean presentFlag = false;
		Alert alert = null;

		try {

			// Check the presence of alert
			alert = driver.switchTo().alert();
			// if present consume the alert
			alert.accept();
			presentFlag = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (presentFlag) {
				failureReport("Alert ", "There was no alert to handle");
			} else if (b && flag) {
				SuccessReport("Alert ", "The Alert is handled successfully ");
			}
		}

		return presentFlag;
	}

	/**
	 * To launch URL
	 * 
	 * @param url
	 *            : url value want to launch
	 * @throws Throwable
	 * 
	 */
	public boolean launchUrl(String url) throws Throwable {
		boolean flag = false;
		try {
			// System.out.println(url);
			driver.navigate().to(url);
			ImplicitWait();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
//				failureReport("Launching URL ", "Failed to launch " + url);
			} else if (b && flag) {
				SuccessReport("Launching URL ", "Successfully launched " + url);
			}
		}
	}

	/*
	 * public static int getResponseCode(String url) { try { return
	 * Request.Get(url).execute().returnResponse().getStatusLine()
	 * .getStatusCode(); } catch (Exception e) { throw new RuntimeException(e);
	 * } }
	 */
	/**
	 * This method verify check box is checked or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:sign in Checkbox etc..)
	 * 
	 * @return: boolean value(True: if it is checked, False: if not checked)
	 * 
	 */
	public boolean isChecked(By locator, String locatorName) throws Throwable {
		boolean bvalue = false;
		boolean flag = false;
		try {
			if (driver.findElement(locator).isSelected()) {
				flag = true;
				bvalue = true;
			}

		} catch (NoSuchElementException e) {

			bvalue = false;
		} finally {
			if (!flag) {
				SuccessReport("IsChecked ", locatorName + " is Selected ");
				// throw new ElementNotFoundException("", "", "");

			} else if (b && flag) {
				failureReport("IsChecked ", locatorName + " is not Select ");
			}
		}
		return bvalue;
	}

	/**
	 * Element is enable or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, UserName
	 *            Textbox etc..)
	 * 
	 * @return: boolean value (True: if the element is enabled, false: if it not
	 *          enabled).
	 * 
	 */

	public boolean isEnabled(By locator, String locatorName) throws Throwable {
		Boolean value = false;
		boolean flag = false;
		try {
			if (driver.findElement(locator).isEnabled()) {
				flag = true;
				value = true;
			}

		} catch (Exception e) {

			flag = false;

		} finally {
			if (!flag) {
				failureReport("IsEnabled ", locatorName + " is not Enabled");

			} else if (b && flag) {
				SuccessReport("IsEnabled ", locatorName + " is Enable");
			}
		}
		return value;
	}

	/**
	 * Element visible or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 * @return: boolean value(True: if the element is visible, false: If element
	 *          not visible)
	 * 
	 */

	public boolean isVisible(By locator, String locatorName) throws Throwable {
		Boolean value = false;
		boolean flag = false;
		try {

			value = driver.findElement(locator).isDisplayed();
			value = true;
			flag = true;
		} catch (Exception e) {
			flag = false;
			value = false;

		} finally {
			if (!flag) {
				failureReport("IsVisible ", locatorName
						+ " Element is Not Visible");
			} else if (b && flag) {
				SuccessReport("IsVisible ", locatorName
						+ " Element is Visible ");

			}
		}
		return value;
	}

	/**
	 * Get the CSS value of an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, label color
	 *            etc..)
	 * 
	 * @param cssattribute
	 *            : CSS attribute name wish to verify the value (id, name,
	 *            etc..)
	 * 
	 * @return: String CSS value of the element
	 * 
	 */

	public String getCssValue(By locator, String cssattribute,
			String locatorName) throws Throwable {
		String value = "";
		boolean flag = false;
		try {
			if (isElementPresent(locator, "locatorName")) {
				value = driver.findElement(locator).getCssValue(cssattribute);
				flag = true;
			}
		} catch (Exception e) {

		} finally {
			if (!flag) {
				failureReport("GetCssValue ",
						" Was able to get Css value from " + locatorName);

			} else if (b & flag) {
				SuccessReport("GetCssValue ",
						" Was not able to get Css value from " + locatorName);
			}
		}
		return value;
	}

	/**
	 * Check the expected value is available or not
	 * 
	 * @param expvalue
	 *            : Expected value of attribute
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param attribute
	 *            : Attribute name of element wish to assert
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * 
	 */
	public boolean assertValue(String expvalue, By locator, String attribute,
			String locatorName) throws Throwable {

		boolean flag = false;
		try {
			AssertJUnit.assertEquals(expvalue,
					getAttribute(locator, attribute, locatorName));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (!flag) {
				failureReport("AssertValue ", locatorName
						+ " present in the page");
				return false;
			} else if (b & flag) {
				SuccessReport("AssertValue ", locatorName
						+ " is not present in the page ");
			}
		}
		return flag;
	}

	/**
	 * Check the text is presnt or not
	 * 
	 * @param text
	 *            : Text wish to assert on the page.
	 * 
	 */
	public boolean assertTextPresent(String text) throws Throwable {
		boolean flag = false;
		try {
			AssertJUnit.assertTrue(isTextPresent(text));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (!flag) {
				failureReport("AssertTextPresent ", text
						+ " present in the page ");
				return false;
			} else if (b & flag) {
				SuccessReport("AssertTextPresent ", text
						+ " is not present in the page ");
			}
		}
		return flag;
	}

	/**
	 * Assert element present or not
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 */
	public boolean assertElementPresent(By by, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {
			AssertJUnit.assertTrue(isElementPresent(by, locatorName));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (!flag) {
				failureReport("AssertElementPresent ", locatorName
						+ " present in the page ");
				return false;
			} else if (b & flag) {
				SuccessReport("AssertElementPresent ", locatorName
						+ " is not present in the page ");
			}
		}
		return flag;

	}

	/**
	 * Assert text on element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param text
	 *            : expected text to assert on the element
	 * 
	 */

	public boolean assertText(By by, String text) throws Throwable {
		boolean flag = false;
		String appText = getText(by, text).trim();
		try {
			Assert.assertEquals(appText, text.trim());
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				 failureReport("AssertText ", text + " is not present in the element. _" + appText + "_");
				return false;

			} else if (b && flag) {
				// SuccessReport("AssertText ", text
				// + " is  present in the element ");
			}
		}

	}

	/**
	 * Assert text on element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param text
	 *            : expected text to assert on the element
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * 
	 */
	public boolean verifyText(By by, String text, String locatorName)
			throws Throwable {
		boolean flag = false;

		try {

			String vtxt = getText(by, locatorName).trim();
			vtxt.equals(text.trim());
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				failureReport("VerifyText ", text
						+ " is not present in the location " + locatorName);
				flag = true;
			} else if (b && flag) {
				SuccessReport("VerifyText ", text
						+ " is present in the location " + locatorName);
				flag = false;
			}
		}
	}

	/**
	 * @return: return title of current page.
	 * 
	 * @throws Throwable
	 */

	public String getTitle() throws Throwable {

		String text = driver.getTitle();
		if (b) {
			SuccessReport("Title ", "Title of the page is " + text);
		}
		return text;
	}

	/**
	 * Assert Title of the page.
	 * 
	 * @param title
	 *            : Expected title of the page.
	 * 
	 */
	public boolean asserTitle(String title) throws Throwable {
		boolean flag = false;

		try {
			By windowTitle = By.xpath("//title[contains(text(),'" + title
					+ "')]");
			if (waitForTitlePresent(windowTitle)) {
				AssertJUnit.assertEquals(getTitle(), title);
				flag = true;
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {

			if (!flag) {
				failureReport("AsserTitle ", "Page title is not matched with "
						+ title);
				return false;
			} else if (b && flag) {
				SuccessReport("AsserTitle ", " Page title is verified with "
						+ title);
			}
		}

	}

	/**
	 * Verify Title of the page.
	 * 
	 * @param title
	 *            : Expected title of the page.
	 * 
	 */
	public boolean verifyTitle(String title) throws Throwable {

		boolean flag = false;

		try {
			getTitle().equals(title);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		}

		finally {
			if (!flag) {
				failureReport("VerifyTitle ", "Page title is not matched with "
						+ title);

			} else if (b && flag) {
				SuccessReport("VerifyTitle ", " Page title is verified with "
						+ title);

			}
		}
	}

	/**
	 * Verify text present or not
	 * 
	 * @param text
	 *            : Text wish to verify on the current page.
	 * 
	 */
	public boolean verifyTextPresent(String text) throws Throwable {
		boolean flag = false;
		;
		if (!(driver.getPageSource()).contains(text)) {

			failureReport("VerifyTextPresent ", text
					+ " is not present in the page ");
			flag = false;
		} else if (b && flag) {
			SuccessReport("VerifyTextPresent ", text
					+ " is present in the page ");
			flag = true;

		}
		return flag;
	}

	/**
	 * Get the value of a the given attribute of the element.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param attribute
	 *            : Attribute name wish to assert the value.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:label, SignIn Link etc..)
	 * 
	 * @return: String attribute value
	 * 
	 */

	public String getAttribute(By by, String attribute, String locatorName)
			throws Throwable {
		String value = "";
		if (isElementPresent(by, locatorName)) {
			value = driver.findElement(by).getAttribute(attribute);
		}
		return value;
	}

	/**
	 * Text present or not
	 * 
	 * @param text
	 *            : Text wish to verify on current page
	 * 
	 * @return: boolean value(true: if Text present, false: if text not present)
	 */

	public boolean isTextPresent(String text) throws Throwable {

		boolean value = false;
		if (driver.getPageSource().contains(text)) {
			value = true;
			flag = true;
		} else {
			System.out.println("is text " + text + " present  " + value);
			flag = false;
		}
		if (!value) {
			failureReport("IsTextPresent ", text
					+ " is  not presented in the page ");
			// System.out.println("is text boolean "+text+" present  " + value);
			return false;
		} else if (b && flag) {
			SuccessReport("IsTextPresent ", "'" + text + "'"
					+ " is presented in the page ");

			return true;
		}
		return value;
	}

	/**
	 * The innerText of this element.
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:label text, SignIn Link
	 *            etc..)
	 * 
	 * @return: String return text on element
	 * 
	 */

	public String getText(By locator, String locatorName) throws Throwable {
		String text = "";
		boolean flag = false;
		try {
			if (isElementPresent(locator, locatorName)) {
				text = driver.findElement(locator).getText();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				warningReport("Get text from " + locatorName, "Unable to extract data from " + locatorName);
			} else if (b && flag) {
//				SuccessReport("Get text from " + locatorName, "Successfully extracted data from " + locatorName);
			}
		}
		return text;
	}

	public String getValue(String locator, String locatorName) throws Throwable {
		String text = "";
		boolean flag = false;
		try {
			if (driver.findElement(By.xpath(locator)).isDisplayed()) {
				text = driver.findElement(By.xpath(locator)).getAttribute(
						"value");
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				failureReport("Get value from " + locatorName,
						" Unable to get Text from " + locatorName);
			} else if (b && flag) {
				SuccessReport("Get value from " + locatorName,
						" Able to get Text from " + locatorName);
			}
		}
		return text;
	}

	public int getElementsSize(By locator, String locatorName) throws Throwable {
		int text = 0;
		try {
			if (driver.findElement(locator).isDisplayed()) {
				text = driver.findElements(locator).size();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return text;
	}

	/**
	 * Capture Screenshot
	 * 
	 * @param fileName
	 *            : FileName screenshot save in local directory
	 * 
	 */
	public void screenShot(String fileName) {
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			// Now you can do whatever you need to do with it, for example copy
			// somewhere
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Click on the Link
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:SignIn Link, menu's
	 *            etc..)
	 */

	public boolean mouseHoverByJavaScript(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				failureReport("Mouse Hover on " + locatorName,
						"MouseOver action is not perform on " + locatorName);
			} else if (b && flag) {
				SuccessReport("Mouse Hover on " + locatorName,
						"MouserOver Action is Done on " + locatorName);
			}
		}
	}

	public boolean JSClick(By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			// driver.executeAsyncScript("arguments[0].click();", element);

			flag = true;

		}

		catch (Exception e) {

		} finally {
			if (!flag) {
				failureReport("MouseOver ",
						" MouseOver action is not perform on " + locatorName);
				return flag;
			} else if (b && flag) {
				SuccessReport("MouseOver ", " MouserOver Action is Done on "
						+ locatorName);
				return flag;
			}
		}
		return flag;
	}

	public boolean JSType(By locator, String text, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].value='" + text + "';",
					element);
			// driver.executeAsyncScript("arguments[0].click();", element);

			flag = true;

		}

		catch (Exception e) {

		} finally {
			if (!flag) {
				failureReport("MouseOver ",
						" MouseOver action is not perform on " + locatorName);
				return flag;
			} else if (b && flag) {
				SuccessReport("MouseOver ", " MouserOver Action is Done on "
						+ locatorName);
				return flag;
			}
		}
		return flag;
	}

	/**
	 * This method switch the focus to selected frame using frame index
	 * 
	 * @param index
	 *            : Index of frame wish to switch
	 * 
	 */
	public boolean switchToFrameByIndex(int index) throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				failureReport("SelectFrame ", " Frame with index " + index
						+ " is not selected");
			} else if (b && flag) {
				SuccessReport("SelectFrame ", " Frame with index " + index
						+ " is selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame ID.
	 * 
	 * @param idValue
	 *            : Frame ID wish to switch
	 * 
	 */
	public boolean switchToFrameById(String idValue) throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(idValue);
			flag = true;
			return flag;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				failureReport("SelectFrame ", " Frame with Id " + idValue
						+ " is not selected");
			} else if (b && flag) {
				SuccessReport("SelectFrame ", " Frame with Id " + idValue
						+ " is selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue
	 *            : Frame Name wish to switch
	 * 
	 */
	public boolean switchToFrameByName(String nameValue) throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				failureReport("SelectFrame ", " Frame with Name " + nameValue
						+ " is not selected");
			} else if (b && flag) {
				SuccessReport("SelectFrame ", " Frame with Name " + nameValue
						+ " is selected");
			}
		}
	}

	/**
	 * This method switch the to Default Frame.
	 * 
	 * @throws Throwable
	 */
	public boolean switchToDefaultFrame() throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				failureReport("SelectFrame ", " The Frame is not selected");
			} else if (b && flag) {
				SuccessReport("SelectFrame ", " Frame with Name is selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue
	 *            : Frame Name wish to switch
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:SignIn Link, login button
	 *            etc..)
	 * 
	 * 
	 */
	public boolean switchToFrameByLocator(By lacator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(driver.findElement(lacator));
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				failureReport("SelectFrame ", " The Frame " + locatorName
						+ " is not selected");
			} else if (b && flag) {
				SuccessReport("SelectFrame ", " Frame with Name " + locatorName
						+ " is selected");
			}
		}
	}

	/**
	 * This method wait selenium until element present on web page.
	 */
	public void ImplicitWait() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public boolean waitUntilTextPresents(By by, String expectedText,
			String locator) throws Throwable {
		wait = new WebDriverWait(driver, 120);
		boolean flag = false;

		try {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(by,
					expectedText));

			flag = true;
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				failureReport("WaitUntilTextPresent ",
						" Falied to locate element " + locator + " with text "
								+ expectedText);
			} else if (b && flag) {
				SuccessReport(" WaitUntilTextPresent ",
						" Successfully located element " + locator
								+ " with text " + expectedText);
			}

		}

	}

	/**
	 * Click on Element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:SignIn Link, login button
	 *            etc..)
	 * 
	 * @throws StaleElementReferenceException
	 *             - If the element no longer exists as initially defined
	 */

	public boolean click1(WebElement locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			locator.click();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				failureReport("Click ", " Unable to click on " + locatorName);
				return false;
			} else if (b && flag) {
				SuccessReport("Click ", " able to click on " + locatorName);
				return true;
			}
		}

	}

	/**
	 * 
	 * This method wait driver until given driver time.
	 * 
	 */
	public WebDriverWait driverwait() {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait;
	}

	/**
	 * This method wait selenium until visibility of Elements on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param locator
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * @throws Throwable
	 * 
	 */

	public boolean waitForVisibilityOfElement(By by, String locatorName)
			throws Throwable {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				failureReport("Wait for " + locatorName + " to be Visible",
						locatorName + " is not visible");
			} else if (b && flag) {
				SuccessReport("Wait for " + locatorName + " to be Visible",
						locatorName + " is visible");
			}
		}
	}

	/**
	 * This method wait driver until Invisibility of Elements on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locator
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 */
	public boolean waitForInVisibilityOfElement(By by, String locator)
			throws Throwable {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				failureReport("Wait for " + locator + " to be Invisible",
						locator + " is visible");
			} else if (b && flag) {
				SuccessReport("Wait for " + locator + " to be Invisible",
						locator + " is not visible");
			}
		}

	}

	public List<WebElement> getElements(By locator) {

		List<WebElement> ele = driver.findElements(locator);

		return ele;
	}

	public boolean assertTextMatching(By by, String text, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			String ActualText = getText(by, text).trim();
			System.out.println("assertTextMatching" + ActualText);
			if (ActualText.contains(text)) {
				flag = true;
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				failureReport("Verify " + locatorName, text
						+ " is not present in the element");
				return false;

			} else if (b && flag) {
				SuccessReport("Verify " + locatorName, text
						+ " is  present in the element ");
			}
		}

	}

	// QuickFlix Funcations added

	public boolean isElementDisplayed(WebElement element) throws Throwable {
		boolean flag = false;
		for (int i = 0; i < 200; i++) {
			if (element.isDisplayed()) {
				flag = true;
				break;
			} else {
				Thread.sleep(50);
			}
		}
		return flag;
	}

	public void executeJavaScriptOnElement(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}

	public void closeBrowser() {
		driver.close();
		driver.quit();
	}

	public boolean hitKey(By locator, Keys keyStroke, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(keyStroke);
			flag = true;
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			if (flag) {
				// SuccessReport("Type ","Data typing action is performed on"
				// + locatorName+" with data is "+testdata);

			} else {
				failureReport("Type ", " Data typing action is not perform on"
						+ locatorName + " with data is " + keyStroke);

			}
		}
	}

	public Collection<WebElement> getWebElementsByTagInsideLocator(By locator,
			String tagName, String locatorName) throws Throwable {
		boolean flag = false;
		Collection<WebElement> elements;
		try {
			WebElement element = driver.findElement(locator);
			elements = element.findElements(By.tagName(tagName));
			flag = true;
		} catch (NoSuchElementException e) {
			throw e;
		} finally {
			if (!flag) {
				failureReport("Type ", "Data typing action is not perform on "
						+ locatorName + " with data is " + tagName);
			}
		}
		return elements;
	}

	public void mouseOverElement(WebElement element, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				failureReport("MouseOver ",
						" MouseOver action is not perform on" + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else {
				SuccessReport("MouseOver ", " MouserOver Action is Done on "
						+ locatorName);
			}
		}
	}

	@Parameters({ "browser" })
	public void SuccessReport(String strStepName, String strStepDescription)
			throws Throwable {

		int intReporterType = Integer.parseInt(configProps
				.getProperty("reportsType"));

		switch (intReporterType) {
		case 1:

			break;
		case 2:
			if (configProps.getProperty("OnSuccessScreenshot")
					.equalsIgnoreCase("True")) {
				screenShot(TestEngine.filePath()
						+ strStepDescription.replace(" ", "_") + ".jpeg");
			}
			onSuccess(strStepName, strStepDescription);

			break;

		default:
			if (configProps.getProperty("OnSuccessScreenshot")
					.equalsIgnoreCase("True")) {
				screenShot(TestEngine.filePath()
						+ strStepDescription.replace(" ", "_") + ".jpeg");
			}
			onSuccess(strStepName, strStepDescription);
			break;
		}
	}

	public void failureReport(String strStepName, String strStepDescription)
			throws Throwable {
		int intReporterType = Integer.parseInt(configProps
				.getProperty("reportsType"));

		String imageFileName;
		
		strStepDescription.replace(":", "");
		strStepDescription.replace(",", "");
		strStepDescription.replace("-", "");
		strStepDescription.replace("'", "");
		strStepDescription.replace("(", "");
		strStepDescription.replace(")", "");
		strStepDescription.replace("/", "");
		strStepDescription.replace("$", "");
		strStepDescription.replace("%", "");

		switch (intReporterType) {
		case 1:
			flag = true;
			break;
		case 2:
			// imageFileName = TestEngine.filePath() + "/" + "Screenshots" + "/"
			// + strStepDescription.replace(" ", "_") + ".jpeg";
			imageFileName = TestEngine.filePath() + "/" + "Screenshots" + "/"
					+ strStepDescription + ".jpeg";
			screenShot(imageFileName);
			flag = true;
			onFailure(strStepName, strStepDescription);
			break;

		default:
			flag = true;
			// imageFileName = TestEngine.filePath() + "/" + "Screenshots" + "/"
			// + strStepDescription.replace(" ", "_") + ".jpeg";
			imageFileName = TestEngine.filePath() + "/" + "Screenshots" + "/"
					+ strStepDescription + ".jpeg";
			screenShot(imageFileName);
			onFailure(strStepName, strStepDescription);
			break;
		}
	}

	public void warningReport(String strStepName, String strStepDescription)
			throws Throwable {
		int intReporterType = Integer.parseInt(configProps.getProperty("reportsType"));
		String imageFileName;

		switch (intReporterType) {
		case 1:
			flag = true;
			break;
		case 2:
			imageFileName = TestEngine.filePath() + "/" + "Screenshots" + "/"
					+ strStepDescription.replace(" ", "_") + ".jpeg";
			screenShot(imageFileName);
			flag = true;
			onWarning(strStepName, strStepDescription);
			break;

		default:
			flag = true;
			imageFileName = TestEngine.filePath() + "/" + "Screenshots" + "/"
					+ strStepDescription.replace(" ", "_") + ".jpeg";
			screenShot(imageFileName);
			onWarning(strStepName, strStepDescription);
			break;
		}

	}

	public void informationReport(String strStepName, String strStepDescription)
			throws Throwable {
		int intReporterType = Integer.parseInt(configProps.getProperty("reportsType"));
		String imageFileName;

		switch (intReporterType) {
		case 1:
			flag = true;
			break;
		case 2:
			imageFileName = TestEngine.filePath() + "/" + "Screenshots" + "/"
					+ strStepDescription.replace(" ", "_") + ".jpeg";
			screenShot(imageFileName);
			flag = true;
			onInformation(strStepName, strStepDescription);
			break;

		default:
			flag = true;
			imageFileName = TestEngine.filePath() + "/" + "Screenshots" + "/"
					+ strStepDescription.replace(" ", "_") + ".jpeg";
			screenShot(imageFileName);
			onInformation(strStepName, strStepDescription);
			break;
		}

	}

	public static boolean isValidDateFormat(String format, String value){
		Date date = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(value);
			if (!value.equals(sdf.format(date))) {
				date = null;
			}
		}catch(ParseException ex){
			ex.printStackTrace();
		}
		return date != null;
	}


	public boolean switchToMainElement() {
		try {
			driver.switchTo().activeElement();
			return true;
		} // try
		catch (NoAlertPresentException Ex) {
			return false;
		} // catch
	}

	public boolean isAlertPresent() {
		try {
			newAlert = driver.switchTo().alert();
			return true;
		} // try
		catch (NoAlertPresentException ex) {
			ex.getMessage();
			return false;
		} // catch
	}
}
