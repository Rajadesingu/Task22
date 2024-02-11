package task22;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WebDriverWait {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriver driver = new ChromeDriver();

		String expVerify ="Thank you!";

		// load URL
		driver.get("https://phptravels.com/demo/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// filling the form
		driver.findElement(By.className("first_name")).sendKeys("jack");
		driver.findElement(By.className("last_name")).sendKeys("john");
		driver.findElement(By.className("business_name")).sendKeys("school");
		driver.findElement(By.xpath("//input[@class=\"email form-control\"]")).sendKeys("test@gmail.com");
		
		//add logic to sum verification
		WebElement num1 = driver.findElement(By.id("numb1"));
		WebElement num2 = driver.findElement(By.id("numb2"));
		String num = String.valueOf(Integer.parseInt(num1.getText()) + Integer.parseInt(num2.getText()));
		String text = num1.getText();
		driver.findElement(By.id("number")).sendKeys(num);
		
		// verify form is submitted successfully 
		WebElement webElement = driver.findElement(By.xpath("//button[text()='Submit']"));
		org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver,
				Duration.ofSeconds(10));

		wait.until(ExpectedConditions.elementToBeClickable(webElement));
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2//strong[text()=' Thank you!']")));
		WebElement actVerify = driver.findElement(By.xpath("//h2//strong[text()=' Thank you!']"));
		String actVerifyText = actVerify.getText(); 
		
		if (expVerify.equals(actVerifyText)) {

			System.out.println("Login successful");

		} else {
			System.out.println("Login failed");
		}
		
		//taking screen shot
		TakesScreenshot screenshot = ((TakesScreenshot) driver );
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File desk = new File("C:\\Users\\PERSONAL\\eclipse-workspace\\ExcelFileOperation\\snap\\ss.png");
		FileUtils.copyFile(source, desk);
	}
}
