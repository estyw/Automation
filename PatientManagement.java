package technician;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
		WebElement patientInList = webDriver.findElement(
				By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/table/tbody/tr[1]/td[2]/div"));
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

	// ========================================================================================================================================
	// create new patient
	public void Tec103() {
		// step 1:
		// make sure you are in patient list screen
		PatientManagement.checkUrl("https://alpha.audyx.com/technician#/patients", webDriver.getCurrentUrl(),
				"Test TEC-103 step 1");
		// -----------------------------------------------------------------------------------------------------------------------------
		// step 2:
		// go to patient creation page by clicking the + add new patient button
		webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[1]/div/div[2]")).click();
		PatientManagement.checkUrl("https://alpha.audyx.com/technician#/patient/create", webDriver.getCurrentUrl(),
				"Test TEC-103 step 2");
		// -----------------------------------------------------------------------------------------------------------------------------
		// Step 3:
		// cancel adding new patient
		webDriver.findElement(By.className("cancel")).click();
		PatientManagement.checkUrl("https://alpha.audyx.com/technician#/patients", webDriver.getCurrentUrl(),
				"Test TEC-103 step 3");
		// -----------------------------------------------------------------------------------------------------------------------------
		// Step 4:
		// check if modal of leaving activity appears when cancel adding new patient
		// click on add new patient
		webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[1]/div/div[2]")).click();
		// click on gender
		webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/form/div[1]/div[2]/ul/li[1]/label"))
				.click();
		webDriver.findElement(By.className("cancel")).click();

		WebElement leaveActivity = webDriver.findElement(By.className("modal-content "));
		if (leaveActivity.isDisplayed())
			System.out.println("Test TEC-103 step 4 passed");
		// -----------------------------------------------------------------------------------------------------------------------------
		// Step 5:
		// click on leaving activity on modal when cancel adding new patient
		webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/div[3]/div/div[1] ")).click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PatientManagement.checkUrl("https://alpha.audyx.com/technician#/patients", webDriver.getCurrentUrl(),
				"Test TEC-103 step 5");
		// -----------------------------------------------------------------------------------------------------------------------------
		// Step 6:
		// Click on add new patient, fill some details then click cancel,
		// in leaving activity modal click on “Continue the activity”
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[1]/div/div[2]")).click();

		WebElement firstName = webDriver.findElement(By.id("firstname"));
		PatientManagement.insertText(firstName, "Test");
		webDriver.findElement(By.className("cancel")).click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		webDriver.findElement(By.xpath(" //*[@id=\"root\"]/div/div/div[2]/div/div/div[3]/div/div[2]")).click();
		PatientManagement.checkUrl("https://alpha.audyx.com/technician#/patient/create", webDriver.getCurrentUrl(),
				"Test TEC-103 step 6");
		// -----------------------------------------------------------------------------------------------------------------------------
		// step7:
		// fill in all details w/o gender

		WebElement lastName = webDriver.findElement(By.id("name"));
		PatientManagement.insertText(lastName, "Automation");

		try {
			Thread.sleep(2000);
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
			Thread.sleep(2000);
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
		PatientManagement.checkButtonClickable(saveNewPatient, "Test TEC-103 step 7");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// -----------------------------------------------------------------------------------------------------------------------------
		// step 8
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
		PatientManagement.checkValidMessage(validMessage, "Test TEC-103 step 8");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// -----------------------------------------------------------------------------------------------------------------------------
		// step 9
		// enter date smaller than 1900
		WebElement birthdate = webDriver.findElement(By.id("birthdate"));
		PatientManagement.insertText(birthdate, "09021899");
		WebElement validMessage2 = webDriver.findElement(
				By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/form/div[1]/div[2]/div[2]/label[2]"));
		if (validMessage2.isDisplayed())
			System.out.println("Test TEC-103 step 9 passed");

		// -----------------------------------------------------------------------------------------------------------------------------

		// step 10
		// enter future date
		PatientManagement.insertText(birthdate, "09022030");
		if (validMessage2.isDisplayed())
			System.out.println("Test TEC-103 step 10 passed");

		// -----------------------------------------------------------------------------------------------------------------------------
		// step 11
		// fill in all details and move to patient folder
		PatientManagement.insertText(firstName, "Test3");
		PatientManagement.insertText(lastName, "Automation3" + "-" + RandomStringUtils.randomNumeric(2));
		PatientManagement.insertText(birthdate, "09022000");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PatientManagement.checkButtonClickable(saveNewPatient, "Test TEC-103 step 11.1");
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
		System.out.println("Test TEC-103 step 11.2 passed");
	}

	// =====================================================================================================================================
	public void Tec104() {
		// step 1:
		// view all elements in patient page screen
		WebElement patientInList = webDriver.findElement(By.cssSelector(
				" #root > div > span > div.app > div.main-view > div > div.patient-table > div.react-bootstrap-table > table > tbody > tr:nth-child(1) > td:nth-child(2) > div"));
		patientInList.click();

		WebElement protoclLink = webDriver.findElement(By.cssSelector(
				"  #root > div > span > div.app > div.main-view > div > div.patient-navigation > div.tabs-navbar > span.item.active"));
		WebElement resultsLink = webDriver.findElement(By.cssSelector(
				" #root > div > span > div.app > div.main-view > div > div.patient-navigation > div.tabs-navbar > span:nth-child(2)"));
		WebElement infoLink = webDriver.findElement(By.cssSelector(
				" #root > div > span > div.app > div.main-view > div > div.patient-navigation > div.tabs-navbar > span:nth-child(3)"));
		WebElement name = webDriver.findElement(By.className("full-name"));
		WebElement age = webDriver.findElement(By.className("age"));
		WebElement status = webDriver.findElement(By.className("status"));

		WebElement rightEar = webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[1]/div[2]"));
		WebElement leftEar = webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[1]/div[3]"));
		System.out.println("Test TEC-104 step 1 passed");
		// -----------------------------------------------------------------------------------------------------------------------------
		// step 2:
		// make sure you are moved to information tab when clicking on patient name
		name.click();
		WebElement informationTitle = webDriver.findElement(
				By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/form/div[1]/div[1]/div[1]"));
		System.out.println("Test TEC-104 step 2 passed");
		// -----------------------------------------------------------------------------------------------------------------------------
		// step 3:
		// status list appears when clicking on status
		status.click();
		WebElement listStatus = webDriver.findElement(By.className("options"));
		System.out.println("Test TEC-104 step 3 passed");
		// -----------------------------------------------------------------------------------------------------------------------------
		// step 4:
		// changing patient status
		// WebElement status1 = webDriver.findElement(By.className("status"));
		// status1.click();
		WebElement waitingStatus = webDriver.findElement(By.className("WAITING"));
		waitingStatus.click();
		WebElement status2 = webDriver.findElement(By.className("WAITING"));
		System.out.println("Test TEC-104 step 4 passed");

		WebElement patients = webDriver
				.findElement(By.cssSelector("#root > div > span > div.app > div.sidebar > a > span"));
		patients.click();
	}

	// =====================================================================================================================================
	public void Tec105() {
		// step 1:
		// perform tonal test and change status to normal hearing
		PatientManagement.createNewPatient();
		WebElement greyOutline = webDriver
				.findElement(By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[1]/div[2]/div[2]/div[1]"));
		System.out.println("Test TEC-106 step 1 passed");
		PatientManagement.onlyTonal();
		PatientManagement.tonalBinNormal();

		WebElement greenOutline = webDriver
				.findElement(By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[1]/div[2]/div[2]/div[2]"));
		System.out.println("Test TEC-106 step 2 passed");
		// step 2:
		// perform tonal test and change status to candidate
		PatientManagement.createNewPatient();
		PatientManagement.onlyTonal();
		PatientManagement.tonalBinCandidate();

		WebElement orangeOutline = webDriver.findElement(By.className("moderate"));
		System.out.println("Test TEC-106 step 3 passed");
	}

	// =====================================================================================================================================
	public void Tec106() {
		// only for step 4
		// PatientManagement.createNewPatient();
		WebElement patientsList = webDriver.findElement(By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[2]/a/span"));
		patientsList.click();

		WebElement patient1 = webDriver.findElement(
				By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/table/tbody/tr[7]/td[2]/div"));
		patient1.click();
		PatientManagement.onlyTonal();
		PatientManagement.tonalBinAbove70();
		WebElement testProtocols = webDriver
				.findElement(By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/span[1]"));
		testProtocols.click();
		
		WebElement protocolStatus = webDriver
				.findElement(By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[2]/div/div[10]/div[1]/span"));
		WebElement protocolTime = webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[2]/div/div[10]/div[2]/div"));
		System.out.println("Test TEC-202 step 7 passed");
	}
	

	// =====================================================================================================================================
	public void Tec202() {
		//go to patient list then click on first patient
		WebElement patientsList = webDriver.findElement(By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[2]/a/span"));
		patientsList.click();
		PatientManagement.tryCatch();
		WebElement patientFirst = webDriver.findElement(By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[1]/table/tbody/tr[1]/td[2]/div"));
		patientFirst.click();
		PatientManagement.tryCatch();
		WebElement protocol = webDriver.findElement(By.className("patient-protocol"));
		//protocol.click();
		PatientManagement.tryCatch();
		System.out.println("Test TEC-202 step 1 passed");
		
		WebElement statusProtocol = webDriver.findElement(By.className("protocol-status"));
		WebElement nameProtocol = webDriver.findElement(By.className("protocol-name"));
		WebElement timeProtocol = webDriver.findElement(By.className("estimated-duration"));
		
		String status = statusProtocol.getAttribute("innerHTML");
		String estimate =  timeProtocol.getAttribute("innerHTML");
		if (status.equals("Not done") && (estimate.contains("Estimate")))
			System.out.println("Test TEC-202 step 2 passed");	
			
	}
	// =====================================================================================================================================

	public static void tonalBinNormal() {
		for (int i = 0; i < 2; i++) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement play = webDriver.findElement(
					By.xpath("  //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div/div/span/div/div[4]/i[2]"));
			WebElement yes = webDriver.findElement(
					By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div/div/span/div/div[4]/i[3]"));
			WebElement no = webDriver.findElement(
					By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div/div/span/div/div[4]/i[1]"));

			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// first play of right & left ear
			play.click();
			PatientManagement.tryCatch();
			// configure the 1st dB
			for (int j = 0; j < 4; j++) {
				PatientManagement.tryCatch();
				yes.click();
				PatientManagement.tryCatch();
			}
			no.click();
			PatientManagement.tryCatch();
			yes.click();
			PatientManagement.tryCatch();
			no.click();
			PatientManagement.tryCatch();
			no.click();
			PatientManagement.tryCatch();
			yes.click();

			for (int j = 0; j < 3; j++) {
				// configure the 2nd dB
				PatientManagement.tryCatch();
				yes.click();
				PatientManagement.tryCatch();
				no.click();
				PatientManagement.tryCatch();
				yes.click();
				PatientManagement.tryCatch();
				no.click();
				PatientManagement.tryCatch();
				no.click();
				PatientManagement.tryCatch();
				yes.click();
			}

		}
		// check if status is changed to normal hearing
		PatientManagement.tryCatch();
		WebElement newstatus = webDriver.findElement(By.className("NORMAL"));
		System.out.println("Test TEC-105 step 1 passed");
	}

//-------------------------------------------------------------
	public static void tonalBinAbove70() {
		for (int i = 0; i < 2; i++) {
			WebElement play = webDriver.findElement(
					By.xpath("  //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div/div/span/div/div[4]/i[2]"));
			WebElement yes = webDriver.findElement(
					By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div/div/span/div/div[4]/i[3]"));
			WebElement no = webDriver.findElement(
					By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div/div/span/div/div[4]/i[1]"));

			// first play of right & left ear
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			play.click();
			PatientManagement.tryCatch();
			// configure the 1st dB

			for (int j = 0; j < 4; j++) {
				no.click();
				PatientManagement.tryCatch();
			}
			PatientManagement.tryCatch();
			yes.click();
			PatientManagement.tryCatch();
			no.click();
			PatientManagement.tryCatch();
			no.click();
			PatientManagement.tryCatch();
			yes.click();
			PatientManagement.tryCatch();
			no.click();
			PatientManagement.tryCatch();
			no.click();
			PatientManagement.tryCatch();
			yes.click();

			// configure the 2nd-4th dB
			for (int j = 0; j < 9; j++) {
				PatientManagement.tryCatch();
				no.click();
				PatientManagement.tryCatch();
				no.click();
				PatientManagement.tryCatch();
				yes.click();
			}
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// check if hearing loss is changed to red
		PatientManagement.tryCatch();
		WebElement redOutline = webDriver.findElement(By.className("severe"));
		System.out.println("Test TEC-106 step 4 passed");
	}

//-------------------------------------------------------------

	public static void tonalBinCandidate() {
		for (int i = 0; i < 2; i++) {
			// first play of right & left ear
			WebElement play = webDriver.findElement(
					By.xpath("  //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div/div/span/div/div[4]/i[2]"));

			WebElement yes = webDriver.findElement(
					By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div/div/span/div/div[4]/i[3]"));
			WebElement no = webDriver.findElement(
					By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div/div/span/div/div[4]/i[1]"));
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			play.click();
			PatientManagement.tryCatch();

			no.click();
			PatientManagement.tryCatch();
			no.click();
			PatientManagement.tryCatch();
			yes.click();
			PatientManagement.tryCatch();
			no.click();
			PatientManagement.tryCatch();
			yes.click();
			PatientManagement.tryCatch();
			no.click();
			PatientManagement.tryCatch();
			no.click();
			PatientManagement.tryCatch();
			yes.click();

			// configure the 2nd dB
			PatientManagement.tryCatch();
			yes.click();
			PatientManagement.tryCatch();
			yes.click();
			PatientManagement.tryCatch();
			no.click();
			PatientManagement.tryCatch();
			yes.click();
			PatientManagement.tryCatch();
			no.click();
			PatientManagement.tryCatch();
			no.click();
			PatientManagement.tryCatch();
			yes.click();

			// configure the 3rd & 4th dB
			for (int j = 0; j < 2; j++) {
				PatientManagement.tryCatch();
				yes.click();
				PatientManagement.tryCatch();
				no.click();
				PatientManagement.tryCatch();
				no.click();
				PatientManagement.tryCatch();
				yes.click();
				PatientManagement.tryCatch();
				no.click();
				PatientManagement.tryCatch();
				no.click();
				PatientManagement.tryCatch();
				yes.click();
			}
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// check if status is changed to candidate
		PatientManagement.tryCatch();
		WebElement newstatus = webDriver.findElement(By.className("CANDIDATE"));
		System.out.println("Test TEC-105 step 2 passed");
	}

//-------------------------------------------------
	public static void tryCatch() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void createNewPatient() {

		WebElement patientPage = webDriver.findElement(By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[2]/a/span"));
		patientPage.click();
		// go to patient creation page by clicking the + add new patient button
		webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[1]/div/div[2]")).click();
//select gender
		webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/form/div[1]/div[2]/ul/li[1]/label"))
				.click();
		WebElement firstName = webDriver.findElement(By.id("firstname"));
		PatientManagement.insertText(firstName, "New");

		WebElement lastName = webDriver.findElement(By.id("name"));
		// PatientManagement.insertText(lastName, "Patient");

		PatientManagement.insertText(lastName, "Patient -" + RandomStringUtils.randomNumeric(2));

		WebElement birthdate = webDriver.findElement(By.id("birthdate"));
		PatientManagement.insertText(birthdate, "05052010");
		// select school and class
		webDriver
				.findElement(By.xpath("//*[@id=\"root\"]/div/span/div[1]/div[3]/div/form/div[2]/div[2]/div[1]/div/div"))
				.click();
		webDriver.findElement(By.xpath("//*[@id=\"1\"]/div")).click();
		try {
			Thread.sleep(2000);
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
		saveNewPatient.click();
	}

	// -------------------------------------
	// go to only tonal test
	public static void onlyTonal() {

		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement onlyTonal = webDriver.findElement(
				By.xpath(" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[2]/div/div[10]/div[1]/div"));

		onlyTonal.click();
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement startModal = webDriver.findElement(By.xpath(
				" //*[@id=\"root\"]/div/span/div[1]/div[3]/div/div[2]/div[2]/div[2]/div[2]/div/div/div[2]/div/div"));
		startModal.click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
