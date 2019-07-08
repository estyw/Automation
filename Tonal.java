package expert;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Tonal {

	private static WebDriver webDriver;
	private String baseURL;
	private String driverFilePath;

	Tonal(String baseURL, String driverFilePath) {
		this.baseURL = baseURL;
		this.driverFilePath = driverFilePath;
		init();
	}

	private void init() {
		System.setProperty("webdriver.chrome.driver", driverFilePath);
		webDriver = new ChromeDriver();
		// Setting the "implicit wait" (for the FindElement/s operations only!)
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
		webDriver.get(baseURL);
	}

	/**
	 * Sign in with user-name and password
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

	// Navigate to tonal test via nav bar
	public void TON101TestPreparation() {
		// first create a patient
		Tonal.createNewPatient();
		// click on tonal icon and validate that it's in tonal screen
		WebElement tonalNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test1 .tonal"));
		tonalNavBar.click();
		Temp.tryCatch(5000);
		Temp.checkUrl("https://alpha.audyx.com/#/test/tone", webDriver.getCurrentUrl(), "Test TON-101 step 1");

		WebElement audyxIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		audyxIcon.click();
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
		Temp.checkUrl("https://alpha.audyx.com/#/test/tone", webDriver.getCurrentUrl(), "Test TON-101 step 2");

		// view all elements in tonal page

		WebElement commands = webDriver
				.findElement(By.cssSelector("#test-screen .top-right-panel .test-commands-panel"));
		WebElement graphArea = webDriver
				.findElement(By.cssSelector("#test-type-2 > div.panel :nth-child(1) .div-chart.type-2"));
		WebElement cabin = webDriver.findElement(By.cssSelector("#test-screen :nth-child(2) .panel-body .cabin-view"));
		WebElement startIcon = webDriver.findElement(By.cssSelector("#test-screen .panel-body .start-test .fa-play"));

		System.out.println("Test TON-101 step 3 passed");

	}

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
	 * See all transducers in cabin avatar, only calibrated are clickable cabin
	 * avatar panel is opened from previous test
	 */
	public void TON104TransducerSelection() {
		WebElement tonalIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap .tonal"));
		tonalIcon.click();
		Temp.tryCatch(10000);

		WebElement cabinPanel = webDriver.findElement(By.className("cabin-params-panel"));
		cabinPanel.click();
		Temp.tryCatch(3000);

		WebElement ac = webDriver.findElement(By.cssSelector("#test-screen .transducers .lg-widget-icon.AirConductor"));
		if (ac.isEnabled())
			System.out.println("AC is calibrated");
		WebElement insert = webDriver
				.findElement(By.cssSelector("#test-screen .transducers .lg-widget-icon.InsertPhone.disabled"));
		System.out.println("insert isn't calibrated");
		WebElement bone = webDriver
				.findElement(By.cssSelector("#test-screen .transducers .lg-widget-icon.BoneConductor.disabled"));
		System.out.println("Bone isn't calibrated");
		WebElement FF = webDriver
				.findElement(By.cssSelector("#test-screen .transducers .lg-widget-icon.FreeField.disabled"));
		System.out.println("FF isn't calibrated");
		System.out.println("Test TON-104 step 1 passed");
	}

	/**
	 * See the dB is changed
	 * 
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

		action.sendKeys(Keys.ARROW_DOWN).build().perform();
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
	 * Disable all no-mandatory levels
	 */
	public static void TON202FrequenciesControl() {
		WebElement tonalNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test1 .tonal"));
		tonalNavBar.click();
		Temp.tryCatch(5000);
		WebElement firstdB = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body li:nth-child(4) .frequency-units"));
		firstdB.click();
		Temp.tryCatch(3000);
		Temp.tryCatch(5000);
		WebElement seconddB = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body li:nth-child(6) .frequency-units"));
		seconddB.click();
		Temp.tryCatch(3000);
		WebElement thirddB = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body li:nth-child(8) .frequency-units"));
		thirddB.click();
		Temp.tryCatch(3000);
		WebElement forthdB = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body li:nth-child(11) .frequency-units"));
		forthdB.click();
		Temp.tryCatch(3000);
		System.out.println("Test TON-202 step 1 passed");
	}

	/**
	 * 
	 */
	public static void TON203Play() {
		// no yes no yes no no yes
	}

	/**
	 * Create a new patient
	 */
	public static void createNewPatient() {
		WebElement audyxIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		audyxIcon.click();
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
		lastName2.sendKeys("PAT " + RandomStringUtils.randomNumeric(2));
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

}
