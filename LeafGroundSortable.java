package week4.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundSortable {

	public static void main(String[] args) throws InterruptedException 
	{
				// Setup the web driver
				WebDriverManager.chromedriver().setup();

				// Launch the chrome browser
				ChromeDriver chromeDriver = new ChromeDriver();

				// Load the url
				chromeDriver.get("http://www.leafground.com/pages/sortable.html");

				// Maximize the browser window
				chromeDriver.manage().window().maximize();

				// Implicit Wait of 2 seconds
				chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

				// Wait for alert box to load
				Thread.sleep(5000);
				
				//Create object for Action class for performing Mouse operations
				Actions builder = new Actions(chromeDriver);
				
				//Find the web elements to be selected From and To
				WebElement item5 = chromeDriver.findElement(By.xpath("//li[text()='Item 5']"));
				
				WebElement item1 = chromeDriver.findElement(By.xpath("//li[text()='Item 1']"));
				
				Thread.sleep(3000);
				
				//Click and Hold the From element and Move till To element and Release the hold
				builder.clickAndHold(item5).moveToElement(item1).release().build().perform();
	}

}
