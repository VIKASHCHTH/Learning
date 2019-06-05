package learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import code.PageElement;
import code.WaitType;
import code.WebPage;

public class HomePage extends WebPage {
	private PageElement Hotels;
	private PageElement label;
	private PageElement Location;
	

	public HomePage(WebDriver driver, String pageName) {
		super(driver, pageName);	
	Hotels=new PageElement(By.xpath("//a[contains(text(),'HOTELS ')]"), "HOTELS", false, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
	label=new PageElement(By.xpath("//label[contains(text(),'Bangalore')]"), "SEArch", false, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
	Location=new PageElement(By.xpath("//input[@id='loc_search']"), "Location", false, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
	
	}
	public  void home() throws InterruptedException{
		click(Hotels);
	    click(label);
	    click(Location);
	    }
	}	
	
