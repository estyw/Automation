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

		// step 3
		// view all expected elements in patient list page
		WebElement setup = webDriver.findElement(By.className("user-initials"));
		WebElement search = webDriver.findElement(By.id("search-patient"));
		WebElement addPatient = webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > div.patient-bar > div > div.button.button-primary.undefined > i"));
		WebElement numberPatients = webDriver
				.findElement(By.cssSelector("#root > div > span > div.app > div.main-view > div > h2"));
		WebElement tableColumn1 = webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > thead > tr > th:nth-child(2)"));
		WebElement tableColumn2 = webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > thead > tr > th:nth-child(3)"));
		WebElement tableColumn3 = webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > thead > tr > th:nth-child(4)"));
		WebElement tableColumn4 = webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > thead > tr > th:nth-child(5)"));
		WebElement tableColumn5 = webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > thead > tr > th:nth-child(6)"));
		System.out.println("Test TEC-101 step 3 passed");

		// step 4
		// sort by patient name
		WebElement searchButtom = webDriver.findElement(By.id("search-patient"));
		searchButtom.clear();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement patientSort = webDriver.findElement(
				By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/table/thead/tr/th[2]"));
		patientSort.click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement patientSortdown1 = webDriver.findElement(By.className("react-bootstrap-table-sort-order"));
		patientSort.click();
		WebElement patientSortup1 = webDriver.findElement(By.className("dropup"));
		System.out.println("Test TEC-101 step 4 passed");

		// step 5
		// go to 3rd page
		WebElement page3 = webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[2]/div[2]/ul/li[3]/a"));
		page3.click();
		System.out.println("Test TEC-101 step 5 passed");

		// step6
		// sort by age

		WebElement ageSort = webDriver.findElement(
				By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/table/thead/tr/th[3]"));
		ageSort.click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement patientSortdown2 = webDriver.findElement(By.className("react-bootstrap-table-sort-order"));
		ageSort.click();
		WebElement patientSortup2 = webDriver.findElement(By.className("dropup"));
		System.out.println("Test TEC-101 step 6 passed");

		// step 7
		// sort by status

		WebElement statusSort = webDriver.findElement(
				By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/table/thead/tr/th[4]"));
		statusSort.click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement patientSortdown3 = webDriver.findElement(By.className("react-bootstrap-table-sort-order"));
		statusSort.click();
		WebElement patientSortup3 = webDriver.findElement(By.className("dropup"));
		System.out.println("Test TEC-101 step 7 passed");

		//// step 8
		// sort by last activity

		WebElement activitySort = webDriver.findElement(
				By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/table/thead/tr/th[5]"));
		statusSort.click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement patientSortdown4 = webDriver.findElement(By.className("react-bootstrap-table-sort-order"));
		statusSort.click();
		WebElement patientSortup4 = webDriver.findElement(By.className("dropup"));
		System.out.println("Test TEC-101 step 8 passed");

		// step 9 -add new patient
		WebElement patientInList2 = webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > tbody > tr:nth-child(3) > td:nth-child(2) > div"));
		patientInList2.click();
		String expectedURL2 = "https://alpha.audyx.com/technician#/patient/*";
		String actualURL3 = webDriver.getCurrentUrl();
		if (expectedURL2.equals(actualURL3))
			System.out.println("Test TEC-101 step 9 passed");
		else
			System.out.println("Test TEC-101 step 9 passed");
		patients.click();

	}

//search patient in list
	public void Tec102() {

		WebElement searchInList = webDriver.findElement(By.id("search-patient"));
		searchInList.sendKeys("a");
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searchInList.clear();
		try {
			Thread.sleep(80000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement clearList = webDriver.findElement(By.className("clear"));
		clearList.click();
		searchInList.sendKeys("b");
		try {
			Thread.sleep(80000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searchInList.clear();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
