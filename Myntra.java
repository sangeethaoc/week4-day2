package week4.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws InterruptedException, IOException {
		// Setup the web driver
		WebDriverManager.chromedriver().setup();

		// Add preference in BROWSER LEVEL
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");

		// Launch the chrome browser
		ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);

		// Load the url
		chromeDriver.get("https://www.myntra.com/");

		// Maximize the browser window
		chromeDriver.manage().window().maximize();

		// Implicit Wait of 2 seconds
		chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

		// Create WebDriverWait object with driver and wait time
		WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(3));

		// Create object for Action class for performing Mouse operations
		Actions builder = new Actions(chromeDriver);

		// Mouse Hover on Men's Fashion
		builder.moveToElement(chromeDriver.findElement(By.xpath("//a[text()='Men']"))).build().perform();

		Thread.sleep(3000);

		// Click Jackets
		builder.moveToElement(chromeDriver.findElement(By.xpath("//a[text()='Jackets']"))).click().build().perform();

		// Find the Total Count of the selected item
		String initialCount = (chromeDriver.findElement(By.className("title-count")).getText());
		System.out.println("Total Count Initial : " + initialCount);

		// Convert string to int
		int totalCountOfJackets = Integer.parseInt(initialCount.split(" ")[2]);
		System.out.println("Total Count of Jackets: " + totalCountOfJackets);

		// Find the Count for the category 'Jackets'
		String jacketCategoryCount = chromeDriver.findElement(By.xpath("(//span[@class='categories-num'])[1]"))
				.getText();
		int countOfJackets = Integer.parseInt(jacketCategoryCount.substring(1, 5));
		System.out.println("Total Count of Jackets under Category : " + countOfJackets);

		// Find the Count for the category 'Rain Jacket'
		String rainJacketCategCount = chromeDriver.findElement(By.xpath("(//span[@class='categories-num'])[2]"))
				.getText();
		int countOfRainJacket = Integer.parseInt(rainJacketCategCount.substring(1, 3));
		System.out.println("Total Count of Rain Jackets under Category: " + countOfRainJacket);

		// Find the total count of categories
		int totalCountOfCategories = countOfJackets + countOfRainJacket;

		System.out.println("Total Count of Jackets and Rain Jackets under Category :" + totalCountOfCategories);

		if (totalCountOfJackets == totalCountOfCategories)
			System.out.println("Total count and  Sum of each category counts are same ");

		// Check Jackets
		builder.moveToElement(
				chromeDriver.findElement(By.xpath("//span[@class='categories-num']/following-sibling::div"))).click()
				.build().perform();

		// Click + More option under BRAND
		builder.moveToElement(chromeDriver.findElement(By.className("brand-more"))).click().build().perform();

		// Type Duke and Press Enter
		WebElement brandNameInputFieldWE = chromeDriver.findElement(By.className("FilterDirectory-searchInput"));
		brandNameInputFieldWE.sendKeys("Duke", Keys.ENTER);

		// Check Duke
		builder.moveToElement(
				chromeDriver.findElement(By.xpath("//span[@class='FilterDirectory-count']/following-sibling::div")))
				.click().build().perform();

		// Close the pop-up x
		builder.moveToElement(chromeDriver
				.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")))
				.click().build().perform();

		// Check brand Name for all the products listed in Page 1
		int totalNumberOfProducts = 0;
		Thread.sleep(3000);
		totalNumberOfProducts = checkForBrandNameDuke(chromeDriver, totalNumberOfProducts, 1);
		System.out.println("Page 1 :" + totalNumberOfProducts);

		// Click brand Name for all the products listed in page 2
		builder.moveToElement(chromeDriver.findElement(By.xpath("//li[@class='pagination-number'][1]/a"))).click()
				.build().perform();

		Thread.sleep(3000);
		totalNumberOfProducts = checkForBrandNameDuke(chromeDriver, totalNumberOfProducts, 2);
		System.out.println("Page 2 :" + totalNumberOfProducts);

		// Click brand Name for all the products listed in page 3
		builder.moveToElement(chromeDriver.findElement(By.xpath("//li[@class='pagination-number'][2]/a"))).click()
				.build().perform();

		Thread.sleep(3000);
		totalNumberOfProducts = checkForBrandNameDuke(chromeDriver, totalNumberOfProducts, 3);
		System.out.println("Page 3 :" + totalNumberOfProducts);

		// Click brand Name for all the products listed in page 4
		builder.moveToElement(chromeDriver.findElement(By.xpath("//li[@class='pagination-number'][3]/a"))).click()
				.build().perform();

		Thread.sleep(3000);
		totalNumberOfProducts = checkForBrandNameDuke(chromeDriver, totalNumberOfProducts, 4);
		System.out.println("Page 4 :" + totalNumberOfProducts);

		// Click brand Name for all the products listed in page 5
		builder.moveToElement(chromeDriver.findElement(By.xpath("//li[@class='pagination-number'][4]/a"))).click()
				.build().perform();

		Thread.sleep(3000);
		totalNumberOfProducts = checkForBrandNameDuke(chromeDriver, totalNumberOfProducts, 5);
		System.out.println("Page 5 :" + totalNumberOfProducts);

		// Sort By better Discount
		builder.moveToElement(chromeDriver.findElement(By.xpath("(//input[@class='discount-input'])[3]"))).click()
				.build().perform();

		// Print the Price of the First Product displayed in the page
		System.out.println("Price of the First Product displayed in the page : "
				+ chromeDriver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText());

		// Click on the First product displayed in the page
		builder.moveToElement(
				chromeDriver.findElement(By.xpath("//div[@class='product-imageSliderContainer']/parent::a"))).click()
				.build().perform();

		// Switch the driver control to second window
		chromeDriver.switchTo().window(new ArrayList<String>(chromeDriver.getWindowHandles()).get(1));

		// Take Screenshot as file
		File screenshotAs = chromeDriver.getScreenshotAs(OutputType.FILE);

		// Copy Location
		File destLocation = new File("./images/Myntra.png");

		// Copy the source file to a specified location
		FileUtils.copyFile(screenshotAs, destLocation);

		// Click on WishList now
		builder.moveToElement(chromeDriver.findElement(By.xpath("//span[text()='WISHLIST']"))).click().build()
				.perform();

	}

	private static int checkForBrandNameDuke(ChromeDriver chromeDriver, int totalNumberOfProducts, int pageindex) {
		String brandName = "";
		// Get the list of brand name for the products displayed in the current page
		List<WebElement> productBrandList = chromeDriver.findElements(By.xpath(("//h3[@class='product-brand']")));
		System.out.println("Size of productBrandList in Page " + pageindex + " : " + productBrandList.size());
		for (WebElement brandNameWE : productBrandList) {
			brandName = brandNameWE.getText();
			if (brandName.equalsIgnoreCase("Duke")) {
				totalNumberOfProducts++;
			}
		}
		return totalNumberOfProducts;
	}
}
