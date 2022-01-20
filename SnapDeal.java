package week4.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// Setup the web driver
		WebDriverManager.chromedriver().setup();

		// Add preference in BROWSER LEVEL
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");

		// Launch the chrome browser
		ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);

		// Load the url
		chromeDriver.get("https://www.snapdeal.com/");

		// Maximize the browser window
		chromeDriver.manage().window().maximize();

		// Implicit Wait of 2 seconds
		chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

		// Create WebDriverWait object with driver and wait time
		WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, Duration.ofMillis(3000));

		// Create object for Action class for performing Mouse operations
		Actions builder = new Actions(chromeDriver);

		// Click on Men's Fashion
		builder.moveToElement(chromeDriver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]"))).click().build()
				.perform();

		// Click on Sports Shoes
		builder.moveToElement(chromeDriver.findElement(By.xpath("//span[text()='Sports Shoes']"))).click().build()
				.perform();

		Thread.sleep(3000);

		// Print the Total Count - Sports Shoes For Men
		System.out.println("Total Count - Sports Shoes For Men : " + chromeDriver
				.findElement(By.xpath("//div[text()='Sports Shoes for Men']/following-sibling::div")).getText());

		// Click Training shoes
		builder.moveToElement(chromeDriver.findElement(By.xpath("//div[text()='Training Shoes']/parent::a"))).click()
				.build().perform();

		// Sort by Low to High [Low Value - 900, High Value - 1200]
		WebElement fromValueWE = chromeDriver.findElement(By.xpath("//input[@name='fromVal']"));
		fromValueWE.clear();
		fromValueWE.sendKeys("900");
		WebElement toValueWE = chromeDriver.findElement(By.xpath("//input[@name='toVal']"));
		toValueWE.clear();
		toValueWE.sendKeys("1200");

		Thread.sleep(3000);

		// Click on GO button
		builder.moveToElement(chromeDriver.findElement(By.xpath("//div[contains(text(),'GO')]"))).click().build()
				.perform();

		Thread.sleep(3000);

		// Click on 'View More' under Color
		builder.moveToElement(chromeDriver.findElement(By.xpath("//button[contains(text(),'View More')]"))).click()
				.build().perform();

		// Select Color Navy
		builder.moveToElement(chromeDriver.findElement(By.xpath("//a[contains(text(),'Navy')]"))).click().build()
				.perform();
		
		//Get the Price filter Value
		String priceFilterText = chromeDriver.findElement(By.xpath("//div[@class='navFiltersPill'][1]/a")).getText();

		//Get the Color filter Value
		String pricecolorFilterText = chromeDriver.findElement(By.xpath("//div[@class='navFiltersPill'][2]/a")).getText();

		System.out.println("Price Filter Text :"+priceFilterText);
		System.out.println("Price Color Text :"+pricecolorFilterText);
		
		if(priceFilterText.contains("Rs. 900 - Rs. 1200") && pricecolorFilterText.contains("Navy"))
			System.out.println("All the applied filters exist and are verified");
		
		// Mouse Hover on First Shoe
		builder.moveToElement(
				chromeDriver.findElement(By.xpath("//img[@title='Red Tape Navy Training Shoes']/ancestor::a"))).build()
				.perform();

		// Find element of Quick View
		//WebElement quickView = chromeDriver.findElement(By.xpath("//div[contains(text(),'Quick View')]"));

		// Wait for 3 secs until the Quick view button is visible
		//webDriverWait.until(ExpectedConditions.visibilityOf(quickView));
		Thread.sleep(3000);

		// Click on Quick View when it is displayed
		builder.moveToElement(chromeDriver.findElement(By.xpath("//div[contains(text(),'Quick View')]"))).click().build().perform();
		
		String price = chromeDriver.findElement(By.className("payBlkBig")).getText();
		String discountpercentage = chromeDriver.findElement(By.className("percent-desc")).getText();
		
		System.out.println("Price of the product :"+price);
		System.out.println("Discount percentage : "+discountpercentage);
		
		//Take Screenshot as file
		File screenshotAs = chromeDriver.getScreenshotAs(OutputType.FILE);
		
		//Copy Location
		File destLocation = new File("./images/SnapDeal.png");
		
		//Copy the source file to a specified location 
		FileUtils.copyFile(screenshotAs, destLocation);
		
		//Close the current window
		chromeDriver.close();
	}

}
