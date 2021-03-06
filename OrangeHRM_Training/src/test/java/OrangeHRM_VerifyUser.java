import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM_VerifyUser {
ChromeDriver driver;
	
	@BeforeTest
	public void LaunchBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}
	@Test(priority=1)
	// This is for Sign On Functionality
	public void Sign_On() throws InterruptedException

	{
		driver.findElement(By.name("txtUsername")).sendKeys("Admin");
		driver.findElement(By.name("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		String Element = driver.findElement(By.linkText("Dashboard")).getText();
		System.out.println(Element);
		Assert.assertEquals("Dashboard", Element);
		
	}
	
	@Test(priority=2)
	public void AddUsers_Page()
	{
		WebElement admin = driver.findElement(By.linkText("Admin"));
		Actions action = new Actions(driver);
		action.moveToElement(admin).build().perform();
		WebElement usermanagement = driver.findElement(By.linkText("User Management"));
		action.moveToElement(usermanagement).build().perform();
		driver.findElement(By.linkText("Users")).click();
		//User Click on Add Button to add users
		driver.findElement(By.id("searchBtn")).click();
		driver.findElement(By.id("btnAdd")).isDisplayed();
		
	}
	@Test(priority=3,enabled=true)
	public void VerifyAddedUser() throws InterruptedException
	{
		driver.findElement(By.id("btnAdd")).click();
		
		//Enter all the mandatory Fields
		Select SelectPass = new Select(driver.findElement(By.id("systemUser_userType")));
		//SelectPass.selectByValue("1");
		SelectPass.selectByVisibleText("Admin");
		
		//SelectPass.selectByIndex(0);
		driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("Fiona Grace");
		
		Random randomGenerator = new Random();
		
		int randomInt = randomGenerator.nextInt(1000); 
		driver.findElement(By.id("systemUser_userName")).sendKeys("Dixit"+randomInt);
		driver.findElement(By.id("systemUser_password")).sendKeys("admin123");
		driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("admin123");
		Thread.sleep(3000);
		driver.findElement(By.id("btnSave")).click();
		Thread.sleep(3000);
		
		String ExpUserName="Dixit"+randomInt;
		System.out.println(ExpUserName);
	    WebElement cellIneed = driver.findElement(By.xpath("//a[text()='"+ExpUserName+"']"));
		//WebElement cellIneed = driver.findElementByXPath("//a[contains(text(),'abhidixit"+randomInt+"')]");
		//WebElement cellIneed = driver.findElementByXPath("//a[text()='Dixit"+randomInt+"')]");
		//a[contains(text(),'abhidixit699')]
		//---//a[text()='"+ExpUserName+"']
	    String valueIneed = cellIneed.getText();
	    System.out.println("Cell value is : " + valueIneed); 
	    Assert.assertEquals(ExpUserName, valueIneed);
	}
	@AfterTest
	public void CloseBrowser()
	{
		driver.quit();
	}

}
