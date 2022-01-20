package week4.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selectable {

	public static void main(String[] args) 
	{
		// Setup the web driver
		WebDriverManager.chromedriver().setup();

		// Launch the chrome browser
		ChromeDriver chromeDriver = new ChromeDriver();

		// Load the url
		chromeDriver.get("https://jqueryui.com/selectable");

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
		WebElement item1 = chromeDriver.findElement(By.xpath("//ol[@id='selectable']/li[1]"));
		WebElement item3 = chromeDriver.findElement(By.xpath("//ol[@id='selectable']/li[3]"));
		WebElement item5 = chromeDriver.findElement(By.xpath("//ol[@id='selectable']/li[5]"));
		
		//Drag and Drop to the given position
		builder.keyDown(Keys.CONTROL)
		.click(item1)
		.click(item3)
		.click(item5)
		.keyUp(Keys.CONTROL)
		.build().perform();;
		
	}

}
