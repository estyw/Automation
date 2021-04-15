package expert;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Weekly {

	private static WebDriver webDriver;
	private String baseURL;
	private String driverFilePath;

	Weekly(String baseURL, String driverFilePath) {
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

	/**
	 * /* Test the accessing to patient list and find all elements there
	 */
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
		WebElement mainPanel = webDriver.findElement(By.id("audyx-main"));
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
				.findElement(By.cssSelector("#patient-list-body .row.patients-pagination .col-lg-10 :nth-child(2) a"));
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
				.findElement(By.cssSelector("#patient-list-body .patient-entry.warning .patient-fullname"));
		String CurrentPatientInList1 = CurrentPatientInList.getText();
		Temp.tryCatch(3000);
		WebElement CurrentPatientInNav = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text .ad-name"));
		// String CurrentPatientInNav1 = CurrentPatientInNav.getText();
		String nameFinal = Temp.patientNameFromString(CurrentPatientInNav);
		Temp.checkEqualString(CurrentPatientInList1, nameFinal, "Test PAT-101 step 10 & Test PAT-501 step 1");
	}

	/**
	 * Add a new patient and test all parameters in this page
	 */
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
		Temp.printElementBackgroundColor(calendarColor, "#a94442", "Test PAT-301 step 5");

		// fill in birthdate - greater than 2030
		firstName.clear();
		firstName.sendKeys("Test");
		Temp.tryCatch(3000);
		lastName.clear();
		lastName.sendKeys("Patient");
		Temp.tryCatch(2000);
		birthdate.sendKeys("06062030");
		Temp.tryCatch(2000);
		// check if calendar icon becomes red
		WebElement calendarColor2 = webDriver.findElement(By.cssSelector(
				"#create-patient-panel .panel-body .row .col-lg-5 .form-group .input-group .input-group-addon .fa-calendar"));
		Temp.printElementBackgroundColor(calendarColor, "#f2dede", "Test PAT-301 step 6");

		// Fill in the correct birthdate and check if the add button is enabled
		firstName.clear();
		firstName.sendKeys("Test");
		Temp.tryCatch(3000);
		lastName.clear();
		lastName.sendKeys("Patient");
		Temp.tryCatch(2000);
		birthdate.sendKeys("06062014");
		Temp.tryCatch(4000);
		Temp.checkButtonClickable(submitNewPatient, "Test PAT-301 step 7");

		// click on cancel
		WebElement cancelNewPatient = webDriver.findElement(By.className("cancel-patient-form"));
		cancelNewPatient.click();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.audyx.com/#/patients", webDriver.getCurrentUrl(), "Test PAT-301 step 8");

		// add again new patient and insert phone number - see if submit patient is
		// clickable
		WebElement addPatient2 = webDriver
				.findElement(By.cssSelector("#patient-list-body .row.patients-pagination .col-lg-2 .fa-plus"));
		addPatient2.click();
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
		Temp.printElementBackgroundColor(emailIcon, "#f2dede", "Test PAT-301 step 10");

		// enter correct email
		WebElement email2 = webDriver.findElement(By.cssSelector(
				"#create-patient-panel .panel-body .row .col-lg-5 .form-group .input-group .patient-email"));
		email2.sendKeys("a@audyx.com");
		WebElement submitNewPatient3 = webDriver
				.findElement(By.cssSelector("#create-patient-panel .panel-body .ad-button-group .submit-patient-form"));
		Temp.checkButtonClickable(submitNewPatient3, "Test PAT-301 step 11");

		// click submit new patient
		submitNewPatient3.click();
		Temp.tryCatch(7000);
		// check the new patient added by present it in nav bar
		WebElement CurrentPatientInNav = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text .ad-name"));
		String CurrentPatientInNavFinal = Temp.patientNameFromString(CurrentPatientInNav);
		System.out.println(CurrentPatientInNavFinal);
		Temp.checkContainString(CurrentPatientInNavFinal, "Patient Automation", "Test PAT-301 step 12");
	}

	/**
	 * Searching a patient - although if it is in another center
	 */
	public void PAT401PatientSearch() {
		// go to patients page
		WebElement audyxIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		Temp.configScreen(audyxIcon, "Test PAT-401 step 0");

		// Check the current center and patient age
		WebElement currentCenter1 = webDriver.findElement(By.cssSelector("#user-details .info-text .center-info"));
		String currentCenter11 = Temp.printElementText(currentCenter1);

		WebElement currentPatientAge1 = webDriver.findElement(By.cssSelector(
				"#audyx-main .menu-bar .patient .info-text :nth-child(2) .ad-name :nth-child(1) :nth-child(1)"));
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
		Actions act = new Actions(webDriver);
		act.moveToElement(searchInOtherCenter).perform();
		Temp.tryCatch(5000);
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
				"#autocomplete .search-patient-holder .search-patient-dropdown :nth-child(2) :nth-child(2) :nth-child(1):nth-child(1).search-patient-row.clickable.result :nth-child(1).fullname"));
		System.out.println("Test PAT-401 step 4 passed");

		listResults.click();
		Temp.tryCatch(3000);
		WebElement otherCenterModal = webDriver
				.findElement(By.cssSelector("body .modal.fade.in .modal-dialog .modal-content"));
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
				"#autocomplete .search-patient-holder .search-patient-dropdown :nth-child(2) :nth-child(2) :nth-child(1):nth-child(1).search-patient-row.clickable.result :nth-child(1).fullname"));
		listResults2.click();
		Temp.tryCatch(3000);

		// click on move patient to this center button
		WebElement movePatient = webDriver
				.findElement(By.cssSelector("body .modal.fade.in .modal-footer .buttons .btn-primary:nth-child(2)"));
		movePatient.click();
		Temp.tryCatch(4000);
		WebElement currentPatientAge2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .menu-bar .patient .info-text :nth-child(2) .ad-name :nth-child(1) :nth-child(1)"));
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

	/**
	 * Patient folder - check elements there
	 */
	public void PAT601PatientFolder() {
		// Go to patient list
		WebElement audyxIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		audyxIcon.click();
		Temp.tryCatch(3000);
		// Choose a patient with many tests and click on patient name in navbar
		WebElement searchPatient = webDriver.findElement(By.className("search-patient"));
		searchPatient.sendKeys("Release3");
		// Click on the patient in patient list = you are moved to patient folder
		WebElement firstPatientInList = webDriver
				.findElement(By.cssSelector("#patient-list-body .table .patient-fullname"));
		firstPatientInList.click();
		Temp.tryCatch(6000);
		WebElement patientInNav = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text .ad-name"));
		patientInNav.click();
		Temp.tryCatch(7000);
		String patientInNavFinal = Temp.patientNameFromString(patientInNav);
		Temp.tryCatch(5000);
		// Check the patient name patient folder is equal to patient in nav
		WebElement patientInFolder = webDriver.findElement(
				By.cssSelector("#patient-info .panel-body .row.primary-row.patient-info-header .panel-title"));
		String patientInFolder2 = Temp.splitStringWithSpecialChar(patientInFolder, "\\(");
		Temp.checkEqualString(patientInNavFinal, patientInFolder2, "Test PAT-601 step 1");

		// See the element in patient folder
		WebElement navBar = webDriver.findElement(By.className("menu-bar"));
		WebElement menuBar = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .app .navigator"));
		System.out.println("Test PAT-601 step 2 passed");

		// Increase the view by clicking the darts
		WebElement increaseWindow = webDriver.findElement(By
				.cssSelector("#patient-info .panel-body .row.primary-row.patient-info-header .container-icon-expand"));
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
		WebElement decreaseView = webDriver.findElement(By.cssSelector(
				"#patient-info .panel-body .row.primary-row.patient-info-header .container-icon-expand button"));
		decreaseView.click();
		Temp.tryCatch(5000);
		System.out.println("Test PAT-601 step 4 passed");
	}

