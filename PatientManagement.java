package technician;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.lang3.test.*;
import org.apache.commons.codec.language.*;

/**
 * PatientManagement class run all patient management environment It has 6 tests
 * cases Each case has some steps
 * 
 * @author Esty Wolpo
 *
 */

public class PatientManagement {

	private static WebDriver webDriver;
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
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ----------------------------------------------------------------------
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

		PatientManagement.checkUrl("https://alpha.audyx.com/technician#/patients", webDriver.getCurrentUrl(),
				"Test TEC-101 step 1");
		// -----------------------------------------------------------------------------------------------------------------------------
		// step 2
		// click on first patient in list and enter to patient folder
		WebElement patientInList = webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > tbody > tr:nth-child(1) > td:nth-child(2)"));
		patientInList.click();
		// click on back icon in patient folder - should be moved to patient list
		WebElement back = webDriver.findElement(By.className("back-icon"));
		back.click();
		PatientManagement.checkUrl("https://alpha.audyx.com/technician#/patients", webDriver.getCurrentUrl(),
				"Test TEC-101 step 2");
		// -----------------------------------------------------------------------------------------------------------------------------
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
		// -----------------------------------------------------------------------------------------------------------------------------
		// step 4
		// sort by patient name
		WebElement searchButtom = webDriver.findElement(By.id("search-patient"));
		searchButtom.clear();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement patientSort = webDriver.findElement(
				By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/table/thead/tr/th[2]"));
		patientSort.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement patientSortdown1 = webDriver.findElement(By.className("react-bootstrap-table-sort-order"));
		patientSort.click();
		WebElement patientSortup1 = webDriver.findElement(By.className("dropup"));
		System.out.println("Test TEC-101 step 4 passed");
		// -----------------------------------------------------------------------------------------------------------------------------
		// step 5
		// go to 3rd page
		WebElement page3 = webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[2]/div[2]/ul/li[3]/a"));
		page3.click();
		System.out.println("Test TEC-101 step 5 passed");
		// -----------------------------------------------------------------------------------------------------------------------------
		// step6
		// sort by age
		WebElement ageSort = webDriver.findElement(
				By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/table/thead/tr/th[3]"));
		ageSort.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement patientSortdown2 = webDriver.findElement(By.className("react-bootstrap-table-sort-order"));
		ageSort.click();
		WebElement patientSortup2 = webDriver.findElement(By.className("dropup"));
		System.out.println("Test TEC-101 step 6 passed");

		// -----------------------------------------------------------------------------------------------------------------------------
		// step 7
		// sort by status
		WebElement statusSort = webDriver.findElement(
				By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/table/thead/tr/th[4]"));
		statusSort.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement patientSortdown3 = webDriver.findElement(By.className("react-bootstrap-table-sort-order"));
		statusSort.click();
		WebElement patientSortup3 = webDriver.findElement(By.className("dropup"));
		System.out.println("Test TEC-101 step 7 passed");

		// -----------------------------------------------------------------------------------------------------------------------------
		// step 8
		// sort by last activity
		WebElement activitySort = webDriver.findElement(
				By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/table/thead/tr/th[5]"));
		statusSort.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement patientSortdown4 = webDriver.findElement(By.className("react-bootstrap-table-sort-order"));
		statusSort.click();
		WebElement patientSortup4 = webDriver.findElement(By.className("dropup"));
		System.out.println("Test TEC-101 step 8 passed");

		// -----------------------------------------------------------------------------------------------------------------------------
		// step 9 -click another patient
		WebElement patientInList2 = webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > tbody > tr:nth-child(3) > td:nth-child(2) > div"));
		patientInList2.click();
		WebElement testProtocols = webDriver
				.findElement(By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/span[1]"));

		System.out.println("Test TEC-101 step 9 passed");
		patients.click();
	}

	// ===================================================================================================================================
	// search patient in list
	public void Tec102() {
		// step 1
		// print all patients in center
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement titleList1 = webDriver.findElement(By.className("list-title"));
		String patientTitleList1 = titleList1.getAttribute("innerHTML");
		System.out.println("There are " + patientTitleList1 + " patients in list");

		WebElement searchInList = webDriver.findElement(By.id("search-patient"));
		searchInList.sendKeys("a");
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement titleList2 = webDriver.findElement(By.className("list-title"));
		String patientTitleList2 = titleList2.getAttribute("innerHTML");
		System.out.println("There are " + patientTitleList2 + " patients in list");

		PatientManagement.checkDiffString(patientTitleList1, patientTitleList2, "Test TEC-102 step 1");
		// -----------------------------------------------------------------------------------------------------------------------------
		// step 2
		// search patient in list
		WebElement clearList = webDriver.findElement(By.className("clear"));
		clearList.click();
		searchInList.sendKeys("b");
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement titleList3 = webDriver.findElement(By.className("list-title"));
		String patientTitleList3 = titleList3.getAttribute("innerHTML");
		System.out.println("There are " + patientTitleList3 + " patients in list");
		PatientManagement.checkDiffString(patientTitleList2, patientTitleList3, "Test TEC-102 step 2");
		// go back to patients list
		webDriver.findElement(By.cssSelector(
				"#root > div > span > div.app > div.main-view > div > div.patient-bar > div > div.form-group > i"))
				.click();
	}

//========================================================================================================================================
	/*
	 * public static void checkRnadom3() { webDriver.findElement(By.xpath(
	 * "//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[1]/div/div[2]")).click();
	 * WebElement patientName = webDriver.findElement(By.id("firstname"));
	 * patientName.sendKeys("First" + " - " + "Patient" + " - " +
	 * RandomStringUtils.randomAlphabetic(3) + RandomStringUtils.randomNumeric(3));
	 * String a = patientName.getText(); System.out.println(a); }
	 * 
	 * public void Tec100() { PatientManagement.checkRnadom3(); }
	 */
	// ========================================================================================================================================
	// create new patient
	public void Tec103() {
		// step 1:
		PatientManagement.checkUrl("https://alpha.audyx.com/technician#/patients", webDriver.getCurrentUrl(),
				"Test TEC-103 step 1");
		// -----------------------------------------------------------------------------------------------------------------------------
		// step 2:
		// go to patient creation page
		webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[1]/div/div[2]")).click();
		PatientManagement.checkUrl("https://alpha.audyx.com/technician#/patient/create", webDriver.getCurrentUrl(),
				"Test TEC-103 step 2");
		// -----------------------------------------------------------------------------------------------------------------------------
		// step3:
		// fill in all details w/o gender
		WebElement firstName = webDriver.findElement(By.id("firstname"));
		PatientManagement.insertText(firstName, "Test");
		WebElement lastName = webDriver.findElement(By.id("name"));
		PatientManagement.insertText(lastName, "Automation");
		WebElement birthdate = webDriver.findElement(By.id("birthdate"));
		PatientManagement.insertText(birthdate, "09022012");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// select school and grade
		webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/form/div[2]/div[2]/div[1]/div/div"))
				.click();
		webDriver.findElement(By.xpath("//*[@id=\"1\"]/div")).click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/form/div[2]/div[2]/div[2]/div/div"))
				.click();
		webDriver.findElement(By.xpath("//*[@id=\"2\"]/div")).click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// save button WebElement
		WebElement saveNewPatient = webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/form/div[4]/div[2]"));
		PatientManagement.checkButtonClickable(saveNewPatient, "Test TEC-103 step 3");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// -----------------------------------------------------------------------------------------------------------------------------
		// step 4
		// choose gender but not fill in first name
		firstName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/form/div[1]/div[2]/ul/li[1]/label"))
				.click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		lastName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		lastName.sendKeys("Automation2");

		WebElement validMessage = webDriver.findElement(By.className("validation-message"));
		PatientManagement.checkValidMessage(validMessage, "Test TEC-103 step 4");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// -----------------------------------------------------------------------------------------------------------------------------
		// step 6
		// fill in all details and move to patient folder
		PatientManagement.insertText(firstName, "Test3");
		PatientManagement.insertText(lastName, "Automation3" + "-" + RandomStringUtils.randomNumeric(2));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PatientManagement.checkButtonClickable(saveNewPatient, "Test TEC-103 step 6.1");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saveNewPatient.click();
		
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement testProtocols = webDriver
				.findElement(By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/span[1]"));
		System.out.println("Test TEC-103 step 6.2 passed");
	}

//=====================================================================================================================================

	public static void checkUrl(String expectedUrl, String actualUrl, String currentStep) {
		if (expectedUrl.equals(actualUrl))
			System.out.println(currentStep + " passed");
		else
			System.out.println(currentStep + " failed");
	}

	public static void checkButtonClickable(WebElement button, String currentStep) {
		boolean enable1 = button.isEnabled();
		if (enable1)
			System.out.println(currentStep + " passed");
		else
			System.out.println(currentStep + " failed");
	}

	public static void checkValidMessage(WebElement validMessage, String currentStep) {
		boolean message = validMessage.isDisplayed();
		if (message)
			System.out.println(currentStep + " passed");
		else
			System.out.println(currentStep + " failed");
	}

	public static void insertText(WebElement textArea, String text) {
		// textArea.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		textArea.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		textArea.sendKeys(text);

	}

	public static void checkDiffString(String string1, String string2, String currentStep) {
		if (string1.equals(string2))
			System.out.println(currentStep + " failed");
		else
			System.out.println(currentStep + " passed");

	}

}
