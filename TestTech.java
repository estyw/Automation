package technician;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class TestTech {

	private WebDriver webDriver;
	private String baseURL;
	private String driverFilePath;

	TestTech(String baseURL, String driverFilePath) {
		this.baseURL = baseURL;
		this.driverFilePath = driverFilePath;
		init();
	}

	// ----------------------------------------------------------------------
	public void init() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("use-fake-ui-for-media-stream");
		options.addArguments("allow-file-access-from-files");
		options.addArguments("allow-file-access");
		System.setProperty("webdriver.chrome.driver", driverFilePath);
		webDriver = new ChromeDriver(options);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
		webDriver.get(baseURL);
	}

	public void signIn() {
		WebElement user = webDriver.findElement(By.id("user_email"));
		WebElement password = webDriver.findElement(By.id("user_password"));
		WebElement submit = webDriver.findElement(By.name("commit"));
		user.sendKeys("qa@audyx.com");
		password.sendKeys("123456Qw");
		submit.click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * setup function configure the the otoscopy, camera and microphone
	 * 
	 */
	public void setup() {
		webDriver.findElement(By.className("user-initials")).click();
		webDriver.findElement(By.className("setup")).click();

		String expectedURL = "https://alpha.audyx.com/technician#/setup";
		String actualURL = webDriver.getCurrentUrl();

		if (expectedURL.equals(actualURL))
			System.out.println("You are in setup page");
		else
			System.out.println("login to setup page failed");

		// configure the otoscopy
		webDriver
				.findElement(By.cssSelector(
						"#root > div > span > div.app > div.main-view > div > div:nth-child(1) > div > div > div"))
				.click();
		webDriver.findElement(By.className("HP")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// WebElement combo1 = webDriver.findElement(By.className("options"));
		// Select selectOtoscopy = new Select(combo1);
		// selectOtoscopy.selectByVisibleText("HP");

		// configure the camera for patient picture
		webDriver
				.findElement(By.cssSelector(
						"#root > div > span > div.app > div.main-view > div > div:nth-child(2) > div > div > div"))
				.click();
		webDriver.findElement(By.className("HP")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// WebElement combo2 = webDriver.findElement(By.className("options"));
		// Select selectCamera = new Select(combo2);
		// selectCamera.selectByVisibleText("HP");

		webDriver.findElement(By.className("item")).click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String expectedURL2 = "https://alpha.audyx.com/technician#/patients";
		String actualURL2 = webDriver.getCurrentUrl();
		if (expectedURL2.equals(actualURL2))
			System.out.println("You are in patients page");
		else
			System.out.println("login to patients page failed");

	}

	public void createUser() {
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement addBtn = webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > div.patient-bar > div > div.button.button-primary.undefined"));
		addBtn.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// selecet gender, first name, name
		webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > form > div:nth-child(1) > div.personal-info"))
				.click();
		webDriver.findElement(By.id("firstname")).sendKeys("Automation");
		webDriver.findElement(By.id("name")).sendKeys("Test1");

		// School information
		WebElement school = webDriver.findElement(By.id(
				"#root > div > span > div.app > div.main-view > div > form > div:nth-child(2)"));
		Select selectSchool = new Select(school);
		selectSchool.selectByIndex(1);

		// Grade information
		WebElement grade = webDriver.findElement(By.id(
				"#root > div > span > div.app > div.main-view > div > form > div:nth-child(2) > div.school-info.inline-inputs > div:nth-child(2) > div > div"));
		Select selectGrade = new Select(grade);
		selectGrade.selectByIndex(1);

		// configure the camera
		// leave activuty modal
		webDriver.findElement(By.cssSelector(
				"#root > div > div > div.fade.in.modal > div > div > div.modal-footer > div > div.button.button-primary.undefined"));

	}

}
