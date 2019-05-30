package expert;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PatientManagement {
	private static WebDriver webDriver;
	private String baseURL;
	private String driverFilePath;

	PatientManagement(String baseURL, String driverFilePath) {
		this.baseURL = baseURL;
		this.driverFilePath = driverFilePath;
		init();
	}

	public void init() {
		System.setProperty("webdriver.chrome.driver", driverFilePath);
		webDriver = new ChromeDriver();
		// Setting the "implicit wait" (for the FindElement/s operations only!)
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
		webDriver.get(baseURL);
	}

	/**
	 * sign in with user name and PW
	 */
	public void signIn() {
		WebElement user = webDriver.findElement(By.id("user_email"));
		WebElement password = webDriver.findElement(By.id("user_password"));
		WebElement submit = webDriver.findElement(By.name("commit"));
		user.sendKeys("qa@audyx.com");
		password.sendKeys("123456Qw");
		submit.click();
		Temp.tryCatch(10000);
	}

	public void PAT101() {
		WebElement audyxIcon = webDriver
				.findElement(By.xpath(" //*[@id=\"audyx-main\"]/div/div/div/div[1]/div[1]/div[2]/img"));
		audyxIcon.click();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.audyx.com/#/patients", webDriver.getCurrentUrl(), "Test PAT-101 step 1");
		// navigate to side menu and enter to patient list
		WebElement sideMenu = webDriver
				.findElement(By.xpath(" //*[@id=\"audyx-main\"]/div/div/div/div[1]/div[1]/div[1]"));
		sideMenu.click();
		Temp.tryCatch(3000);
		WebElement patientListIcon = webDriver.findElement(By.xpath(" //*[@id=\"menu-side\"]/div[1]/div"));
		patientListIcon.click();
		Temp.tryCatch(3000);

		WebElement mainPanel = webDriver.findElement(By.xpath("//*[@id=\"audyx-main\"]/div/div"));
		Actions act = new Actions(webDriver);
		act.moveToElement(mainPanel).perform();
		Temp.tryCatch(5000);
		Temp.checkUrl("https://alpha.audyx.com/#/patients", webDriver.getCurrentUrl(), "Test PAT-101 step 2");

		// view all elements in patient list page
		WebElement navBar = webDriver.findElement(By.xpath(" //*[@id=\"audyx-main\"]/div/div/div/div[1]"));
		WebElement sideBar = webDriver
				.findElement(By.className("navigator"));
		WebElement searchArea = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-list-body\"]/div/div/div[1]/div/div/input"));
		WebElement addPatient = webDriver.findElement(By.xpath(" //*[@id=\"audyx-main\"]/div/div/div/div[1]"));
		WebElement PatientList = webDriver.findElement(By.xpath(" //*[@id=\"patient-list-body\"]/div"));
		WebElement activityFeed = webDriver.findElement(By.xpath(" //*[@id=\"activity-feed\"]/div"));
		System.out.println("Test PAT-101 step 3 passed");
		// view all elements in line of patient list
		WebElement patientName = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-list-body\"]/div/div/table/tbody/tr[1]/td[1]/a"));
		WebElement patientAge = webDriver.findElement(By.className("patient-age-birthdate"));
		WebElement patientStatus = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-list-body\"]/div/div/table/tbody/tr[1]/td[3]/span"));
		WebElement patientActivity = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-list-body\"]/div/div/table/tbody/tr[1]/td[4]"));
		WebElement comment = webDriver
				.findElement(By.className("patient-note"));
		WebElement patientOverview = webDriver.findElement(By.className("patient-overview-icon"));
		System.out.println("Test PAT-101 step 4 passed");
		// sort by name
		WebElement sortName = webDriver.findElement(By.className("patient-full-name"));
		sortName.click();
		Temp.tryCatch(3000);
		WebElement sortUp = webDriver.findElement(By.className("fa-sort-desc"));
		WebElement sortName2 = webDriver.findElement(By.className("patient-full-name"));
		sortName2.click();
		Temp.tryCatch(3000);
		WebElement sortDown = webDriver.findElement(By.className("fa-sort-asc"));
		System.out.println("Test PAT-101 step 5 passed");
		// go to page 2
		WebElement page2 = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-list-body\"]/div/div/div[2]/div[1]/ul/li[2]/a"));
		page2.click();
		Temp.tryCatch(3000);
		System.out.println("Test PAT-101 step 6 passed");
		// sort list by age
		WebElement sortAge = webDriver.findElement(By.className("patient-age"));
		sortAge.click();
		Temp.tryCatch(3000);
		System.out.println("Test PAT-101 step 7 passed");
		// sort list by status
		WebElement sortStatus = webDriver.findElement(By.className("patient-status"));
		sortStatus.click();
		Temp.tryCatch(3000);
		System.out.println("Test PAT-101 step 8 passed");
		// sort list by last activity
		WebElement sortActivity = webDriver.findElement(By.className("patient-last-test"));
		sortActivity.click();
		Temp.tryCatch(3000);
		System.out.println("Test PAT-101 step 9 passed");
//compare the name in navbar is as in list
		WebElement CurrentPAtientInList = webDriver.findElement(By.xpath("//*[@id=\"patient-list-body\"]/div/div/table/tbody/tr[1]/td[1]/a"));
		String CurrentPAtientInList1 = CurrentPAtientInList.getAttribute("innerHTML");
		System.out.println(CurrentPAtientInList1);
		Temp.tryCatch(3000);
	
	}
	
}
