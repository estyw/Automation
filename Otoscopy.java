package expert;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;

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
		Temp.tryCatch(3000);
// action act...

		Temp.checkUrl("https://alpha.audyx.com/#/otoscope", webDriver.getCurrentUrl(), "Test OTO-101-Access step 1");

		WebElement patientIcon = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .patient .info-text :nth-child(2) .ad-name"));
		patientIcon.click();
		Temp.tryCatch(5000);
		WebElement cameraIcon = webDriver.findElement(By.cssSelector(
				"#patient-info .panel-body :nth-child(2) .ears-condition.row fieldset:nth-child(1) .fa-camera"));
		System.out.println("Test OTO-101Access step 2 passed");
		cameraIcon.click();

		Temp.checkUrl("https://alpha.audyx.com/#/otoscope", webDriver.getCurrentUrl(), "Test OTO-101Access step 3");
		Temp.tryCatch(3000);
	}

	public static void OTO201Access() {

		WebElement rightEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(2) .otoscope-ear .ear "));
		WebElement leftEar = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-ear .ear"));
		WebElement deviceConnectStatus = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.empty-otoscope .device-status-text"));
		WebElement setupLink = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .empty-otoscope .device-status-text .device-status-action"));
		String setupLink1 = Temp.printElementText(setupLink);
		Temp.checkContainString(setupLink1, "Setup", "Test OTO-201ImageCapture step 1");

		setupLink.click();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.audyx.com/#/audio-setup", webDriver.getCurrentUrl(),
				"Test OTO-201ImageCapture step 2");

		WebElement addCabin = webDriver.findElement(By.cssSelector("#audyx-main .view-animate .top-panel .fa-plus"));
		addCabin.click();
		Temp.tryCatch(3000);
		WebElement insertNewCabin = webDriver
				.findElement(By.cssSelector("#audyx-main section .view-animate .top-panel input"));
		insertNewCabin.sendKeys("b");
		WebElement createCabin = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .top-panel .btn.btn-primary.create-or-edit-item"));
		createCabin.click();
		WebElement otoscopyArea = webDriver.findElement(By.cssSelector(" #s2id_autogen7 .select2-chosen .placeholder"));
		otoscopyArea.click();
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
		String deviceConnected = Temp.printElementText(deviceStatus);
		Temp.checkContainString(deviceConnected, "Device is connected", "Test OTO-201ImageCapture step 3");

// =====left ear
		leftEar.click();
		Temp.tryCatch(2000);
		WebElement submitOtoscopy = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .fa-check.clickable"));
		System.out.println("Test OTO-201ImageCapture step 4 passed");
		submitOtoscopy.click();
		Temp.tryCatch(7000);
		WebElement leftImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel .otoscope-image"));
		System.out.println("Test OTO-201ImageCapture step 5 passed");

		WebElement leftEar2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-ear .ear"));
		leftEar2.click();
		Temp.tryCatch(3000);

		WebElement previousImageLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .previous-otoscope.clickable"));
		System.out.println("Test OTO-201ImageCapture step 6 passed");

		WebElement submitOtoscopy2Left = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .fa-check.clickable"));
		submitOtoscopy2Left.click();
		Temp.tryCatch(6000);
		WebElement confirmEditOtoscopyModalLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .panel-confirm"));
		System.out.println("Test OTO-201ImageCapture step 7 passed");

		WebElement confirmEditOtosocpyLeft = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(1) :nth-child(1) .otoscope-panel.edit-otoscope .panel-confirm .btn-primary"));
		confirmEditOtosocpyLeft.click();
		Temp.tryCatch(3000);
		System.out.println("Test OTO-201ImageCapture step 8 passed");
// ===right ear
		rightEar.click();
		Temp.tryCatch(2000);
		WebElement submitOtoscopyRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .fa-check.clickable"));
		System.out.println("Test OTO-201ImageCapture step 9.1 passed");
		submitOtoscopy.click();
		Temp.tryCatch(7000);
		WebElement rightImage = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(1) :nth-child(2) :nth-child(2) .otoscope-panel .otoscope-image"));
		System.out.println("Test OTO-201ImageCapture step 9.2 passed");

		WebElement rightEar2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .body section .view-animate :nth-child(1) :nth-child(2) .otoscope-ear .ear "));
		rightEar2.click();
		Temp.tryCatch(3000);

		WebElement previousImageRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .previous-otoscope.clickable"));
		System.out.println("Test OTO-201ImageCapture step 9.3 passed");

		WebElement submitOtoscopy2Right = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .fa-check.clickable"));
		submitOtoscopy2Right.click();
		Temp.tryCatch(6000);
		WebElement confirmEditOtoscopyModalRight = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .panel-confirm"));
		System.out.println("Test OTO-201ImageCapture step 9.4 passed");

		WebElement confirmEditOtosocpyRight = webDriver.findElement(By.cssSelector(
				"audyx-main .view-animate :nth-child(2) :nth-child(2) .otoscope-panel.edit-otoscope .panel-confirm .btn-primary"));
		confirmEditOtosocpyRight.click();
		Temp.tryCatch(3000);
		System.out.println("Test OTO-201ImageCapture step 9.5 passed");

	}

}