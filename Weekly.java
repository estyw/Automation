package expert;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

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
		webDriver.findElement(By.tagName("body")).sendKeys(Keys.LEFT_CONTROL, Keys.ADD);
		webDriver.get(baseURL);
	}

	/**
	 * sign in with user name and PW
	 */
	public void signIn() {
		WebElement user = webDriver.findElement(By.id("email"));
		WebElement password = webDriver.findElement(By.id("password"));
		WebElement submit = webDriver.findElement(By.name("commit"));
		user.sendKeys("qa@audyx.com");
		password.sendKeys("1234567Qw");
		submit.click();
		Temp.tryCatch(10000);
	}

	/**
	 * Create a new patient
	 */
	@Test
	@DisplayName("Creating a new patient")

	public void createNewPatient() {
		String currentDate = Temp.printDate();

		WebElement koalysIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		koalysIcon.click();
		Temp.tryCatch(5000);
		WebElement addPatient = webDriver
				.findElement(By.cssSelector("#patient-list-body .row.patients-pagination .col-lg-2 .fa-plus"));
		addPatient.click();
		Temp.tryCatch(5000);
		WebElement firstName = webDriver.findElement(
				By.cssSelector("#create-patient-panel .panel-body .row .col-lg-5 .form-group .patient-firstname"));
		firstName.sendKeys("Test");
		Temp.tryCatch(2000);
		WebElement lastName2 = webDriver.findElement(
				By.cssSelector("#create-patient-panel .panel-body .row .col-lg-5 .form-group .patient-name"));
		lastName2.sendKeys("PAT " + currentDate + "_" + RandomStringUtils.randomNumeric(2));
		Temp.tryCatch(2000);
		WebElement birthdate = webDriver.findElement(By.cssSelector(
				"#create-patient-panel .panel-body .row .col-lg-5 .form-group .input-group .patient-birthdate"));
		birthdate.sendKeys("06052014");
		Temp.tryCatch(2000);
		// Submit new patient
		WebElement submitNewPatient = webDriver
				.findElement(By.cssSelector("#create-patient-panel .panel-body .ad-button-group .submit-patient-form"));
		submitNewPatient.click();
		Temp.tryCatch(7000);
	}

	/**
	 * entering tonal & otoscopy in order to accept the windows pop-up
	 */

	public void AccessingTonal() {
		// accessing to tonal via nav bar
		WebElement tonalNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test1 .tonal"));
		tonalNavBar.click();
		Temp.tryCatch(5000);

		// moving back to home page
		WebElement koalysIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		koalysIcon.click();
		Temp.tryCatch(5000);
	}

//====== Otoscopy 
	/**
	 * Configure camera, take otoscopy image zooming & cropping Edit & delete image
	 * configure otoscopy device and access to screen
	 */

	public void OTO101AccessToModule() {

		// select the first patient in patient list
		// WebElement selectPatient = webDriver
		/// .findElement(By.cssSelector("#patient-list-body tr:nth-child(1)
		// td.patient-fullname"));

		// selectPatient.click();
		// Temp.tryCatch(5000);

		// access to otoscopy screen via side bar
		WebElement sideBar2 = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .navigator"));
		sideBar2.click();
		Temp.tryCatch(2000);
		Temp.scrollDown(webDriver);

		WebElement otoscopyIcon = webDriver.findElement(By.cssSelector("#menu-side :nth-child(8)"));
		otoscopyIcon.click();
		WebElement mainPanel = webDriver.findElement(By.id("audyx-main"));
		Actions act = new Actions(webDriver);
		act.moveToElement(mainPanel).perform();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.koalys.com/#/otoscope", webDriver.getCurrentUrl(), "Test OTO-101 step 1");

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
	 * first connecting - configure the camera in audio setup page. include also
	 * test OTO-101 step 3
	 */
	public void OTO102DeviceConfiguration() {
		WebElement patientIcon2 = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text :nth-child(2) .ad-name"));
		patientIcon2.click();
		Temp.tryCatch(5000);

		WebElement rightEar = webDriver
				.findElement(By.cssSelector("#audyx-main .ears-condition.row fieldset:nth-child(1) legend"));
		WebElement leftEar = webDriver
				.findElement(By.cssSelector("#audyx-main .ears-condition.row fieldset:nth-child(2) legend"));
//click on camera icon in order to get otoscopy page
		WebElement cameraIcon = webDriver.findElement(
				By.cssSelector("#patient-info .panel-body :nth-child(2) .ears-condition.row fieldset:nth-child(1) i"));
		cameraIcon.click();
		Temp.tryCatch(2000);
		Temp.scrollDown(webDriver);
		Temp.checkUrl("https://alpha.koalys.com/#/otoscope", webDriver.getCurrentUrl(), "Test OTO-101 step 3");

		WebElement setupLink = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-panel.empty-otoscope .device-status-text .device-status-action"));
		String setupLink1 = Temp.printElementText(setupLink);
		Temp.checkContainString(setupLink1, "Setup", "Test OTO-102 step 1");
//click on setup link to config the camera
		setupLink.click();
		Temp.tryCatch(4000);
		Temp.checkUrl("https://alpha.koalys.com/#/audio-setup", webDriver.getCurrentUrl(), "Test OTO-102 step 2");

		// select FF cabin order to configure the camera

		WebElement cabinArea = webDriver.findElement(By.cssSelector("#audyx-main .cabin-option.placeholder"));
		cabinArea.click();
		Temp.tryCatch(3000);
		WebElement selectCabin = webDriver
				.findElement(By.cssSelector("#select2-drop :nth-child(1) .select2-result-label .cabin-name"));
		selectCabin.click();
		Temp.tryCatch(3000);
		WebElement acceptCabin = webDriver.findElement(By.cssSelector("body :nth-child(9) .modal-footer button"));
		acceptCabin.click();
		Temp.tryCatch(3000);

		// choose the otoscopy device and perform refresh several times

		Temp.tryCatch(3000);
		WebElement otoscopyArea = webDriver
				.findElement(By.cssSelector("#audyx-main span.select2-chosen .device-option .device-label"));
		otoscopyArea.click();
		Temp.tryCatch(3000);
		// clear previous selection
		WebElement clearPreSelection = webDriver.findElement(By.cssSelector(
				"#select2-drop .select2-results-dept-0.select2-result.select2-result-selectable.empty-device-option"));
		clearPreSelection.click();
		Temp.tryCatch(3000);

		// click again on otoscopy area in order to select the device
		for (int i = 0; i < 2; i++) {
			WebElement otoscopyArea2 = webDriver
					.findElement(By.cssSelector("#audyx-main :nth-child(4) .select2-chosen .placeholder"));
			otoscopyArea2.click();
			Temp.tryCatch(3000);

			WebElement chooseOtoscopyDevice = webDriver.findElement(By.cssSelector(
					"#select2-drop .select2-highlighted .select2-result-label .device-option .device-label"));
			chooseOtoscopyDevice.click();
			Temp.tryCatch(4000);
			webDriver.navigate().refresh();
			Temp.tryCatch(4000);
		}
		WebElement patientIcon = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text :nth-child(2) .ad-name"));
		patientIcon.click();
		Temp.tryCatch(5000);
		WebElement cameraIcon2 = webDriver.findElement(By.cssSelector(
				"#patient-info .panel-body :nth-child(2) .ears-condition.row fieldset:nth-child(1) .fa-camera"));
		cameraIcon2.click();
		Temp.tryCatch(6000);
		// icon of otoscopy device status, it can be green = of the device is connected,
		// and red if not.
		WebElement deviceStatus3 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .empty-otoscope .device-status-text"));

		Temp.checkContainString(deviceStatus3.getText(), "Device is connected", "Test OTO-102 step 3");
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
		Temp.tryCatch(9000);
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
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel .otoscope-image "));
		WebElement imageDateLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main  .view-animate  :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper .otoscope-date"));
		System.out.println("Test OTO-201 step 3 passed");

		// Take right ear image
		// Click R
		Temp.tryCatch(3000);
		WebElement rightEar2 = webDriver.findElement(By
				.cssSelector("#audyx-main .body section .view-animate :nth-child(1) :nth-child(2) .otoscope-ear .ear"));
		rightEar2.click();
		Temp.tryCatch(5000);
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
		Temp.tryCatch(4000);
		WebElement savedImageRight = webDriver
				.findElement(By.cssSelector("#audyx-main :nth-child(2) :nth-child(2) .otoscope-view .otoscope-image"));
		WebElement imageDateRight = webDriver.findElement(By.cssSelector(
				"#audyx-main :nth-child(2) :nth-child(2) .otoscope-bar .edit-image.btn-wrapper .otoscope-date"));
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
		Temp.tryCatch(8000);
		WebElement rightEar3 = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(2) .otoscope-ear .ear"));
		rightEar3.click();
		Temp.tryCatch(6000);
		WebElement previousRightImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(2) .otoscope-view .otoscope-panel.edit-otoscope .previous-otoscope.clickable"));
		WebElement currentRightLiveImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(2) .otoscope-view .otoscope-panel.edit-otoscope .webcam video"));
		System.out.println("Test OTO-202 step 5.1 passed");

		WebElement captureButtonRight = webDriver.findElement(By
				.cssSelector("#audyx-main .view-animate :nth-child(1) :nth-child(2) .otoscope-bar .capture-otoscope"));
		captureButtonRight.click();
		Temp.tryCatch(6000);
		WebElement imageCircleRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(2) .otoscope-view .otoscope-panel.edit-otoscope .edit-mode"));
		System.out.println("Test OTO-202 step 5.2 passed");

		Actions act2 = new Actions(webDriver);
		act2.dragAndDropBy(imageCircleRight, -350, -400).build().perform();
		WebElement submitOtoscopy2Right = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) .otoscope-bar .crop-otoscope.btn-wrapper .otoscope-btn:nth-child(1)"));
		Temp.tryCatch(6000);
		submitOtoscopy2Right.click();
		Temp.tryCatch(8000);
		WebElement confirmEditOtoscopyModalRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(2) .otoscope-panel.edit-otoscope .panel-confirm .btn.btn-primary"));
		System.out.println("Test OTO-202 step 5.3 passed");

		Temp.tryCatch(3000);
		confirmEditOtoscopyModalRight.click();
		Temp.tryCatch(9000);
		System.out.println("Test OTO-202 step 5.4 passed");
		Temp.tryCatch(8000);

	}

	/**
	 * Edit & delete image
	 */
