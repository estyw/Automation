package expert;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
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
		Temp.tryCatch(6000);
		WebElement otoscopyIcon = webDriver.findElement(By.cssSelector("#menu-side :nth-child(8)"));
		otoscopyIcon.click();
		Temp.tryCatch(3000);
		WebElement mainPanel = webDriver.findElement(By.id("audyx-main"));
		Actions act = new Actions(webDriver);
		act.moveToElement(mainPanel).perform();
		Temp.tryCatch(5000);

		Temp.checkUrl("https://alpha.audyx.com/#/otoscope", webDriver.getCurrentUrl(), "Test OTO-101 step 1");
		Temp.tryCatch(2000);
		WebElement patientIcon = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text :nth-child(2) .ad-name"));
		patientIcon.click();
		Temp.tryCatch(5000);
		WebElement cameraIcon = webDriver.findElement(By.cssSelector(
				"#patient-info .panel-body :nth-child(2) .ears-condition.row fieldset:nth-child(1) .fa-camera"));
		System.out.println("Test OTO-101 step 2 passed");

		cameraIcon.click();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.audyx.com/#/otoscope", webDriver.getCurrentUrl(), "Test OTO-101 step 3");
		Temp.tryCatch(3000);
		System.out.println("===Test OTO-101 finished===");
	}

	public void OTO201Access() {

		WebElement rightEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(2) .otoscope-ear .ear "));
		WebElement leftEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-ear .ear"));
		WebElement deviceConnectStatus = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.empty-otoscope .device-status-text"));
		WebElement setupLink = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .empty-otoscope .device-status-text .device-status-action"));
		String setupLink1 = Temp.printElementText(setupLink);
		Temp.checkContainString(setupLink1, "Setup", "Test OTO-201 step 1");

		setupLink.click();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.audyx.com/#/audio-setup", webDriver.getCurrentUrl(), "Test OTO-201 step 2");

		WebElement addCabin = webDriver.findElement(By.cssSelector("#audyx-main .view-animate .top-panel .fa-plus"));
		addCabin.click();
		Temp.tryCatch(3000);
		WebElement insertNewCabin = webDriver.findElement(By.cssSelector("#audyx-main .view-animate .top-panel input"));
		insertNewCabin.sendKeys("Otoscopy Cabin");
		WebElement submitCabin = webDriver
				.findElement(By.cssSelector("#audyx-main .view-animate .top-panel .create-or-edit-item .btn-primary"));
		submitCabin.click();
		Temp.tryCatch(4000);
		WebElement otoscopyArea = webDriver.findElement(By.cssSelector("#audyx-main :nth-child(4) .select2-chosen"));
		otoscopyArea.click();
		Temp.tryCatch(4000);
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
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .empty-otoscope .device-status-text"));
		// String deviceConnected = Temp.printElementText(deviceStatus);
		String deviceConnected = Temp.splitStringWithSpecialChar(deviceStatus, "<");
		Temp.checkContainString(deviceConnected, "Device is connected", "Test OTO-201 step 3");

// =====left ear
		WebElement leftEar2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-ear .ear"));

		leftEar2.click();
		Temp.tryCatch(2000);
		WebElement submitOtoscopy = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .fa-check.clickable"));
		System.out.println("Test OTO-201 step 4 passed");
		submitOtoscopy.click();
		Temp.tryCatch(7000);
		WebElement leftImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel .otoscope-image"));
		System.out.println("Test OTO-201 step 5 passed");

		WebElement leftEar3 = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-ear .ear"));
		leftEar3.click();
		Temp.tryCatch(3000);

		WebElement previousImageLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .previous-otoscope.clickable"));
		System.out.println("Test OTO-201 step 6 passed");

		WebElement submitOtoscopy2Left = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .fa-check.clickable"));
		submitOtoscopy2Left.click();
		Temp.tryCatch(6000);
		WebElement confirmEditOtoscopyModalLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .panel-confirm"));
		System.out.println("Test OTO-201 step 7 passed");

		WebElement confirmEditOtosocpyLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .panel-confirm .btn-primary"));
		confirmEditOtosocpyLeft.click();
		Temp.tryCatch(3000);
		System.out.println("Test OTO-201 step 8 passed");
// ===right ear
		WebElement rightEar2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(2) .otoscope-ear .ear "));
		rightEar2.click();
		Temp.tryCatch(2000);
		WebElement submitOtoscopyRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .fa-check.clickable"));
		System.out.println("Test OTO-201 step 9.1 passed");
		submitOtoscopyRight.click();
		Temp.tryCatch(7000);
		WebElement rightImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(2) :nth-child(2) .otoscope-panel .otoscope-image"));
		System.out.println("Test OTO-201 step 9.2 passed");

		WebElement rightEar3 = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(2) .otoscope-ear .ear "));
		rightEar3.click();
		Temp.tryCatch(3000);

		WebElement previousImageRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .previous-otoscope.clickable"));
		System.out.println("Test OTO-201 step 9.3 passed");

		WebElement submitOtoscopy2Right = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .fa-check.clickable"));
		submitOtoscopy2Right.click();
		Temp.tryCatch(6000);
		WebElement confirmEditOtoscopyModalRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .panel-confirm"));
		System.out.println("Test OTO-201 step 9.4 passed");

		WebElement confirmEditOtosocpyRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .panel-confirm .btn-primary"));
		confirmEditOtosocpyRight.click();
		Temp.tryCatch(3000);
		System.out.println("Test OTO-201 step 9.5 passed");
		System.out.println("===Test OTO-201 finished===");
	}

	public void OTO202EditDelete() {

		WebElement firstOtoscopyDate = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) :nth-child(2) .date-edit-container .otoscope-date"));
		String firstOtoscopyDate1 = Temp.printElementText(firstOtoscopyDate);
		WebElement delteOtoscopy = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel .date-edit-container .fa.fa-trash-o.clickable"));
		WebElement editOtoscopy = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel .date-edit-container .fa.fa-pencil.clickable"));
		System.out.println("Test OTO-202 step 1 passed");

		editOtoscopy.click();
		Temp.tryCatch(4000);
		WebElement previousImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .previous-otoscope.clickable"));
		System.out.println("Test OTO-202 step 2 passed");

		WebElement takeSnapShot = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .fa.fa-check.clickable"));
		takeSnapShot.click();
		Temp.tryCatch(10000);
		WebElement editOtoscopyConfirm = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2)  :nth-child(2) .otoscope-panel.edit-otoscope .panel-confirm"));
		System.out.println("Test OTO-202 step 3 passed");

		WebElement cancelEditConfirm = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2)  :nth-child(2) .otoscope-panel.edit-otoscope .panel-confirm a"));
		cancelEditConfirm.click();
		Temp.tryCatch(10000);
		WebElement previousImage2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .previous-otoscope.clickable"));
		System.out.println("Test OTO-202 step 4 passed");

		WebElement takeSnapShot2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .fa.fa-check.clickable"));
		takeSnapShot2.click();
		Temp.tryCatch(10000);
		WebElement acceptSnapshot = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .panel-confirm .btn.btn-primary"));
		Temp.tryCatch(10000);
		acceptSnapshot.click();
		Temp.tryCatch(8000);
		WebElement secondOtoscopyDate = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) :nth-child(2) .date-edit-container .otoscope-date"));
		String secondOtoscopyDate2 = Temp.printElementText(secondOtoscopyDate);
		Temp.checkDiffString(secondOtoscopyDate2, firstOtoscopyDate1, "Test OTO-202 step 5");

		delteOtoscopy.click();
		Temp.tryCatch(3000);
		WebElement deleteOtoscopyPanel = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2)  :nth-child(2) :nth-child(2).otoscope-panel .panel-confirm"));
		System.out.println("Test OTO-202 step 6 passed");

		WebElement cancelDeleteConfirm = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2)  :nth-child(2) :nth-child(2).otoscope-panel .panel-confirm a"));
		cancelDeleteConfirm.click();
		Temp.tryCatch(4000);
		
		WebElement thirdoscopyDate = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) :nth-child(2) .date-edit-container .otoscope-date"));
		String thirdOtoscopyDate3 = Temp.printElementText(thirdoscopyDate);
		Temp.checkEqualString(thirdOtoscopyDate3, secondOtoscopyDate2, "Test OTO-202 step 7");

		WebElement delteOtoscopy2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel .date-edit-container .fa.fa-trash-o.clickable"));
		delteOtoscopy2.click();
		Temp.tryCatch(4000);
		
		WebElement acceptDeleteConfirm = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2)  :nth-child(2) :nth-child(2).otoscope-panel .panel-confirm  .btn.btn-primary"));
		acceptDeleteConfirm.click();
		Temp.tryCatch(4000);
		WebElement forthOtoscopyDate = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) :nth-child(2) .date-edit-container .otoscope-date"));
		String forthOtoscopyDate4 = Temp.printElementText(forthOtoscopyDate);
		Temp.checkEqualString(forthOtoscopyDate4, firstOtoscopyDate1, "Test OTO-202 step 8");
		System.out.println("===Test OTO-202 finished===");
	}

}