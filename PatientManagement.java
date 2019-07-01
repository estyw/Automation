package expert;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
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

	public void PAT101PatientList() {
		// make sure you are in patient list page when clicking on Audyx icon
		WebElement audyxIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		audyxIcon.click();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.audyx.com/#/patients", webDriver.getCurrentUrl(), "Test PAT-101 step 1");
		// navigate to side menu and enter to patient list
		WebElement sideMenu = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .navigator"));
		sideMenu.click();
		Temp.tryCatch(6000);
		WebElement patientListIcon = webDriver.findElement(By.cssSelector("#menu-side :nth-child(1) .patient"));
		patientListIcon.click();
		Temp.tryCatch(3000);
		// hover back to main screen
		WebElement mainPanel = webDriver.findElement(By.cssSelector("audyx-main"));
		Actions act = new Actions(webDriver);
		act.moveToElement(mainPanel).perform();
		Temp.tryCatch(5000);
		Temp.checkUrl("https://alpha.audyx.com/#/patients", webDriver.getCurrentUrl(), "Test PAT-101 step 2");

		// view all elements in patient list page
		WebElement navBar = webDriver.findElement(By.className("menu-bar"));
		WebElement sideBar = webDriver.findElement(By.className("navigator"));
		WebElement searchArea = webDriver.findElement(By.className("search-patient"));
		WebElement addPatient = webDriver
				.findElement(By.cssSelector("#patient-list-body .row.patients-pagination .col-lg-2 .fa-plus"));
		WebElement PatientList = webDriver.findElement(By.cssSelector("#patient-list-body .table"));
		WebElement activityFeed = webDriver.findElement(By.id("activity-feed"));
		System.out.println("Test PAT-101 step 3 passed");

		// view all elements in line of patient list
		WebElement patientName = webDriver
				.findElement(By.cssSelector("#patient-list-body .table .patient-entry.warning .patient-fullname"));
		WebElement patientAge = webDriver.findElement(By.className("patient-age-birthdate"));
		WebElement patientStatus = webDriver
				.findElement(By.cssSelector("#patient-list-body .table .patient-entry.warning :nth-child(3)"));
		WebElement patientActivity = webDriver
				.findElement(By.cssSelector("#patient-list-body .table .patient-entry.warning .last-test"));
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

		// compare the name in navbar is as in list
		WebElement selectCurrentPatient = webDriver
				.findElement(By.cssSelector("#patient-list-body .table tr:nth-child(2)"));
		selectCurrentPatient.click();
		Temp.tryCatch(3000);
		WebElement CurrentPatientInList = webDriver
				.findElement(By.cssSelector(" patient-list-body .table tr:nth-child(2) td.patient-fullname"));
		String CurrentPatientInList1 = CurrentPatientInList.getText();
		Temp.tryCatch(3000);
		WebElement CurrentPatientInNav = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text .ad-name"));
		// String CurrentPatientInNav1 = CurrentPatientInNav.getText();
		String nameFinal = Temp.patientNameFromString(CurrentPatientInNav);
		Temp.checkEqualString(CurrentPatientInList1, nameFinal, "Test PAT-101 step 10 & Test PAT-501 step 1");
	}

	public void PAT102SearchInList() {
		// see list appear according to string that are typed
		WebElement searchPatient = webDriver.findElement(By.className("search-patient"));
		searchPatient.sendKeys("a");
		WebElement firstLine = webDriver
				.findElement(By.cssSelector("#patient-list-body .table  tr.patient-entry.warning"));
		Temp.checkContainString(firstLine.getText(), "a", "Test PAT-102 step 1");
		searchPatient.clear();
		Temp.tryCatch(2000);
		searchPatient.sendKeys("s");
		WebElement firstLine2 = webDriver
				.findElement(By.cssSelector("#patient-list-body .table  tr.patient-entry.warning"));
		Temp.checkContainString(firstLine2.getText(), "s", "Test PAT-102 step 2");
		searchPatient.clear();
	}

	public void PAT201TestsPreview() {
		// view test preview when there isn't any test in preview
		// filter accordint to last activity
		WebElement filterActivity = webDriver.findElement(By.className("patient-last-test"));
		filterActivity.click();
		Temp.tryCatch(3000);
		System.out.println("Test PAT-201 step 1 passed");

		// view the patient
		WebElement firstLineHover = webDriver
				.findElement(By.cssSelector("#patient-list-body .table tr.patient-entry.warning td:nth-child(5)"));
		Actions act = new Actions(webDriver);
		act.moveToElement(firstLineHover).build().perform();
		Temp.tryCatch(3000);
		WebElement searchPreview = webDriver
				.findElement(By.cssSelector("#patient-list-body .table tr.patient-entry.warning td:nth-child(6)"));
		searchPreview.click();
		Temp.tryCatch(6000);
		WebElement noTestPreview = webDriver.findElement(By.className("no-tests-box"));
		System.out.println("Test PAT-201 step 2 passed");

		// see the test preview of patient with tonal test
		WebElement searchPatient = webDriver.findElement(By.className("search-patient"));
		searchPatient.sendKeys("Release3");
		// choose the patient details
		WebElement firstLineHover2 = webDriver
				.findElement(By.cssSelector("#patient-list-body .table tr.patient-entry.warning td:nth-child(5)"));
		Actions act2 = new Actions(webDriver);
		act.moveToElement(firstLineHover2).build().perform();
		Temp.tryCatch(3000);
		WebElement searchPreview2 = webDriver
				.findElement(By.cssSelector(" #patient-list-body .table tr.patient-entry.warning td:nth-child(6)"));
		searchPreview2.click();
		Temp.tryCatch(3000);
		WebElement tonalPreview = webDriver
				.findElement(By.cssSelector("#patient-overview :nth-child(2) .panel-body.patient-list.tonal"));
		System.out.println("Test PAT-201 step 3 passed");

		// see the test preview of patient with speech test
		WebElement speechPreview = webDriver
				.findElement(By.cssSelector("#patient-overview :nth-child(2) :nth-child(2) h3"));
		Actions act3 = new Actions(webDriver);
		act3.moveToElement(speechPreview).build().perform();
		Temp.tryCatch(6000);
		System.out.println("Test PAT-201 step 4 passed");

		// see the test preview of patient with SIN test
		WebElement sinPreview = webDriver
				.findElement(By.cssSelector("#patient-overview :nth-child(2) :nth-child(3) h3"));
		Actions act4 = new Actions(webDriver);
		act4.moveToElement(sinPreview).build().perform();
		Temp.tryCatch(4000);
		System.out.println("Test PAT-201 step 5 passed");

		// click on graph in test preview - check you are moved to patient folder
		WebElement sinGraphPreview = webDriver.findElement(By.cssSelector(
				" #patient-overview .panel-body .col-lg-12 .vocal .row .patient-list-group-panel .row .col-xs-12 .type-1"));
		Actions act5 = new Actions(webDriver);
		act5.moveToElement(sinGraphPreview).click().perform();
		Temp.tryCatch(6000);
		Temp.checkContainString(webDriver.getCurrentUrl(), "https://alpha.audyx.com/#/patientFolder/test/speech",
				"Test PAT-201 step 6");
	}

	public void PAT301NewPatient() {
		// go to patients page
		WebElement audyxIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		Temp.configScreen(audyxIcon, "Test PAT-301 step 1");

		// click + to add new patient
		WebElement addPatient = webDriver
				.findElement(By.cssSelector("#patient-list-body .row.patients-pagination .col-lg-2 .fa-plus"));
		addPatient.click();
		Temp.tryCatch(3000);
		WebElement patientPanel = webDriver.findElement(By.className("panel-body"));
		System.out.println("Test PAT-301 step 2 passed");

		WebElement firstName = webDriver.findElement(
				By.cssSelector("#create-patient-panel .panel-body .row .col-lg-5 .form-group .patient-firstname"));
		firstName.sendKeys("Test");
		Temp.tryCatch(2000);
		WebElement submitNewPatient = webDriver
				.findElement(By.cssSelector("#create-patient-panel .panel-body .ad-button-group .submit-patient-form"));
		Temp.checkButtonNotClickable(submitNewPatient, "Test PAT-301 step 3");

		// fill in last name
		WebElement lastName = webDriver.findElement(
				By.cssSelector("#create-patient-panel .panel-body .row .col-lg-5 .form-group .patient-name"));
		lastName.sendKeys("Patient");
		Temp.tryCatch(2000);
		Temp.checkButtonNotClickable(submitNewPatient, "Test PAT-301 step 4");

		// fill in too old birthdate - smaller than 1900
		WebElement birthdate = webDriver.findElement(By.cssSelector(
				"#create-patient-panel .panel-body .row .col-lg-5 .form-group .input-group .patient-birthdate"));
		birthdate.sendKeys("06061899");
		Temp.tryCatch(2000);
		// check if calendar icon becomes red
		WebElement calendarColor = webDriver.findElement(By.cssSelector(
				"#create-patient-panel .panel-body .row .col-lg-5 .form-group .input-group .input-group-addon .fa-calendar"));
		Temp.printElementColor(calendarColor, "#a94442", "Test PAT-301 step 5");

		// fill in birthdate - greater than 2030
		WebElement birthdate2 = webDriver.findElement(By.cssSelector(
				"#create-patient-panel .panel-body .row .col-lg-5 .form-group .input-group .patient-birthdate"));
		birthdate2.clear();
		Temp.tryCatch(2000);
		birthdate2.sendKeys("06062030");
		Temp.tryCatch(2000);
		// check if calendar icon becomes red
		WebElement calendarColor2 = webDriver.findElement(By.cssSelector(
				"#create-patient-panel .panel-body .row .col-lg-5 .form-group .input-group .input-group-addon .fa-calendar"));
		Temp.printElementColor(calendarColor, "#f2dede", "Test PAT-301 step 6");

		// Fill in the correct birthdate and check if the add button is enabled
		WebElement birthdate3 = webDriver.findElement(By.cssSelector(
				"#create-patient-panel .panel-body .row .col-lg-5 .form-group .input-group .patient-birthdate"));
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

		// add again new patient and insert phone number - see if submit patient is
		// clickable
		addPatient.click();
		Temp.tryCatch(3000);
		WebElement firstName2 = webDriver.findElement(
				By.cssSelector("#create-patient-panel .panel-body .row .col-lg-5 .form-group .patient-firstname"));
		firstName2.sendKeys("Patient");
		Temp.tryCatch(2000);
		WebElement lastName2 = webDriver.findElement(
				By.cssSelector("#create-patient-panel .panel-body .row .col-lg-5 .form-group .patient-name"));
		lastName2.sendKeys("Automation " + RandomStringUtils.randomNumeric(2));
		Temp.tryCatch(2000);
		WebElement birthdate4 = webDriver.findElement(By.cssSelector(
				"#create-patient-panel .panel-body .row .col-lg-5 .form-group .input-group .patient-birthdate"));
		birthdate4.sendKeys("06052013");
		Temp.tryCatch(2000);
		WebElement phoneNumber = webDriver.findElement(By.cssSelector(
				"#create-patient-panel .panel-body .row .col-lg-5 .form-group .input-group .patient-phone"));
		phoneNumber.sendKeys("05044444");
		WebElement submitNewPatient2 = webDriver
				.findElement(By.cssSelector("#create-patient-panel .panel-body .ad-button-group .submit-patient-form"));
		Temp.checkButtonClickable(submitNewPatient2, "Test PAT-301 step 9");

		// enter invalid email address
		WebElement email = webDriver.findElement(By.cssSelector(
				"#create-patient-panel .panel-body .row .col-lg-5 .form-group .input-group .patient-email"));
		email.sendKeys("a");
		WebElement emailIcon = webDriver.findElement(By.cssSelector(
				"#create-patient-panel .panel-body .row .col-lg-5 .form-group .input-group .input-group-addon .fa-envelope-o"));
		Temp.printElementColor(emailIcon, "#f2dede", "Test PAT-301 step 10");

		// enter correct email
		WebElement email2 = webDriver.findElement(By.cssSelector(
				"#create-patient-panel .panel-body .row .col-lg-5 .form-group .input-group .patient-email"));
		email2.sendKeys("a@audyx.com");
		WebElement submitNewPatient3 = webDriver
				.findElement(By.cssSelector("#create-patient-panel .panel-body .ad-button-group .submit-patient-form"));
		Temp.checkButtonClickable(submitNewPatient3, "Test PAT-301 step 11");

		// click submit new patient
		submitNewPatient3.click();
		Temp.tryCatch(5000);
		// check the new patient added by present it in nav bar
		WebElement CurrentPatientInNav = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text .ad-name"));
		String CurrentPatientInNavFinal = Temp.patientNameFromString(CurrentPatientInNav);
		System.out.println(CurrentPatientInNavFinal);
		Temp.checkContainString(CurrentPatientInNavFinal, "Patient Automation", "Test PAT-301 step 12");
	}

	public void PAT401PatientSearch() {
		// go to patients page
		WebElement audyxIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		Temp.configScreen(audyxIcon, "Test PAT-401 step 0");

		// Check the current center and patient age
		WebElement currentCenter1 = webDriver.findElement(By.cssSelector("#user-details .info-text .center-info"));
		String currentCenter11 = Temp.printElementText(currentCenter1);

		WebElement currentPatientAge1 = webDriver.findElement(By.cssSelector(
				"#audyx-main > div > div > div > div.menu-bar > div.patient > div.info-text > div:nth-child(2) > span.ad-name > span > span"));
		String currentPatientAge11 = currentPatientAge1.getText();
		System.out.println(currentPatientAge11);
		WebElement searchIcon = webDriver.findElement(By.cssSelector("#patient-autocomplete-toggle"));
		searchIcon.click();
		Temp.tryCatch(3000);
		WebElement searchArea1 = webDriver.findElement(By.className("search-patient-input"));
		System.out.println("Test PAT-401 step 1 passed");

		// click on search in another centers at the bottom of patient list
		// go to last page

		WebElement searchInOtherCenter = webDriver
				.findElement(By.cssSelector("#patient-list-body .advanced-search .text .autocmplete-link"));
		searchInOtherCenter.click();
		Temp.tryCatch(5000);
		WebElement searchArea2 = webDriver.findElement(By.className("search-patient-input"));
		searchArea2.click();
		Temp.tryCatch(3000);
		System.out.println("Test PAT-401 step 2 passed");

		// type a string and see the list of results
		searchArea2.sendKeys("test test3");
		Temp.tryCatch(1000);
		WebElement dropdownResults = webDriver.findElement(By.className("search-patient-dropdown"));
		System.out.println("Test PAT-401 step 3 passed");

		// click on search in another area
		WebElement searchAnotherCenter = webDriver
				.findElement(By.cssSelector("#autocomplete .search-patient-row.clickable.suggestion"));
		searchAnotherCenter.click();
		Temp.tryCatch(3000);
		WebElement listResults = webDriver.findElement(By.cssSelector(
				"#autocomplete .search-patient-holder .search-patient-dropdown .search-patient-row.clickable.result .fullname"));
		System.out.println("Test PAT-401 step 4 passed");

		listResults.click();
		Temp.tryCatch(3000);
		WebElement otherCenterModal = webDriver
				.findElement(By.cssSelector("body  .modal.fade.in .modal-dialog .modal-content"));
		System.out.println("Test PAT-401 step 5 passed");

		// switch to patient center
		WebElement switchCenter = webDriver
				.findElement(By.cssSelector("body .modal.fade.in .modal-footer .buttons .btn-primary:nth-child(1)"));

		switchCenter.click();
		Temp.tryCatch(5000);
		WebElement currentCenter2 = webDriver.findElement(By.cssSelector("#user-details .info-text .center-info"));
		String currentCenter22 = Temp.printElementText(currentCenter2);
		Temp.checkDiffString(currentCenter11, currentCenter22, "Test PAT-401 step 6");

		// go to search patient area and insert patient name
		WebElement searchIcon2 = webDriver.findElement(By.cssSelector("#patient-autocomplete-toggle"));
		searchIcon2.click();
		Temp.tryCatch(3000);

		WebElement searchArea3 = webDriver.findElement(By.className("search-patient-input"));
		searchArea3.sendKeys("test test3");
		Temp.tryCatch(1000);

		// click on search in another center
		WebElement searchAnotherCenter2 = webDriver
				.findElement(By.cssSelector("#autocomplete .search-patient-row.clickable.suggestion"));
		searchAnotherCenter2.click();
		Temp.tryCatch(3000);
		WebElement listResults2 = webDriver.findElement(By.cssSelector(
				"#autocomplete .search-patient-holder .search-patient-dropdown .search-patient-row.clickable.result .fullname"));
		listResults2.click();
		Temp.tryCatch(3000);

		// click on move patient to this center button
		WebElement movePatient = webDriver
				.findElement(By.cssSelector("body .modal.fade.in .modal-footer .buttons .btn-primary:nth-child(2)"));
		movePatient.click();
		Temp.tryCatch(4000);
		WebElement currentPatientAge2 = webDriver.findElement(
				By.xpath("//*[@id=\"audyx-main\"]/div/div/div/div[1]/div[3]/div[2]/div[2]/span[1]/span/span"));
		String currentPatientAge22 = currentPatientAge2.getText();
		System.out.println(currentPatientAge22);
		Temp.checkDiffString(currentPatientAge11, currentPatientAge22, "Test PAT-401 step 7");

		// type a short string see message of "narrow your search app

		WebElement searchIcon3 = webDriver.findElement(By.cssSelector("#patient-autocomplete-toggle"));
		searchIcon3.click();
		Temp.tryCatch(3000);
		WebElement searchArea4 = webDriver.findElement(By.className("search-patient-input"));
		searchArea4.sendKeys("t");
		Temp.tryCatch(2000);
		webDriver.findElement(By.cssSelector("#autocomplete .search-patient-row.narrow"));
		System.out.println("Test PAT-401 step 8 passed");

	}

	public void PAT501ActivePatient() {
		// step 1 done at PAT-101 step 10
		WebElement audyxIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		audyxIcon.click();
		Temp.tryCatch(3000);

		// Click on patient in recent activity in order to activate it, see the same
		// Name appears in navbar
		WebElement nameInActivity = webDriver.findElement(By.cssSelector(
				"#activity-feed .feed-item .item-details.feed-item-details .activity-details .patient.clickable"));
		String nameInActivity1 = Temp.printElementText(nameInActivity);
		nameInActivity.click();
		Temp.tryCatch(3000);

		// Check the name in nav and compare
		WebElement CurrentPatientInNav = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text .ad-name"));
		String nameFinal = Temp.patientNameFromString(CurrentPatientInNav);
		Temp.checkEqualString(nameInActivity1, nameFinal, "Test PAT-501 step 2");
	}

	public void PAT601PatientFolder() {
		// Go to patient list
		WebElement audyxIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		audyxIcon.click();
		Temp.tryCatch(3000);
		// Choose a patient with many tests and click on patient name in navbar
		WebElement searchPatient = webDriver.findElement(By.className("search-patient"));
		searchPatient.sendKeys("Release3");
		// Click on the patient in patient list = you are moved to patient folder
		WebElement firstPatientInList = webDriver.findElement(By.cssSelector("#patient-list-body .table .patient-fullname"));
		firstPatientInList.click();
		Temp.tryCatch(6000);
		WebElement patientInNav = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text .ad-name"));
		patientInNav.click();
		Temp.tryCatch(7000);
		String patientInNavFinal = Temp.patientNameFromString(patientInNav);
		Temp.tryCatch(5000);
		// Check the patient name patient folder is equal to patient in nav
		WebElement patientInFolder = webDriver
				.findElement(By.cssSelector("#patient-info .panel-body .row.primary-row.patient-info-header .panel-title"));
		String patientInFolder2 = Temp.splitStringWithSpecialChar(patientInFolder, "\\(");
		Temp.checkEqualString(patientInNavFinal, patientInFolder2, "Test PAT-601 step 1");

		// See the element in patient folder
		WebElement navBar = webDriver.findElement(By.className("menu-bar"));
		WebElement menuBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .app .navigator"));
		System.out.println("Test PAT-601 step 2 passed");

		// Increase the view by clicking the darts
		WebElement increaseWindow = webDriver
				.findElement(By.cssSelector("#patient-info .panel-body .row.primary-row.patient-info-header .container-icon-expand"));
		increaseWindow.click();
		Temp.tryCatch(3000);

		// View more elements in patient folder
		WebElement testsIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .tests"));
		WebElement patientInfo = webDriver.findElement(By.cssSelector("#patient-folder-left"));
		WebElement bookmarkIcon = webDriver.findElement(By.cssSelector("#bookmarks .bookmarks-bottom-panel"));
		Actions act = new Actions(webDriver);
		act.moveToElement(bookmarkIcon).build().perform();
		Temp.tryCatch(3000);
		WebElement anamnesisIcon = webDriver.findElement(By.cssSelector("#add-questionnaire-1"));
		System.out.println("Test PAT-601 step 3 passed");

		// Decrease the view
		WebElement decreaseView = webDriver
				.findElement(By.cssSelector("#test-type-4 .panel .panel-body.mini-patient-folder .row .col-lg-12 .container-icon-expand .full"));
		decreaseView.click();
		Temp.tryCatch(5000);
		System.out.println("Test PAT-601 step 4 passed");
	}

	public void PAT602EditPatient() {
		// Print the old patient name
		WebElement firstPatientName = webDriver
				.findElement(By.cssSelector("#patient-info .panel-body .row.primary-row.patient-info-header .panel-title"));
		String patientInFolder2 = Temp.splitStringWithSpecialChar(firstPatientName, "\\(");
		System.out.println(patientInFolder2);
		// Click on pencil to edit patient
		WebElement editIcon = webDriver
				.findElement(By.cssSelector("#patient-info .panel-body .row.primary-row.patient-info-header .fa.fa-pencil.on-hover.clickable"));
		Actions act = new Actions(webDriver);
		act.moveToElement(editIcon).click().perform();
		Temp.tryCatch(5000);
		System.out.println("Test PAT-602 step 1 passed");

		// View the elements that can be edited
		WebElement firstName = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[1]/div[1]/div/input"));
		//#patient-info .panel-body .edit-patient.question-group .row .col-xs-6 .form-group .form-control = 12 items
		WebElement lastName = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[1]/div[2]/div/input"));
		WebElement birthDate = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[2]/div/div/div/input"));
		WebElement additionalInfo = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[3]/div[1]/span/h5"));
		System.out.println("Test PAT-602 step 2 passed");

		// Delete mandatory field - save button isn't enabled
		WebElement submit = webDriver
				.findElement(By.cssSelector("#patient-info .panel-body .edit-patient.question-group .row .col-xs-12 .ad-button-group .btn-primary"));
		Temp.checkButtonClickable(submit, "Test PAT-602 step 3");

		// Empty patient name
		firstName.clear();
		Temp.tryCatch(2000);
		Temp.checkButtonClickable(submit, "Test PAT-602 step 4");

		// Click cancel - patient name wasn't changed
		WebElement cancel = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[6]/div/div/a"));
		cancel.click();
		Temp.tryCatch(5000);
		// See the patient name in folder
		WebElement thirdPatientName = webDriver
				.findElement(By.cssSelector("#patient-info .panel-body .row.primary-row.patient-info-header .panel-title"));
		String patientInFolder4 = Temp.splitStringWithSpecialChar(thirdPatientName, "\\(");
		System.out.println(patientInFolder4);
		Temp.checkEqualString(patientInFolder2, patientInFolder4, "Test PAT-602 step 5");

		// Change patient name - see it was changed
		// Hover mouse near pencil and click
		WebElement editIcon2 = webDriver
				.findElement(By.cssSelector("#patient-info .panel-body .row.primary-row.patient-info-header .fa.fa-pencil.on-hover.clickable"));
		Actions act2 = new Actions(webDriver);
		act2.moveToElement(editIcon2).click().perform();
		Temp.tryCatch(5000);
		// Edit first name
		WebElement firstName2 = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[1]/div[1]/div/input"));
		WebElement submit2 = webDriver
				.findElement(By.xpath("//*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[6]/div/div/button"));
		Temp.editDetails(firstName2, "Test-" + RandomStringUtils.randomNumeric(2), submit2);
		// See the patient name in folder
		WebElement forthPatientName = webDriver
				.findElement(By.cssSelector("#patient-info .panel-body .row.primary-row.patient-info-header .panel-title"));
		String patientInFolder6 = Temp.splitStringWithSpecialChar(forthPatientName, "\\(");
		System.out.println(patientInFolder6);
		Temp.checkDiffString(patientInFolder4, patientInFolder6, "Test PAT-602 step 6");

		// See the patient name was changed in navbar
		WebElement CurrentPatientInNav2 = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text .ad-name"));
		String forthPatient = Temp.patientNameFromString(CurrentPatientInNav2);
		Temp.checkEqualString(patientInFolder6, forthPatient, "Test PAT-602 step 7");

		// Click on edit patient in order to delete patient
		WebElement editIcon3 = webDriver
				.findElement(By.cssSelector("#patient-info .panel-body .row.primary-row.patient-info-header .fa.fa-pencil.on-hover.clickable"));
		Actions act3 = new Actions(webDriver);
		act3.moveToElement(editIcon3).click().perform();
		Temp.tryCatch(5000);
		WebElement trash = webDriver
				.findElement(By.xpath(" //*[@id=\"patient-info\"]/div/div/div/div[3]/div[1]/div/i"));
		System.out.println("Test PAT-602 step 8 passed");

		// Click on delete patient
		Temp.tryCatch(2000);
		trash.click();
		Temp.tryCatch(3000);
		WebElement confirmModal = webDriver.findElement(By.xpath("//*[@id=\"patient-info\"]/div/div/div/div[2]"));
		System.out.println("Test PAT-602 step 9 passed");

		// Click on cancel
		WebElement cancelDelete = webDriver.findElement(By.xpath(" //*[@id=\"patient-info\"]/div/div/div/div[2]/a"));
		cancelDelete.click();
		Temp.tryCatch(3000);
		System.out.println("Test PAT-602 step 10 passed");

		// Go to patient list page
		webDriver.findElement(By.xpath("//*[@id=\"audyx-main\"]/div/div/div/div[1]/div[1]/div[2]")).click();
		Temp.tryCatch(3000);
	}

	public void PAT604NavigationButton() {
		// Click on audyx icon in order to move to patients page
		webDriver.findElement(By.xpath("//*[@id=\"audyx-main\"]/div/div/div/div[1]/div[1]/div[2]")).click();
		Temp.tryCatch(3000);
		// Choose a patient and see all items appears in navigation icon
		WebElement searchPatient = webDriver.findElement(By.className("search-patient"));
		searchPatient.sendKeys("Release3");
		// Click on the patient in patient list = you are moved to patient folder
		webDriver.findElement(By.xpath("//*[@id=\"patient-list-body\"]/div/div/table/tbody/tr[1]/td[1]/a")).click();
		Temp.tryCatch(5000);
		Temp.checkContainString(webDriver.getCurrentUrl(), "https://alpha.audyx.com/#/patientFolder",
				"Test PAT-604 step 1");

		// Click on navigation icon
		WebElement navigationIcon = webDriver.findElement(By.xpath("//*[@id=\"bookmarks\"]/div/div[2]/div"));
		Actions act = new Actions(webDriver);
		act.moveToElement(navigationIcon).clickAndHold().perform();
		// Find all bookmarks
		WebElement tonal = webDriver.findElement(By.cssSelector("#bookmarks .bookmarks-wrapper .bookmarks-list .bookmark.tonal"));
		WebElement speech = webDriver.findElement(By.cssSelector("#bookmarks .bookmarks-wrapper .bookmarks-list .bookmark.noise"));
		WebElement patientFolder = webDriver
				.findElement(By.cssSelector("#bookmarks .bookmarks-wrapper .bookmarks-list .bookmark patient-info"));
		WebElement annamnesis = webDriver.findElement(By.cssSelector("#bookmarks .bookmarks-wrapper .bookmarks-list .bookmark questionnaire anamnesis"));
		WebElement fittings = webDriver.findElement(By.cssSelector("#bookmarks .bookmarks-wrapper .bookmarks-list .bookmark questionnaire adaptation"));
		System.out.println("Test PAT-604 step 2 passed");
	}

	/*
	 * Update HA of patient
	 * 
	 */
	public void PAT701HearingAidsUpdate() {

		// Create new patient
		PatientManagement.createNewPatient();
		Temp.checkContainString(webDriver.getCurrentUrl(), "https://alpha.audyx.com/#/patientFolder",
				"Test PAT-701 step 1");

		// Click on pencil in order to edit patient and see if HA panel appears
		Temp.tryCatch(9000);
		WebElement editIcon = webDriver
				.findElement(By.cssSelector("#patient-info .panel-body .row.primary-row.patient-info-header .fa.fa-pencil.on-hover.clickable"));
		editIcon.click();
		Temp.tryCatch(3000);
		WebElement HearingAids = webDriver.findElement(By.cssSelector(
				"#patient-info .panel-body .edit-patient.question-group .sub-header "));
		System.out.println("Test PAT-701 step 2 passed");

				// Click on HA icon on order to edit it -see if edit area appears
		HearingAids.click();
		// HearingAids.click();
		Temp.tryCatch(4000);
		WebElement rightEar = webDriver.findElement(By.cssSelector(
				"#patient-info .panel-body .edit-patient.question-group .question-group .sub-group .row .col-xs-6.fitting .form-group.right_hearing_instrument-form .form-control"));
		System.out.println("Test PAT-701 step 3 passed");

		// Type some string in right ear - drop-down appears
		rightEar.sendKeys("pho");
		WebElement firstHA = webDriver.findElement(By.xpath(
				" //*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[4]/div[2]/div/div[1]/div/div/div/div/div[3]"));
		System.out.println("Test PAT-701 step 4 passed");

		// Choose option and save - see the HA is saved and appears in patient folder
		firstHA.click();
		Temp.tryCatch(1000);
		WebElement chooseHA = webDriver.findElement(By.className("autocomplete-instruments-result"));
		chooseHA.click();
		Temp.tryCatch(3000);
		WebElement saveEditPatient = webDriver
				.findElement(By.xpath("//*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[6]/div/div/button"));
		saveEditPatient.click();
		Temp.tryCatch(5000);
		WebElement HAinFolder = webDriver.findElement(By.className("hearing-aids-name"));
		System.out.println("Test PAT-701 step 5 passed");

		// Choose option of no HA and save- see the HA area is empty but can be edited
		WebElement editIcon2 = webDriver
				.findElement(By.cssSelector("#patient-info .panel-body .row.primary-row.patient-info-header .fa.fa-pencil.on-hover.clickable"));
		editIcon.click();
		Temp.tryCatch(3000);
		WebElement editHA = webDriver.findElement(
				By.xpath("//*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[4]/div[2]/div/div[1]/div/select"));
		editHA.click();
		Temp.tryCatch(3000);
		WebElement noHA = webDriver.findElement(By.xpath(
				"//*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[4]/div[2]/div/div[1]/div/select/option[2]"));
		noHA.click();
		Temp.tryCatch(3000);
		WebElement saveEditPatient2 = webDriver
				.findElement(By.xpath("//*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[6]/div/div/button"));
		saveEditPatient2.click();
		Temp.tryCatch(3000);
		System.out.println("Test PAT-701 step 6 passed");

		// Edit patient name and choose HA from history and save - HA appears in patient
		// folder
		// Click on edit patient
		webDriver.findElement(By.xpath(" //*[@id=\"patient-info\"]/div/div/div/div[3]/div[1]/div/i")).click();
		Temp.tryCatch(3000);
		// Click on right ear area
		webDriver
				.findElement(By.xpath(
						"//*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[4]/div[2]/div/div[1]/div/select"))
				.click();
		Temp.tryCatch(3000);
		// Choose HA in history
		webDriver.findElement(By.xpath(
				"//*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[4]/div[2]/div/div[1]/div/select/option[3]"))
				.click();
		Temp.tryCatch(3000);
		// Save changes
		webDriver.findElement(By.xpath("//*[@id=\"patient-info\"]/div/div/div/div[3]/div[2]/div[6]/div/div/button"))
				.click();
		Temp.tryCatch(5000);
		WebElement HAinFolder2 = webDriver.findElement(By.className("hearing-aids-name"));
		System.out.println("Test PAT-701 step 7 passed");

	}

	public void PAT802DisplayDQ() {
		// Click on audyx icon in order to move to patients page
		webDriver.findElement(By.xpath("//*[@id=\"audyx-main\"]/div/div/div/div[1]/div[1]/div[2]")).click();
		Temp.tryCatch(3000);
		// Choose a patient and see all items appears in navigation icon
		WebElement searchPatient = webDriver.findElement(By.className("search-patient"));
		searchPatient.sendKeys("Release3");
		// Click on the patient in patient list = you are moved to patient folder
		webDriver.findElement(By.xpath("//*[@id=\"patient-list-body\"]/div/div/table/tbody/tr[1]/td[1]/a")).click();

		Temp.checkContainString(webDriver.getCurrentUrl(), "https://alpha.audyx.com/#/patientFolder",
				"Test PAT-802 step 1");

		// Check the DQ in avatar
		Temp.tryCatch(4000);
		WebElement rightEarDQ = webDriver
				.findElement(By.xpath("//*[@id=\"audyx-main\"]/div/div/div/div[1]/div[3]/div[1]/div[1]"));
		Temp.tryCatch(3000);

		WebElement leftEarDQ = webDriver
				.findElement(By.xpath(" //*[@id=\"audyx-main\"]/div/div/div/div[1]/div[3]/div[1]/div[2]"));
		// Go to right ear and print the DQ
		Temp.tryCatch(4000);
		Actions toolAct = new Actions(webDriver);
		toolAct.moveToElement(rightEarDQ).clickAndHold().perform();
		WebElement toolTipElement = webDriver.findElement(By.className("tooltip"));
		String toolTipText = toolTipElement.getText();
		System.out.println(toolTipText);
		Temp.tryCatch(9000);
		// Go to left ear and print the DQ
		toolAct.moveToElement(leftEarDQ).clickAndHold().perform();
		Temp.tryCatch(2000);
		WebElement toolTipElement2 = webDriver.findElement(By.className("tooltip"));
		Temp.tryCatch(3000);
		String toolTipText2 = toolTipElement2.getText();
		System.out.println(toolTipText2);
		Temp.tryCatch(6000);
		if ((toolTipText.equals("SLIGHT")) && (toolTipText.equals("MODERATE")))
			System.out.println("Test PAT-802 step 2 passed");

		// Check the DQ in patient folder near tonal graph
		WebElement tonalRightEar = webDriver
				.findElement(By.xpath("//*[@id=\"test-type-2\"]/div[2]/div/div[2]/div[2]/div[2]/div[1]/div/span[2]"));
		WebElement tonalLeftEar = webDriver
				.findElement(By.xpath("//*[@id=\"test-type-2\"]/div[2]/div/div[2]/div[2]/div[2]/div[2]/div/span[2]"));
		if ((tonalRightEar.getText().contains("30")) && (tonalLeftEar.getText().contains("65")))
			System.out.println("Test PAT-802 step 3 passed");
	}

	public void REP101RequestWindow() {
		// Click on PDF icon and see the modal appears
		webDriver.findElement(By.xpath("//*[@id=\"patient-info\"]/div/div/div/div[3]/div[1]/div/div[1]/i")).click();
		Temp.tryCatch(3000);
		WebElement pdfModal = webDriver.findElement(By.xpath(" //*[@id=\"pdfModal\"]/div/div"));
		System.out.println("Test REP-101 step 1 passed");

		// View all the items - some are enabled and some are disabled
		WebElement observations = webDriver
				.findElement(By.xpath("//*[@id=\"pdfModal\"]/div/div/div[2]/div[1]/div[1]/div"));
		WebElement listTests = webDriver
				.findElement(By.xpath("//*[@id=\"pdfModal\"]/div/div/div[2]/div[1]/div[2]/div"));
		WebElement signatureList = webDriver
				.findElement(By.xpath("//*[@id=\"pdfModal\"]/div/div/div[2]/div[1]/div[2]/div"));
		System.out.println("Test REP-101 step 2 passed");

		// Close the modal w/o download
		WebElement closeModal = webDriver.findElement(By.xpath("//*[@id=\"pdfModal\"]/div/div/div[1]/a"));
		closeModal.click();
		Temp.tryCatch(3000);
		System.out.println("Test REP-101 step 3 passed");

		// Click again on pdf icon. select all checkbox and click download
		webDriver.findElement(By.xpath("//*[@id=\"patient-info\"]/div/div/div/div[3]/div[1]/div/div[1]")).click();
		Temp.tryCatch(3000);
		webDriver.findElement(By.xpath("//*[@id=\"pdf\"]/div/button")).click();
		Temp.tryCatch(3000);
		// Verify in download folder that the PDF download
		String downloadPath = "C:\\Users\\Esty Wolpo\\Downloads";
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();
		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().contains("Test Release")) {
				System.out.println("Test REP-101 step 4 passed");
			}
		}

		// Click again on pdf icon. un-check tonal test then download
		webDriver.findElement(By.xpath("//*[@id=\"patient-info\"]/div/div/div/div[3]/div[1]/div/div[1]/i")).click();
		Temp.tryCatch(3000);
		WebElement uncheckTonal = webDriver
				.findElement(By.xpath("//*[@id=\"pdfModal\"]/div/div/div[2]/div[1]/div[2]/div/div[5]/label/input"));
		uncheckTonal.click();
		Temp.tryCatch(2000);
		// Click on download file
		webDriver.findElement(By.xpath("//*[@id=\"pdf\"]/div/button")).click();
		Temp.tryCatch(3000);
		// Verify in download folder that the PDF download
		String downloadPath2 = "C:\\Users\\Esty Wolpo\\Downloads";
		File getLatestFile2 = Temp.getLatestFilefromDir(downloadPath2);
		String fileName2 = getLatestFile2.getName();
		System.out.println(fileName2);
		Temp.checkContainString(fileName2, "Test Release", "Test REP-101 step 5");
	}

	public static void createNewPatient() {
		// Click on Audyx icon
		webDriver.findElement(By.xpath("//*[@id=\"audyx-main\"]/div/div/div/div[1]/div[1]/div[2]/img")).click();
		Temp.tryCatch(5000);
		// Click on +
		webDriver.findElement(By.xpath("//*[@id=\"patient-list-body\"]/div/div/div[2]/div[2]/button/i")).click();
		Temp.tryCatch(5000);
		// Insert first name and last name and birthdate
		webDriver.findElement(By.xpath("//*[@id=\"create-patient-panel\"]/div/form/div[1]/div[1]/div/input"))
				.sendKeys("Test");
		Temp.tryCatch(2000);
		webDriver.findElement(By.xpath("//*[@id=\"create-patient-panel\"]/div/form/div[1]/div[2]/div/input"))
				.sendKeys("DQ " + RandomStringUtils.randomNumeric(2));
		webDriver.findElement(By.xpath(" //*[@id=\"create-patient-panel\"]/div/form/div[2]/div[1]/div/div/input"))
				.sendKeys("09092003");
		Temp.tryCatch(2000);
		// Submit new patient
		webDriver.findElement(By.xpath("//*[@id=\"create-patient-panel\"]/div/form/div[4]/button")).click();
		Temp.tryCatch(5000);
	}
}
