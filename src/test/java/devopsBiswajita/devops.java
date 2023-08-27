package devopsBiswajita;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class devops {

	WebDriver driver;

	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			
			ChromeOptions option=new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
//			driver = new ChromeDriver(option);
			DesiredCapabilities cap = new DesiredCapabilities(option);
			cap.setCapability("browsername", "chrome");

			try {
				driver = new RemoteWebDriver(new URL("http://ec2-52-200-37-224.compute-1.amazonaws.com:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
		}

		if (browser.equalsIgnoreCase("edge")) {
			
			
			System.setProperty("webdriver.edge.driver", "C:\\browserdrivers\\msedgedriver.exe");
			EdgeOptions opt=new EdgeOptions();
			opt.addArguments("--remote-allow-origins=*");
//			driver=new EdgeDriver(opt);
			DesiredCapabilities cap = new DesiredCapabilities(opt);
			cap.setCapability("browsername", "edge");

			try {
				driver = new RemoteWebDriver(new URL("http://ec2-52-200-37-224.compute-1.amazonaws.com:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
		}
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://mail.apmosys.com/webmail/#sign-in");
	}
	
	@Test
	public void test1() throws InterruptedException {
		String title = driver.getTitle();
		Thread.sleep(3000);
		System.out.println(title);

	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}