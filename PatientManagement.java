package technician;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * PatientManagement class run all patient management environment It has 6 tests
 * cases Each case has some steps
 * 
 * @author Esty Wolpo
 *
 */

public class PatientManagement {

	private WebDriver webDriver;
	//private WebElement patients;
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
			System.out.println("Test TEC-101 step 1 fialed");

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
			System.out.println("Test TEC-101 step 2 fialed");

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
			System.out.println("Test TEC-101 step 9 failed");
		patients.click();
	}

//search patient in list
	public void Tec102() {

		// print all patients in center
		WebElement titleList = webDriver.findElement(By.className("list-title"));
		String patientTitleList = titleList.getAttribute("innerHTML");
		System.out.println(titleList);
		System.out.println("There are " + patientTitleList + " patients in list");
		WebElement searchInList = webDriver.findElement(By.id("search-patient"));
		searchInList.sendKeys("a");
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement titleList1 = webDriver.findElement(By.className("list-title"));
		String patientTitleList1 = titleList1.getAttribute("innerHTML");

		System.out.println("There are " + patientTitleList1 + " patients in list");
		if (patientTitleList.equals(patientTitleList1))
			System.out.println("Test TEC-102 step 1 failed");
		else
			System.out.println("Test TEC-102 step 1 passed");

		WebElement clearList = webDriver.findElement(By.className("clear"));
		clearList.click();
		searchInList.sendKeys("b");
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement titleList2 = webDriver.findElement(By.className("list-title"));
		String patientTitleList2 = titleList2.getAttribute("innerHTML");
		// System.out.println(titleList2);
		System.out.println("There are " + patientTitleList2 + " patients in list");
		if (patientTitleList1.equals(patientTitleList2))
			System.out.println("Test TEC-102 step 2 failed");
		else
			System.out.println("Test TEC-102 step 2 passed");
		webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > div.patient-bar > div > div.form-group > i"))
				.click();
	}

	// create new patient
	public void Tec103() {
		// step 1:
		String expectedURL1 = "https://alpha.audyx.com/technician#/patients";
		String actualURL4 = webDriver.getCurrentUrl();
		if (expectedURL1.equals(actualURL4))
			System.out.println("Test TEC-103 step 1 passed");
		else
			System.out.println("Test TEC-103 step 1 fialed");
		// step 2:
		webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[1]/div/div[2]")).click();
		String expectedURL2 = "https://alpha.audyx.com/technician#/patient/create";
		String actualURL5 = webDriver.getCurrentUrl();
		if (expectedURL2.equals(actualURL5))
			System.out.println("Test TEC-103 step 2 passed");
		else
			System.out.println("Test TEC-103 step 2 fialed");
		// step3:
		WebElement firstName = webDriver.findElement(By.id("firstname"));
		firstName.sendKeys("First");
		WebElement lastName = webDriver.findElement(By.id("name"));
		lastName.sendKeys("Test");
		WebElement birthdate = webDriver.findElement(By.id("birthdate"));
		birthdate.sendKeys("09022012");
		// select school and grade
		webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/form/div[2]/div[2]/div[1]/div/div"))
				.click();
		webDriver.findElement(By.xpath("//*[@id=\"1\"]/div")).click();

		webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/form/div[2]/div[2]/div[2]/div/div"))
				.click();
		webDriver.findElement(By.xpath("//*[@id=\"2\"]/div")).click();

		// save button WebElement
		WebElement saveNewPatient = webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/form/div[4]/div[2]"));
		boolean enable1 = saveNewPatient.isEnabled();
		if (enable1)
			System.out.println("Test TEC-103 step 3 passed");
		else
			System.out.println("Test TEC-103 step 3 failed");
		// step4: choose gender but not fill in first name
		webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/form/div[1]/div[2]/ul/li[1]/label"))
				.click();
		firstName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		lastName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		lastName.sendKeys("Test2");

		WebElement validMessage = webDriver.findElement(By.className("validation-message"));
		boolean message = validMessage.isDisplayed();
		if (message)
			System.out.println("Test TEC-103 step 4 passed");
		else
			System.out.println("Test TEC-103 step 4 failed");
		// step 6:
		firstName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		firstName.sendKeys("Third");
		lastName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		lastName.sendKeys("Test3");
		boolean enable2 = saveNewPatient.isEnabled();
				
		if (enable2) 
			System.out.println("Test TEC-103 step 6.1 passed");
		else
			System.out.println("Test TEC-103 step 6.1 failed");
		saveNewPatient.click();
		
		String expectedURL3 = "https://alpha.audyx.com/technician#/patient/*";
		String actualURL6 = webDriver.getCurrentUrl();
		if (expectedURL3.equals(actualURL6))
			System.out.println("Test TEC-103 step 6.2 passed");
		else
			System.out.println("Test TEC-103 step 6.2 failed");
			

	}
}
