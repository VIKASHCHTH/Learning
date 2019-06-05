package code;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;		
		public class WebDriverWrapper {
			WebDriver driver;
			static int counter=0;
			public WebDriverWrapper(WebDriver driver) {
				this.driver = driver;
			}

			public static String getUniqueValue() {
				char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
				Random r = new Random(System.currentTimeMillis());
				char[] id = new char[9];
				for (int i = 0; i < 9; i++) {
					id[i] = chars[r.nextInt(chars.length)];
				}
				return new String(id);
			}

			public String captureScreenShot(File destinationFilePathLocation) throws HeadlessException, AWTException, IOException{
				String fileName = new String();
				WebDriver augmentedDriver = driver;
				if (!(driver instanceof InternetExplorerDriver))
					augmentedDriver = new Augmenter().augment(driver);

				File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

				DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaadd_MMM_yyyy");
				Date date = new Date();
				fileName = driver.getTitle().replace(":", "").replace("-", "") + dateFormat.format(date) + ".png";
				File destinationFilePath = new File((destinationFilePathLocation + File.separator + fileName));
				FileUtils.copyFile(screenshot, destinationFilePath);
				//ImageIO.write(image, "png", destinationFilePath);

				Reporter.log("<br> <a target = \"_blank\" href=\"" + destinationFilePath +"\">"+
						"<img src=\""
						+ destinationFilePath 
						+ "\" alt=\"ScreenShot Not Available\" height=\"400\" width=\"400\"> </a>");
				return fileName;
			}

			public String captureAssertionScreenShot(File destinationFilePathLocation, String error) throws HeadlessException, AWTException, IOException{
				String fileName = new String();
				//BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				WebDriver augmentedDriver = driver;
				if (!(driver instanceof InternetExplorerDriver))
					augmentedDriver = new Augmenter().augment(driver);

				File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
				Reporter.log("<B>"+"TestCase Failed Due to:::"+error+"</B>");
				DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaadd_MMM_yyyy");
				Date date = new Date();
				fileName = driver.getTitle().replace(":", "").replace("-", "") + dateFormat.format(date) + ".png";
				File destinationFilePath = new File((destinationFilePathLocation + File.separator + fileName));
				FileUtils.copyFile(screenshot, destinationFilePath);
				//ImageIO.write(image, "png", destinationFilePath);

				Reporter.log("<br> <a target = \"_blank\" href=\"" + destinationFilePath +"\">"+
						"<img src=\""
						+ destinationFilePath 
						+ "\" alt=\"ScreenShot Not Available\" height=\"400\" width=\"400\"> </a>");
				return fileName;
			}

			public String getUniqueTimeStamp() {
				DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaadd_MMM_yyyy");
				Date date = new Date();
				return (dateFormat.format(date));
			}

			@Deprecated
			public void sendEnterKey(WebElement element, Keys key) {
				if (getCurrentBrowserName().equalsIgnoreCase("internet explorer")) {
					element.sendKeys(key);
				} else {
					element.click();
				}
			}

			private String getCurrentBrowserName() {
				return null;
			}

			@Deprecated
			public void clearAndSendKeysInTextBox(WebElement textbox, String value) {
				try {
					textbox.clear();
					textbox.sendKeys(value);
				} catch (NoSuchElementException e) {
					throw new NoSuchElementException(
							"Could not clear and type the value in textbox");
				}
			}

			@Deprecated
			public void moveToElement(WebElement element) {
				Actions moveToElementAction = new Actions(driver);
				moveToElementAction.moveToElement(element).perform();
			}

			public WebElement waitForElementToBeDisplayed(final WebElement element, int timeOutPeriod) {
				WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
				webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);

				return webDriverWait.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver driver) {
						try {
							if (element.isDisplayed())
								return element;
							else
								return null;
						} 
						catch (NoSuchElementException ex) {
							return null;
						} 
						catch (StaleElementReferenceException ex) {
							return null;
						} 
						catch (NullPointerException ex) {
							return null;
						}
					}
				});
			}

			public WebElement waitForElementToBeDisplayed(final By by, int timeOutPeriod) {
				WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
				webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
				return webDriverWait.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver driver) {
						try {
							WebElement element = driver.findElement(by);
							if (element.isDisplayed())
								return element;
							else
								return null;
						} 
						catch (NoSuchElementException ex) {
							return null;
						} 
						catch (StaleElementReferenceException ex) {
							return null;
						} 
						catch (NullPointerException ex) {
							return null;
						}
					}
				});
			}

			public WebElement waitForElementToBeClickable(final By by, int timeOutPeriod) {
				WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
				webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
				return webDriverWait.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver driver) {
						try {
							WebElement element = driver.findElement(by);
							if (element.isEnabled() && element.isDisplayed())
								return element;
							else
								return null;
						} 
						catch (NoSuchElementException ex) {
							return null;
						} 
						catch (StaleElementReferenceException ex) {
							return null;
						} 
						catch (NullPointerException ex) {
							return null;
						}
					}
				});
			}

			public WebElement waitForElementToBePresent(final By by) {
				WebDriverWait webDriverWait = new WebDriverWait(driver, 100);
				webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
				return webDriverWait.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver driver) {
						try {
							WebElement element = driver.findElement(by);
							if (element.isEnabled() && element.isDisplayed())
								return element;
							else
								return null;
						} 
						catch (NoSuchElementException ex) {
							return null;
						} 
						catch (StaleElementReferenceException ex) {
							return null;
						} 
						catch (NullPointerException ex) {
							return null;
						}
					}
				});
			}

			public WebElement waitForElementToBeClickable(final WebElement element, int timeOutPeriod) {
				WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
				webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);

				return webDriverWait.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver driver) {
						try {
							if (element.isEnabled() && element.isDisplayed())
								return element;
							else
								return null;
						} 
						catch (NoSuchElementException ex) {
							return null;
						} 
						catch (StaleElementReferenceException ex) {
							return null;
						} 
						catch (NullPointerException ex) {
							return null;
						}
					}
				});
			}

			public WebElement waitForElementToBeEnabled(final WebElement element, int timeOutPeriod) {
				WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
				webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);

				return webDriverWait.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver driver) {
						try {
							if (element.isEnabled())
								return element;
							else
								return null;
						} 
						catch (NoSuchElementException ex) {
							return null;
						} 
						catch (StaleElementReferenceException ex) {
							return null;
						} 
						catch (NullPointerException ex) {
							return null;
						}
					}
				});
			}

			public WebElement waitForElementToBeEnabled(final By by, int timeOutPeriod) {
				WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
				webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
				return webDriverWait.until(new ExpectedCondition<WebElement>(){
					public WebElement apply(WebDriver driver) {
						try {
							WebElement element = driver.findElement(by);
							if (element.isEnabled())
								return element;
							else
								return null;
						} 
						catch (NoSuchElementException ex) {
							return null;
						} 
						catch (StaleElementReferenceException ex) {
							WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
							WebElement elementNew = driver.findElement(by);
							WebElement	element = webDriverWait.until(ExpectedConditions.refreshed(
							        ExpectedConditions.elementToBeClickable(elementNew)));
				            return element;
						} 
						catch (NullPointerException ex) {
							return null;
						}
					}
				});
			}

			public WebElement waitForOptionToBePopulatedInList(final WebElement dropdownList, int timeOutPeriod) {
				WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
				webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
				return webDriverWait.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver driver) {
						try {
							List<WebElement> options = dropdownList.findElements(By
									.tagName("option"));
							if (options.size() > 1) {
								return dropdownList;
							} 
							else
								return null;

						} 
						catch (NoSuchElementException ex) {
							return null;
						} 
						catch (StaleElementReferenceException ex) {
							return null;
						} 
						catch (NullPointerException ex) {
							return null;
						}
					}
				});
			}

			public void bringElementInView(WebElement element) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}

			public boolean isAlertPresent() 
			{ 
				try 
				{ 
					driver.switchTo().alert(); 
					return true; 
				}   // try 
				catch (NoAlertPresentException Ex) 
				{ 
					return false; 
				}   // catch 
			}   // isAlertPresent
			public void waitForAlert(int timeOutPeriod) {
				WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
				webDriverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class).pollingEvery(10, TimeUnit.MILLISECONDS).until(ExpectedConditions.alertIsPresent());
			}

			public String acceptAlert(int timeOutPeriod) {
				waitForAlert(timeOutPeriod);
				Alert alert = driver.switchTo().alert();
				String AlertMessage = alert.getText();
				alert.accept();
				return AlertMessage;
			}

			public String dismissAlert(int timeOutPeriod) {
				waitForAlert(timeOutPeriod);
				Alert alert = driver.switchTo().alert();
				String AlertMessage = alert.getText();
				alert.dismiss();
				return alert.getText();
			}

			public void acceptAlertForFirefox() {
				if (getCurrentBrowserName().equalsIgnoreCase("firefox")) {
					try {
						waitForAlert(20);
						driver.switchTo().alert().accept();
					} 
					catch (Exception e) {
					}
				}
			}

			@Deprecated
			public void selectVisibleElementFromDropdown(WebElement element, String text) {
				Select selectType = new Select(element);
				selectType.selectByVisibleText(text);
			}

			@Deprecated
			public void selectByIndexFromDropdown(WebElement element, String index) {
				Select selectType = new Select(element);
				selectType.selectByIndex(Integer.parseInt(index));
			}

			public static void explicitWait(int waitTime) {
				try {
					Thread.sleep(waitTime * 1000);
				} 
				catch (InterruptedException e) {
				}
			}

			@Deprecated
			public String getSelectedValueFromList(WebElement webelement) {
				Select selectType = new Select(webelement);
				return selectType.getFirstSelectedOption().getText();
			}

			@Deprecated
			public ArrayList<String> getAllOptionsInList(WebElement webList) {
				Select selectList = new Select(webList);
				ArrayList<String> optionList = new ArrayList<String>();
				for (WebElement option : selectList.getOptions()) {
					optionList.add(option.getText().trim());
				}
				return optionList;
			}
		}
