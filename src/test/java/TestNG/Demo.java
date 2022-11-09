package TestNG;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Demo {
	WebDriver driver;

	@Test(groups = { "smoke" })
	public void setbrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("http://demowebshop.tricentis.com/login");
		driver.manage().window().maximize();
		System.out.println("open the browser");
	}

	@Test(groups = { "sanity" },dependsOnMethods = "setbrowser")
	public void Books() {
		driver.findElement(By.xpath("//input[@type= 'text']")).sendKeys("Books");
		driver.findElement(By.xpath("//input[contains(text(),'button-1 search-box-button')]")).click();
		System.out.println("Books");
	}

	@Test(groups = { "regression" })
	public void links() {
		List<WebElement> list = driver.findElements(By.tagName("a"));
		System.out.println("Number of links:" + list.size());
	}

	@Test(groups = { "smoke" }, dependsOnMethods = "setbrowser")
	public void loginapp() {

		driver.findElement(By.id("Email")).sendKeys("vinoth01@gmail.com");
		driver.findElement(By.name("Password")).sendKeys("Vinoab");
		driver.findElement(By.xpath("//input[@value= 'Log in']")).click();
		System.out.println("Login successful");
	}

	@Test(groups = { "smoke" },dependsOnMethods = "loginapp")
	public void pagesource() {
		String pageSource = driver.getPageSource();
		System.out.println("Pagesource is:" + pageSource);
	}

	@Test(groups = { "sanity" })

	public void computers() {
		driver.findElement(By.xpath("//a[@href='/computers']")).click();
		System.out.println("computers");
	}

	@Test(groups = { "smoke" },dependsOnMethods = "pagesource")
	public void logoutapp() {
		driver.findElement(By.linkText("Log out")).click();
		System.out.println("Logout successful");
	}

	@Test(groups = { "regression" })

	public void closebrowser() {
		driver.close();
		System.out.println("close the browser");
	}

}