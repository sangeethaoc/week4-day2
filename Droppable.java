package week4.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Droppable {

	public static void main(String[] args) 
	{
				// Setup the web driver
				WebDriverManager.chromedriver().setup();

				// Launch the chrome browser
				ChromeDriver chromeDriver = new ChromeDriver();

				// Load the url
				chromeDriver.get("https://jqueryui.com/droppable");

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
				WebElement draggableWE = chromeDriver.findElement(By.id("draggable"));
				
				//Find element where it has to be dropped
				WebElement droppableWE = chromeDriver.findElement(By.id("droppable"));
				
				//Wait for 3 secs until the web element is visible
				webDriverWait.until(ExpectedConditions.visibilityOf(draggableWE));
				
				//Drag and Drop at the given position
				builder.dragAndDrop(draggableWE, droppableWE).perform();
				//builder.clickAndHold(draggableWE).moveToElement(droppableWE).release().build().perform();
				
				
	}

}