//====== Otoscopy 

	/**
	 * Configure camera, take otoscopy image zooming & cropping Edit & delete image
	 */

	/**
	 * configure otoscopy device and access to screen
	 */
	public void OTO101AccessToModule() {
//access to otoscopy screen via side bar
		WebElement sideBar2 = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .navigator"));
		sideBar2.click();
		Temp.tryCatch(3000);
		WebElement otoscopyIcon = webDriver.findElement(By.cssSelector("#menu-side :nth-child(8)"));
		otoscopyIcon.click();
		WebElement mainPanel = webDriver.findElement(By.id("audyx-main"));
		Actions act = new Actions(webDriver);
		act.moveToElement(mainPanel).perform();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://koalys.audyx.com/#/otoscope", webDriver.getCurrentUrl(), "Test OTO-101 step 1");

		// access to otoscopy screen via patient folder
		WebElement patientIcon = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text .ad-name"));
		patientIcon.click();
		Temp.tryCatch(5000);
		WebElement cameraIcon = webDriver.findElement(By.cssSelector(
				"#patient-info .panel-body :nth-child(2) .ears-condition.row fieldset:nth-child(1) .fa-camera"));
		System.out.println("Test OTO-101 step 2 passed");
	}

	/**
	 * access to otoscopy via patient folder, check elements in otoscopy screen in
	 * first connecting - configure the camera in audio setup page
	 */
	public void OTO102DeviceConfiguration() {
		WebElement patientIcon2 = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text :nth-child(2) .ad-name"));
		patientIcon2.click();
		Temp.tryCatch(5000);

		WebElement rightEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(2) .otoscope-ear .ear "));
		WebElement leftEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-ear .ear"));
		WebElement deviceConnectStatus = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.empty-otoscope .device-status-text"));
		WebElement setupLink = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-panel.empty-otoscope .device-status-text .device-status-action"));
		String setupLink1 = Temp.printElementText(setupLink);
		Temp.checkContainString(setupLink1, "Setup", "Test OTO-102 step 1");

		setupLink.click();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://koalys.audyx.com/#/audio-setup", webDriver.getCurrentUrl(), "Test OTO-102 step 2");

		// add a new cabin in order to configure the camera
		WebElement addCabin = webDriver.findElement(By.cssSelector("#audyx-main .view-animate .top-panel .fa-plus"));
		addCabin.click();
		Temp.tryCatch(3000);
		WebElement insertNewCabin = webDriver
				.findElement(By.cssSelector("#audyx-main section .view-animate .top-panel input"));
		insertNewCabin.sendKeys("b");
		WebElement createCabin = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .top-panel .btn.btn-primary.create-or-edit-item"));
		createCabin.click();
		Temp.tryCatch(3000);
		WebElement otoscopyArea = webDriver.findElement(By.cssSelector("#s2id_autogen7"));
		// #s2id_autogen7 .select2-chosen .placeholder
		otoscopyArea.click();
		Temp.tryCatch(3000);
		WebElement chooseOtoscopyDevice = webDriver.findElement(By
				.cssSelector("#select2-drop .select2-highlighted .select2-result-label .device-option .device-label"));
		chooseOtoscopyDevice.click();
		WebElement patientIcon = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text :nth-child(2) .ad-name"));
		patientIcon.click();
		Temp.tryCatch(5000);
		WebElement cameraIcon = webDriver.findElement(By.cssSelector(
				"#patient-info .panel-body :nth-child(2) .ears-condition.row fieldset:nth-child(1) .fa-camera"));
		cameraIcon.click();
		Temp.tryCatch(6000);
		Temp.checkUrl("https://koalys.audyx.com/#/otoscope", webDriver.getCurrentUrl(), "Test OTO-101 step 3");
		Temp.tryCatch(3000);
		WebElement deviceStatus = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .empty-otoscope .device-status-text"));

		Temp.checkContainString(deviceStatus.getText(), "Device is connected", "Test OTO-102 step 3");
	}

	/**
	 * Take an otoscopy image for the first time of this patient
	 */
	public void OTO201ImageCapture() {
//click on L icon
		WebElement leftEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-ear .ear"));
		leftEar.click();
		Temp.tryCatch(2000);
		WebElement leftEarActive = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view  :nth-child(1).otoscope-panel.edit-otoscope .webcam .webcam-live"));
		System.out.println("Test OTO-201 step 1 passed");
		// Take left ear image
		WebElement captureButtonLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .capture-otoscope"));
		Temp.tryCatch(7000);
		captureButtonLeft.click();
		Temp.tryCatch(7000);
		WebElement imageCircle = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-panel.edit-otoscope .edit-mode"));
		WebElement saveCrop = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .crop-otoscope :nth-child(1).otoscope-btn"));
		WebElement noCrop = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .crop-otoscope :nth-child(2).otoscope-btn"));
		System.out.println("Test OTO-201 step 2 passed");
//save no-cropped image
		noCrop.click();
		Temp.tryCatch(3000);
		WebElement savedImageLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main :nth-child(2) :nth-child(2) :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-image"));
		WebElement imageDateLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main :nth-child(2) :nth-child(2) :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper .otoscope-date"));
		System.out.println("Test OTO-201 step 3 passed");

		// Take right ear image
		// Click R
		WebElement rightEar = webDriver.findElement(By
				.cssSelector("#audyx-main .body section .view-animate :nth-child(1) :nth-child(2) .otoscope-ear .ear"));
		rightEar.click();
		Temp.tryCatch(3000);
		WebElement rightEarActive = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(2) .otoscope-view  :nth-child(1).otoscope-panel.edit-otoscope .webcam .webcam-live"));
		System.out.println("Test OTO-201 step 4 passed");
//Click capture of right ear
		WebElement captureButtonRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(2) .otoscope-bar .capture-otoscope"));
		Temp.tryCatch(7000);
		captureButtonRight.click();
		Temp.tryCatch(7000);
		WebElement imageCircleRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(2) .otoscope-view .otoscope-panel.edit-otoscope .edit-mode"));
		WebElement saveCropRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(2) .otoscope-bar .crop-otoscope :nth-child(1).otoscope-btn"));
		WebElement noCropRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(2) .otoscope-bar .crop-otoscope :nth-child(2).otoscope-btn"));
		System.out.println("Test OTO-201 step 5 passed");
//Save no cropped right ear
		noCropRight.click();
		Temp.tryCatch(3000);
		WebElement savedImageRight = webDriver.findElement(
				By.cssSelector("#audyx-main :nth-child(2) :nth-child(2) :nth-child(2) .otoscope-view .otoscope-image"));
		WebElement imageDateRight = webDriver.findElement(By.cssSelector(
				"#audyx-main :nth-child(2) :nth-child(2) :nth-child(2) .otoscope-bar .edit-image.btn-wrapper .otoscope-date"));
		System.out.println("Test OTO-201 step 6 passed");
	}

	/**
	 * Take images, with zooming & cropping
	 */
	public void OTO202ZoomingCropping() {
		// left ear
		WebElement leftEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-ear .ear"));
		leftEar.click();
		Temp.tryCatch(2000);
		WebElement previousLeftImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-panel.edit-otoscope .previous-otoscope.clickable"));
		WebElement currentLiveImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-panel.edit-otoscope .webcam video"));
		System.out.println("Test OTO-202 step 1 passed");

		WebElement captureButtonLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .capture-otoscope"));
		captureButtonLeft.click();
		Temp.tryCatch(7000);
		WebElement imageCircle = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-panel.edit-otoscope .edit-mode"));
		System.out.println("Test OTO-202 step 2 passed");

		Actions act = new Actions(webDriver);
		act.dragAndDropBy(imageCircle, -350, -400).build().perform();
		WebElement submitOtoscopy2Left = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .crop-otoscope.btn-wrapper :nth-child(1) :nth-child(1)"));
		submitOtoscopy2Left.click();
		Temp.tryCatch(6000);
		WebElement confirmEditOtoscopyModalLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .panel-confirm .btn.btn-primary"));
		System.out.println("Test OTO-202 step 3 passed");

		confirmEditOtoscopyModalLeft.click();
		Temp.tryCatch(6000);
		System.out.println("Test OTO-202 step 4 passed");

		// right ear
		WebElement rightEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(2) .otoscope-ear .ear"));
		rightEar.click();
		Temp.tryCatch(2000);
		WebElement previousRightImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(2) .otoscope-view .otoscope-panel.edit-otoscope .previous-otoscope.clickable"));
		WebElement currentRightLiveImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(2) .otoscope-view .otoscope-panel.edit-otoscope .webcam video"));
		System.out.println("Test OTO-202 step 5.1 passed");

		WebElement captureButtonRight = webDriver.findElement(By
				.cssSelector("#audyx-main .view-animate :nth-child(1) :nth-child(2) .otoscope-bar .capture-otoscope"));
		captureButtonRight.click();
		Temp.tryCatch(4000);
		WebElement imageCircleRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(2) .otoscope-view .otoscope-panel.edit-otoscope .edit-mode"));
		System.out.println("Test OTO-202 step 5.2 passed");

		Actions act2 = new Actions(webDriver);
		act2.dragAndDropBy(imageCircleRight, -350, -400).build().perform();
		WebElement submitOtoscopy2Right = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) .otoscope-bar .crop-otoscope.btn-wrapper .otoscope-btn:nth-child(1)"));
		submitOtoscopy2Right.click();
		Temp.tryCatch(6000);
		WebElement confirmEditOtoscopyModalRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(2) .otoscope-panel.edit-otoscope .panel-confirm .btn.btn-primary"));
		System.out.println("Test OTO-202 step 5.3 passed");

		confirmEditOtoscopyModalRight.click();
		Temp.tryCatch(3000);
		System.out.println("Test OTO-202 step 5.4 passed");

	}

	/**
	 * Edit & delete image
	 */
	public void OTO203EdidDeleteOtoscopy() {
//select a patient with otoscopy images		
		// test OTO-101 4&5
		// go to patient folder and see the saved otoscopy image
		WebElement patientIcon204 = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text .ad-name"));
		patientIcon204.click();
		Temp.tryCatch(5000);
		WebElement otoscopyImage = webDriver.findElement(By.cssSelector(".ear-condition .col-lg-5.otoscope-img"));
		System.out.println("Test OTO-101 step 4 passed");

		otoscopyImage.click();
		Temp.tryCatch(4000);
		Temp.checkUrl("https://koalys.audyx.com/#/otoscope", webDriver.getCurrentUrl(), "Test OTO-101 step 5");

		// WebElement cameraIcon204 = webDriver.findElement(By.cssSelector(
		// "#patient-info .panel-body :nth-child(2) .ears-condition.row
		// fieldset:nth-child(1) .fa-camera"));

		// check all elements in otoscopy image
		WebElement LeftImageDate1 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper .otoscope-date"));
		WebElement editLeftOtoscopy = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate  :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper :nth-child(2)"));
		WebElement editRightOtoscopy = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate  :nth-child(1) :nth-child(2) .otoscope-bar .edit-image.btn-wrapper :nth-child(2)"));
		WebElement deleteLeftOtosocpy = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper :nth-child(3)"));
		WebElement deleteRightOtosocpy = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) .otoscope-bar .edit-image.btn-wrapper :nth-child(3)"));
		System.out.println("Test OTO-203 step 1 passed");

		// Click on edit left image
		editLeftOtoscopy.click();
		Temp.tryCatch(3000);
		WebElement previousLeftImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-panel.edit-otoscope .previous-otoscope.clickable"));
		System.out.println("Test OTO-203 step 2 passed");
