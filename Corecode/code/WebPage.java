package code;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import bsh.ParseException;
import databin.CustomizableEntity;

public abstract class WebPage {

	private By by;
	public WebDriver driver;
	protected WebDriverWrapper webDriverWrapper;
	protected String pageName;
	WebDriverWait wait;
	public WebPage(WebDriver driver, String pageName) {
		this.driver = driver;
		this.pageName = pageName;
		webDriverWrapper = new WebDriverWrapper(driver);
	}
	public WebPage(){

	}
	private WebElement waitForElementAndReturnElement(PageElement pageElement) {
		switch (pageElement.getWaitType()) {
		case WAITFORELEMENTTOBECLICKABLE:
			return webDriverWrapper.waitForElementToBeClickable(pageElement.getBy(), pageElement.getTimeOut());

		case WAITFORELEMENTTOBEEENABLED:
			return webDriverWrapper.waitForElementToBeEnabled(pageElement.getBy(), pageElement.getTimeOut());

		case WAITFORELEMENTTOBEDISPLAYED:
			return webDriverWrapper.waitForElementToBeDisplayed(pageElement.getBy(), pageElement.getTimeOut());

		default:
			return driver.findElement(pageElement.getBy());
		}
	}

	protected void waitForPageElement(PageElement pageElement) {
		boolean isWebElementAvailableInPageElement = isWebElementAvailableInPageElement(pageElement);
		switch (pageElement.getWaitType()) {
		case WAITFORELEMENTTOBECLICKABLE:
			if (!isWebElementAvailableInPageElement)
				webDriverWrapper.waitForElementToBeClickable(pageElement.getBy(), pageElement.getTimeOut());
			else
				webDriverWrapper.waitForElementToBeClickable(pageElement.getWebElement(), pageElement.getTimeOut());
			break;

		case WAITFORELEMENTTOBEEENABLED:
			if (!isWebElementAvailableInPageElement)
				webDriverWrapper.waitForElementToBeEnabled(pageElement.getBy(), pageElement.getTimeOut());
			else
				webDriverWrapper.waitForElementToBeEnabled(pageElement.getWebElement(), pageElement.getTimeOut());
			break;

		case WAITFORELEMENTTOBEDISPLAYED:
			if (!isWebElementAvailableInPageElement)
				webDriverWrapper.waitForElementToBeDisplayed(pageElement.getBy(), pageElement.getTimeOut());
			else
				webDriverWrapper.waitForElementToBeDisplayed(pageElement.getWebElement(), pageElement.getTimeOut());
			break;
		default:
			break;
		}
	}

	private boolean isWebElementAvailableInPageElement(PageElement pageElement) {
		return !(pageElement.getWebElement() == null);
	}

	protected WebElement getWebElement(PageElement pageElement) {
		if (pageElement.isSlowLoadableComponent()) {
			return waitForElementAndReturnElement(pageElement);
		} else
			return driver.findElement(pageElement.getBy());
	}

