package week4.assignments;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ERailUnique {

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
		
		String trainName = "";
		
		Thread.sleep(3000);
		
		Set<String> trainNameSet = new HashSet<String>();
		
		//Iterate over the rows and get the list of train names 
		for(int i=1; i<=rowSize;i++)
		{
			//Get the train name
			trainName = chromeDriver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr["+i+"]/td")).getText();
			System.out.println("Train Name : "+trainName);
			
			//Add all the train name names in the set to check for duplicates
			if(!trainNameSet.add(trainName))
				System.out.println("Duplicate Train Name : "+trainName);
		}
		
		//Get the Train Name Set Size
		int trainNameSetSize = trainNameSet.size();
		
		System.out.println("Row Size : "+rowSize);
		System.out.println("TrainName SetSize : "+trainNameSetSize);
		
		//Check if the initial row size and Train Name Set size are equal
		if(rowSize == trainNameSetSize)
		{
			System.out.println("There are no duplicate train names");
		}
		else
			System.out.println("There exists duplicate train names");
		
	}

}
