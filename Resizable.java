package week4.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Resizable {

	public static void main(String[] args) 
	{
		// Setup the web driver
		WebDriverManager.chromedriver().setup();

		// Launch the chrome browser
		ChromeDriver chromeDriver = new ChromeDriver();

		// Load the url
		chromeDriver.get("https://jqueryui.com/resizable/");

		// Maximize the browser window
		chromeDriver.manage().window().maximize();

		// Implicit Wait of 2 seconds
		chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
		
		// Create WebDriverWait object with driver and wait time
		WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, Duration.ofMillis(3000));

		//Create object for Action class for performing Mouse operations
		Actions builder = new Actions(chromeDriver);
		
		//Switch to frame
		chromeDriver.switchTo().frame(0);
		
		//Find element to be draggable
		WebElement resizableWE = chromeDriver.findElement(By.xpath("//div[@id='resizable']/div[3]"));
		
		//Wait for 3 secs until the web element is visible
		webDriverWait.until(ExpectedConditions.visibilityOf(resizableWE));
		
		//Drag and Drop to the given position
		builder.dragAndDropBy(resizableWE, resizableWE.getLocation().getX(),resizableWE.getLocation().getY()).build().perform();;
		
	}

}
