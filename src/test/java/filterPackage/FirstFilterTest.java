package filterPackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstFilterTest {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		driver = new ChromeDriver();

		driver.get("https://www.t-mobile.com/tablets");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(5000);

		selectFilter("Deals", "New");

		selectFilter("Brands", "all");

		selectFilter("Operating System", "Android", "iPadOS");

	}

	public static void selectFilter(String... listOfString) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement element = driver.findElement(By.xpath("//legend[contains(text(),'" + listOfString[0] + "')]"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		driver.findElement(By.xpath("//legend[contains(text(),'" + listOfString[0] + "')]")).click();

		if (listOfString[1].equalsIgnoreCase("all")) {
			List<WebElement> list = driver.findElements(By.xpath(
					"//mat-expansion-panel-header//legend[contains(text(),'" + listOfString[0]
							+ "')]//..//..//..//following-sibling::div[@role='region']//span[@class='filter-display-name']"));
			for (int i = 0; i < list.size(); i++) {
				list.get(i).click();
			}
		} else {
			for (int i = 1; i < listOfString.length; i++) {
				Actions action = new Actions(driver);
				WebElement subFilterElement = driver.findElement(By
						.xpath("//span[@class='filter-display-name' and contains(text(),'" + listOfString[i] + "')]"));
				action.moveToElement(subFilterElement);
				subFilterElement.click();
			}
		}
	}

}
