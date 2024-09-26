package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testedge {
	WebDriver driver;
	
	@Test 
	public void openEdge()
	{
		//System.setProperty("webdriver.edge.driver", "D:\\\\Automation\\\\edgedriver_win64\\\\msedgedriver.exe");
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get("gdata.in");
	}

}