//select a patient with otoscopy images		
	// include test OTO-101 4&5
	// go to patient folder and see the saved otoscopy image
	public void OTO203EditDeleteOtoscopy() {
		WebElement patientIcon204 = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text .ad-name"));
		patientIcon204.click();
		Temp.tryCatch(5000);
		WebElement otoscopyImage = webDriver.findElement(By.cssSelector(".ear-condition .col-lg-5.otoscope-img"));
		System.out.println("Test OTO-101 step 4 passed");

		otoscopyImage.click();
		Temp.tryCatch(4000);
		Temp.checkUrl("https://alpha.koalys.com/#/otoscope", webDriver.getCurrentUrl(), "Test OTO-101 step 5");

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
		Temp.tryCatch(5000);
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
		System.out.println("Otoscopy test ran successfully");
	}

	/**
	 * Navigate to tonal test via nav bar
	 */
	public void TON101TestPreparation() {
		// first create a patient
		// Tonal.createNewPatient();
		// click on tonal icon and validate that it's in tonal screen
		WebElement tonalNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test1 .tonal"));
		tonalNavBar.click();
		Temp.tryCatch(5000);
		Temp.checkUrl("https://alpha.koalys.com/#/test/tone", webDriver.getCurrentUrl(), "Test TON-101 step 1");

		// Ignore alert cabin
		WebElement ignoreAlertCabin = webDriver
				.findElement(By.cssSelector("body  .modal-footer :nth-child(2).btn.btn-primary"));
		ignoreAlertCabin.click();
		Temp.tryCatch(3000);

		WebElement koalysIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		koalysIcon.click();
		Temp.tryCatch(5000);
		WebElement sideBar = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .navigator"));
		sideBar.click();
		Temp.tryCatch(3000);
		WebElement testMenu = webDriver.findElement(By.cssSelector("#testsMenu"));
		testMenu.click();
		Temp.tryCatch(3000);
		WebElement tonalIcon = webDriver
				.findElement(By.cssSelector("#audyx-main .ad-slide-menu.sub .app-a .ad-sprite.tonal"));
		tonalIcon.click();
		Temp.tryCatch(3000);
		WebElement mainPanel = webDriver.findElement(By.id("audyx-main"));
		Actions act = new Actions(webDriver);
		act.moveToElement(mainPanel).perform();
		Temp.tryCatch(1000);
		Temp.checkUrl("https://alpha.koalys.com/#/test/tone", webDriver.getCurrentUrl(), "Test TON-101 step 2");

		// view all elements in tonal page
		WebElement commands = webDriver
				.findElement(By.cssSelector("#test-screen .top-right-panel .test-commands-panel"));
		WebElement graphArea = webDriver
				.findElement(By.cssSelector("#test-type-2 > div.panel :nth-child(1) .div-chart.type-2"));
		WebElement cabin = webDriver.findElement(By.cssSelector("#test-screen :nth-child(2) .panel-body .cabin-view"));
		WebElement startIcon = webDriver.findElement(By.cssSelector("#test-screen .panel-body .start-test .fa-play"));

		System.out.println("Test TON-101 step 3 passed");

	}

	/**
	 * Check MCL&UCL icons also appear
	 */
	public void TON102MeasurementType() {
		// See threshold type is playing
		// you are in tonal test from the previous test
		WebElement tonalIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap .tonal"));
		tonalIcon.click();
		Temp.tryCatch(10000);

		// check you are in threshold mode
		WebElement threshold = webDriver
				.findElement(By.cssSelector("#test-screen .test-commands-panel .measurement-title.active"));
		String currentThreshold = threshold.getAttribute("innerHTML");
		System.out.println(currentThreshold);
		Temp.checkContainString(currentThreshold, "Threshold", "Test TON-102 step 1");

		// change to MCL
		Temp.tryCatch(3000);

		WebElement testCommandPanel = webDriver.findElement(By.className("test-commands-panel"));
		testCommandPanel.click();
		Temp.tryCatch(3000);
		WebElement chooseMcl = webDriver
				.findElement(By.cssSelector("#test-screen .test-commands-panel .measurement-title:nth-child(2)"));
		chooseMcl.click();
		Temp.tryCatch(3000);
		WebElement closePanel = webDriver.findElement(By.className("chart-container"));
		closePanel.click();
		Temp.tryCatch(2000);

		// check you are in MCL mode
		WebElement mcl = webDriver
				.findElement(By.cssSelector("#test-screen .test-commands-panel .measurement-title.active"));
		String currentMcl = mcl.getAttribute("innerHTML");
		System.out.println(currentMcl);
		Temp.checkContainString(currentMcl, "MCL", "Test TON-102 step 2");

		WebElement testCommandPanel2 = webDriver.findElement(By.className("test-commands-panel"));
		testCommandPanel2.click();
		Temp.tryCatch(3000);
		WebElement chooseUcl = webDriver
				.findElement(By.cssSelector("#test-screen .test-commands-panel .measurement-title:nth-child(3)"));
		chooseUcl.click();
		Temp.tryCatch(3000);
		WebElement closePanel2 = webDriver.findElement(By.className("chart-container"));
		closePanel2.click();
		Temp.tryCatch(2000);
		// check you are in UCL mode
		WebElement ucl = webDriver
				.findElement(By.cssSelector("#test-screen .test-commands-panel .measurement-title.active"));
		String currentUcl = ucl.getAttribute("innerHTML");
		System.out.println(currentUcl);
		Temp.checkContainString(currentUcl, "UCL", "Test TON-102 step 3");

	}

