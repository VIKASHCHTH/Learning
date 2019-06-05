package code;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Basepage extends WebPage {
	private PageElement RedHomePage;
	
	
	
	
	public Basepage(WebDriver driver, String pageName) {
		super(driver, pageName);
		   
		RedHomePage=new PageElement(By.xpath("//span[contains(text(),'Home')]"), "Ebay home page", false, WaitType.WAITFORELEMENTTOBEDISPLAYED, 10);
	
		
	}
	public Basepage(){
		super();
	}
	public void navigateToMyPreferencePage()
	{
		click(RedHomePage);
	}

	public void click(PageElement ebayHomePage) {}
	public void highlightTheElement(WebElement element) {
		
	}
	/*private WebElement getWebElement(Object pageElement) {
		// TODO Auto-generated method stub
		return null;
	}
	private boolean isWebElementAvailableInPageElement(Object pageElement) {
		// TODO Auto-generated method stub
		return false;
	}
*/
	/*public static void main(String[] args) {

	}*/

}
