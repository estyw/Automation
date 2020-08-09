package expert;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Otoscopy {

	// configure otoscopy device in audio-setup
	// first go to patients page

	private static WebDriver webDriver;
	private String baseURL;
	private String driverFilePath;

	Otoscopy(String baseURL, String driverFilePath) {
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
	 * configure otoscopy device and access to screen
	 */
	public void OTO101AccessToModule() {

		WebElement sideBar2 = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .navigator"));
		sideBar2.click();
		Temp.tryCatch(3000);
		WebElement otoscopyIcon = webDriver.findElement(By.cssSelector("#menu-side :nth-child(8)"));
		otoscopyIcon.click();
		WebElement mainPanel = webDriver.findElement(By.id("audyx-main"));
		Actions act = new Actions(webDriver);
		act.moveToElement(mainPanel).perform();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.audyx.com/#/otoscope", webDriver.getCurrentUrl(), "Test OTO-101 step 1");

		WebElement patientIcon = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text :nth-child(2) .ad-name"));
		patientIcon.click();
		Temp.tryCatch(5000);
		WebElement cameraIcon = webDriver.findElement(By.cssSelector(
				"#patient-info .panel-body :nth-child(2) .ears-condition.row fieldset:nth-child(1) .fa-camera"));
		System.out.println("Test OTO-101 step 2 passed");
		cameraIcon.click();

		Temp.checkUrl("https://alpha.audyx.com/#/otoscope", webDriver.getCurrentUrl(), "Test OTO-101 step 3");
		Temp.tryCatch(3000);
	}

	public void OTO201DeviceConfiguration() {

		WebElement rightEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(2) .otoscope-ear .ear "));
		WebElement leftEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-ear .ear"));
		WebElement deviceConnectStatus = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.empty-otoscope .device-status-text"));
		WebElement setupLink = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-panel.empty-otoscope .device-status-text .device-status-action"));
		String setupLink1 = Temp.printElementText(setupLink);
		Temp.checkContainString(setupLink1, "Setup", "Test OTO-201 step 1");

		setupLink.click();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.audyx.com/#/audio-setup", webDriver.getCurrentUrl(), "Test OTO-201 step 2");

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
		WebElement deviceStatus = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .empty-otoscope .device-status-text"));

		Temp.checkContainString(deviceStatus.getText(), "Device is connected", "Test OTO-201 step 3");
	}

	public void OTO202ImageCapture() {

		WebElement leftEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-ear .ear"));
		leftEar.click();
		Temp.tryCatch(2000);
		WebElement leftEarActive = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view  :nth-child(1).otoscope-panel.edit-otoscope .webcam .webcam-live"));
		System.out.println("Test OTO-202 step 1 passed");

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

		System.out.println("Test OTO-202 step 2 passed");

		noCrop.click();
		Temp.tryCatch(3000);
		WebElement savedImageLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main :nth-child(2) :nth-child(2) :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-image"));
		WebElement imageDateLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main :nth-child(2) :nth-child(2) :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper .otoscope-date"));
		System.out.println("Test OTO-202 step 3 passed");
		// right ear
		WebElement rightEar = webDriver.findElement(By
				.cssSelector("#audyx-main .body section .view-animate :nth-child(1) :nth-child(2) .otoscope-ear .ear"));
		rightEar.click();
		Temp.tryCatch(3000);
		WebElement rightEarActive = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(2) .otoscope-view  :nth-child(1).otoscope-panel.edit-otoscope .webcam .webcam-live"));
		System.out.println("Test OTO-202 step 4.1 passed");

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
		System.out.println("Test OTO-202 step 4.2 passed");

		noCropRight.click();
		Temp.tryCatch(3000);
		WebElement savedImageRight = webDriver.findElement(
				By.cssSelector("#audyx-main :nth-child(2) :nth-child(2) :nth-child(2) .otoscope-view .otoscope-image"));
		WebElement imageDateRight = webDriver.findElement(By.cssSelector(
				"#audyx-main :nth-child(2) :nth-child(2) :nth-child(2) .otoscope-bar .edit-image.btn-wrapper .otoscope-date"));
		System.out.println("Test OTO-202 step 4.3 passed");
	}

	public void PAT203ZoomingCropping() {
		// left ear
		WebElement leftEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-ear .ear"));
		leftEar.click();
		Temp.tryCatch(2000);
		WebElement previousLeftImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-panel.edit-otoscope .previous-otoscope.clickable"));
		WebElement currentLiveImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-panel.edit-otoscope .webcam video"));
		System.out.println("Test OTO-203 step 1 passed");

		WebElement captureButtonLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .capture-otoscope"));
		captureButtonLeft.click();
		Temp.tryCatch(7000);
		WebElement imageCircle = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-panel.edit-otoscope .edit-mode"));
		System.out.println("Test OTO-203 step 2 passed");

		Actions act = new Actions(webDriver);
		act.dragAndDropBy(imageCircle, -350, -400).build().perform();
		WebElement submitOtoscopy2Left = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .crop-otoscope.btn-wrapper :nth-child(1) :nth-child(1)"));
		submitOtoscopy2Left.click();
		Temp.tryCatch(6000);
		WebElement confirmEditOtoscopyModalLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .panel-confirm .btn.btn-primary"));
		System.out.println("Test OTO-203 step 3 passed");

		confirmEditOtoscopyModalLeft.click();
		Temp.tryCatch(6000);
		System.out.println("Test OTO-203 step 4 passed");

		// right ear
		WebElement rightEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(2) .otoscope-ear .ear"));
		rightEar.click();
		Temp.tryCatch(2000);
		WebElement previousRightImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(2) .otoscope-view .otoscope-panel.edit-otoscope .previous-otoscope.clickable"));
		WebElement currentRightLiveImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(2) .otoscope-view .otoscope-panel.edit-otoscope .webcam video"));
		System.out.println("Test OTO-203 step 5.1 passed");

		WebElement captureButtonRight = webDriver.findElement(By
				.cssSelector("#audyx-main .view-animate :nth-child(1) :nth-child(2) .otoscope-bar .capture-otoscope"));
		captureButtonRight.click();
		Temp.tryCatch(4000);
		WebElement imageCircleRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(2) .otoscope-view .otoscope-panel.edit-otoscope .edit-mode"));
		System.out.println("Test OTO-203 step 5.2 passed");

		Actions act2 = new Actions(webDriver);
		act2.dragAndDropBy(imageCircleRight, -350, -400).build().perform();
		WebElement submitOtoscopy2Right = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) .otoscope-bar .crop-otoscope.btn-wrapper .otoscope-btn:nth-child(1)"));
		submitOtoscopy2Right.click();
		Temp.tryCatch(6000);
		WebElement confirmEditOtoscopyModalRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(2) .otoscope-panel.edit-otoscope .panel-confirm .btn.btn-primary"));
		System.out.println("Test OTO-203 step 5.3 passed");

		confirmEditOtoscopyModalRight.click();
		Temp.tryCatch(3000);
		System.out.println("Test OTO-203 step 5.4 passed");

	}

	public void PAT204EditOtoscopy() {
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
		System.out.println("Test OTO-204 step 1 passed");

		editLeftOtoscopy.click();
		Temp.tryCatch(3000);
		WebElement previousLeftImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view .otoscope-panel.edit-otoscope .previous-otoscope.clickable"));
		System.out.println("Test OTO-204 step 2 passed");

		WebElement captureButtonLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .capture-otoscope"));
		captureButtonLeft.click();
		Temp.tryCatch(4000);
		WebElement noCropping = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1)  :nth-child(1) .otoscope-bar .crop-otoscope.btn-wrapper :nth-child(2)"));
		noCropping.click();
		Temp.tryCatch(4000);
		WebElement confirmEditOtoscopyModalLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .panel-confirm"));
		System.out.println("Test OTO-204 step 3 passed");

		WebElement cancelEditOtoscopyLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .panel-confirm a"));
		cancelEditOtoscopyLeft.click();
		Temp.tryCatch(4000);
		WebElement LeftImageDate2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper .otoscope-date"));
		Temp.checkEqualString(LeftImageDate1.getText(), LeftImageDate2.getText(), "Test OTO-204 step 4");

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
		Temp.checkDiffString(LeftImageDate2.getText(), LeftImageDate3.getText(), "Test OTO-204 step 5");

		WebElement deleteImageLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper :nth-child(3)"));
		deleteImageLeft.click();
		Temp.tryCatch(3000);
		WebElement deleteConfirmModal = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view :nth-child(2) .panel-confirm"));
		System.out.println("Test OTO-204 step 6 passed");

		WebElement cancelDeleteModal = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-view :nth-child(2) .panel-confirm a"));
		cancelDeleteModal.click();
		Temp.tryCatch(3000);
		WebElement LeftImageDate4 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-bar .edit-image.btn-wrapper .otoscope-date"));
		Temp.checkEqualString(LeftImageDate4.getText(), LeftImageDate3.getText(), "Test OTO-204 step 7");

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
		Temp.checkEqualString(LeftImageDate5.getText(), LeftImageDate5.getText(), "Test OTO-204 step 8");

	}

	public void DAT101()

	{

		WebElement centerIcon = webDriver.findElement(By.cssSelector("#user-details .ad-name"));
		centerIcon.click();
		Temp.tryCatch(3000);
		WebElement downloadCenterDetails = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel .center-admin-buttons :nth-child(2).btn.btn-primary"));
		downloadCenterDetails.click();
		Temp.tryCatch(3000);

		WebElement confirmDownloadModal = webDriver.findElement(By.cssSelector("body .modal.fade.in .modal-dialog"));
		System.out.println("Test DAT-101 step 1 passed");

		WebElement cancelButton = webDriver.findElement(By.cssSelector("body .modal.fade.in .modal-footer a"));
		cancelButton.click();
		Temp.tryCatch(3000);

	}
}