//Check type of signals appear in nav bar
	public void TON103Signalsconfiguration() {
		WebElement tonalIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap .tonal"));
		tonalIcon.click();
		Temp.tryCatch(10000);

		WebElement cabinPanel = webDriver.findElement(By.className("cabin-params-panel"));
		cabinPanel.click();
		Temp.tryCatch(3000);

		WebElement signals = webDriver
				.findElement(By.cssSelector("#test-screen .signal.lg-widget-icon.audyx-icon-pure"));
		signals.click();
		Temp.tryCatch(3000);
		WebElement signalOptions = webDriver
				.findElement(By.cssSelector("#test-screen .signal.lg-widget-icon.audyx-icon-pure .options"));
		System.out.println("Test TON-103 step 1 passed");

		WebElement presentation = webDriver
				.findElement(By.cssSelector("#test-screen .signal.lg-widget-icon.audyx-icon-repeated"));
		presentation.click();
		Temp.tryCatch(3000);
		WebElement presentationOptions = webDriver
				.findElement(By.cssSelector("#test-screen .signal.lg-widget-icon.audyx-icon-repeated .options"));
		System.out.println("Test TON-103 step 2 passed");

		WebElement masking = webDriver
				.findElement(By.cssSelector("#test-screen .signal.lg-widget-icon.audyx-icon-filtered-noise"));
		masking.click();
		Temp.tryCatch(3000);
		WebElement maskingOptions = webDriver
				.findElement(By.cssSelector("#test-screen .signal.lg-widget-icon.audyx-icon-filtered-noise .options"));
		System.out.println("Test TON-103 step 3 passed");
	}

	/**
	 * See all transducers in cabin avatar, only calibrated are clickable
	 */
	public void TON104TransducerSelection() {
		WebElement tonalIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap .tonal"));
		tonalIcon.click();
		Temp.tryCatch(10000);

		WebElement cabinPanel = webDriver.findElement(By.className("cabin-params-panel"));
		cabinPanel.click();
		Temp.tryCatch(3000);

		WebElement ac = webDriver.findElement(By.cssSelector("#test-screen .transducers .lg-widget-icon.AirConductor"));
		Temp.checkElementEnabled(ac, "AC");
		WebElement insert = webDriver
				.findElement(By.cssSelector("#test-screen .transducers .lg-widget-icon.InsertPhone"));
		Temp.checkElementEnabled(insert, "Insert");
		WebElement bone = webDriver
				.findElement(By.cssSelector("#test-screen .transducers .lg-widget-icon.BoneConductor"));
		Temp.checkElementEnabled(bone, "Bone");
		WebElement FF = webDriver.findElement(By.cssSelector("#test-screen .transducers .lg-widget-icon.FreeField"));
		Temp.checkElementEnabled(FF, "FF");
		System.out.println("Test TON-104 step 1 passed");
	}

	/**
	 * See the dB is changed
	 */
	public void TON201LevelControls() {
		WebElement tonalIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap .tonal"));
		tonalIcon.click();
		Temp.tryCatch(10000);
		// View the current level
		WebElement currentDB1 = webDriver.findElement(
				By.cssSelector("#test-screen .graph-actions .graph-volume .volume-controls .flow-value .title"));
		String currentDB11 = Temp.printElementText(currentDB1);
		Temp.tryCatch(3000);
		Actions action = new Actions(webDriver);
		action.sendKeys(Keys.ARROW_UP).build().perform();
		Temp.tryCatch(4000);
		action.sendKeys(Keys.ARROW_UP).build().perform();
		WebElement upDB = webDriver.findElement(By.className("fa-long-arrow-up"));
		Temp.clickElement(upDB, 1);
		// Check if the dB is changed
		WebElement currentDB2 = webDriver.findElement(
				By.cssSelector("#test-screen .graph-actions .graph-volume .volume-controls .flow-value .title"));
		String currentDB22 = Temp.printElementText(currentDB2);
		Temp.checkDiffString(currentDB11, currentDB22, "Test TON-201 step 1.1");

		WebElement downDB = webDriver.findElement(By.className("fa-long-arrow-down"));
		Temp.clickElement(downDB, 2);
		// Check if the dB is changed
		WebElement currentDB4 = webDriver.findElement(
				By.cssSelector("#test-screen .graph-actions .graph-volume .volume-controls .flow-value .title"));
		String currentDB44 = Temp.printElementText(currentDB4);
		Temp.checkDiffString(currentDB22, currentDB44, "Test TON-201 step 1.2");
		// Use keyboard short cut
		Temp.tryCatch(3000);
		// WebElement cursor = webDriver.findElement(By.cssSelector("#cursor"));
		Actions action3 = new Actions(webDriver);
		action3.sendKeys(Keys.ARROW_UP).build().perform();
		Temp.tryCatch(4000);
		System.out.println("Test TON-201 step 2.1 passed");

		action3.sendKeys(Keys.ARROW_DOWN).build().perform();
		Temp.tryCatch(3000);
		System.out.println("Test TON-201 step 2.2 passed");
		// Change test to MCL and check you can navigate right and left
		WebElement testCommandPanel = webDriver.findElement(By.className("test-commands-panel"));
		testCommandPanel.click();
		Temp.tryCatch(3000);
		WebElement chooseMcl = webDriver
				.findElement(By.cssSelector("#test-screen .test-commands-panel .measurement-title:nth-child(2)"));
		chooseMcl.click();
		Temp.tryCatch(3000);

		WebElement playMcl = webDriver.findElement(By.className("fa-play"));
		Temp.clickElement(playMcl, 1);

		// click on + button
		WebElement plusButton = webDriver.findElement(By.className("plus-active"));
		Temp.clickElement(plusButton, 1);

		// Type keyboard shortcut on + and -
		Actions action2 = new Actions(webDriver);
		action2.sendKeys(Keys.ADD).build().perform();
		Temp.tryCatch(2000);
		action.sendKeys(Keys.SUBTRACT).build().perform();
		Temp.tryCatch(2000);
		System.out.println("Test TON-201 step 3 passed");
	}

	/**
	 * Test this test with bone & AC check the frequency in ruler bar is changed
	 */
	public void TON202FrequenciesControl() {
		WebElement tonalNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test1 .tonal"));
		tonalNavBar.click();
		Temp.tryCatch(3000);
		// click on 1.5K element
		WebElement firstdB = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body li:nth-child(4) .frequency-units"));
		firstdB.click();
		Temp.tryCatch(2000);
		Temp.printElementText(firstdB);
		Temp.tryCatch(2000);
		// click on 3K element
		WebElement seconddB = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body li:nth-child(6) .frequency-units"));
		seconddB.click();
		Temp.tryCatch(2000);
		Temp.printElementText(seconddB);
		Temp.tryCatch(2000);
		// click on 6K element
		WebElement thirddB = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body li:nth-child(8) .frequency-units"));
		thirddB.click();
		Temp.tryCatch(2000);
		Temp.printElementText(thirddB);
		Temp.tryCatch(2000);
		// click on 750dB element
		WebElement forthdB = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body li:nth-child(11) .frequency-units"));
		forthdB.click();
		Temp.tryCatch(2000);
		Temp.printElementText(forthdB);
		Temp.tryCatch(2000);
		System.out.println("Test TON-202 step 1 passed");

		// choose AC device and see the available frequencies. then change the
		// transducer and see the frequencies are changed
		WebElement cabinPanel = webDriver.findElement(By.className("cabin-params-panel"));
		cabinPanel.click();
		Temp.tryCatch(3000);
		WebElement ac = webDriver.findElement(By.cssSelector("#test-screen .transducers .lg-widget-icon.AirConductor"));
		ac.click();
		Temp.tryCatch(3000);

		// this webelement is 6K
		String ACdB = Temp.printElementText(thirddB);

		// change transducer and see if available frequencies are changed
		WebElement cabinPanel2 = webDriver.findElement(By.className("cabin-params-panel"));
		cabinPanel2.click();
		Temp.tryCatch(3000);
		WebElement bone = webDriver
				.findElement(By.cssSelector("#test-screen .transducers .lg-widget-icon.BoneConductor"));
		bone.click();
		Temp.tryCatch(1000);
		// check if the dB in ruler were changed
		String bonedB = Temp.printElementText(thirddB);
		Temp.checkDiffString(ACdB, bonedB, "Test TON-202 step 2");
	}

	/**
	 * Play tonal test this test include also TON-302 all steps: 1-3
	 */
	public void TON203Play() {
		// select again AC device
		// transducer and see the frequencies are changed
		WebElement cabinPanel = webDriver.findElement(By.className("cabin-params-panel"));
		cabinPanel.click();
		Temp.tryCatch(3000);
		WebElement ac = webDriver.findElement(By.cssSelector("#test-screen .transducers .lg-widget-icon.AirConductor"));
		ac.click();
		Temp.tryCatch(3000);

		WebElement startPlay = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content .panel-confirm.panel-test-init .fa-play"));
		startPlay.click();
		Temp.tryCatch(3000);

		WebElement yes = webDriver.findElement(By.cssSelector(
				" #test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(1) .thumb-up"));
		WebElement no = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(2) .thumb-down"));
		System.out.println("Test TON-302 step 1 passed");
		yes.click();
		Temp.tryCatch(3000);
		System.out.println("Test TON-302 step 2 passed");
		WebElement nextDB = webDriver.findElement(By.cssSelector("#svg-flow :nth-child(7).datapoint-text"));
		no.click();
		Temp.tryCatch(3000);
		WebElement nextDB2 = webDriver.findElement(By.cssSelector("#svg-flow :nth-child(9).datapoint-text"));
		System.out.println("Test TON-302 step 3 passed");

		for (int i = 0; i < 2; i++) {
			no.click();
			Temp.tryCatch(3000);
			yes.click();
			Temp.tryCatch(3000);
			no.click();
			Temp.tryCatch(3000);
			yes.click();
			Temp.tryCatch(3000);
			no.click();
			Temp.tryCatch(3000);
			no.click();
			Temp.tryCatch(3000);
			yes.click();
			Temp.tryCatch(3000);
		}
		// the score for this frequency is determined
		WebElement savedPoint = webDriver
				.findElement(By.cssSelector("#test-type-2 .panel :nth-child(1) :nth-child(3)  circle:nth-child(2)"));
		WebElement cursorNextDB = webDriver.findElement(By.cssSelector("#cursor"));
		System.out.println("Test TON-302 step 4 passed");

		WebElement newTestButton = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body .actions button.btn.btn-primary.pull-right.next-ear :nth-child(1)"));
		newTestButton.click();
		Temp.tryCatch(2000);
		WebElement startPlay1 = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content .panel-confirm.panel-test-init .fa-play"));
		Temp.checkButtonClickable(startPlay1, "Test TON-203 step 1");

		startPlay1.click();
		Temp.tryCatch(4000);
		WebElement yes2 = webDriver.findElement(By.cssSelector(
				" #test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(1) .thumb-up"));
		WebElement no2 = webDriver.findElement(By.cssSelector(
				" #test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(2) .thumb-down"));

		for (int i = 0; i < 2; i++) {

			yes2.click();
			Temp.tryCatch(3000);
			no2.click();
			Temp.tryCatch(3000);
			no2.click();
			Temp.tryCatch(3000);
			yes2.click();
			Temp.tryCatch(3000);
			no2.click();
			Temp.tryCatch(3000);
			no2.click();
			Temp.tryCatch(3000);
			yes2.click();
			Temp.tryCatch(3000);
		}

		WebElement restartButton = webDriver.findElement(
				By.cssSelector("#test-screen :nth-child(1) :nth-child(2) .panel-body .actions :nth-child(2)"));
		restartButton.click();
		Temp.tryCatch(3000);
		WebElement restartModal = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(1) :nth-child(2) :nth-child(2).panel-exit-test"));
		WebElement acceptRestartTest = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(1) :nth-child(2) :nth-child(2) .btn-primary"));
		acceptRestartTest.click();
		Temp.tryCatch(4000);
		WebElement startPlay2 = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content .panel-confirm.panel-test-init .fa-play"));
		System.out.println("Test TON-203 step 2 passed");
		

		System.out.println("This is a manual test");
		WebElement currentFre1 = webDriver.findElement(By.cssSelector(
				"#test-screen .col-lg-6.col-no-left-padding .top-right-panel :nth-child(1) .graph-frequencies .loop-values .loop-value.current .title"));
		String currentFrequency1 = Temp.printElementText(currentFre1);
		WebElement leftArrow = webDriver.findElement(By.cssSelector(
				"#test-screen .col-lg-6.col-no-left-padding .top-right-panel :nth-child(1) .graph-frequencies .btn-link .fa-long-arrow-left"));
		leftArrow.click();
		Temp.tryCatch(3000);
		WebElement currentFre2 = webDriver.findElement(By.cssSelector(
				"#test-screen .col-lg-6.col-no-left-padding .top-right-panel :nth-child(1) .graph-frequencies .loop-values .loop-value.current .title"));
		String currentFrequency2 = Temp.printElementText(currentFre2);
		Temp.checkDiffString(currentFrequency1, currentFrequency2, "Test TON-203 step 3");

		WebElement currentDB1 = webDriver.findElement(By.cssSelector(
				"#test-screen .col-no-left-padding .top-right-panel :nth-child(1) .graph-volume .volume-controls .flow-value.current .title"));
		String currentDB11 = Temp.printElementText(currentDB1);
		WebElement upDB = webDriver.findElement(By.cssSelector(
				"#test-screen .col-no-left-padding .top-right-panel :nth-child(1) .graph-volume .volume-controls .btn-link:nth-child(1) .fa-long-arrow-up"));
		upDB.click();
		Temp.tryCatch(3000);
		WebElement currentDB2 = webDriver.findElement(By.cssSelector(
				"#test-screen .col-no-left-padding .top-right-panel :nth-child(1) .graph-volume .volume-controls .flow-value.current .title"));
		String currentDB22 = Temp.printElementText(currentDB2);
		Temp.checkDiffString(currentDB11, currentDB22, "Test TON-203 step 4");

		WebElement downDB = webDriver.findElement(By.cssSelector(
				"#test-screen .col-lg-6.col-no-left-padding .top-right-panel :nth-child(1) .graph-volume .volume-controls :nth-child(5) .fa-long-arrow-down"));
		downDB.click();
		Temp.tryCatch(3000);
		downDB.click();
		Temp.tryCatch(3000);
		WebElement saveTestButton = webDriver.findElement(
				By.cssSelector("#test-screen :nth-child(1) :nth-child(2) .panel-body .actions .save-test"));
		Temp.checkButtonNotClickable(saveTestButton, "Test TON-203 step 5");

		System.out.println("This is a guided test");
		Temp.tryCatch(2000);
		WebElement startPlay3 = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content .panel-confirm.panel-test-init .fa-play"));
		startPlay3.click();
		Temp.tryCatch(4000);
		WebElement yes3 = webDriver.findElement(By.cssSelector(
				" #test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(1) .thumb-up"));
		WebElement no3 = webDriver.findElement(By.cssSelector(
				" #test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(2) .thumb-down"));
		yes3.click();
		Temp.tryCatch(2000);
		no3.click();
		Temp.tryCatch(2000);
		yes3.click();
		Temp.tryCatch(2000);
		no3.click();
		Temp.tryCatch(2000);
		no3.click();
		Temp.tryCatch(2000);
		yes3.click();
		Temp.tryCatch(2000);
		WebElement playIcon = webDriver.findElement(By
				.cssSelector("#test-screen .col-lg-6.col-no-left-padding .top-right-panel :nth-child(1) .fa.fa-play"));
		WebElement thresholdCircle = webDriver.findElement(
				By.cssSelector("#test-type-2 :nth-child(1) .type-2 :nth-child(1) :nth-child(2) :nth-child(3) circle"));
		System.out.println("Test TON-203 step 6 passed");

		WebElement restartButton2 = webDriver.findElement(
				By.cssSelector("#test-screen :nth-child(1) :nth-child(2) .panel-body .actions :nth-child(2)"));
		restartButton2.click();
		Temp.tryCatch(3000);
		WebElement restartModal2 = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(1) :nth-child(2) :nth-child(2).panel-exit-test"));
		System.out.println("Test TON-203 step 7 passed");

		WebElement continueCurrentTest = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) :nth-child(1) :nth-child(2).panel-confirm.panel-exit-test a"));
		continueCurrentTest.click();
		Temp.tryCatch(4000);
		WebElement thresholdCircle2 = webDriver.findElement(
				By.cssSelector("#test-type-2 :nth-child(1) .type-2 :nth-child(1) :nth-child(2) :nth-child(3) circle"));
		System.out.println("Test TON-203 step 8 passed");

		yes3.click();
		Temp.tryCatch(2000);
		no3.click();
		Temp.tryCatch(2000);
		yes3.click();
		Temp.tryCatch(2000);
		no3.click();
		Temp.tryCatch(2000);
		no3.click();
		Temp.tryCatch(2000);
		yes3.click();
		Temp.tryCatch(2000);
		WebElement newTestButton2 = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body .actions button.btn.btn-primary.pull-right.next-ear :nth-child(1)"));
		newTestButton2.click();
		Temp.tryCatch(4000);
		WebElement startPlay4 = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content .panel-confirm.panel-test-init .fa-play"));
		System.out.println("Test TON-203 step 9 passed");

		startPlay4.click();
		Temp.tryCatch(4000);

		yes3.click();
		Temp.tryCatch(2000);
		no3.click();
		Temp.tryCatch(2000);
		yes3.click();
		Temp.tryCatch(2000);
		no3.click();
		Temp.tryCatch(2000);
		no3.click();
		Temp.tryCatch(2000);
		yes3.click();
		Temp.tryCatch(2000);

		WebElement saveTest = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body .btn.btn-primary.pull-right.save-test"));
		saveTest.click();
		Temp.tryCatch(3000);
		Temp.checkContainString(webDriver.getCurrentUrl(), "/patientFolder/test/tone/", "Test TON-203 step 10");
	}

	/**
	 * Run tonal and check the points are saved
	 */
	public void TON204PointSaving() {
		// play tonal and save point with s
		WebElement tonalNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test1 .tonal"));
		tonalNavBar.click();
		Temp.tryCatch(5000);
		Actions action1 = new Actions(webDriver);
		action1.sendKeys(Keys.ARROW_UP).build().perform();
		Temp.tryCatch(2000);
		action1.sendKeys(Keys.ARROW_UP).build().perform();
		action1.sendKeys("s").build().perform();
		WebElement saveDB = webDriver.findElement(By.cssSelector("#cursor"));
		System.out.println("Test TON-204 step 1 passed");

		// play MCL and mark V for saving point
		WebElement testCommandPanel1 = webDriver.findElement(By.className("test-commands-panel"));
		testCommandPanel1.click();
		Temp.tryCatch(3000);
		WebElement chooseMcl = webDriver
				.findElement(By.cssSelector("#test-screen .test-commands-panel .measurement-title:nth-child(2)"));
		chooseMcl.click();
		Temp.tryCatch(3000);
		WebElement closePanel1 = webDriver.findElement(By.className("chart-container"));
		closePanel1.click();
		Temp.tryCatch(2000);
		WebElement startPlayTest = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body  .panel-confirm.panel-test-init .fa.fa-play"));
		Temp.clickElement(startPlayTest, 1);
		WebElement plusButton = webDriver.findElement(By.className("plus-active"));
		Temp.clickElement(plusButton, 2);
		Actions action2 = new Actions(webDriver);
		action2.sendKeys("v").build().perform();
		WebElement mclCursor = webDriver.findElement(By.cssSelector("#test-type-2 :nth-child(3) circle"));
		System.out.println("Test TON-204 step 2 passed");

		// play UCL and mark V for saving point
		WebElement testCommandPanel2 = webDriver.findElement(By.className("test-commands-panel"));
		testCommandPanel1.click();
		Temp.tryCatch(3000);
		WebElement chooseUcl = webDriver
				.findElement(By.cssSelector("#test-screen .test-commands-panel .measurement-title:nth-child(3)"));
		chooseUcl.click();
		Temp.tryCatch(3000);
		WebElement closePanel2 = webDriver.findElement(By.className("chart-container"));
		closePanel2.click();
		Temp.tryCatch(2000);
		WebElement playTest = webDriver.findElement(By
				.cssSelector("#test-screen .col-lg-6.col-no-left-padding .top-right-panel :nth-child(1) .fa.fa-play"));
		Temp.clickElement(playTest, 1);
		WebElement plusButton2 = webDriver.findElement(By.className("plus-active"));
		Temp.clickElement(plusButton2, 2);
		Actions action3 = new Actions(webDriver);
		action3.sendKeys("v").build().perform();
		// WebElement uclCursor = webDriver.findElement(By.cssSelector("#test-type-2
		// g:nth-child(3) > circle:nth-child(6)"));
		System.out.println("Test TON-204 step 3 passed");

		// run threshold and mark as not hear with n
		WebElement testCommandPanel3 = webDriver.findElement(By.className("test-commands-panel"));
		testCommandPanel3.click();
		Temp.tryCatch(3000);
		WebElement chooseThreshold = webDriver
				.findElement(By.cssSelector("#test-screen .test-commands-panel .measurement-title:nth-child(1)"));
		chooseThreshold.click();
		Temp.tryCatch(3000);
		WebElement closePanel3 = webDriver.findElement(By.className("chart-container"));
		closePanel3.click();
		Temp.tryCatch(2000);
		Actions action4 = new Actions(webDriver);
		action4.sendKeys("n").build().perform();
		WebElement noResponse = webDriver.findElement(By.cssSelector("#svg-flow foreignObject .no-response"));
		System.out.println("Test TON-204 step 4 passed");

		// run UCl and mark point as not hear
		WebElement testCommandPanel4 = webDriver.findElement(By.className("test-commands-panel"));
		testCommandPanel4.click();
		Temp.tryCatch(3000);
		WebElement chooseUcl2 = webDriver
				.findElement(By.cssSelector("#test-screen .test-commands-panel .measurement-title:nth-child(3)"));
		chooseUcl2.click();
		Temp.tryCatch(3000);
		WebElement closePanel4 = webDriver.findElement(By.className("chart-container"));
		closePanel4.click();
		Temp.tryCatch(2000);

		WebElement plusButton3 = webDriver.findElement(By.className("plus-active"));
		Temp.clickElement(plusButton3, 3);
		Actions action5 = new Actions(webDriver);
		action5.sendKeys("n").build().perform();
		WebElement noResponseUcl = webDriver.findElement(By.cssSelector("#svg-flow foreignObject .no-response"));
		System.out.println("Test TON-204 step 5 passed");

		// change again to threshold in order to mark the point as not hear by clicking
		// no all the time
		WebElement testCommandPanel5 = webDriver.findElement(By.className("test-commands-panel"));
		testCommandPanel5.click();
		Temp.tryCatch(3000);
		WebElement chooseThreshold2 = webDriver
				.findElement(By.cssSelector("#test-screen .test-commands-panel .measurement-title:nth-child(1)"));
		chooseThreshold2.click();
		Temp.tryCatch(3000);
		WebElement closePanel5 = webDriver.findElement(By.className("chart-container"));
		closePanel5.click();
		Temp.tryCatch(2000);
		// move right in graph in order to mark this as not response
		Actions action6 = new Actions(webDriver);
		action6.sendKeys(Keys.ARROW_RIGHT).build().perform();
		Temp.tryCatch(2000);
		WebElement replay = webDriver.findElement(
				By.cssSelector("#test-screen :nth-child(3) :nth-child(1) .buttons-container .play-in-label"));
		replay.click();
		Temp.tryCatch(2000);
		// mark this point as no
		WebElement no = webDriver.findElement(By.cssSelector(" #test-screen .thumb-down"));
		for (int i = 0; i < 4; i++) {
			no.click();
			Temp.tryCatch(4000);
		}
		WebElement warningMessage = webDriver.findElement(By.cssSelector("#test-type-2 .panel  .graph-warning"));
		System.out.println("Test TON-204 step 6 passed");

		// decline the message in order to mark the point as not hear
		WebElement declineWarning = webDriver.findElement(
				By.cssSelector("#test-type-2 .panel .graph-warning .fa.fa-times.response-button.clickable"));
		declineWarning.click();
		Temp.tryCatch(2000);
		WebElement notHearPoint = webDriver
				.findElement(By.cssSelector("#test-type-2 :nth-child(1) :nth-child(3) circle"));
		System.out.println("Test TON-204 step 7 passed");

		// run threshold and delete the point with keyboard shortcut - backspace
		WebElement upDB = webDriver.findElement(By.className("fa-long-arrow-up"));
		Temp.clickElement(upDB, 4);
		WebElement setDb1 = webDriver.findElement(By.cssSelector("#test-screen .volume-point-mark .fa-check"));
		setDb1.click();
		Temp.tryCatch(3000);
		Actions action7 = new Actions(webDriver);
		action7.sendKeys(Keys.BACK_SPACE).build().perform();
		Temp.tryCatch(3000);
		System.out.println("Test TON-204 step 8 passed");

		// run threshold and delete the point with keyboard shortcut - delete button
		WebElement setDb2 = webDriver.findElement(By.cssSelector("#test-screen .volume-point-mark .fa-check"));
		setDb2.click();
		Actions action8 = new Actions(webDriver);
		action8.sendKeys(Keys.DELETE).build().perform();
		Temp.tryCatch(3000);
		System.out.println("Test TON-204 step 9 passed");

		WebElement setDb3 = webDriver.findElement(By.cssSelector("#test-screen .volume-point-mark .fa-check"));
		setDb3.click();
		WebElement deleteButton = webDriver.findElement(By.cssSelector("#test-screen .fa-times-circle"));
		deleteButton.click();
		Temp.tryCatch(3000);
		System.out.println("Test TON-204 step 10 passed");
		// Tonal.cancelTest();
	}

	public void TON205Remeasure() {
//start a new test for re-measure
		Temp.scrollDown(webDriver);
		WebElement newTest = webDriver
				.findElement(By.cssSelector("#test-screen .actions button.btn.btn-primary.pull-right.next-ear"));
		newTest.click();
		Temp.tryCatch(2000);
		WebElement startPlay = webDriver.findElement(By.cssSelector(
				"#test-screen .ad-vocal-test-content .panel-confirm.panel-test-init .start-test .fa.fa-play"));
		startPlay.click();
		Temp.tryCatch(4000);

		WebElement yes = webDriver.findElement(By.cssSelector(
				" #test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(1) .thumb-up"));
		WebElement no = webDriver.findElement(By.cssSelector(
				" #test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(2) .thumb-down"));
		Temp.clickElement(no, 1);
		Temp.clickElement(yes, 1);
//see the score of the dB in the left side
		WebElement noScore = webDriver.findElement(By.cssSelector("#svg-flow > text:nth-child(7)"));
		WebElement yesScore = webDriver.findElement(By.cssSelector("#svg-flow > text:nth-child(8)"));
		System.out.println("Test TON-205 step 1 passed");

		WebElement remeasure = webDriver.findElement(By
				.cssSelector("#test-screen  .ad-vocal-test-content :nth-child(3) .ad-reload .fa.fa-refresh.clickable"));
		remeasure.click();
		Temp.tryCatch(3000);
		WebElement newDataPoint = webDriver.findElement(By.cssSelector("#svg-flow circle"));
		System.out.println("Test TON-205 step 2 passed");
	}

	/**
	 * View all element in tonal graph in patient folder
	 */
	public void TON301AudiogramGraph() {
		WebElement koalysIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		koalysIcon.click();
		Temp.tryCatch(2000);
		WebElement searchPatient = webDriver.findElement(By.className("search-patient"));
		searchPatient.sendKeys("with many tests");
		Temp.tryCatch(3000);
		WebElement patientName = webDriver
				.findElement(By.cssSelector("#patient-list-body table tbody .patient-fullname a"));
		patientName.click();
		Temp.tryCatch(7000);
		Temp.checkContainString(webDriver.getCurrentUrl(), "patientFolder", "Test TON-301 step 1");
		Temp.scrollDown(webDriver);
		// scroll up
		Temp.scrollup(webDriver);
		WebElement increaseView = webDriver.findElement(By.cssSelector("#test-type-2 .panel :nth-child(1) button"));
		increaseView.click();
		Temp.tryCatch(3000);
		WebElement chooseAnotherResults = webDriver.findElement(By.cssSelector(
				"#test-type-2 :nth-child(1) :nth-child(1) :nth-child(1) :nth-child(1).legends-container :nth-child(3) .prevent-selection .test-selector"));
		System.out.println("Test TON-303 step 1 passed");
		chooseAnotherResults.click();
		Temp.tryCatch(3000);
		WebElement previousPointInGraph = webDriver.findElement(By.cssSelector(
				"#test-type-2 :nth-child(2) :nth-child(1) .row .div-chart.type-2 :nth-child(1) :nth-child(1)  :nth-child(2) :nth-child(3) circle:nth-child(10)"));
		WebElement lastPointInGraph = webDriver.findElement(By.cssSelector(
				"#test-type-2 :nth-child(2) :nth-child(1) .row .div-chart.type-2 :nth-child(1) :nth-child(1)  :nth-child(2) :nth-child(3) circle:nth-child(12)"));
		System.out.println("Test TON-301 step 2 passed");

		String color1 = previousPointInGraph.getCssValue("opacity");
		System.out.println("color1: " + color1);
		String color2 = lastPointInGraph.getCssValue("opacity");
		System.out.println("color2: " + color2);
		Temp.checkDiffString(color2, color1, "Test TON-303 step 2");

		WebElement filterView = webDriver.findElement(By.cssSelector("#test-type-2 .panel .row .chart-filter"));
		filterView.click();
		Temp.tryCatch(3000);
		WebElement lastVisit = webDriver
				.findElement(By.cssSelector("#test-type-2 .panel .row .chart-filter :nth-child(2)"));
		lastVisit.click();
		Temp.tryCatch(3000);
		System.out.println("Test TON-301 step 3 passed");

	}

	/**
	 * continue with patient: patient with many tests
	 */
	public void TON304AverageLoss() {
		WebElement speechIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .tests :nth-child(4)"));
		speechIcon.click();
		Temp.tryCatch(3000);
		WebElement speechSilence = webDriver.findElement(By.cssSelector(
				"#audyx-main .menu-bar .tests .tests-menu.menu-vocal :nth-child(1).test-menu .vocal_silence"));
		speechSilence.click();
		Temp.tryCatch(3000);
		WebElement averageRight = webDriver.findElement(By.cssSelector(
				"#test-type-0 .panel .div-chart.type-0 :nth-child(1) :nth-child(2) :nth-child(3) path:nth-child(1)"));
		WebElement averageLeft = webDriver.findElement(By.cssSelector(
				"#test-type-0 .panel .div-chart.type-0 :nth-child(1) :nth-child(2) :nth-child(3) path:nth-child(2)"));
		System.out.println("Test TON-304 step 1 passed");

	}

	public void TON401SilentMode() {
		/*
		 * WebElement koalysIcon =
		 * webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		 * koalysIcon.click(); Temp.tryCatch(2000); WebElement searchPatient =
		 * webDriver.findElement(By.className("search-patient"));
		 * searchPatient.sendKeys("Test PAT 35"); Temp.tryCatch(3000); WebElement
		 * patientName = webDriver .findElement(By.
		 * cssSelector("#patient-list-body table tbody .patient-fullname a"));
		 * patientName.click();
		 */Temp.tryCatch(7000);
		WebElement tonalNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test1 .tonal"));
		tonalNavBar.click();
		Temp.tryCatch(5000);
		WebElement testCommandPanel = webDriver.findElement(By.className("test-commands-panel"));
		testCommandPanel.click();
		Temp.tryCatch(3000);
		WebElement silentMode = webDriver.findElement(By.cssSelector(
				"#test-screen .top-right-panel .test-panel-animation :nth-child(3) .silent-mode.audyx-icon-silent-mode"));
		Actions act = new Actions(webDriver);
		act.moveToElement(silentMode).perform();
		Temp.tryCatch(3000);
		WebElement silentModeHelper = webDriver.findElement(By.cssSelector(
				"#test-screen .top-right-panel .test-panel-animation :nth-child(3) .command-panel.displayed"));
		System.out.println("Test TON-401 step 1 passed");

		silentMode.click();
		Temp.tryCatch(3000);
		WebElement startPlay = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content .panel-confirm.panel-test-init .fa-play"));
		startPlay.click();
		Temp.tryCatch(3000);
		WebElement yes = webDriver.findElement(By.cssSelector(
				" #test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(1) .thumb-up"));
		WebElement no = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(2) .thumb-down"));
		Actions act2 = new Actions(webDriver);
		act2.moveToElement(yes).perform();
		Temp.tryCatch(2000);
		WebElement nextDB = webDriver.findElement(By.cssSelector("#svg-flow .datapoint-text:nth-child(7)"));
		Actions act3 = new Actions(webDriver);
		act3.moveToElement(no).perform();
		WebElement nextDB2 = webDriver.findElement(By.cssSelector("#svg-flow .datapoint-text:nth-child(9)"));
		Temp.tryCatch(2000);
		System.out.println("Test TON-401 step 2 passed");
		// finish test and exit
		// WebElement exitTonal = webDriver.findElement(By.cssSelector("#test-screen
		// :nth-child(1) :nth-child(2) .panel-body .actions .save-test"));
		// exitTonal.click();
		// Temp.tryCatch(3000);

	}

	public void TON402ReverseMode() {
		/*
		 * WebElement koalysIcon =
		 * webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		 * koalysIcon.click(); Temp.tryCatch(2000); WebElement searchPatient =
		 * webDriver.findElement(By.className("search-patient"));
		 * searchPatient.sendKeys("Test PAT 35"); Temp.tryCatch(3000); WebElement
		 * patientName = webDriver .findElement(By.
		 * cssSelector("#patient-list-body table tbody .patient-fullname a"));
		 * patientName.click(); Temp.tryCatch(7000); WebElement tonalNavBar = webDriver
		 * .findElement(By.
		 * cssSelector("#audyx-main .menu-bar .tests .test-wrap.test1 .tonal"));
		 * tonalNavBar.click(); Temp.tryCatch(5000);
		 */WebElement testCommandPanel = webDriver.findElement(By.className("test-commands-panel"));
		testCommandPanel.click();
		Temp.tryCatch(3000);
		WebElement reverseMode = webDriver
				.findElement(By.cssSelector("#test-screen .top-right-panel .test-commands .command.reverse-mode"));
		Actions act = new Actions(webDriver);
		act.moveToElement(reverseMode).perform();
		Temp.tryCatch(3000);
		WebElement reverseModeHelper = webDriver.findElement(By.cssSelector(
				"#test-screen .top-right-panel .test-panel-animation :nth-child(4) .command-panel.displayed"));

		System.out.println("Test TON-402 step 1 passed");
		reverseMode.click();
		Temp.tryCatch(3000);
		// WebElement startPlay = webDriver.findElement(By.cssSelector(
		// "#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1)
		// .ad-vocal-test-content .panel-confirm.panel-test-init .fa-play"));
		// startPlay.click();
		Temp.tryCatch(3000);
		// click on the play button near the graph
		WebElement playButtonNearGraph = webDriver
				.findElement(By.cssSelector("#test-screen .top-right-panel :nth-child(1) .fa.fa-pause"));
		Temp.tryCatch(2000);
		Actions act2 = new Actions(webDriver);
		act2.moveToElement(playButtonNearGraph)
				.clickAndHold(webDriver
						.findElement(By.cssSelector("#test-screen .top-right-panel :nth-child(1) .fa.fa-pause")))
				.perform();
		Temp.tryCatch(3000);
		Temp.tryCatch(2000);
		System.out.println("Test TON-402 step 2 passed");

		WebElement restartButton = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body .actions :nth-child(2)"));
		restartButton.click();
		Temp.tryCatch(3000);
		WebElement acceptRestart = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(2).panel-confirm.panel-exit-test  .btn-primary"));
		acceptRestart.click();
		Temp.tryCatch(4000);
	}

	public void TON403ExtendedRange() {
		/*
		 * WebElement koalysIcon =
		 * webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		 * koalysIcon.click(); Temp.tryCatch(2000); WebElement searchPatient =
		 * webDriver.findElement(By.className("search-patient"));
		 * searchPatient.sendKeys("Test PAT 35"); Temp.tryCatch(3000); WebElement
		 * patientName = webDriver .findElement(By.
		 * cssSelector("#patient-list-body table tbody .patient-fullname a"));
		 * patientName.click(); Temp.tryCatch(7000); WebElement tonalNavBar = webDriver
		 * .findElement(By.
		 * cssSelector("#audyx-main .menu-bar .tests .test-wrap.test1 .tonal"));
		 * tonalNavBar.click(); Temp.tryCatch(5000);
		 */
		WebElement startPlay = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content .panel-confirm.panel-test-init .fa-play"));
		startPlay.click();
		Temp.tryCatch(3000);
		WebElement no = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(2) .thumb-down"));
		for (int i = 0; i < 6; i++) {
			no.click();
			Temp.tryCatch(2000);
		}
		WebElement warningMessage = webDriver.findElement(By.cssSelector("#test-type-2 :nth-child(1) .graph-warning"));
		System.out.println("Test TON-403 step 1 passed");

		WebElement declineExtend = webDriver.findElement(
				By.cssSelector("#test-type-2 .panel :nth-child(1) .graph-warning .fa-times.response-button.clickable"));
		declineExtend.click();
		Temp.tryCatch(5000);
		WebElement noHear = webDriver.findElement(
				By.cssSelector("#test-type-2 :nth-child(1) :nth-child(1) :nth-child(2) :nth-child(3) circle"));
		System.out.println("Test TON-403 step 2 passed");

		WebElement no2 = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(2) .thumb-down"));
		for (int i = 0; i < 4; i++) {
			no2.click();
			Temp.tryCatch(2000);
		}
		WebElement testCommandPanel = webDriver.findElement(By.className("test-commands-panel"));
		testCommandPanel.click();
		Temp.tryCatch(3000);
		WebElement extendedMode = webDriver
				.findElement(By.cssSelector("#test-screen .top-right-panel .test-commands .command.extended-range"));
		Actions act = new Actions(webDriver);
		act.moveToElement(extendedMode).perform();
		Temp.tryCatch(3000);
		WebElement extendedHelper = webDriver.findElement(By.cssSelector(
				"#test-screen .top-right-panel .test-panel-animation :nth-child(2) .command-panel.displayed"));
		extendedMode.click();
		Temp.tryCatch(3000);
		WebElement highDB = webDriver.findElement(By.cssSelector("#svg-flow text:nth-child(13)"));
		System.out.println("Test TON-403 step 3 passed");

		WebElement yes = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark .thumb-up"));
		yes.click();
		Temp.tryCatch(3000);
		for (int i = 0; i < 2; i++) {
			no2.click();
			Temp.tryCatch(2000);
			no2.click();
			Temp.tryCatch(2000);
			yes.click();
			Temp.tryCatch(2000);
		}
		WebElement thresholdHigh = webDriver.findElement(By.cssSelector(
				"#test-type-2 :nth-child(1) :nth-child(1) :nth-child(2) :nth-child(3) circle:nth-child(2)"));
		System.out.println("Test TON-403 step 4 passed");

		WebElement restartButton = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body .actions :nth-child(2)"));
		restartButton.click();
		Temp.tryCatch(3000);
		WebElement acceptRestart = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(2).panel-confirm.panel-exit-test  .btn-primary"));
		acceptRestart.click();
		Temp.tryCatch(4000);
	}

	public void TON501ProtocolTON502FlowChartTON503ResponseButtonsTON504() {
		/*
		 * WebElement koalysIcon =
		 * webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		 * koalysIcon.click(); Temp.tryCatch(2000); WebElement searchPatient =
		 * webDriver.findElement(By.className("search-patient"));
		 * searchPatient.sendKeys("Test PAT 35"); Temp.tryCatch(3000); // select patient
		 * WebElement patientName = webDriver .findElement(By.
		 * cssSelector("#patient-list-body table tbody .patient-fullname a"));
		 * patientName.click(); Temp.tryCatch(7000);
		 */
		// click on tonal icon
		WebElement tonalNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test1 .tonal"));
		tonalNavBar.click();
		Temp.tryCatch(5000);
//start tonal test
		WebElement startPlay = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content .panel-confirm.panel-test-init .fa-play"));
		startPlay.click();
		Temp.tryCatch(3000);
		// see that first dB scoring in left side is gray, at 50dB
		WebElement firstDB = webDriver.findElement(By.cssSelector("#svg-flow circle"));
		Temp.printElementFillColor(firstDB, "#999999", "Test TON-501 step 1 passed\nTest TON-502 step 1");
// check yes & no buttons appear
		WebElement no = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(2) .thumb-down"));
		WebElement yes = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark .thumb-up"));
		no.click();
		Temp.tryCatch(3000);
		// click on the data point - it's changed to green-yes
		WebElement changeDataPoint = webDriver.findElement(By.cssSelector("#svg-flow text.datapoint-text.togglable"));
		Temp.clickElement(changeDataPoint, 1);

		WebElement yesDataPoint = webDriver.findElement(By.cssSelector("#svg-flow circle.datapoint.togglable"));
		// check of the data point is green
		Temp.tryCatch(3000);
		Temp.printElementFillColor(yesDataPoint, "#97cd7e", "Test TON-501 step 2 passed\nTest TON-502 step 2");

		WebElement yesDataPoint2 = webDriver.findElement(By.cssSelector("#svg-flow text.datapoint-text.togglable"));
		Temp.tryCatch(3000);
		// print the dB of green element
		int yesDataPointNumber = Temp.stringToInt(yesDataPoint2);

		WebElement nextDataPoint = webDriver.findElement(By.cssSelector("#svg-flow text:nth-child(7)"));
		// print the dB of the next point
		int nextDataPointValue = Temp.stringToInt(nextDataPoint);

		if (nextDataPointValue == yesDataPointNumber - 10) {
			System.out.println("Test TON-503 step 1 passed");
		}
		no.click();
		Temp.tryCatch(3000);
		WebElement noDataPoint = webDriver.findElement(By.cssSelector("#svg-flow circle.datapoint.togglable"));
		Temp.printElementFillColor(noDataPoint, "#f94c43", "Test TON-502 step 3");

		WebElement noDataPoint2 = webDriver.findElement(By.cssSelector("#svg-flow text.datapoint-text.togglable"));
		// print the current point number
		int noDataPointValue = Temp.stringToInt(noDataPoint2);
		WebElement nextDataPoint2 = webDriver.findElement(By.cssSelector("#svg-flow text:nth-child(9)"));
		// print the next point number
		int nextDataPointValue2 = Temp.stringToInt(nextDataPoint2);
		if (nextDataPointValue2 == noDataPointValue + 5) {
			System.out.println("Test TON-501 step 3 passed\nTest TON-503 step 2 passed");
		}
		yes.click();
		Temp.tryCatch(3000);
		WebElement thresholdCandidate = webDriver
				.findElement(By.cssSelector("#svg-flow foreignObject .threshold-text"));
		System.out.println(thresholdCandidate.getText());
		System.out.println("Test TON-501 step 4 passed\nTest TON-504 step 1 passed");
		no.click();
		Temp.tryCatch(2000);
		no.click();
		Temp.tryCatch(2000);
		yes.click();
		Temp.tryCatch(2000);
		WebElement firstScoring = webDriver.findElement(
				By.cssSelector("#test-type-2 :nth-child(1) :nth-child(1) svg g:nth-child(2)  g:nth-child(3) circle"));
		System.out.println("Test TON-501 step 5 passed\nTest TON-504 step 2 passed");

		no.click();
		Temp.tryCatch(2000);
		no.click();
		Temp.tryCatch(2000);
		yes.click();
		WebElement remeasure = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) .ad-reload a"));
		remeasure.click();
		Temp.tryCatch(2000);
		WebElement firstDB2 = webDriver.findElement(By.cssSelector("#svg-flow text.datapoint-text"));
		System.out.println("Test TON-501 step 6 passed");
		// click on finish test
		// click on save test
		WebElement finishTest = webDriver.findElement(By.cssSelector(".panel-body .actions .save-test"));
		Temp.clickElement(finishTest, 1);
		Temp.tryCatch(4000);
		System.out.println("Tonal test ran successfully");
	}

	public void TonalModerateDQ() {
		// go to tonal page via bav bar
		WebElement tonalNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test1 .tonal"));
		tonalNavBar.click();
		Temp.tryCatch(10000);

		// save the first point - 1000 Hz
		Actions action1 = new Actions(webDriver);
		action1.sendKeys("s").build().perform();
		Temp.tryCatch(1000);

		// move right for 2000, 4000 Hz
		for (int i = 0; i < 2; i++) {
			action1.sendKeys(Keys.ARROW_RIGHT).build().perform();
			Temp.tryCatch(2000);
			action1.sendKeys(Keys.ARROW_RIGHT).build().perform();
			Temp.tryCatch(2000);
			action1.sendKeys("s").build().perform();
			Temp.tryCatch(2000);
		}
		// move left to point 500 Hz
		for (int i = 0; i < 6; i++) {
			action1.sendKeys(Keys.ARROW_LEFT).build().perform();
			Temp.tryCatch(1000);
		}
		action1.sendKeys("s").build().perform();
		Temp.tryCatch(6000);

		// click on new test for running left ear
		Temp.scrollDown(webDriver);
		Temp.tryCatch(6000);
		WebElement newTest = webDriver
				.findElement(By.cssSelector("#test-screen .actions button.btn.btn-primary.pull-right.next-ear"));
		Temp.clickElement(newTest, 1);

		// go up to 45 dB for left ear
		Actions action2 = new Actions(webDriver);
		action2.sendKeys(Keys.ARROW_UP).build().perform();
		Temp.tryCatch(1000);
		action2.sendKeys("s").build().perform();
		Temp.tryCatch(1000);

		// move right for 2000, 4000 Hz
		for (int i = 0; i < 2; i++) {
			action2.sendKeys(Keys.ARROW_RIGHT).build().perform();
			action2.sendKeys(Keys.ARROW_RIGHT).build().perform();
			Temp.tryCatch(2000);
			action2.sendKeys("s").build().perform();
			Temp.tryCatch(1000);
		}
		// move left to point 500 Hz
		for (int i = 0; i < 6; i++) {
			action2.sendKeys(Keys.ARROW_LEFT).build().perform();
			Temp.tryCatch(1000);
		}
		action2.sendKeys("s").build().perform();
		Temp.tryCatch(2000);
		// click on save test
		WebElement finishTest = webDriver.findElement(By.cssSelector(".panel-body .actions .save-test"));
		Temp.clickElement(finishTest, 1);

		// in avatar, see the DQ- green with moderate text
		// Check the DQ in avatar
		Temp.tryCatch(8000);
		// right DQ
		WebElement rightEarDQ = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient-hearing-loss.right.moderate"));
		Temp.tryCatch(3000);
//left DQ
		WebElement leftEarDQ = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient-hearing-loss.left.moderate"));
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
		if ((toolTipText.equals("MODERATE")) && (toolTipText2.equals("MODERATE")))
			System.out.println("Tonal test in moderate DQ succeed");
		System.out.println("Tonal test ran successfully");
	}

	/**
	 * ADP 101, ADP 201 step 3, ADP-202, ADP-303
	 */
	public void AdaptiveSilence() {

		// ADP-101
		WebElement adaptiveNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test3 .adaptive"));
		Temp.clickElement(adaptiveNavBar, 1);
		// go to adaptive test via nav bar
		WebElement adaptiveSilence = webDriver
				.findElement(By.cssSelector("#audyx-main .tests-menu.menu-adaptive  .adaptive_silence"));
		Temp.clickElement(adaptiveSilence, 1);
		// WebElement acceptAdaptive = webDriver
		// .findElement(By.cssSelector("body :nth-child(7) .modal-footer
		// .btn-primary"));
		// acceptAdaptive.click();
		Temp.tryCatch(6000);
		Temp.checkUrl("https://alpha.koalys.com/#/test/adaptive", webDriver.getCurrentUrl(), "Test ADP-101 step 1");

		// go to adaptive test via side menu
		WebElement koalysIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		koalysIcon.click();
		Temp.tryCatch(5000);
		WebElement sideBar = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .navigator"));
		sideBar.click();
		Temp.tryCatch(3000);
		WebElement testMenu = webDriver.findElement(By.cssSelector("#testsMenu"));
		testMenu.click();
		Temp.tryCatch(3000);
		WebElement adaptiveIcon = webDriver.findElement(
				By.cssSelector("#audyx-main .ad-slide-menu.sub .app-a .ad-sprite.adaptive_silence.active"));
		Temp.clickElement(adaptiveIcon, 1);

		WebElement mainPanel2 = webDriver.findElement(By.id("audyx-main"));
		Actions act2 = new Actions(webDriver);
		act2.moveToElement(mainPanel2).perform();
		Temp.tryCatch(1000);
		Temp.checkUrl("https://alpha.koalys.com/#/test/adaptive", webDriver.getCurrentUrl(), "Test ADP-101 step 2");

		// see the elements in adaptive test
		WebElement transducerArea = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(2) .ad-param .params-container.transducers"));
		WebElement listeningConditions = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(1) :nth-child(3) .params-container"));
		WebElement TestProtocol = webDriver.findElement(By.cssSelector("#test-screen :nth-child(4) .ad-param"));
		System.out.println("Test ADP-101 step 3 passed\nTest ADP-101 step 5 passed");

		// after selecting AC transducer, it appears in cabin area
		WebElement cabinTransducer = webDriver
				.findElement(By.cssSelector("#test-screen  .cabine-speakers .cabin-transducer.R.airconductor"));
		System.out.println("Test ADP-101 step 4 passed");

		// change to the FF listening Conditions
		WebElement FF = webDriver
				.findElement(By.cssSelector("#test-screen .form-control.transducer.FreeField .image.available"));
		FF.click();
		Temp.tryCatch(3000);

		WebElement equipped = webDriver.findElement(By.cssSelector("#test-screen .form-control.show-equipped"));
		System.out.println("Test ADP-101 step 6 passed");

		// change to fitted and from right to left
		WebElement fitted = webDriver
				.findElement(By.cssSelector("#test-screen .form-control.show-equipped :nth-child(2).radio-inline"));
		fitted.click();
		Temp.tryCatch(2000);
		WebElement fittingPanel = webDriver.findElement(By.cssSelector("#test-screen .ear.right-fitting"));
		// change to left and see the title is changed
		WebElement leftEar = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) :nth-child(1) :nth-child(3).radio-inline label"));
		leftEar.click();
		Temp.tryCatch(2000);
		WebElement titleLeftEar = webDriver
				.findElement(By.cssSelector("#test-screen:nth-child(1) .panel-body :nth-child(3)  label span"));
		String titleLeftFittedEar = Temp.printElementText(titleLeftEar);
		Temp.checkContainString(titleLeftFittedEar, "Left (Fitted)", "Test ADP-101 step 7");

		// change again to AC, binaural & not fitted in order to continue
		WebElement AC = webDriver
				.findElement(By.cssSelector("#test-screen .form-control.transducer.AirConductor .image.available"));
		AC.click();
		Temp.tryCatch(2000);

		// select Dissyllabiques de Fournier list
		WebElement selectList = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(1) :nth-child(3) :nth-child(4) .text-prop"));
		Temp.clickElement(selectList, 1);

		// see all protocol parameters, No option to select voice material appears
		WebElement Audience = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(1).list-group-item.list-group-prop"));
		WebElement Category = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(2).list-group-item.list-group-prop"));
		WebElement UnitOfError = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(3).list-group-item.list-group-prop"));
		WebElement NumberOfFiles = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(6).list-group-item.list-group-prop"));
		WebElement InitialVoice = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(7).list-group-item.list-group-prop"));
		WebElement NumberOfItemsPerFile = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(8).list-group-item.list-group-prop"));
		WebElement Language = webDriver
				.findElement(By.cssSelector("#test-screen :nth-child(10).list-group-item.list-group-prop"));
		System.out.println("Test ADP-202 step 1 passed");

		WebElement StartTest = webDriver.findElement(By.cssSelector("#test-screen :nth-child(1) .start-test-btn"));
		Temp.clickElement(StartTest, 1);
		// click on play test
		WebElement playTest = webDriver.findElement(By.cssSelector("#test-screen .start-test .fa-play"));
		Temp.clickElement(playTest, 1);

		// see the test starts playing at minimum dB-0
		Temp.scrollDown(webDriver);
		WebElement startFlow = webDriver.findElement(By.cssSelector("#svg-flow foreignObject .current-circle"));
		String startTest = Temp.printElementText(startFlow);
		Temp.checkContainString(startTest, "0", "Test  step 4");

		// run the test by clicking yes & no
		WebElement no = webDriver.findElement(By.cssSelector("#test-screen .thumb-down.judge-icon"));
		WebElement yes = webDriver.findElement(By.cssSelector("#test-screen .thumb-up.judge-icon"));
		System.out.println("Test ADP-302 step 1 passed");

		// scroll up
		Temp.scrollup(webDriver);
		Temp.tryCatch(2000);

		// ADP-303, see the current word
		WebElement playAgainTest = webDriver.findElement(By.cssSelector("#test-screen .fa.fa-play.clickable"));
		playAgainTest.click();
		Temp.checkButtonNotClickable(yes, "Test ADP-303 step 1");
		Temp.tryCatch(3000);

		// see the next word is heard after playing the first one
		Temp.clickElement(no, 1);
		WebElement nextWord = webDriver.findElement(By.cssSelector("#svg-flow foreignObject .current-circle"));
		String nextWord2 = Temp.printElementText(nextWord);
		Temp.checkContainString(startTest, "0", "Test ADP-302 step 2");

		// see the previous word is marked with red point
		WebElement firstWordNo = webDriver.findElement(By.cssSelector("#svg-flow circle.answer.no"));
		Temp.printElementFillColor(firstWordNo, "#f94c43", "Test ADP-303 step 2");

		// mark the second word yes
		Temp.clickElement(yes, 1);
		WebElement secondWordYes = webDriver.findElement(By.cssSelector("#svg-flow circle.answer.yes"));
		Temp.printElementFillColor(secondWordYes, "#6fb253", "Test ADP-303 step 3");

		// see the previous word
		WebElement previousWordYes = webDriver.findElement(By.cssSelector("body .adaptive-tooltip.n .yes.phoneme"));
		WebElement currentWord = webDriver
				.findElement(By.cssSelector("#test-screen .current-question.correct-answer .item.current"));

		String previouWord = previousWordYes.getText();
		String currentWord2 = currentWord.getText();
		Temp.checkEqualString(previouWord, currentWord2, "Test ADP-302 step 4");

		Temp.tryCatch(2000);
		Temp.clickElement(no, 2);
		Temp.clickElement(yes, 16);
