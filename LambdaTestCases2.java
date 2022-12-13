package lambdatest.sel.certification;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
 
public class LambdaTestCases2
{
   WebDriver driver = null;
   public static String status = "passed";
   String username = "m1035029";
   String access_key = "id2nmuw7jtp0bGIBmBpEtBkcqOzdks8oE0mSJFblDOwwBEFSvX";
 
   String testURL = "https://www.lambdatest.com/selenium-playground";
   String testURLTitle = "React • TodoMVC";
 
   @BeforeTest
   @Parameters(value={"browser","version","platform", "resolution"})
   public void testSetUp(String browser, String version, String platform, String resolution) throws Exception
   //public void testSetUp() throws Exception
   {
       DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability("build", "[LambdaTest] Demonstration of Selenium Automation Testing");
       capabilities.setCapability("name", "[LambdaTest] Demonstration of Selenium Automation Testing");
       capabilities.setCapability("platform", platform);
       capabilities.setCapability("browserName", browser);
       capabilities.setCapability("version",version);
       capabilities.setCapability("resolution",resolution);
       capabilities.setCapability("tunnel",false);
       capabilities.setCapability("network",true);
       capabilities.setCapability("console",true);
       capabilities.setCapability("visual",true);
 
       try
       {
           driver = new RemoteWebDriver(new URL("https://" + username + ":" + access_key + "@hub.lambdatest.com/wd/hub"), capabilities);
       }
       catch (MalformedURLException e)
       {
           System.out.println("Invalid grid URL");
       }
       System.out.println("Started session");
   }
	@Test
	public void testScenario1() throws InterruptedException {

		// System.out.println("Loading Url");

		// driver.get("https://www.lambdatest.com/selenium-playground");
		  driver.get(testURL);
	       Thread.sleep(5000);

		driver.findElement(By.partialLinkText("Simple Form Demo")).click();
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL is:" + currentUrl);

		if (currentUrl.contains("simple-form-demo")) {
			System.out.println("URL contains the text simple-form-demo");

		} else

			System.out.println("URL does not contains the text simple-form-demo");

		String entValue = "Welcome to LambdaTest";
		driver.findElement(By.id("user-message")).sendKeys(entValue);
		driver.findElement(By.xpath("//button[contains(text(),'Get Checked value')]")).click();
		String actText = driver.findElement(By.id("message")).getText();
		System.out.println(actText);
		if (entValue.equals(actText)) {
			System.out.println("Entered value and Actual Values are Matched!");

		} else
			System.out.println("Entered value and Actual Values are not Matched!");
		
		Thread.sleep(150);

		System.out.println("TestScenario1 Finished");

	}

	@Test
	public void testScenario2() throws InterruptedException {

		  driver.get(testURL);
	       Thread.sleep(5000);
		driver.findElement(By.partialLinkText("Drag & Drop Sliders")).click();

		WebElement slider = driver.findElement(By.xpath("//input[@value='15']"));
		assertTrue(slider.isDisplayed());
		WebElement value = driver.findElement(By.id("rangeSuccess"));
		System.out.println("Default Output value:" + value.getText());
		Actions action = new Actions(driver);
		action.dragAndDropBy(slider, 120, 0).perform();
		String output = value.getText();
		System.out.println("Output value after Drag and drop:" + output);
		Assert.assertEquals(output, "95");
			Thread.sleep(150);

		System.out.println("TestScenario2 Finished");

	}

	@Test
	public void testScenario3() throws InterruptedException {
		  driver.get(testURL);
	       Thread.sleep(5000);
		// Error Message Validation
		driver.findElement(By.partialLinkText("Input Form Submit")).click();
		WebElement e1 = driver.findElement(By.xpath("//input[@id='name']"));
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String actual = (String) js.executeScript("return arguments[0].validationMessage;", e1);
		System.out.println("Actual Validation message displayed: " + actual);
		String expected = "Please fill out this field.";
		Assert.assertEquals(actual, expected);

		// Submit with Valid input for all the fields
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Divya");
		// WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(By.id("inputEmail4")).sendKeys("testemail@email.com");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.name("company")).sendKeys("XXXX");
		driver.findElement(By.name("website")).sendKeys("https://XXX.com");
		WebElement countryDropDopwn = driver.findElement(By.name("country"));
		Select s = new Select(countryDropDopwn);
		s.selectByVisibleText("United States");
		driver.findElement(By.name("city")).sendKeys("Chennai");
		driver.findElement(By.name("address_line1")).sendKeys("Ramanujam IT park");
		driver.findElement(By.name("address_line2")).sendKeys("Tharamani, chennai");
		driver.findElement(By.id("inputState")).sendKeys("Tamil Nadu");
		driver.findElement(By.id("inputZip")).sendKeys("600 113");
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		WebElement text = driver.findElement(By.xpath("//p[@class='success-msg hidden']"));
		String actualText = text.getText();
		String expectedText = "Thanks for contacting us, we will get back to you shortly.";
		System.out.println("Actual Success message displayed: " + actualText);
		Assert.assertEquals(actualText, expectedText);
		Thread.sleep(150);

		System.out.println("TestScenario3 Finished");

	}

	@AfterTest
	public void tearDown() {
		
		 if (driver != null)
	       {
	           ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
	           driver.quit();
	       }
	}

}
