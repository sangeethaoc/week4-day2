package week4.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {
		// Setup the web driver
		WebDriverManager.chromedriver().setup();

		// Add preference in BROWSER LEVEL
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");

		// Launch the chrome browser
		ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);

		// Load the url
		chromeDriver.get("https://www.amazon.in/");

		// Maximize the browser window
		chromeDriver.manage().window().maximize();

		// Implicit Wait of 2 seconds
		chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

		// Create WebDriverWait object with driver and wait time
		WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, Duration.ofMillis(5000));

		// Create object for Action class for performing Mouse operations
		Actions builder = new Actions(chromeDriver);

		// Search as oneplus 9 pro
		chromeDriver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro", Keys.ENTER);

		//Get the Price of the First product displayed in the search result
		String productPrice = chromeDriver.findElement(By.className("a-price-whole")).getText();
		
		// Print the Price of the First product displayed in the search result
		System.out.println("Price of the First product displayed : " + productPrice);

		// Print the number of customer ratings for the first displayed product
		System.out.println("Number of Customer ratings of the First displayed product : " + chromeDriver
				.findElement(By.xpath("//span[@class='a-size-base a-color-base s-underline-text']")).getText());

		// Mouse Hover on the stars
		builder.moveToElement(chromeDriver.findElement(By.xpath("//span[@class='a-icon-alt']"))).click().build()
				.perform();

		// Get the percentage of ratings for the 5 star.
		System.out.println("Percentage Ratings of the First product displayed : " + chromeDriver
				.findElement(
						By.xpath("//span[@class='a-size-medium a-color-base a-text-beside-button a-text-bold']"))
				.getText());

		// Click the first text link of the first image
		builder.moveToElement(
				chromeDriver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"))).click()
				.build().perform();

		// Switch the driver control to second window
		chromeDriver.switchTo().window(new ArrayList<String>(chromeDriver.getWindowHandles()).get(1));

		// Take Screenshot as file
		File screenshotAs = chromeDriver.getScreenshotAs(OutputType.FILE);

		// Copy Location
		File destLocation = new File("./images/Amazon.png");

		// Copy the source file to a specified location
		FileUtils.copyFile(screenshotAs, destLocation);

		// Click on Add to Cart
		builder.moveToElement(chromeDriver.findElement(By.id("add-to-cart-button"))).click().build().perform();
		
		Thread.sleep(5000);
		
		//Apply webdriver wait of 3 secs for the web element to be visible
		webDriverWait.until(ExpectedConditions.visibilityOf(chromeDriver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']"))));
		
		String cartTotal = chromeDriver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']")).getText();
		
		//Trim the Currency symbol character at the first
		cartTotal = cartTotal.substring(1,7);
				
		System.out.println("Cart Total Amount : "+cartTotal);
		
		if(productPrice.equals(cartTotal))
			System.out.println("Price of the product and Cart Total amount is same and verified");
		else
			System.out.println("Price of the product and Cart Total amount is not same");

	}

}