//Take a new image
		WebElement captureButtonLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .capture-otoscope"));
		captureButtonLeft.click();
		Temp.tryCatch(4000);
		// find 2 button for saving the image
		WebElement noCropping = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1)  :nth-child(1) .otoscope-bar .crop-otoscope.btn-wrapper :nth-child(2)"));
		WebElement saveImage = webDriver.findElement(By.cssSelector(
				" #audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .crop-otoscope :nth-child(1).otoscope-btn"));
		System.out.println("Test OTO-203 step 3 passed");
		// click on no cropping button
		noCropping.click();
		Temp.tryCatch(4000);
		WebElement confirmEditOtoscopyModalLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .panel-confirm"));
		System.out.println("Test OTO-203 step 4 passed");

		// cancel saving the new image
		WebElement cancelEditOtoscopyLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .panel-confirm a"));
		cancelEditOtoscopyLeft.click();
		Temp.tryCatch(4000);
		WebElement LeftImageDate2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper .otoscope-date"));
		Temp.checkEqualString(LeftImageDate1.getText(), LeftImageDate2.getText(), "Test OTO-203 step 5");

		// click again on edit left image
		WebElement editLeftOtoscopy2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate  :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper :nth-child(2)"));
		editLeftOtoscopy2.click();
		Temp.tryCatch(7000);
		WebElement captureButtonLeft2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .capture-otoscope"));
		captureButtonLeft2.click();
		Temp.tryCatch(10000);
		WebElement noCropping2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1)  :nth-child(1) .otoscope-bar .crop-otoscope.btn-wrapper :nth-child(2)"));
		noCropping.click();
		Temp.tryCatch(8000);
		WebElement confirmEdit = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .panel-confirm .btn-primary"));
		confirmEdit.click();
		Temp.tryCatch(5000);
		WebElement LeftImageDate3 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper .otoscope-date"));
		Temp.checkDiffString(LeftImageDate2.getText(), LeftImageDate3.getText(), "Test OTO-203 step 6");

		// delete the left image
		WebElement deleteImageLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper :nth-child(3)"));
		deleteImageLeft.click();
		Temp.tryCatch(3000);
		WebElement deleteConfirmModal = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view :nth-child(2) .panel-confirm"));
		System.out.println("Test OTO-203 step 7 passed");

		WebElement cancelDeleteModal = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view :nth-child(2) .panel-confirm a"));
		cancelDeleteModal.click();
		Temp.tryCatch(3000);
		WebElement LeftImageDate4 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper .otoscope-date"));
		Temp.checkEqualString(LeftImageDate4.getText(), LeftImageDate3.getText(), "Test OTO-203 step 8");

		WebElement deleteImageLeft2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper :nth-child(3)"));
		deleteImageLeft2.click();
		Temp.tryCatch(3000);
		WebElement confirmDeleteImageLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view :nth-child(2) .panel-confirm .btn-primary"));
		confirmDeleteImageLeft.click();
		Temp.tryCatch(8000);

		WebElement LeftImageDate5 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper .otoscope-date"));
		Temp.checkEqualString(LeftImageDate5.getText(), LeftImageDate5.getText(), "Test OTO-203 step 9");

	}

}
