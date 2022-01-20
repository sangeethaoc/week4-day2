package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {

		// Setup the web driver
		WebDriverManager.chromedriver().setup();

		// Add preference in BROWSER LEVEL
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");

		// Launch the chrome browser
		ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);

		// Load the url
		chromeDriver.get("https://www.nykaa.com/");

		// Maximize the browser window
		chromeDriver.manage().window().maximize();

		// Implicit Wait of 2 seconds
		chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

		// Create WebDriverWait object with driver and wait time
		WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, Duration.ofMillis(3000));

		// Create object for Action class for performing Mouse operations
		Actions builder = new Actions(chromeDriver);

		// Mouse hover on brands
		builder.moveToElement(chromeDriver.findElement(By.xpath("//a[text()='brands']"))).build().perform();

		// Click on L'Oreal Paris
		builder.moveToElement(chromeDriver.findElement(By.xpath("//a[text()=\"L'Oreal Paris\"]"))).click().build()
				.perform();

		// Get the page title
		String title = chromeDriver.getTitle();

		// Print the current page Title
		System.out.println("Page Title : " + title);

		// Verify if it contains L'Oreal Paris
		if (title != null && title.contains("L'Oreal Paris"))
			System.out.println("The page title has the text L'Oreal Paris as expected");
		else
			System.out.println("The page displayed is not as expected");

		// Click 'Sort By'
		builder.moveToElement(chromeDriver.findElement(By.className("sort-name"))).click().build().perform();

		// Select 'customer top rated'
		builder.moveToElement(
				chromeDriver.findElement(By.xpath("//span[text()='customer top rated']/following::div[1]"))).click()
				.build().perform();

		// Click Category
		builder.moveToElement(chromeDriver.findElement(By.xpath("//span[text()='Category']"))).click().build()
				.perform();

		Thread.sleep(3000);

		// Click Hair
		builder.moveToElement(chromeDriver.findElement(By.xpath("//span[text()='Hair']"))).click().build().perform();

		// Click Hair Care
		builder.moveToElement(chromeDriver.findElement(By.xpath("//span[text()='Hair Care']"))).click().build()
				.perform();

		// Click Shampoo
		builder.moveToElement(chromeDriver.findElement(By.xpath("//span[text()='Shampoo']/following::div[1]"))).click()
				.build().perform();

		// Check if the filter is applied with Shampoo
		if (chromeDriver.findElement(By.xpath("//span[text()='Shampoo']")).isDisplayed())
			System.out.println("Filter is applied with 'Shampoo'");

		// Click on L'Oreal Paris Colour Protect Shampoo
		builder.moveToElement(chromeDriver
				.findElement(By.xpath("//div[text()=\"L'Oreal Paris Colour Protect Shampoo\"]/ancestor::a"))).click()
				.build().perform();

		// Get all window handles
		Set<String> windowHandles = chromeDriver.getWindowHandles();

		// Covert the set to List for retrieving the particular window based on the
		// index
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);

		// Switch the driver control to second window
		chromeDriver.switchTo().window(windowHandlesList.get(1));

		// Print MRP value
		System.out.println("MRP : "
				+ chromeDriver.findElement(By.xpath("//span[text()='MRP:']/following-sibling::span")).getText());

		// Click on ADD to BAG
		builder.moveToElement(chromeDriver.findElement(By.xpath("//span[text()='ADD TO BAG']"))).click().build()
				.perform();

		// Go to Shopping Bag
		builder.moveToElement(chromeDriver.findElement(By.xpath("//span[@class='cart-count']"))).click().build()
				.perform();

		// Switch to Frame
		chromeDriver.switchTo().frame(0);

		Thread.sleep(3000);

		// Print the Grand Total Value in Shopping Bag Page
		String grandTotal = chromeDriver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		grandTotal = grandTotal.substring(1);
		System.out.println("Grand Total in Shopping Bag Page: " + grandTotal);

		// Click PROCEED
		builder.moveToElement(chromeDriver.findElement(By.xpath("//span[text()='PROCEED']"))).click().build().perform();

		// Click 'CONTINUE AS GUEST' button
		builder.moveToElement(chromeDriver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']"))).click()
				.build().perform();

		// Print the Grand Total Value in Final page
		String grandTotalFinal = chromeDriver
				.findElement(By.xpath("//div[text()='Grand Total']//following-sibling::div/span")).getText();
		System.out.println("Grand Total in CheckOut Page: " + grandTotalFinal);

		if (grandTotal.equals(grandTotalFinal))
			System.out.println("Both Shopping Bag page and Checkout page shows the same Grand Total Value");
		else
			System.out.println("Both Shopping Bag page and Checkout page shows the different Grand Total Value");
	}

}
