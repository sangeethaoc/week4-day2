package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ERailSorting {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		//Set up the driver
		WebDriverManager.chromedriver().setup();
		
		//Launch the chrome driver
		ChromeDriver chromeDriver = new ChromeDriver();
		
		//Load the URL
		chromeDriver.get("https://erail.in/");
		
		//Maximize the window
		chromeDriver.manage().window().maximize();
		
		//Implicit wait of 3 secs
		chromeDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		
		//Find  From station element, clear the default value and enter MS
		WebElement from = chromeDriver.findElement(By.id("txtStationFrom"));
		from.clear();
		from.sendKeys("MS",Keys.ENTER);
		
		//Find  To station element, clear the default value and enter MDU
		WebElement to = chromeDriver.findElement(By.id("txtStationTo"));
		to.clear();
		to.sendKeys("MDU",Keys.ENTER);
		
		//Find the 'Sort on Date' checkbox and unselect the checkbox
		WebElement dateCB = chromeDriver.findElement(By.id("chkSelectDateOnly"));
		
		if(dateCB.isSelected())
			dateCB.click();
		
		Thread.sleep(3000);
		
		//Get the total number of rows for the listed trains
		List<WebElement> rowElementsWE = chromeDriver.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr"));
		int rowSize = rowElementsWE.size();
		
		Thread.sleep(3000);

		String trainName = "";
		List<String> trainNameList = new ArrayList<String>();
		
		//Iterate over the rows and get the list of train names 
		for(int i=1; i<=rowSize;i++)
		{
			//Get the train name
			trainName = chromeDriver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr["+i+"]/td[2]")).getText();

			//Add all the train name names in the set to check for duplicates
			trainNameList.add(trainName);
		}
		
		//Sort the train names in ascending order
		Collections.sort(trainNameList);
		System.out.println(trainNameList);
		
	}

}