//when finishing test, result line appears
		WebElement resultLine = webDriver.findElement(By.cssSelector("#svg-flow foreignObject.result-text"));
		System.out.println("Adaptive test succeed");
		// scroll down in order to see finish test
		Temp.scrollDown(webDriver);

		WebElement finishTest = webDriver.findElement(By.cssSelector("#test-screen .save-test"));
		Temp.clickElement(finishTest, 1);
		// see the adaptive results in patient folder
		WebElement AdaptiveResults = webDriver.findElement(By.cssSelector("#test-type-4 h3.panel-title.tests-title"));
		System.out.println("Adaptive results were saved");
		System.out.println("Test ADP-302 step 3 passed");
	}

	/**
	 * run test in multi-phoneme, ADP-302, 401, 402 step 2
	 */
	public void AdaptiveMultiPhoneme() {
		// go to adaptive page
		WebElement adaptiveNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test3 .adaptive"));
		Temp.clickElement(adaptiveNavBar, 1);
		// go to adaptive test via nav bar
		WebElement adaptiveSilence = webDriver
				.findElement(By.cssSelector("#audyx-main .tests-menu.menu-adaptive  .adaptive_silence"));
		Temp.clickElement(adaptiveSilence, 1);

		// search phoneme list
		WebElement textArea = webDriver
				.findElement(By.cssSelector("#test-screen .search.col-xs-offset-1.col-xs-10  input"));
		textArea.sendKeys("phon");
		Temp.tryCatch(2000);
//select the list and start test
		WebElement selectList = webDriver.findElement(By.cssSelector("#test-screen .select-details"));
		selectList.click();
		Temp.tryCatch(2000);

		WebElement StartTest = webDriver.findElement(By.cssSelector("#test-screen :nth-child(1) .start-test-btn"));
		Temp.clickElement(StartTest, 1);

		// click on play test
		WebElement playTest = webDriver.findElement(By.cssSelector("#test-screen .start-test .fa-play"));
		Temp.clickElement(playTest, 1);

		WebElement allYes = webDriver
				.findElement(By.cssSelector("#test-screen  .judge-icon-wrapper .thumb-up.thumb-up-stack.judge-icon"));
		WebElement allNo = webDriver.findElement(
				By.cssSelector("#test-screen  .judge-icon-wrapper .thumb-down.thumb-down-stack.judge-icon"));
		WebElement yes = webDriver
				.findElement(By.cssSelector("#test-screen .ad-judge.can-mark :nth-child(2) .thumb-up.judge-icon"));
		WebElement no = webDriver
				.findElement(By.cssSelector("#test-screen .ad-judge.can-mark :nth-child(3) .thumb-down.judge-icon"));
		System.out.println("Test ADP-302 step 4 passed");

		// mark all yes and see the word is marked with all phoneme as yes - green
		Temp.clickElement(allYes, 1);

		WebElement firstWordAllYes1 = webDriver.findElement(By.cssSelector("#svg-flow > circle:nth-child(4)"));
		WebElement firstWordAllYes2 = webDriver.findElement(By.cssSelector("#svg-flow > circle:nth-child(5)"));
		WebElement firstWordAllYes3 = webDriver.findElement(By.cssSelector("#svg-flow > circle:nth-child(6)"));
		Temp.printElementFillColor(firstWordAllYes1, "#6fb253", "Test ADP-302 step 5.1");
		Temp.printElementFillColor(firstWordAllYes2, "#6fb253", "Test ADP-302 step 5.2");
		Temp.printElementFillColor(firstWordAllYes3, "#6fb253", "Test ADP-302 step 5.3");
		System.out.println("Test ADP-302 step 5 passed");

		Temp.clickElement(allNo, 1);

		WebElement secondWordAllNo1 = webDriver.findElement(By.cssSelector("#svg-flow > circle:nth-child(7)"));
		WebElement secondWordAllNo2 = webDriver.findElement(By.cssSelector("#svg-flow > circle:nth-child(9)"));
		WebElement secondWordAllNo3 = webDriver.findElement(By.cssSelector("#svg-flow > circle:nth-child(11)"));
		Temp.printElementFillColor(secondWordAllNo1, "#f94c43", "Test ADP-302 step 6.1");
		Temp.printElementFillColor(secondWordAllNo2, "#f94c43", "Test ADP-302 step 6.2");
		Temp.printElementFillColor(secondWordAllNo3, "#f94c43", "Test ADP-302 step 6.3");
		System.out.println("Test ADP-302 step 6 passed");

		// see the debut of scoring
		Temp.clickElement(allNo, 1);
		WebElement debutOfScoring = webDriver.findElement(By.cssSelector("#svg-flow  path.dotted-line"));
		System.out.println("Test ADP-401 step 1 passed");
		Temp.clickElement(allNo, 1);
		WebElement forecastAreaRange = webDriver.findElement(By.cssSelector(
				"#test-type-4 .panel :nth-child(1) :nth-child(1)  g:nth-child(2) g:nth-child(2) g:nth-child(2)  g"));
		System.out.println("Test ADP-401 step 2 passed");

		Temp.clickElement(allNo, 12);
		Temp.clickElement(allYes, 4);
		WebElement finishTest = webDriver.findElement(By.cssSelector("#test-screen .save-test"));
		Temp.clickElement(finishTest, 1);

		// see the results in patient folder
		Temp.checkContainString(webDriver.getCurrentUrl(), "/patientFolder/test/adaptive/", "Test ADP-302 step 7");

		WebElement adaptiveResult = webDriver
				.findElement(By.cssSelector("#test-type-4 .panel :nth-child(2) :nth-child(1) circle"));
		System.out.println("Test ADP-402 step 2 passed");

	}

	/**
	 * run adaptive in noise test, ADP-402 step 1, ADP-201 step 1, step 2
	 */
	public void AdaptiveNoise() {
		// go to adaptive in noise page
		WebElement adaptiveNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test3 .adaptive"));
		Temp.clickElement(adaptiveNavBar, 1);
		// go to adaptive test via nav bar
		WebElement adaptiveNoise = webDriver
				.findElement(By.cssSelector("#audyx-main .tests-menu.menu-adaptive  .adaptive_noise"));
		Temp.clickElement(adaptiveNoise, 1);

		// //select list SRT de Lefèvre implanté and see the test start at SNR +18

		WebElement selectList1 = webDriver.findElement(
				By.cssSelector("#test-screen :nth-child(1) :nth-child(3) :nth-child(3) :nth-child(4) .text-prop"));
		Temp.clickElement(selectList1, 1);
		// start the test
		WebElement StartTest = webDriver.findElement(By.cssSelector("#test-screen :nth-child(1) .start-test-btn"));
		Temp.clickElement(StartTest, 1);

		// click on play test
		WebElement playTest = webDriver.findElement(By.cssSelector("#test-screen .start-test .fa-play"));
		Temp.clickElement(playTest, 1);

		WebElement firstDb2 = webDriver.findElement(By.cssSelector("#svg-flow foreignObject .current-circle"));
		String firstDb22 = Temp.printElementText(firstDb2);
		Temp.checkContainString(firstDb22, "18", "Test ADP-201 step 2");

		//cancel the test
			
		WebElement cancelTest = webDriver.findElement(By.cssSelector("#test-screen .panel-body.ad-vocal-test .actions a:nth-child(1)"));
		Temp.clickElement(cancelTest, 1);
		WebElement acceptCancelTest = webDriver.findElement(By.cssSelector("#test-screen :nth-child(1).panel-confirm.panel-exit-test .btn.btn-primary"));
		Temp.clickElement(acceptCancelTest, 1);
		
		
		// select Dissyllabiques de Boorsma list
		WebElement selectList2 = webDriver.findElement(
				By.cssSelector("#test-screen :nth-child(1) :nth-child(3) :nth-child(3) :nth-child(1) :nth-child(1) :nth-child(1) .text-prop"));
		Temp.clickElement(selectList2, 1);

		// see noise element in page
		WebElement noiseFile = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(3) .col-lg-6.col-no-left-padding :nth-child(13) .text-danger"));
		String noiseFile1 = Temp.printElementText(noiseFile);
		Temp.checkContainString(noiseFile1, "Noise file", "Test ADP-402 step 1.1");

		// start the test
		Temp.clickElement(StartTest, 1);
		// click on play test
				Temp.clickElement(playTest, 1);

		/// see the first dB starts at SNR +14
		WebElement firstDb = webDriver.findElement(By.cssSelector("#svg-flow foreignObject .current-circle"));
		String firstDb1 = Temp.printElementText(firstDb);
		Temp.checkContainString(firstDb1, "14", "Test ADP-201 step 1");

		WebElement no = webDriver.findElement(By.cssSelector("#test-screen .thumb-down.judge-icon"));
		WebElement yes = webDriver.findElement(By.cssSelector("#test-screen .thumb-up.judge-icon"));

		Temp.clickElement(yes, 3);
		Temp.clickElement(no, 4);
		Temp.clickElement(yes, 6);
		Temp.clickElement(no, 8);

		WebElement finishTest = webDriver.findElement(By.cssSelector(".panel-body .actions .save-test"));
		Temp.clickElement(finishTest, 1);

		WebElement resultsArea = webDriver
				.findElement(By.cssSelector("#test-type-5 .panel :nth-child(1) h3.panel-title.tests-title"));
		System.out.println("Test ADP-402 step 1 passed");
		System.out.println("Adaptive test ran successfully");

	}

	// =====PatientManagement
	/**
	 * /* Test the accessing to patient list and find all elements there
	 */
	public void PAT101PatientList() {
		// make sure you are in patient list page when clicking on Audyx icon
		WebElement koalysIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		koalysIcon.click();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.koalys.com/#/patients", webDriver.getCurrentUrl(), "Test PAT-101 step 1");

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
		Temp.checkUrl("https://alpha.koalys.com/#/patients", webDriver.getCurrentUrl(), "Test PAT-101 step 2");

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
		Temp.checkEqualString(CurrentPatientInList1, nameFinal, "Test PAT-101 step 10 passed\nTest PAT-501 step 1");
	}

	/**
	 * Add a new patient and test all parameters in this page
	 */
	public void PAT301NewPatient() {
		// go to patients page
		WebElement koalysIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		Temp.configScreen(koalysIcon, "Test PAT-301 step 1");

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
		Temp.printElementBackgroundColor(calendarColor, "#a94442", "Test PAT-301 step 6");

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
		Temp.checkUrl("https://alpha.koalys.com/#/patients", webDriver.getCurrentUrl(), "Test PAT-301 step 8");

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
		lastName2.sendKeys("Automation " + Temp.printDate() + RandomStringUtils.randomAlphabetic(2).toUpperCase());
		// lastName2.sendKeys("Automation " + RandomStringUtils.randomNumeric(3));
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
		Temp.printElementBackgroundColor(emailIcon, "#a94442", "Test PAT-301 step 10");

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
		WebElement koalysIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		Temp.configScreen(koalysIcon, "Test PAT-401 step 0");

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
		WebElement koalysIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		koalysIcon.click();
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
		System.out.println("Patient Management test ran successfully");
	}

	// =====SupportPage
	/**
	 * see all elements in support page
	 */
	public void SUP101SupportPage() {
		WebElement sideMenu = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .navigator"));
		sideMenu.click();
		Temp.tryCatch(2000);
		// choose support page
		WebElement support = webDriver.findElement(By.cssSelector("#menu-side :nth-child(9) .support"));
		support.click();
		Temp.tryCatch(3000);
		// move mouse to main screen
		WebElement mainPanel = webDriver.findElement(By.id("audyx-main"));
		Actions act = new Actions(webDriver);
		act.moveToElement(mainPanel).perform();
		Temp.tryCatch(5000);
		Temp.checkUrl("https://alpha.koalys.com/#/support", webDriver.getCurrentUrl(), "Test SUP-101 step 1");
		// see elements in support page

		WebElement contactArea = webDriver.findElement(By.cssSelector("#audyx-main .view-animate .ribbon.row"));

		WebElement versionInnovations = webDriver
				.findElement(By.cssSelector("#audyx-main .resources.row :nth-child(1) .versions"));

		WebElement downloadIfu = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .resources.row :nth-child(2) :nth-child(2) a button"));
		System.out.println("Test SUP-101 step 2 passed");

		WebElement downloadNoah = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .resources.row :nth-child(2) :nth-child(3) a button"));
		downloadNoah.click();
		Temp.tryCatch(4000);

		String downloadPath = "C:\\Users\\audix\\Downloads";
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();

		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().contains("koalys_module_setup")) {
				System.out.println("Test SUP-101 step 3 passed");
			}
		}
	}

}
