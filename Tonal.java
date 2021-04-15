package expert;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
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
	public void TON202FrequenciesControl() {
		WebElement tonalNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test1 .tonal"));
		tonalNavBar.click();
		Temp.tryCatch(3000);
		WebElement firstdB = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body li:nth-child(4) .frequency-units"));
		firstdB.click();
		Temp.tryCatch(1000);
		WebElement seconddB = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body li:nth-child(6) .frequency-units"));
		seconddB.click();
		Temp.tryCatch(1000);
		WebElement thirddB = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body li:nth-child(8) .frequency-units"));
		thirddB.click();
		Temp.tryCatch(1000);
		WebElement forthdB = webDriver
				.findElement(By.cssSelector("#test-screen .panel-body li:nth-child(11) .frequency-units"));
		forthdB.click();
		Temp.tryCatch(1000);
		System.out.println("Test TON-202 step 1 passed");
	}

	/**
	 * Play tonal test
	 */
	public void TON203Play() {
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
		WebElement nextDB = webDriver.findElement(By.cssSelector("#svg-flow :nth-child(7).datapoint-text"));
		no.click();
		Temp.tryCatch(3000);
		WebElement nextDB2 = webDriver.findElement(By.cssSelector("#svg-flow :nth-child(9).datapoint-text"));
		System.out.println("Test TON-302 step 2 passed");

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
		System.out.println("Test TON-302 step 3 passed");

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

	public void Ton205Remeasure() {
		WebElement tonalNavBar = webDriver
				.findElement(By.cssSelector("#audyx-main .menu-bar .tests .test-wrap.test1 .tonal"));
		tonalNavBar.click();
		Temp.tryCatch(5000);

		WebElement startPlay = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content .panel-confirm.panel-test-init .fa-play"));
		startPlay.click();
		Temp.tryCatch(4000);

		WebElement yes = webDriver.findElement(By.cssSelector(
				" #test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(1) .thumb-up"));
		WebElement no = webDriver.findElement(By.cssSelector(
				" #test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) :nth-child(1) .ad-judge.can-mark :nth-child(2) .thumb-down"));

		System.out.println("Test TON-205 step 1 passed");
		yes.click();
		Temp.tryCatch(2000);
		no.click();
		Temp.tryCatch(2000);
		WebElement remeasure = webDriver.findElement(By.cssSelector(
				"#test-screen :nth-child(1) :nth-child(2) .panel-body :nth-child(1) .ad-vocal-test-content :nth-child(3) .ad-reload a i"));
		remeasure.click();
		Temp.tryCatch(3000);
		WebElement newDataPoint = webDriver.findElement(By.cssSelector("#svg-flow circle"));
		System.out.println("Test TON-205 step 2 passed");
	}

	/**
	 * View all element in tonal graph in patient folder
	 */
	public void TON301AudiogramGraph() {
		WebElement audyxIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		audyxIcon.click();
		Temp.tryCatch(2000);
		WebElement searchPatient = webDriver.findElement(By.className("search-patient"));
		searchPatient.sendKeys("with many tests");
		Temp.tryCatch(3000);
		WebElement patientName = webDriver
				.findElement(By.cssSelector("#patient-list-body table tbody .patient-fullname a"));
		patientName.click();
		Temp.tryCatch(7000);
		Temp.checkContainString(webDriver.getCurrentUrl(), "patientFolder", "Test TON-301 step 1");

		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		jse.executeScript("scrollTo(0, -document.body.scrollHeight);");
		Temp.tryCatch(6000);
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
		WebElement audyxIcon = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .audyx-status"));
		audyxIcon.click();
		Temp.tryCatch(2000);
		WebElement searchPatient = webDriver.findElement(By.className("search-patient"));
		searchPatient.sendKeys("Test PAT 35");
		Temp.tryCatch(3000);
		WebElement patientName = webDriver
				.findElement(By.cssSelector("#patient-list-body table tbody .patient-fullname a"));
		patientName.click();
		Temp.tryCatch(7000);
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