	protected void sendKeys (PageElement pageElement, String value) {
		try {
			value = (value == null) ? "" : value;

			if (!isWebElementAvailableInPageElement(pageElement))
				getWebElement(pageElement).sendKeys(value);
			else
				pageElement.getWebElement().sendKeys(value);

		} catch (Exception exception) { 
			throw new ScriptExecutionException ("Failed to type value: '" + value + "' in " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}

	protected void sendKeys (PageElement pageElement, Keys key) {
		try {
			if (!isWebElementAvailableInPageElement(pageElement))
				getWebElement(pageElement).sendKeys(key);
			else
				pageElement.getWebElement().sendKeys(key);
		} catch (Exception exception) {
			throw new ScriptExecutionException("Failed to press : '" + key + "' in " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}

	protected void clearAndSendKeys (PageElement pageElement, String value) {
		try {
			WebElement element;
			if (!isWebElementAvailableInPageElement(pageElement))
				element = getWebElement(pageElement);
			else
				element = pageElement.getWebElement();
			highlightTheElement(element);
			element.clear();
			element.sendKeys(value);
		} catch (Exception exception) {
			throw new ScriptExecutionException ("Failed to clear and type value: '" + value + "' in " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}

	protected void SelectValueAndTypeForTextField (PageElement pageElement, String value) {
		try {
			WebElement element;
			if (!isWebElementAvailableInPageElement(pageElement))
				element = getWebElement(pageElement);
			else
				element = pageElement.getWebElement();
			highlightTheElement(element);
			element.click();
			Actions actionBuilder = new Actions(driver);
			actionBuilder.doubleClick(element).build().perform();
			//String comboKeys = ;
			element.sendKeys(Keys.SHIFT ,Keys.END);
			element.clear();
			element.sendKeys(value);
		} catch (Exception exception) {
			throw new ScriptExecutionException ("Failed to clear and type value: '" + value + "' in " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}
	protected void SelectValueAndTypeForHomeTextField (PageElement pageElement, String value) {
		try {
			WebElement element;
			if (!isWebElementAvailableInPageElement(pageElement))
				element = getWebElement(pageElement);
			else
				element = pageElement.getWebElement();
			highlightTheElement(element);
			element.click();
			//String comboKeys = ;
			element.sendKeys(Keys.SHIFT ,Keys.HOME);
			element.sendKeys(value);
		} catch (Exception exception) {
			throw new ScriptExecutionException ("Failed to clear and type value: '" + value + "' in " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}

	protected void click(PageElement pageElement) {
		try {
			WebElement element;
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if (!isWebElementAvailableInPageElement(pageElement))
				element = getWebElement(pageElement);
			else
				element = pageElement.getWebElement();
			highlightTheElement(element);
			element.click();
		} catch (Exception exception) {
			throw new ScriptExecutionException ("Failed to click on : '" + pageElement.getName() + "' on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}

	protected void check(PageElement pageElement) {
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			if (!webElement.isSelected())
				highlightTheElement(webElement);
			webElement.click();
		} catch (Exception exception) {
			//throw new ScriptExecutionException ("Failed to check: '" + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}

	protected void unCheck(PageElement pageElement) {
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			if (webElement.isSelected())
				highlightTheElement(webElement);
			webElement.click();

		} catch (Exception exception) {
			//throw new ScriptExecutionException ("Failed to uncheck: '" + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}

	protected String acceptAlertAndReturnConfirmationMessage() {
		try {
			Thread.sleep(3000);
			String confirmationMessage = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();

			return confirmationMessage;

		} catch (Exception exception) {
			//throw new ScriptExecutionException ("failed to accept alert on " + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		}
		return pageName;
	}

	protected String dismissAlertAndReturnConfirmationMessage() {
		try {
			String confirmationMessage = driver.switchTo().alert().getText();
			driver.switchTo().alert().dismiss();
			return confirmationMessage;
		} catch (Exception exception) {
			//throw new ScriptExecutionException ("failed to dismiss alert on " + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		}
		return pageName;
	}

	protected void doubleClick(PageElement pageElement) {
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();
			//Ashutosh 18-06-2018 Purpose(Highlight was placed on wrong place)
			highlightTheElement(webElement);
			Actions actionBuilder = new Actions(driver);
			actionBuilder.doubleClick(webElement).build().perform();
		} catch (Exception exception) {
			//throw new ScriptExecutionException("Failed to doubleclick: '" + "' on " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}

	protected String getText(PageElement pageElement) {
		String text = new String();
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			text = webElement.getText().trim();
			highlightTheElement(webElement);
		} catch (Exception exception) {
			//throw new ScriptExecutionException("Failed to fetch text: '" + "' of " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
		return text;
	}

	protected String getAttribute(PageElement pageElement, String attributeName) {
		String atributeValue = new String();
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();
			highlightTheElement(webElement);
			atributeValue = webElement.getAttribute(attributeName).trim();
		} catch (Exception exception) {
			//throw new ScriptExecutionException("Failed to fetch '" + attributeName + "' of " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
		return atributeValue;
	}

	protected boolean isElementDisplayed(PageElement pageElement) {
		boolean isElementDisplayed = false;
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();
			highlightTheElement(webElement);
			isElementDisplayed = webElement.isDisplayed();

		} catch (Exception e) {
			//throw new ScriptExecutionException ("Failed to display: '" + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", e);
		} finally {
			pageElement = null;
		}
		return isElementDisplayed;
	}

	protected boolean isElementSelected(PageElement pageElement) {
		boolean isElementDisplayed = false;
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			isElementDisplayed = webElement.isSelected();
			highlightTheElement(webElement);
		} catch (Exception e) {
			//frameworkServices.logMessage(pageElement.getName() + " is not Displayed ");
			//frameworkServices.logMessage(pageElement.getName() + " is not Displayed ", logger);
		} finally {
			pageElement = null;
		}
		return isElementDisplayed;
	}

	protected boolean isElementEnabled(PageElement pageElement) {
		boolean isElementEnabled = false;
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			isElementEnabled = webElement.isEnabled();
			highlightTheElement(webElement);
		} catch (Exception e) {
		} finally {
			pageElement = null;
		}
		return isElementEnabled;
	}

	protected void selectValueFromList(PageElement pageElement, String value) {
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();
			Select select = new Select(webElement);
			select.selectByVisibleText(value);
			highlightTheElement(webElement);
			
		} catch (Exception exception) {
			//throw new ScriptExecutionException("Failed to Select value: '" + value + "' of " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}

	protected void mouseOver(PageElement pageElement) {
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			Actions actionBuilder = new Actions(driver);
			actionBuilder.moveToElement(webElement).build().perform();
			highlightTheElement(webElement);
		} catch (Exception exception) {
			//throw new ScriptExecutionException("Failed to hover mouse: '" + "' on " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}

	protected String getSelectedValueFromList(PageElement pageElement) {
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			Select selectType = new Select(webElement);
			String selectedValue = selectType.getFirstSelectedOption().getText();
			highlightTheElement(webElement);	
			return selectedValue;
		} catch (Exception exception) {
/*			throw new ScriptExecutionException("Failed to fetch "
					+ "Selected Value" + "' of " + pageElement.getName()
					+ " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
*/		} finally {
			pageElement = null;
		}
		return pageName;
	}

	protected ArrayList<String> getAllOptionsInList(PageElement pageElement) {
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			Select selectList = new Select(webElement);
			ArrayList<String> optionList = new ArrayList<String>();

			for (WebElement option : selectList.getOptions()) {
				optionList.add(option.getText().trim());
			}


			return optionList;
		} catch (Exception exception) {
			//throw new ScriptExecutionException("Failed to fetch " + "option Value" + "' of " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
		return null;
	}

	protected void bringElementInView (PageElement pageElement) {
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();
			highlightTheElement(webElement);
		
		} catch (Exception exception) {
			//throw new ScriptExecutionException("Failed to bring Element  " + pageElement.getName() + "  " + " in the current browser view " + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}
	
	protected void switchToWindowContinue(String windowTitle) throws InterruptedException {
		try{
			Thread.sleep(1000);
			Set<String> windows = driver.getWindowHandles();

			for (String window : windows) {
				driver.switchTo().window(window);
				driver.manage().window().maximize();
				if (true)
				{
					driver.findElement(By.name("Continue")).click();
					System.out.println("session found");
				}	
			}
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println ("failed to close the Session window"); 
		}
	}



	protected void switchToWindow(String windowTitle) throws InterruptedException {
		try{
			Thread.sleep(1000);
			Set<String> windows = driver.getWindowHandles();

			for (String window : windows) {
				driver.switchTo().window(window);
				driver.manage().window().maximize();
				if (driver.getTitle().contains(windowTitle))
					break;	
			}
		}

		catch(Exception e){
			//	Thread.sleep(5000);
			//	switchToWindowRetry(windowTitle);
		}
	}

	protected void switchToWindowRetry(String windowTitle) throws InterruptedException {
		try{
			Thread.sleep(1000);
			Set<String> windows = driver.getWindowHandles();

			for (String window : windows) {
				driver.switchTo().window(window);
				driver.manage().window().maximize();
				if (driver.getTitle().contains(windowTitle))
					break;	
			}
		}

		catch(Exception e){
			//throw new ScriptExecutionException ("Failed to search Criteria: ", e);
		}
	}

	protected void switchToWindow(String windowTitle, String errorTitleWindow) throws InterruptedException 
	{
		try{
			Thread.sleep(1000);
			ArrayList<String> alltitles = new ArrayList<String>();
			Set<String> windows = driver.getWindowHandles();


			for (String window : windows) {
				driver.switchTo().window(window);
				driver.manage().window().maximize();
				alltitles.add(driver.getTitle());
			}
			if (alltitles.contains(errorTitleWindow)){
				//throw new ScriptExecutionException ("Error Window Encountered While Execution.");
			}
			else if(alltitles.contains(windowTitle)){
				switchToWindow(windowTitle);

			}else {
			//	throw new ScriptExecutionException ("Expected page: "+windowTitle+ " NOT FOUND ");
			}
		}
		catch(Exception e){
			//throw new ScriptExecutionException ("Failed to search Criteria: ", e);
		}
	}

	protected void closeWindow(String windowTitle) throws InterruptedException {
		try {
			Thread.sleep(1000);
			driver.close();
		}
		catch (Exception exception) {
			//throw new ScriptExecutionException("Failed to Close the Window with title \"" + windowTitle + " \"", exception);
		}
	}

	protected String locatorforQuestionAnswerPanel(String title, String type) { 
		String xpath=null;
		if(type.equalsIgnoreCase("input")) {
			xpath="//td//div[@id='"+title+"']/following::"+type+"[1]";
		}
		else if(type.equalsIgnoreCase("select")) {
			xpath="//td//div[@id='"+title+"']/following::"+type+"[1]";
		}
		else if(type.equalsIgnoreCase("textarea")) {
			xpath="//td//div[@id='"+title+"']/following::"+type+"[1]";
		}
		return xpath;
	}

	//td//b[contains(text(),'Pan Number')]/following::input[1]
	protected String locatorforPanel(String title, String type) {
		String xpath=null;
		if(type.equalsIgnoreCase("input")) {
			xpath="//td//b[contains(text(),'"+title+"')]/following::"+type;
		}
		else if(type.equalsIgnoreCase("select")) {
			xpath="//td//b[contains(text(),'"+title+"')]/following::"+type;
		}
		else if(type.equalsIgnoreCase("textarea")) {
			xpath="//td//b[contains(text(),'"+title+"')]/following::"+type;
		}
		return xpath;
	}

	protected String genericLocatorforLabel(String formName, String title) {
		//form[@name='S0230']//td//b[contains(text(),'Policy Status')]/following::td
		String xpath=null;
		xpath="//form[@name='"+formName+"']//td//b[contains(text(),'"+title+"')]/following::td";
		return xpath;
	}
	protected void checkOrUncheck(PageElement pageElement,Boolean config) {
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			if (!webElement.isSelected()&&config.equals(true)){
				webElement.click();
			}
			else if(webElement.isSelected()&&config.equals(false)){
				webElement.click();
			}
			highlightTheElement(webElement);
					} catch (Exception exception) {
			//throw new ScriptExecutionException ("Failed to check: '" + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}
	protected String genericLocatorforPrevPageDetails(String title)
	{
		String xpath=null;
		xpath="//td[contains(text(),'"+title+"')]/following::td";
		return xpath;
	}
	protected String fetchValueFromFields(PageElement pageElement) {
		String text = new String();
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			text = webElement.getText().trim();
			highlightTheElement(webElement);
			
		} catch (Exception exception) {
			//throw new ScriptExecutionException("Failed to fetch text: '" + "' of " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
		return text;
	}
	protected String fetchValueFromTextFields(PageElement pageElement) {
		String text = new String();
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			text = webElement.getAttribute("value").trim();
			highlightTheElement(webElement);
			
		} catch (Exception exception) {
			//throw new ScriptExecutionException("Failed to fetch text: '" + "' of " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
		return text;
	}
	protected String fetchValueFromList(PageElement pageElement){
		String text = new String();
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();
			Select select=new Select(webElement);
			text=	select.getFirstSelectedOption().getText();
			highlightTheElement(webElement);
			
		} catch (Exception exception) {
			//throw new ScriptExecutionException("Failed to fetch text: '" + "' of " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
		return text;
	}

	protected void clearAndEnterDate(PageElement pageElement,String date)
	{
		try {
			String[] splitddate=date.split("/");
			WebElement element;
			if (!isWebElementAvailableInPageElement(pageElement))
				element = getWebElement(pageElement);
			else
				element = pageElement.getWebElement();
			element.clear();
			acceptAlertAndReturnConfirmationMessages();
			for(int i=0;i<splitddate.length;i++){
				Thread.sleep(500);
				element.sendKeys(splitddate[i]);
				Thread.sleep(500);
			}
			highlightTheElement(element);
		} catch (Exception exception) {
			//throw new ScriptExecutionException ("Failed to clear and type value: '" + date + "' in " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}

	}
	protected void switchToFrame(String frameName){
		try {
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			String xpathForFrame="//frameset//frame[@name='"+frameName+"']";
			by=By.xpath(xpathForFrame);
			WebElement frame=webDriverWrapper.waitForElementToBePresent(by);
			//WebElement frame=driver.findElement(By.xpath(xpathForFrame));
			driver.switchTo().frame(frame);
			
		} catch (Exception exception) {
			//throw new ScriptExecutionException ("Failed to switch to Frame: '" + frameName + "' in on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			frameName=null;
		}
	}
	protected void selectDateFromCalender(PageElement pageElement,String dateToBeAdded) throws InterruptedException{
		String date=null;
		RandomCodeGenerator randomCodeGenerator=new RandomCodeGenerator();
		date=randomCodeGenerator.calenderDateGenerator(dateToBeAdded);
		click(pageElement);
		String[] splitddate=date.split("/");
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		switchToWindow("Ebay");
		driver.switchTo().defaultContent();
		driver.manage().window().maximize();
		Thread.sleep(1000);
		WebElement year=driver.findElement(By.name("year"));
		year.clear();
		acceptAlertAndReturnConfirmationMessage();
		String yearVal = splitddate[2].trim();
		year.sendKeys(yearVal);
		Thread.sleep(200);
		WebElement month=driver.findElement(By.name("month"));
		month.click();
		Thread.sleep(200);
		Select select = new Select(month);
		select.selectByVisibleText(splitddate[1].trim());
		Thread.sleep(200);
		WebElement dayOfMonth=driver.findElement(By.xpath("//input[@value='"+splitddate[0].trim()+"']"));
		dayOfMonth.click();
		Thread.sleep(200);
		switchToWindow();
	}
	protected void enterMessageInAlert(String message) {
		try {
			Alert alert=driver.switchTo().alert();
			String alertMessage=driver.switchTo().alert().getText();
			alert.sendKeys(message);
			alert.accept();
			

		} catch (Exception exception) {
			//throw new ScriptExecutionException ("failed to accept alert on " + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		}
	}
	protected void switchToFrame(String frameName,WebDriver driver){
		try {
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			Thread.sleep(500);
			String xpathForFrame="//frameset//frame[@name='"+frameName+"']";
			WebElement frame=driver.findElement(By.xpath(xpathForFrame));
			driver.switchTo().frame(frame);
			
		} catch (Exception exception) {
			//throw new ScriptExecutionException ("Failed to switch to Frame: '" + frameName + "' in on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			frameName=null;
		}
	}
	protected void switchForSearchCriteria(PageElement pageElement,String searchFieldLocator,String searchCriteria,String frameName) throws InterruptedException{
		try {
			click(pageElement);
			Thread.sleep(500);
			switchToWindow();
			PageElement searchCriteriaTextField=new PageElement(By.name(searchFieldLocator), "search FieldLocator Textfield", false, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
			clearAndSendKeys(searchCriteriaTextField, searchCriteria);
			Thread.sleep(500);
			PageElement findButton=new PageElement(By.xpath("//input[@name='"+searchFieldLocator+"']/following::a['firstFocus']"), "Party Code Textfield", false, WaitType.WAITFORELEMENTTOBECLICKABLE, 10);
			click(findButton);
			Thread.sleep(500);
			PageElement searchCriteriaLink=new PageElement(By.linkText(searchCriteria), ""+searchCriteria+"Link", false, WaitType.WAITFORELEMENTTOBECLICKABLE, 10);
			click(searchCriteriaLink);
			switchToWindow();
			switchToFrame(frameName);
		} catch (Exception e) {
			//throw new ScriptExecutionException ("Failed to search Criteria: ", e);
		}

	}
	protected void switchToWindow() throws InterruptedException {
		String title = null;

		try{
			Thread.sleep(1000);
			Set<String> parentWindow = driver.getWindowHandles();


			for(String winHandle : driver.getWindowHandles()) {
				if (!parentWindow.equals(winHandle)) {
					title=	driver.switchTo().window(winHandle).getTitle();
					driver.manage().window().maximize();
					Dimension dimension2=driver.manage().window().getSize();
					int heightAfterSwitch=dimension2.getHeight();
					int widthAfterSwitch=dimension2.getWidth();
					if(heightAfterSwitch==1000 && widthAfterSwitch==1296 ){
						break;
					}
					Thread.sleep(1000);
				}
			}
		}
		catch(Exception e){
			//throw new ScriptExecutionException ("Failed to Switch window: ", e);
		}
	}
	protected void switchToDefaultFrame() throws InterruptedException {
		String title = null;
		try{
			driver.switchTo().defaultContent();
		}
		catch(Exception e){
			//throw new ScriptExecutionException ("Failed to search Criteria: ", e);
		}
	}


	protected void clearAndEnterTime(PageElement pageElement){
		try {
			RandomCodeGenerator randomCodeGenerator=new RandomCodeGenerator();
			String time=randomCodeGenerator.timeGenerator();
			String[] splitTime=time.split(":");
			WebElement element;
			if (!isWebElementAvailableInPageElement(pageElement))
				element = getWebElement(pageElement);
			else
				element = pageElement.getWebElement();
			element.clear();
			for(int i=0;i<splitTime.length;i++){
				Thread.sleep(300);
				element.sendKeys(splitTime[i]);
				Thread.sleep(300);
			}
			highlightTheElement(element);
			
		} catch (Exception exception) {
			//throw new ScriptExecutionException ("Failed to clear and type value: '' in " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}
	protected void click(PageElement pageElement,WebDriver driver){

		try {
			WebElement element;
			if (!isWebElementAvailableInPageElement(pageElement))
				element = getWebElement(pageElement);
			else
				element = pageElement.getWebElement();
			element.click();
			highlightTheElement(element);
			
		} catch (Exception exception) {
			//throw new ScriptExecutionException ("Failed to click on : '" + pageElement.getName() + "' on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}

	}
	public boolean isAlertPresent(){
		try {
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e) {
			return false;
		}

	}
	protected void bringWebElementInView (PageElement pageElement) {
		try {
			WebElement webElement = getWebElement(pageElement);
			if (!isWebElementAvailableInPageElement(pageElement))
				
			highlightTheElement(webElement);
		} catch (Exception exception) {
			//throw new ScriptExecutionException("Failed to bring Element  " + pageElement.getName() + "  " + " in the current browser view " + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}
	public void switchToJSPPage() throws InterruptedException{
		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			driver.manage().window().maximize();
			System.out.println(winHandle);
			Thread.sleep(1000);
			switchToDefaultFrame();
		}
	}
	public boolean isConfigTrue(String config){
		if(config.equalsIgnoreCase("yes")){
			return true;
		}
		else{
			return false;
		}
	}
	protected void clearTextField (PageElement pageElement) {
		try {
			WebElement element;
			if (!isWebElementAvailableInPageElement(pageElement))
				element = getWebElement(pageElement);
			else
				element = pageElement.getWebElement();

			element.clear();
			highlightTheElement(element);

		} catch (Exception exception) {
			//throw new ScriptExecutionException ("Failed to Cleared  in " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}
	protected void clearAndEnterForwardTime(PageElement pageElement,String timeOfIntimation) throws Throwable{
		try {
			String time=RandomCodeGenerator.timeGeneratorForClaimAdmission(timeOfIntimation);
			String[] splitTime=time.split(":");
			WebElement element;
			if (!isWebElementAvailableInPageElement(pageElement))
				element = getWebElement(pageElement);
			else
				element = pageElement.getWebElement();
			element.clear();
			for(int i=0;i<splitTime.length;i++){
				Thread.sleep(300);
				element.sendKeys(splitTime[i]);
				Thread.sleep(300);
			}
		} catch (Exception exception) {
			//throw new ScriptExecutionException ("Failed to clear and type value: '' in " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}
	protected boolean isWebElementDisplayed(PageElement pageElement){
		try{
			WebElement element;
			element=driver.findElement(pageElement.getBy());
			if(element.isDisplayed()){
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception e){
			return false;
		}
	}
	protected void switchToMainPage(){
		String windowTitle="Integrated Insurance Management System";
		try{

			Set<String> windows = driver.getWindowHandles();

			for (String window : windows) {

				while(!driver.getTitle().contains(windowTitle)){
					closeWindow();
					switchToWindow();
				}
				switchToWindow(windowTitle);
			}
		}
		catch(Exception e){
			//throw new ScriptExecutionException ("Failed to Switch"+windowTitle+" window ", e);
		}

	}
	protected void closeWindow() throws InterruptedException {
		try {
			driver.wait(500);
			driver.close();
		}
		catch (Exception exception) {
			//throw new ScriptExecutionException("Failed to Close the Window  ", exception);
		}
	}
	protected void switchAndCloseWindow(){
		try {
			String parentWindow= driver.getWindowHandle();
			String winHandleBefore = driver.getWindowHandle();

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			driver.close();
			//driver.switchTo().window("Integrated Insurance Management System");
			driver.switchTo().window(parentWindow);
		}catch(Exception e){

		}
	}

	//added by PKP
	protected boolean waitForElement(PageElement pageElement,int timeInMilliSeconds){
		try{
			WebDriverWait wait = new WebDriverWait(driver,timeInMilliSeconds);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(pageElement.getBy())));
			return true;
		}catch(ElementNotVisibleException e){
			e.printStackTrace();
			return false;
		}


	}

	//Abhijit
	protected void clickTabButton(PageElement pageElement) throws InterruptedException {
		try {
			WebElement webElement = driver.findElement(pageElement.getBy());
			webElement.sendKeys(Keys.TAB);
			highlightTheElement(webElement);
		}
		catch (Exception exception) {
			//throw new ScriptExecutionException("Failed To Click Tab", exception);
		}
	}

	//Amit AND Nilesh
	protected void handleAlert() throws InterruptedException {
		try{
			Thread.sleep(1000);
			Robot robot=new Robot();
			robot.delay(200);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(200);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			driver.switchTo().alert().accept();
		}catch(Exception e){}
	}

	public void acceptForCheckBoxPopUP() {
		try{

			if(isAlertPresent()){
				acceptAlertAndReturnConfirmationMessage();
			}

			if(isAlertPresent()){
				acceptAlertAndReturnConfirmationMessage();
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	protected void clearAndSendKey (PageElement pageElement, String value) {
		try {
			WebElement element;
			if (!isWebElementAvailableInPageElement(pageElement))
				element = getWebElement(pageElement);
			else
				element = pageElement.getWebElement();
			highlightTheElement(element);
			element.clear();
			acceptForCheckBoxPopUP();
			element.sendKeys(value);

		} catch (Exception exception) {
			throw new ScriptExecutionException ("Failed to clear and type value: '" + value + "' in " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
	}

	protected void switchToFrameForRS(String frameName){
		try {
			acceptAlertAndReturnConfirmationMessage();
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			String xpathForFrame="//frameset//frame[@name='"+frameName+"']";
			WebElement frame=driver.findElement(By.xpath(xpathForFrame));
			driver.switchTo().frame(frame);

			//frameworkServices.logMessage("Cleared and Typed Value: " + value + "' in " + pageElement.getName(), logger);
		} catch (Exception exception) {
			//throw new ScriptExecutionException ("Failed to switch to Frame: '" + frameName + "' in on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			frameName=null;
		}
	}

	//Abhijit
	protected boolean isPageElementExists(String path) {
		boolean flag=driver.findElements(By.xpath(path)).isEmpty();
		return flag;
	}

	public void highlightTheElement(WebElement element) {
		try{
			

		}catch(Exception e){
		}

	}

	protected void acceptAlertAndReturnConfirmationMessages() throws InterruptedException {
		try {
			Thread.sleep(3000);
			String confirmationMessage = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();

		} catch (NoAlertPresentException exception) {

		}
	}
	public String convertDate(String date) throws ParseException, java.text.ParseException
	{
		Date date1=new SimpleDateFormat("dd/M/yyyy").parse(date);
		String convertedDate = new SimpleDateFormat("d/M/yyyy").format(date1);
		return convertedDate;
	}
	protected String genericLocatorforLabelparty(String formName, String title) {
		String xpath=null;
		xpath="//form[@name='"+formName+"']//td//b[contains(text(),'"+title+"')]/following::input";
		return xpath;
	}
	protected String fetchValueFromFieldsforClaimParty(PageElement pageElement) {
		String text = new String();
		try {
			WebElement webElement;
			if (!isWebElementAvailableInPageElement(pageElement))
				webElement = getWebElement(pageElement);
			else
				webElement = pageElement.getWebElement();

			text = webElement.getText().trim();
			highlightTheElement(webElement);
			
			text=webElement.getAttribute("Value").trim();
					
		} catch (Exception exception) {
			throw new ScriptExecutionException("Failed to fetch text: '" + "' of " + pageElement.getName() + " on : '" + pageName + "' Refer to Sheet:  '"+CustomizableEntity.runningSheetName+"' "+ "' Column Name:  '"+CustomizableEntity.runningElement+"' "+ "' Row Reference :  '"+CustomizableEntity.runningRowReference+"' ", exception);
		} finally {
			pageElement = null;
		}
		return text;
	}

}



