package expert;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * PatientManagement class run all patient management environment It has 6 tests
 * cases Each case has some steps
 * 
 * @author Esty Wolpo
 *
 */
public class Configuration {
	private static WebDriver webDriver;
	private String baseURL;
	private String driverFilePath;

	/**
	 * Constructor of Configuration class
	 * 
	 * @param baseURL        - the URL that's given
	 * @param driverFilePath - the path of driver location
	 */
	Configuration(String baseURL, String driverFilePath) {
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

	// =======================================================================================================================
	public void CFG101ConfigurationScreen() {
		// click on side bar menu then click on configuration icon
		WebElement sideMenu = webDriver.findElement(By.cssSelector("#audyx-main .menu-bar .navigator"));
		sideMenu.click();
		Temp.tryCatch(6000);
		WebElement configuration = webDriver.findElement(By.cssSelector("#menu-side :nth-child(5) .config"));
		configuration.click();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.audyx.com/#/configuration", webDriver.getCurrentUrl(), "Test CFG-101 step 1");

		// click on user name icon - configuration screen appears
		WebElement mainPanel = webDriver.findElement(By.id("audyx-main"));
		Actions act = new Actions(webDriver);
		act.moveToElement(mainPanel).perform();
		Temp.tryCatch(1000);
		WebElement userIcon = webDriver.findElement(By.cssSelector("#user-details .ad-name"));
		userIcon.click();
		Temp.tryCatch(3000);
		Temp.checkUrl("https://alpha.audyx.com/#/configuration", webDriver.getCurrentUrl(), "Test CFG-101 step 2");

		// check all elements appears in this page
		WebElement userDetails = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .col-lg-4 .panel.user-panel .panel-body .col-xs-3.col-lg-8"));
		WebElement usersInCenter = webDriver
				.findElement(By.cssSelector("#audyx-main .view-animate .col-lg-4 :nth-child(2) :nth-child(1).row"));
		WebElement centerDetails = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel :nth-child(1) .display-center.col-lg-6"));
		WebElement cabinsArea = webDriver
				.findElement(By.cssSelector("#audyx-main .col-lg-8 :nth-child(2) .cabins-container"));
		System.out.println("Test CFG-101 step 3 passed");
	}

//=======================================================================================================================
	public void CFG201CenterDetails() {
		// step 1 - go to configuration screen and view all center details
		WebElement centerName = webDriver.findElement(By.cssSelector("#user-details .center-info"));
		centerName.click();
		Temp.tryCatch(1000);
		Temp.checkUrl("https://alpha.audyx.com/#/configuration", webDriver.getCurrentUrl(), "Test CFG-201 step 1");
		WebElement centerMap = webDriver.findElement(By.cssSelector("#map"));
		// #audyx-main .view-animate .col-lg-8 .center-panel .panel-body
		// .col-lg-6:nth-child(2)"));
		WebElement editCenter = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel :nth-child(1) .display-center.col-lg-6  .fa-pencil"));
		WebElement centerPhone = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel :nth-child(1) .display-center.col-lg-6 p:nth-child(4) .fa-phone"));
		WebElement centerMail = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel :nth-child(1) .display-center.col-lg-6 :nth-child(5) :nth-child(2)"));
		WebElement centerAddress = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel :nth-child(1) .display-center.col-lg-6 :nth-child(6) :nth-child(2)"));
		WebElement addUser = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel .center-admin-buttons :nth-child(1) .fa-plus"));
		WebElement dwonloadCenter = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel .center-admin-buttons :nth-child(2) .fa-download"));
		Temp.checkUrl("https://alpha.audyx.com/#/configuration", webDriver.getCurrentUrl(), "Test CFG-201 step 2");

		// edit center name and cancel editing -check the center name wasn't changed

		WebElement centerFax1 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel :nth-child(1) .display-center.col-lg-6  p:nth-child(4) :nth-child(3) :nth-child(2)"));
		String oldFax = Temp.printElementText(centerFax1);
		Temp.tryCatch(2000);
		editCenter.click();
		Temp.tryCatch(3000);
		WebElement centerFax2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel :nth-child(2) :nth-child(1) .col-lg-6 :nth-child(2) :nth-child(3) .form-control"));
		WebElement cancelEdit = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel :nth-child(2) :nth-child(4) a:nth-child(1)"));
		Temp.editDetails(centerFax2, "02020202", cancelEdit);

		WebElement centerFax3 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel :nth-child(1) .display-center.col-lg-6  p:nth-child(4) :nth-child(3) :nth-child(2)"));
		String newFax = Temp.printElementText(centerFax3);
		Temp.checkEqualString(oldFax, newFax, "Test CFG-201 step 3");

		// edit center Fax and save -check the center name was changed
		WebElement centerFax4 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel :nth-child(1) .display-center.col-lg-6  p:nth-child(4) :nth-child(3) :nth-child(2)"));
		String oldFax2 = Temp.printElementText(centerFax4);
		Temp.tryCatch(2000);
		editCenter.click();
		Temp.tryCatch(5000);
		WebElement centerFax5 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel :nth-child(2) :nth-child(1) .col-lg-6 :nth-child(2) :nth-child(3) .form-control"));
		WebElement saveEdit = webDriver.findElement(By.cssSelector(
				"#audyx-main .body .content .view-animate .main-content :nth-child(2).col-lg-8 .panel.center-panel .panel-body.row .col-sm-12 .edit-center .col-lg-12 .btn.btn-primary"));
		Temp.editDetails(centerFax5, "02020202", saveEdit);

		WebElement centerFax6 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel :nth-child(1) .display-center.col-lg-6  p:nth-child(4) :nth-child(3) :nth-child(2)"));
		String newFax2 = Temp.printElementText(centerFax6);
		Temp.checkDiffString(oldFax2, newFax2, "Test CFG-201 step 4");
		System.out.println("==Test CFG-201 passed==");

		// change again the Fax number in order to pass next time testing it
		editCenter.click();
		Temp.tryCatch(5000);
		WebElement centerFax7 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel :nth-child(2) :nth-child(1) .col-lg-6 :nth-child(2) :nth-child(3) .form-control"));
		WebElement saveEdit2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .body .content .view-animate .main-content :nth-child(2).col-lg-8 .panel.center-panel .panel-body.row .col-sm-12 .edit-center .col-lg-12 .btn.btn-primary"));
		Temp.editDetails(centerFax7, "888888", saveEdit2);

	}

//=======================================================================================================================

	// change center
	public void CFG202SwitchCenter() {
//choose another center and check of modal of switch center appears
		WebElement configScreen = webDriver.findElement(By.cssSelector("#user-details .center-info"));
		configScreen.click();
		Temp.tryCatch(1000);
		WebElement centerName1 = webDriver.findElement(By.cssSelector("#user-details .center-info"));
		String centerName11 = centerName1.getAttribute("innerHTML");
		System.out.println("Current Center name is: " + centerName11);

		Temp.tryCatch(2000);
		WebElement changeCenter1 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-4 .panel.user-panel ng-include .panel-body .col-xs-6.col-lg-12 table  tbody :nth-child(2) :nth-child(3) button"));
		changeCenter1.click();
		Temp.tryCatch(2000);
		WebElement stayInCenter = webDriver.findElement(By.cssSelector("body .modal.fade.in .modal-footer a"));
		System.out.println("Test CFG-202 step 1 passed");

//click on stay in current center
		stayInCenter.click();
		Temp.tryCatch(2000);
		WebElement centerName2 = webDriver.findElement(By.cssSelector("#user-details .center-info"));
		String centerName22 = centerName2.getAttribute("innerHTML");
		System.out.println("Current Center name is: " + centerName22);
		Temp.checkEqualString(centerName11, centerName22, "Test CFG-202 step 2");

// change center and accept the change
		WebElement changeCenter2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-4 .panel.user-panel ng-include .panel-body .col-xs-6.col-lg-12 table  tbody :nth-child(2) :nth-child(3) button"));
		changeCenter2.click();
		Temp.tryCatch(2000);
		WebElement acceptChange = webDriver.findElement(By.cssSelector("body .modal.fade.in .modal-footer button"));
		acceptChange.click();
		Temp.tryCatch(8000);
		Temp.checkDiffString(centerName2.getText(), centerName22, "Test CFG-202 step 3");

		// return back to previous center

		WebElement changeCenter3 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-4 .panel.user-panel ng-include .panel-body .col-xs-6.col-lg-12 table tbody :nth-child(1) :nth-child(3) button"));
		changeCenter3.click();
		Temp.tryCatch(4000);
		WebElement acceptChange2 = webDriver.findElement(By.cssSelector("body .modal.fade.in .modal-footer button"));
		acceptChange2.click();
		Temp.tryCatch(5000);
		System.out.println("==Test CFG-202 passed==");

	}

//=======================================================================================================================
	// cabin view in configuration screen
	public void CFG203CenterCabins() {

		WebElement configScreen = webDriver.findElement(By.cssSelector("#user-details .center-info"));
		configScreen.click();
		Temp.tryCatch(1000);

		WebElement cabinPanel = webDriver.findElement(By.cssSelector(
				"#audyx-main  .view-animate .col-lg-8 :nth-child(2) .cabins-container :nth-child(1) .panel.ad-cabine-panel"));
		WebElement cabinName = webDriver.findElement(By.cssSelector(
				"#audyx-main  .view-animate .col-lg-8 :nth-child(2) .cabins-container :nth-child(1) .panel.ad-cabine-panel .panel-heading .cabin-title"));
		WebElement microphoneName = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 :nth-child(2) :nth-child(1) :nth-child(1) .ad-cabine-panel .field-configuration :nth-child(2) :nth-child(2).cabine-prop-text"));
		WebElement output = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 :nth-child(2) :nth-child(1) :nth-child(1) .ad-cabine-panel .transducers-mapping :nth-child(1) :nth-child(2).cabine-prop-text"));
		WebElement monitor = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 :nth-child(2) :nth-child(1) :nth-child(1) .ad-cabine-panel .transducers-mapping :nth-child(1) :nth-child(2).cabine-prop-text"));
		WebElement talkBack = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 :nth-child(2) :nth-child(1) :nth-child(1) .ad-cabine-panel .transducers-mapping :nth-child(2) :nth-child(2).cabine-prop-text"));
		WebElement talkForward = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 :nth-child(2) :nth-child(1) :nth-child(1) .ad-cabine-panel .transducers-mapping :nth-child(3) :nth-child(2).cabine-prop-text"));
		System.out.println("Test CFG-203 step 1 passed");
//edit cabin name
		/*
		 * WebElement editCabinName = webDriver.findElement(By.cssSelector(
		 * " #audyx-main > div > div > div > div.body > section > div.view-animate > div > div.col-lg-8 > div:nth-child(2) > div > div:nth-child(2) > div > div.panel-heading > span.text-primary.cabin-title.clickable"
		 * )); editCabinName.click(); Temp.tryCatch(6000);
		 * 
		 * editCabinName.sendKeys(Keys.DELETE); editCabinName.sendKeys("newCabinName");
		 * Temp.tryCatch(3000); Temp.checkDiffString("oldCabinName", "newCabinName",
		 * "Test CFG-203 step 2");
		 */
// click on delete cabin icon
		WebElement deleteCabin = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 :nth-child(1) :nth-child(1) :nth-child(1) .panel-heading i.fa.fa-trash.clickable"));
		deleteCabin.click();
		Temp.tryCatch(4000);
		WebElement confirmDelete = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 :nth-child(1) :nth-child(1) .panel.ad-cabine-panel .panel-confirm"));
		System.out.println("Test CFG-203 step 3 passed");

		WebElement cancelDelete = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 :nth-child(1) :nth-child(1) .panel.ad-cabine-panel .panel-confirm a"));
		cancelDelete.click();
		Temp.tryCatch(4000);
		System.out.println("Test CFG-203 step 4 passed");
		System.out.println("==Test CFG-203 passed==");
	}

//=======================================================================================================================
	// User details - in left side of configuration screen
	public void CFG301UserDetails() {
		// check you are in configuration screen
		WebElement configurationScreen = webDriver.findElement(By.cssSelector("#user-details .center-info"));
		Temp.configScreen(configurationScreen, "Test CFG-301 step 1");
		// view all user details
		WebElement userName1 = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate  .col-lg-4 .panel.user-panel .panel-heading h3"));
		String userName11 = Temp.splitStringWithSpecialChar(userName1, "<");
		System.out.println(userName11);
		WebElement emailUser = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-4 .panel.user-panel .panel-body .col-xs-3.col-lg-8 .user-email :nth-child(2)"));
		WebElement portalUser = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-4 .panel.user-panel .panel-body .col-xs-3.col-lg-8 :nth-child(4) a"));
		System.out.println("Test CFG-301 step 2 passed");

		WebElement editButton = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .col-lg-4 .panel.user-panel span i.fa.fa-pencil"));
		editButton.click();
		Temp.tryCatch(3000);
		WebElement editUserName = webDriver.findElement(By.cssSelector("#lastname"));
		editUserName.sendKeys("No name");

		Temp.tryCatch(1000);
		WebElement cancelEdit = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .col-lg-4 .panel.user-panel .panel-body .row a"));
		cancelEdit.click();
		Temp.tryCatch(5000);
		WebElement userName3 = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate  .col-lg-4 .panel.user-panel .panel-heading h3"));
		String userName33 = Temp.splitStringWithSpecialChar(userName3, "<");
		System.out.println(userName33);
		Temp.checkEqualString(userName11, userName33, "Test CFG-301 step 3");

// edit user details and accept
		WebElement userName4 = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate  .col-lg-4 .panel.user-panel .panel-heading h3"));
		String userName44 = Temp.splitStringWithSpecialChar(userName4, "<");
		System.out.println(userName44);
		WebElement editButton1 = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .col-lg-4 .panel.user-panel span i.fa.fa-pencil"));
		editButton1.click();
		Temp.tryCatch(3000);
		WebElement editUserName2 = webDriver.findElement(By.cssSelector("#lastname"));
		editUserName2.clear();
		Temp.tryCatch(1000);
		editUserName2.sendKeys("Test " + RandomStringUtils.randomNumeric(2));

		WebElement saveEdit = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-4 .panel.user-panel .panel-body .row .ad-button-group.clickable .btn-primary"));
		saveEdit.click();
		Temp.tryCatch(5000);
		WebElement userName5 = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate  .col-lg-4 .panel.user-panel .panel-heading h3"));
		String userName55 = userName5.getAttribute("innerHTML");
		Temp.checkDiffString(userName44, userName55, "Test CFG-301 step 4");
		System.out.println("==Test CFG-301 finished==");
	}

//=======================================================================================================================
	public void CFG302InviteUser() {
		// check you are in configuration screen, then click add user
		WebElement configurationScreen = webDriver.findElement(By.cssSelector("#user-details .center-info"));
		configurationScreen.click();
		Temp.tryCatch(1000);
		WebElement addUserButton = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel .center-admin-buttons button:nth-child(1)"));
		addUserButton.click();
		System.out.println("Test CFG-302 step 1 passed");
		Temp.tryCatch(3000);
//view add user panel
		WebElement email = webDriver.findElement(By.cssSelector("#email"));
		WebElement userRole = webDriver.findElement(By.cssSelector("#audyx-main .select2-chosen .label-Collaborator"));
		WebElement language = webDriver.findElement(By.cssSelector("#audyx-main .select2-chosen .label-locale"));

		WebElement cancel = webDriver
				.findElement(By.cssSelector("#audyx-main .view-animate .col-lg-8 :nth-child(2) .ad-button-group a"));
		WebElement submit = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .col-lg-8 :nth-child(2) .ad-button-group .btn-primary"));
		System.out.println("Test CFG-302 step 2 passed");
//enter email address but press cancel
		email.sendKeys("esty@audyx.com");
		cancel.click();
		Temp.tryCatch(3000);
		System.out.println("Test CFG-302 step 3 passed");
//try to add user with empty name field -add button in not clickable
		WebElement addUserButton2 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel .center-admin-buttons button:nth-child(1)"));
		addUserButton2.click();
		Temp.tryCatch(5000);
		WebElement submit2 = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .col-lg-8 :nth-child(2) .ad-button-group .btn-primary"));
		Temp.checkButtonNotClickable(submit2, "Test CFG-302 step 4");
//enter invalid email address - check if error message appears
		WebElement email2 = webDriver.findElement(By.cssSelector("#email"));
		email2.sendKeys("a");
		WebElement invalidMessage = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .col-lg-8 :nth-child(2) .row .col-lg-4 .help-block"));
		System.out.println("Test CFG-302 step 5 passed");
//add new user and submit, check if authentication modal appears
		WebElement addUserButton3 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel .center-admin-buttons button:nth-child(1)"));
		addUserButton3.click();
		Temp.tryCatch(5000);
		WebElement email3 = webDriver.findElement(By.cssSelector("#email"));
		email3.sendKeys("stw55772@gmail.com");
		WebElement submit3 = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .col-lg-8 :nth-child(2) .ad-button-group .btn-primary"));
		submit3.click();
		Temp.tryCatch(5000);
		WebElement authenticateModal = webDriver.findElement(By.cssSelector(".modal.fade.in .modal-content"));
		System.out.println("Test CFG-302 step 6 passed");
		// cancel authentication modal
		WebElement cancelAutenticateModal = webDriver
				.findElement(By.cssSelector(".modal.fade.in .modal-content :nth-child(1).btn-primary"));
		cancelAutenticateModal.click();
		System.out.println("Test CFG-302 step 7 passed");
//re-enter data and confirm password
		WebElement addUserButton4 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel .center-admin-buttons button:nth-child(1)"));
		addUserButton4.click();
		Temp.tryCatch(5000);
		WebElement email4 = webDriver.findElement(By.cssSelector("#email"));
		email4.sendKeys("stw55772@gmail.com");
		// change role to administrator:
		WebElement changeRole = webDriver
				.findElement(By.cssSelector("#audyx-main .select2-chosen .label-Collaborator"));
		changeRole.click();
		Temp.tryCatch(3000);
		WebElement admin = webDriver.findElement(By.cssSelector("#select2-drop li:nth-child(1) .select2-result-label"));
		admin.click();
		Temp.tryCatch(3000);
		WebElement submit4 = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .col-lg-8 :nth-child(2) .ad-button-group .btn-primary"));
		submit4.click();
		Temp.tryCatch(5000);
		WebElement enterPassword = webDriver.findElement(By.cssSelector(".modal.fade.in .modal-body .ng-pristine"));
		enterPassword.sendKeys("123456Qw");
		Temp.tryCatch(3000);
		WebElement continueButton = webDriver.findElement(By.cssSelector(".modal.fade.in .modal-footer :nth-child(2)"));
		continueButton.click();
		Temp.tryCatch(3000);
		System.out.println("Test CFG-302 step 8 passed");
//go to configuration screen and see the new user is added
		WebElement newUserInList = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .col-lg-4 :nth-child(2) :nth-child(2) .col-lg-6"));
		System.out.println("Test CFG-302 step 9 passed");

		// try to add user that already exist
		WebElement addUserButton5 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel .center-admin-buttons button:nth-child(1)"));
		addUserButton.click();
		addUserButton5.click();
		Temp.tryCatch(5000);
		WebElement email5 = webDriver.findElement(By.cssSelector("#email"));
		Temp.tryCatch(3000);
		WebElement submit5 = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .col-lg-8 :nth-child(2) .ad-button-group .btn-primary"));
		submit5.click();
		Temp.tryCatch(5000);
		System.out.println("Test CFG-302 step 12 passed");

		WebElement addUserButton6 = webDriver.findElement(By.cssSelector(
				"#audyx-main .view-animate .col-lg-8 .panel.center-panel .center-admin-buttons button:nth-child(1)"));
		addUserButton6.click();
		Temp.tryCatch(3000);
		WebElement email6 = webDriver.findElement(By.cssSelector("#email"));
		email5.sendKeys("esty@audyx.com");
		Temp.tryCatch(3000);
		// change role to administrator:
		WebElement changeRole2 = webDriver
				.findElement(By.cssSelector("#audyx-main .select2-chosen .label-Collaborator"));
		changeRole2.click();
		Temp.tryCatch(3000);
		WebElement admin2 = webDriver
				.findElement(By.cssSelector("#select2-drop li:nth-child(1) .select2-result-label"));
		admin2.click();
		Temp.tryCatch(3000);
		WebElement submit6 = webDriver.findElement(
				By.cssSelector("#audyx-main .view-animate .col-lg-8 :nth-child(2) .ad-button-group .btn-primary"));
		submit6.click();
		Temp.tryCatch(5000);
		WebElement warningtModal = webDriver.findElement(By.cssSelector(".modal.fade.in .modal-content"));
		System.out.println("Test CFG-302 step 13 passed");

		WebElement acceptExistModal = webDriver
				.findElement(By.cssSelector(".modal.fade.in .modal-footer .btn-primary"));
		acceptExistModal.click();
		Temp.tryCatch(5000);
		Temp.checkUrl("https://alpha.audyx.com/#/configuration", webDriver.getCurrentUrl(), "Test CFG-302 step 14");
		System.out.println("==Test CFG-302 passed==");

	}

//=======================================================================================================================

	public void CFG303RemoveUser() {

		WebElement configurationScreen = webDriver.findElement(By.cssSelector("#user-details .center-info"));
		Temp.configScreen(configurationScreen, "Test CFG-303 step 1");
		WebElement trashButton = webDriver.findElement(By.cssSelector("#collaborator-678 .panel-body i"));
		trashButton.click();
		Temp.tryCatch(6000);
		WebElement confirmTrashButton = webDriver.findElement(By.cssSelector("#collaborator-678 .panel-confirm"));
		System.out.println("Test CFG-303 step 2 passed");

// cancel delete user
		WebElement cancelConfirmTrashButton = webDriver
				.findElement(By.cssSelector("#collaborator-678 .panel-confirm a"));
		cancelConfirmTrashButton.click();
		Temp.tryCatch(5000);
		System.out.println("Test CFG-303 step 3 passed");

	}
	// =======================================================================================================================

	public void CFG304UserRole() {
		// see all user details in users panel
		WebElement configurationScreen = webDriver.findElement(By.cssSelector("#user-details .center-info"));
		Temp.configScreen(configurationScreen, "Test CFG-304 step 1");
		WebElement userName = webDriver
				.findElement(By.cssSelector("#collaborator-678 .panel-heading.ellipsis .text-primary"));
		WebElement userPicture = webDriver
				.findElement(By.cssSelector("#collaborator-678 .panel-body :nth-child(2) .user-image"));
		WebElement userRole = webDriver.findElement(By.cssSelector("#audyx-main .select2-chosen"));
		System.out.println("Test CFG-304 step 2 passed");

		// change user role
		WebElement oldRole = webDriver.findElement(By.cssSelector("#audyx-main .select2-chosen"));
		String currentRole = oldRole.getText();
		System.out.println(currentRole);
		oldRole.click();
		Temp.tryCatch(3000);
		WebElement chooseAnotherRole = webDriver
				.findElement(By.cssSelector("#select2-drop :nth-child(2).select2-results-dept-0"));
		chooseAnotherRole.click();
		Temp.tryCatch(3000);
		WebElement pwArea = webDriver.findElement(By.cssSelector("body .modal.fade.in .modal-body .ng-pristine"));
		pwArea.sendKeys("123456Qw");
		Temp.tryCatch(5000);
		WebElement continueButton = webDriver
				.findElement(By.cssSelector("body .modal.fade.in .modal-footer :nth-child(2)"));
		continueButton.click();
		Temp.tryCatch(5000);
		System.out.println("Test CFG-304 step 3 passed");

		// change again the role
		WebElement userRole2 = webDriver.findElement(By.cssSelector("#audyx-main .select2-chosen"));
		String newRole = userRole2.getText();
		System.out.println(newRole);
		userRole2.click();
		Temp.tryCatch(3000);
		WebElement chooseNewRole = webDriver.findElement(By.cssSelector("#select2-drop ul li:nth-child(1)"));
		chooseNewRole.click();
		Temp.tryCatch(5000);
		Temp.checkDiffString(newRole, currentRole, "Test CFG-304 step 4");
		// return back to first role - for the next tests
		WebElement backToOldRole = webDriver.findElement(By.cssSelector("#audyx-main .select2-chosen"));
		backToOldRole.click();
		Temp.tryCatch(3000);
		WebElement chooseNewRole2 = webDriver.findElement(By.cssSelector("#select2-drop ul li:nth-child(1)"));
		chooseNewRole2.click();
		Temp.tryCatch(5000);

	}
}
