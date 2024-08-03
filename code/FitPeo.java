package AutomationTestAssignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FitPeo {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		// TestCase 1
		driver.get("https://www.fitpeo.com/");
		
		// TestCase 2
		driver.findElement(By.xpath("//div[@class='MuiBox-root css-v0i3e8']//div[6]")).click();
		Thread.sleep(2000); //sleep is use because it take time to open the revenue calculator and code move forword.
		
		// TestCase 3
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement RevenueCalculator=driver.findElement(By.xpath("//h4[@class='MuiTypography-root MuiTypography-h4 crimsonPro css-12siehf']"));
		
		js.executeScript("arguments[0].scrollIntoView();", RevenueCalculator);
		
		// TestCase 4 (actions class methods dont work in this slider because it move on 816 and 823 not in between then thats why I have to use loop)
		
		WebElement slider=driver.findElement(By.xpath("//input[@aria-orientation='horizontal']"));//slider is initially on 200
		
		for(int i=0;i<(820-200);i++) {
			slider.sendKeys(Keys.ARROW_RIGHT);
			}
		
		// TestCase 5
		
		Actions act = new Actions(driver);
				
		driver.findElement(By.xpath("//input[@type='number']")).click();//clear and then sendkeys directly not work. after clear when sendkeys use it use hard quate value thats why i use keyboard action
		
		act.keyDown(Keys.BACK_SPACE).keyDown(Keys.BACK_SPACE).keyDown(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE).perform();
		driver.findElement(By.xpath("//input[@type='number']")).sendKeys("560");
		
		// TestCase 6
		
		String SliderValue=driver.findElement(By.xpath("//input[@type='number']")).getAttribute("value");
		if(SliderValue.equals("560")) {
			System.out.println("Slider is on 560");
		}
		else {
			System.out.println("Slider is not on 560");
			System.out.println("Slider is on "+SliderValue);
		}
		
		// TestCase 7 
		
		driver.findElement(By.xpath("//div[@class='MuiBox-root css-1p19z09']//div[1]//input")).click();//CPT-99091
		driver.findElement(By.xpath("//div[@class='MuiBox-root css-1p19z09']//div[2]//input")).click();//CPT-99453
		driver.findElement(By.xpath("//div[@class='MuiBox-root css-1p19z09']//div[3]//input")).click();//CPT-99454
		driver.findElement(By.xpath("//div[@class='MuiBox-root css-1p19z09']//div[8]//input")).click();//CPT-99474
		
		// 8 task verify the value
		
		String total=driver.findElement(By.xpath("//div[@class='MuiToolbar-root MuiToolbar-gutters MuiToolbar-regular css-1lnu3ao']//p[4]/p")).getText();
		System.out.println("Total Recurring Reimbursement for all Patients Per Month: "+total);
		if(total.equals("$110700")) {
			System.out.println("Test Passed - Value Matched ");
		}
		else {
			System.out.println("Test Failed - value not matched");
		}
		
		driver.close();
	}
}
