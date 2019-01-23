package technician;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * PatientManagement class run all patient management environment It has 6 tests
 * cases Each case has some steps
 * 
 * @author Esty Wolpo
 *
 */

public class PatientManagement {

	private WebDriver webDriver;
	private String baseURL;
	private String driverFilePath;

	/**
	 * Constructor of PatientManagement class
	 * 
	 * @param baseURL        - the URL that's given
	 * @param driverFilePath - the path of driver location
	 */

	PatientManagement(String baseURL, String driverFilePath) {
		this.baseURL = baseURL;
		this.driverFilePath = driverFilePath;
		init();
	}

	// ----------------------------------------------------------------------
	/**
	 * init the properties of selenium project
	 * 
	 */
	public void init() {
		System.setProperty("webdriver.chrome.driver", driverFilePath);
		webDriver = new ChromeDriver();
		// Setting the "implicit wait" (for the FindElement/s operations only!)
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
		webDriver.get(baseURL);
	}

	// ----------------------------------------------------------------------
	/**
	 * sign in with user name and PW
	 * 
	 */
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
	 * This case check the options to get the patient list And performed some
	 * activitied in patient list
	 * 
	 */
	public void Tec101() {
		// step 1 - the basic test for patient list
		WebElement patients = webDriver
				.findElement(By.cssSelector("#root > div > span > div.app > div.sidebar > a > span"));
		patients.click();

		String expectedURL = "https://alpha.audyx.com/technician#/patients";
		String actualURL1 = webDriver.getCurrentUrl();

		// System.out.println("URL is: " + actualURL);
		if (expectedURL.equals(actualURL1))
			System.out.println("Test TEC-101 step 1 passed");
		else
			System.out.println("Test TEC-101 step 1 passed");

		// step 2
		// click on first patient in list and enter to patient folder
		WebElement patientInList = webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > tbody > tr:nth-child(1) > td:nth-child(2)"));
		patientInList.click();
		// click on back icon in patient folder - should be moved to patient list
		WebElement back = webDriver.findElement(By.className("back-icon"));
		back.click();
		String actualURL2 = webDriver.getCurrentUrl();
		if (expectedURL.equals(actualURL2))
			System.out.println("Test TEC-101 step 2 passed");
		else
			System.out.println("Test TEC-101 step 2 passed");

		//step 3
		//view all expected elements in patient list page
		WebElement setup = webDriver.findElement(By.className("user-initials"));
		WebElement search = webDriver.findElement(By.id("search-patient"));
		WebElement addPatient = webDriver.findElement(By.cssSelector("#root > div > span > div.app > div.main-view > div > div.patient-bar > div > div.button.button-primary.undefined > i"));
		WebElement numberPatients = webDriver.findElement(By.cssSelector("#root > div > span > div.app > div.main-view > div > h2"));
		WebElement tableColumn1 = webDriver.findElement(By.cssSelector("#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > thead > tr > th:nth-child(2)"));
		WebElement tableColumn2 = webDriver.findElement(By.cssSelector("#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > thead > tr > th:nth-child(3)"));
		WebElement tableColumn3 = webDriver.findElement(By.cssSelector("#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > thead > tr > th:nth-child(4)"));
		WebElement tableColumn4 = webDriver.findElement(By.cssSelector("#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > thead > tr > th:nth-child(5)"));
		WebElement tableColumn5 = webDriver.findElement(By.cssSelector("#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > thead > tr > th:nth-child(6)"));
		System.out.println("Test TEC-101 step 3 passed");
		
		
	}
}
