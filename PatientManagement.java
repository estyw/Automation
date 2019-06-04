package expert;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

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
		WebElement sideBar = webDriver.findElement(By.className("navigator"));
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
		WebElement comment = webDriver.findElement(By.className("patient-note"));
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
		WebElement selectCurrentPatient = webDriver
				.findElement(By.xpath("//*[@id=\"patient-list-body\"]/div/div/table/tbody/tr[1]/td[2]"));
		selectCurrentPatient.click();
		Temp.tryCatch(3000);
		WebElement CurrentPatientInList = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-list-body\"]/div/div/table/tbody/tr[1]/td[1]/a"));
		String CurrentPatientInList1 = CurrentPatientInList.getText();

		Temp.tryCatch(3000);
		WebElement CurrentPatientInNav = webDriver
				.findElement(By.xpath(" //*[@id=\"audyx-main\"]/div/div/div/div[1]/div[3]/div[2]/div[2]/span[1]"));
		String CurrentPatientInNav1 = CurrentPatientInNav.getText();
		String[] mytext1 = CurrentPatientInNav1.split(" ");
		String CurrentPatientInNavFinal = mytext1[0] + " " + mytext1[1];
		System.out.println(CurrentPatientInNavFinal);
		Temp.checkEqualString(CurrentPatientInList1, CurrentPatientInNavFinal, "Test PAT-101 step 10");
	}

	public void PAT102() {
		WebElement searchPatient = webDriver.findElement(By.className("search-patient"));
		searchPatient.sendKeys("a");
		WebElement firstLine = webDriver
				.findElement(By.xpath("//*[@id=\"patient-list-body\"]/div/div/table/tbody/tr[1]"));
		if (firstLine.getText().contains("a"))
			Temp.tryCatch(2000);
		System.out.println("Test PAT-102 step 1 passed");

		searchPatient.clear();
		Temp.tryCatch(2000);
		searchPatient.sendKeys("c");
		WebElement firstLine2 = webDriver
				.findElement(By.xpath("//*[@id=\"patient-list-body\"]/div/div/table/tbody/tr[1]"));

		if (firstLine2.getText().contains("c"))
			System.out.println("Test PAT-102 step 2 passed");
		searchPatient.clear();
	}

	public void PAT201() {
		// view test preview when there isn't any test in preview
		WebElement filterActivity = webDriver.findElement(By.className("patient-last-test"));
		filterActivity.click();
		Temp.tryCatch(3000);
		System.out.println("Test PAT-201 step 1 passed");

		WebElement firstLineHover = webDriver
				.findElement(By.xpath("//*[@id=\"patient-list-body\"]/div/div/table/tbody/tr[1]/td[5]"));
		Actions act = new Actions(webDriver);
		act.moveToElement(firstLineHover).build().perform();
		Temp.tryCatch(3000);
		WebElement searchPreview = webDriver
				.findElement(By.xpath("//*[@id=\"patient-list-body\"]/div/div/table/tbody/tr[1]/td[6]/div/i"));
		searchPreview.click();
		Temp.tryCatch(3000);
		WebElement noTestPreview = webDriver.findElement(By.className("no-tests-box"));
		System.out.println("Test PAT-201 step 2 passed");

		// see the test preview of patient with many tests
		WebElement searchPatient = webDriver.findElement(By.className("search-patient"));
		searchPatient.sendKeys("Test Release3");

		WebElement firstLineHover2 = webDriver
				.findElement(By.xpath("//*[@id=\"patient-list-body\"]/div/div/table/tbody/tr/td[6]"));
		Actions act2 = new Actions(webDriver);
		act.moveToElement(firstLineHover2).build().perform();
		Temp.tryCatch(3000);
		WebElement searchPreview2 = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-list-body\"]/div/div/table/tbody/tr/td[6]/div/i"));
		searchPreview2.click();
		Temp.tryCatch(3000);
		WebElement tonalPreview = webDriver
				.findElement(By.xpath("//*[@id=\"patient-overview\"]/div/div[2]/div/div[1]/div/h3"));
		System.out.println("Test PAT-201 step 3 passed");

		WebElement speechPreview = webDriver
				.findElement(By.xpath("//*[@id=\"patient-overview\"]/div/div[2]/div/div[2]/div/h3"));
		Actions act3 = new Actions(webDriver);
		act3.moveToElement(speechPreview).build().perform();
		Temp.tryCatch(6000);
		System.out.println("Test PAT-201 step 4 passed");

		WebElement sinPreview = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-overview\"]/div/div[2]/div/div[3]/div/h3"));
		Actions act4 = new Actions(webDriver);
		act4.moveToElement(sinPreview).build().perform();
		Temp.tryCatch(4000);
		System.out.println("Test PAT-201 step 5 passed");

		WebElement sinGraphPreview = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-overview\"]/div/div[2]/div/div[2]/div/div"));
		Actions act5 = new Actions(webDriver);
		act4.moveToElement(sinGraphPreview).click().perform();
		Temp.tryCatch(6000);
		// sinGraphPreview.click();
		// Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.audyx.com/#/patientFolder/test/speech/13813446", webDriver.getCurrentUrl(),
				"Test PAT-201 step 6");
	}

	public void PAT301() {
		// go to patients page
		WebElement audyxIcon = webDriver
				.findElement(By.xpath(" //*[@id=\"audyx-main\"]/div/div/div/div[1]/div[1]/div[2]/img"));
		Temp.configScreen(audyxIcon, "Test PAT-301 step 1");

		// click + to add new patient
		WebElement addPatient = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-list-body\"]/div/div/div[2]/div[2]/button"));
		addPatient.click();
		Temp.tryCatch(3000);
		WebElement patientPanel = webDriver.findElement(By.className("panel-body"));
		System.out.println("Test PAT-301 step 2 passed");

		// fill in first name
		WebElement firstName = webDriver
				.findElement(By.xpath(" //*[@id=\"create-patient-panel\"]/div/form/div[1]/div[1]/div/input"));
		firstName.sendKeys("Test");
		Temp.tryCatch(2000);
		WebElement submitNewPatient = webDriver
				.findElement(By.xpath("//*[@id=\"create-patient-panel\"]/div/form/div[4]/button"));
		Temp.checkButtonNotClickable(submitNewPatient, "Test PAT-301 step 3");

		// fill in last name
		WebElement lastName = webDriver
				.findElement(By.xpath(" //*[@id=\"create-patient-panel\"]/div/form/div[1]/div[2]/div/input"));
		lastName.sendKeys("Patient");
		Temp.tryCatch(2000);
		// WebElement submitNewPatient =
		// webDriver.findElement(By.xpath("//*[@id=\"create-patient-panel\"]/div/form/div[4]/button"));
		Temp.checkButtonNotClickable(submitNewPatient, "Test PAT-301 step 4");

		// fill in too old birthdate - smaller than 1900

		WebElement birthdate = webDriver
				.findElement(By.xpath(" //*[@id=\"create-patient-panel\"]/div/form/div[2]/div[1]/div/div/input"));
		birthdate.sendKeys("06061899");
		Temp.tryCatch(2000);
		// check if calendar icon becomes red

		WebElement calendarColor = webDriver
				.findElement(By.xpath("//*[@id=\"create-patient-panel\"]/div/form/div[2]/div[1]/div/div/span"));
		Temp.printElementColor(calendarColor, "#a94442", "Test PAT-301 step 5");
		// fill in birthdate - greater than 2030
		WebElement birthdate2 = webDriver
				.findElement(By.xpath(" //*[@id=\"create-patient-panel\"]/div/form/div[2]/div[1]/div/div/input"));
		birthdate2.clear();
		Temp.tryCatch(2000);
		birthdate2.sendKeys("06062030");
		Temp.tryCatch(2000);
		// check if calendar icon becomes red
		WebElement calendarColor2 = webDriver
				.findElement(By.xpath("//*[@id=\"create-patient-panel\"]/div/form/div[2]/div[1]/div/div/span"));
		Temp.printElementColor(calendarColor, "#f2dede", "Test PAT-301 step 6");

		// Fill in the correct birthdate and check if the add button is enabled
		WebElement birthdate3 = webDriver
				.findElement(By.xpath(" //*[@id=\"create-patient-panel\"]/div/form/div[2]/div[1]/div/div/input"));
		birthdate3.clear();
		Temp.tryCatch(2000);
		birthdate3.sendKeys("06062014");
		Temp.tryCatch(4000);

		Temp.checkButtonClickable(submitNewPatient, "Test PAT-301 step 7");

		// click on cancel
		WebElement cancelNewPatient = webDriver.findElement(By.className(" cancel-patient-form"));
		cancelNewPatient.click();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.audyx.com/#/patients", webDriver.getCurrentUrl(), "Test PAT-301 step 8");

		// add again new patient and insert phone number
		addPatient.click();
		Temp.tryCatch(3000);
		WebElement firstName2 = webDriver
				.findElement(By.xpath(" //*[@id=\"create-patient-panel\"]/div/form/div[1]/div[1]/div/input"));
		firstName2.sendKeys("Patient");
		Temp.tryCatch(2000);
		WebElement lastName2 = webDriver
				.findElement(By.xpath(" //*[@id=\"create-patient-panel\"]/div/form/div[1]/div[2]/div/input"));
		lastName2.sendKeys("Automation");
		Temp.tryCatch(2000);
		WebElement birthdate4 = webDriver
				.findElement(By.xpath("//*[@id=\"create-patient-panel\"]/div/form/div[2]/div[1]/div/div/input"));
		birthdate4.sendKeys("06052013");
		Temp.tryCatch(2000);
		// insert phone number
		webDriver.findElement(By.xpath("//*[@id=\"create-patient-panel\"]/div/form/div[2]/div[2]/div/div/input"))
				.sendKeys("05044444");
		Temp.checkButtonClickable(addPatient, "Test PAT-301 step 9");

		// enter invalid email address
		webDriver.findElement(By.xpath("//*[@id=\"create-patient-panel\"]/div/form/div[3]/div[2]/div/div/input"))
				.sendKeys("a");
		WebElement emailIcon = webDriver
				.findElement(By.xpath(" //*[@id=\"create-patient-panel\"]/div/form/div[3]/div[2]/div/div/span"));
		Temp.printElementColor(emailIcon, "#f2dede", "Test PAT-301 step 10");

		webDriver.findElement(By.xpath("//*[@id=\"create-patient-panel\"]/div/form/div[3]/div[2]/div/div/input"))
				.sendKeys("a@audyx.com");

		Temp.checkButtonClickable(addPatient, "Test PAT-301 step 11");
		WebElement addPatient2 = webDriver
				.findElement(By.xpath("//*[@id=\"create-patient-panel\"]/div/form/div[4]/button"));
		addPatient2.click();
		Temp.tryCatch(5000);
		// check the new patient added by present it in nav bar
		WebElement CurrentPatientInNav = webDriver
				.findElement(By.xpath(" //*[@id=\"audyx-main\"]/div/div/div/div[1]/div[3]/div[2]/div[2]/span[1]"));
		String CurrentPatientInNav1 = CurrentPatientInNav.getText();
		String[] mytext1 = CurrentPatientInNav1.split(" ");
		String CurrentPatientInNavFinal = mytext1[0] + " " + mytext1[1];
		System.out.println(CurrentPatientInNavFinal);
		Temp.checkEqualString("Patient Automation", CurrentPatientInNavFinal, "Test PAT-301 step 12");
	}

	public void PAT401PatientSearch() {
		WebElement audyxIcon = webDriver.findElement(By.xpath("//*[@id=\"audyx-main\"]/div/div/div/div[1]/div[1]/div[2]/img"));
		Temp.configScreen(audyxIcon, "Test PAT-401 step 0");
		WebElement searchIcon = webDriver.findElement(By.xpath("//*[@id=\"patient-autocomplete-toggle\"]"));
		searchIcon.click();
		Temp.tryCatch(3000);
		WebElement searchArea = webDriver.findElement(By.className("search-patient-input"));
		
		searchArea.sendKeys(arg0);
		
	}
}
