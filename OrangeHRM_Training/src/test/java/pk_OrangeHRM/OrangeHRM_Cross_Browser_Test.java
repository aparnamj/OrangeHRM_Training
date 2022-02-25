package pk_OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import OrangeHRM_Training.OrangeHRM_Training.Orange_dataset;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM_Cross_Browser_Test extends Orange_dataset{
	WebDriver driver;

	@BeforeTest
	@Parameters("browser")
	public void LaunchBrowser(String browser) throws Exception {

		if(browser.equalsIgnoreCase("firefox")){
		    WebDriverManager.firefoxdriver().setup();
		    driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
		    WebDriverManager.chromedriver().setup();
		    
		    driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")){
		    WebDriverManager.edgedriver().setup();
		    driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("ie")){
		    WebDriverManager.iedriver().setup();
		    driver = new InternetExplorerDriver();
		}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
	
	}

	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}
	
	@Test(dataProvider="Login")
	public void Login_Validation(String uname, String upass) throws InterruptedException {
	//@Test	
	//public void Login_Validation() throws InterruptedException {
		// Enter the URL
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		// Enter the valid Username and valid Password
		driver.findElement(By.name("txtUsername")).sendKeys(uname);
		driver.findElement(By.name("txtPassword")).sendKeys(upass);
		// click on the Login Button
		driver.findElement(By.id("btnLogin")).click();
		// verify Dashboard page is displayed
		driver.findElement(By.linkText("Dashboard")).isDisplayed();
		System.out.print("Test Success");
		
		// ----------------To Verify Logout Function without using
		driver.findElement(By.id("welcome")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("logInPanelHeading")).isDisplayed();
	}
}
