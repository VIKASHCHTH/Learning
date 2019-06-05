package TestRunner;
import org.testng.annotations.Test;

import excel.Database;
import learning.HomePage;
import learning.ReportsPage;
import learning.override;
import logs.LogPage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class Testrunner {
	public  WebDriver driver;
	public static Properties p;
	public static String pageName;
	public File dest;
	public HomePage objHomePage;
	public Database objDatabase;
	public Database objlog;

  @Test
	@BeforeMethod
  public void test() throws InterruptedException {
			try {
		FileReader reader = new FileReader(System.getProperty("user.dir") + "\\configsss\\config.properties");
				p = new Properties();
				p.load(reader);
			} catch (IOException e) {
				e.printStackTrace();		
  }
			
				  String browsername = p.getProperty("browser");
					if (browsername.equalsIgnoreCase("FIREFOX")) {
						System.setProperty("webdriver.gecko.driver", "D:\\cucumber\\learning\\driver\\geckodriver.exe");
						driver = new FirefoxDriver();
					}
					if (browsername.equalsIgnoreCase("chrome")) {						
						ChromeOptions options = new ChromeOptions();
						options.addArguments("--disable-notifications");
						System.setProperty("webdriver.chrome.driver", "D:\\cucumber\\learning\\driver\\chromedriver.exe");
						 driver =new ChromeDriver(options);

					}									
					String applicationURL = p.getProperty("AplicationUrl");
					driver.get(applicationURL);
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					HomePage objHomePage = new HomePage(driver,pageName);
					objHomePage.home();	
					ReportsPage objReportsPage = new ReportsPage();
					objReportsPage.testMethodPass();
					LogPage objlog = new LogPage();
					objlog.log();
					}

 @override
  public void afterTest(){
	 
	  driver.quit();
	  SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
	  Date date = new Date();
	  String returnDate = formatter.format(date);
	  dest = new File("D:\\Execution_Result_pract\\ExecutionResultsSummarypractice" + returnDate);
	  dest.mkdirs();
	  Date ExecutionWatch= new Date();
	  System.out.println( " Execution Endeded at time  :-"+ ExecutionWatch);	
	  System.out.println("ExecutionResultSummary Report generated at path " +dest .toString());
  }
}

