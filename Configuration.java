package expert;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
	 * Constructor of PatientManagement class
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

	public void CFG201() {
		// step 1 - go to configuration screen and view all center details
		WebElement configScreen = webDriver.findElement(By.xpath("//*[@id=\"user-details\"]/span/div/span[2]"));
		configScreen.click();
		Temp.tryCatch(1000);
		Temp.checkUrl("https://alpha.audyx.com/#/configuration", webDriver.getCurrentUrl(), "Test CFG-201 step 1");
		WebElement centerMap = webDriver
				.findElement(By.cssSelector(" #map > div > div > div:nth-child(1) > div:nth-child(3)"));
		WebElement centerPhone = webDriver
				.findElement(By.cssSelector(" #map > div > div > div:nth-child(1) > div:nth-child(3)"));
		WebElement centerMail = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[1]/div/div[1]/p[2]"));
		WebElement centerAddress = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[1]/div/div[1]/p[3]"));
		WebElement addUser = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[2]/button[1]"));
		WebElement dwonloadCenter = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[2]/button[2]"));
		WebElement editCenter = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[1]/div/div[1]/span"));
		Temp.checkUrl("https://alpha.audyx.com/#/configuration", webDriver.getCurrentUrl(), "Test CFG-201 step 2");

		// edit center name and cancel editing -check the center name wasn't changed
		WebElement centerPhone1 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[1]/div/div[1]/p[1]/span[2]"));
		String oldPhone = centerPhone1.getAttribute("innerHTML");
		System.out.println(oldPhone);

		Temp.tryCatch(1000);
		editCenter.click();
		Temp.tryCatch(3000);
		WebElement centerPhone2 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[2]/div/form/div[1]/div[1]/div[2]/div/div[2]/div/input"));
		centerPhone2.click();
		Temp.tryCatch(1000);
		centerPhone2.clear();
		centerPhone2.sendKeys("02020202");
		WebElement cancelEdit = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[2]/div/form/div[2]/div/div/a[1]"));

		cancelEdit.click();
		Temp.tryCatch(6000);
		WebElement centerPhone3 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[1]/div/div[1]/p[1]/span[2]"));
		String newPhone = centerPhone3.getAttribute("innerHTML");
		System.out.println(newPhone);
		Temp.checkEqualString(oldPhone, newPhone, "Test CFG-201 step 3");

		// edit center name and cancel editing -check the center name was changed
		WebElement centerPhone4 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[1]/div/div[1]/p[1]/span[2]"));
		String oldPhone2 = centerPhone4.getAttribute("innerHTML");
		System.out.println(oldPhone2);
		Temp.tryCatch(1000);
		WebElement editCenter2 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[1]/div/div[1]/span"));
		editCenter2.click();
		Temp.tryCatch(5000);
		WebElement centerPhone5 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[2]/div/form/div[1]/div[1]/div[2]/div/div[2]/div/input"));
		centerPhone5.click();
		Temp.tryCatch(7000);
		centerPhone5.clear();
		centerPhone5.sendKeys("02020202");
		WebElement saveEdit = webDriver.findElement(By.xpath(
				"//*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[2]/div/form/div[2]/div/div/a[2] "));
		saveEdit.click();
		Temp.tryCatch(5000);
		WebElement centerPhone6 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[1]/div/div[1]/p[1]/span[2]"));
		String newPhone2 = centerPhone6.getAttribute("innerHTML");
		System.out.println(newPhone2);
		Temp.checkDiffString(oldPhone2, newPhone2, "Test CFG-201 step 4");
		System.out.println("==Test CFG-201 passed==");

		// change again the phone number in order to pass next time testing it
		WebElement editCenter3 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[1]/div/div[1]/span"));
		editCenter3.click();
		Temp.tryCatch(5000);
		WebElement centerPhone7 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[2]/div/form/div[1]/div[1]/div[2]/div/div[2]/div/input"));
		centerPhone7.click();
		Temp.tryCatch(7000);
		centerPhone7.clear();
		centerPhone7.sendKeys("888888");
		WebElement saveEdit2 = webDriver.findElement(By.xpath(
				"//*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[2]/div/form/div[2]/div/div/a[2] "));
		saveEdit2.click();
	}

//================================================================================
	// change center
	public void CFG202() {
//choose another center and check of modal of switch center appears
		WebElement configScreen = webDriver.findElement(By.xpath("//*[@id=\"user-details\"]/span/div/span[2]"));
		configScreen.click();
		Temp.tryCatch(1000);
		WebElement centerName1 = webDriver.findElement(By.xpath(" //*[@id=\"user-details\"]/span/div/span[2]"));
		String centerName11 = centerName1.getAttribute("innerHTML");
		System.out.println("Current Center name is: " + centerName11);
		// centerName1.click();
		Temp.tryCatch(2000);
		WebElement changeCenter1 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[1]/div[1]/div/ng-include/div/div[2]/div/div[3]/table/tbody/tr[1]/td[3]/button"));
		changeCenter1.click();
		Temp.tryCatch(2000);
		WebElement stayInCenter = webDriver
				.findElement(By.cssSelector(" body > div.modal.fade.in > div > div > div.modal-footer > a"));
		System.out.println("Test CFG-202 step 1 passed");

//click on stay in current center
		stayInCenter.click();
		Temp.tryCatch(2000);
		WebElement centerName2 = webDriver.findElement(By.xpath(" //*[@id=\"user-details\"]/span/div/span[2]"));
		String centerName22 = centerName2.getAttribute("innerHTML");
		System.out.println("Current Center name is: " + centerName22);
		Temp.checkEqualString(centerName11, centerName22, "Test CFG-202 step 2");

// change center and accept the change
		WebElement changeCenter2 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[1]/div[1]/div/ng-include/div/div[2]/div/div[3]/table/tbody/tr[1]/td[3]/button"));
		changeCenter2.click();
		Temp.tryCatch(2000);
		WebElement acceptChange = webDriver
				.findElement(By.cssSelector("body > div.modal.fade.in > div > div > div.modal-footer > button "));
		acceptChange.click();
		Temp.tryCatch(8000);
		if (centerName2.getText().equals("centerName22 "))
			System.out.println("Test CFG-202 step 3 failed");
		else
			System.out.println("Test CFG-202 step 3 passed");

// return back to previous center
		webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[1]/div[1]/div/ng-include/div/div[2]/div/div[3]/table/tbody/tr[2]/td[3]/button"))
				.click();
		Temp.tryCatch(4000);
		WebElement acceptChange2 = webDriver
				.findElement(By.cssSelector("body > div.modal.fade.in > div > div > div.modal-footer > button "));

		acceptChange2.click();
		Temp.tryCatch(5000);
		System.out.println("==Test CFG-202 passed==");
	}

	// cabin view in configuration screen
	public void CFG203() {
		// these 3 lines should be removed when running all steps
		WebElement configScreen = webDriver.findElement(By.xpath("//*[@id=\"user-details\"]/span/div/span[2]"));
		configScreen.click();
		Temp.tryCatch(3000);

		WebElement cabinPanel = webDriver.findElement(
				By.xpath(" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div[1]"));
		WebElement cabinName = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div[1]/div/div[2]"));
		WebElement microphoneName = webDriver.findElement(By.xpath(
				"//*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div[1]/div/div[3]/div[1]/p[1]/span[2] "));
		WebElement output = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div[1]/div/div[3]/div[1]/p[2]/span[2]"));
		WebElement monitor = webDriver.findElement(By.xpath(
				"//*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div[1]/div/div[3]/div[2]/p[1]/span[2]	"));
		WebElement talkBack = webDriver.findElement(By.xpath(
				"//*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div[1]/div/div[3]/div[2]/p[2] "));
		WebElement talkForward = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div[1]/div/div[3]/div[2]/p[3] "));
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
		WebElement deleteCabin = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div[2]/div/div[2]/div/i[2]"));
		deleteCabin.click();
		Temp.tryCatch(4000);
		WebElement confirmDelete = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div[2]/div/div[1]"));
		System.out.println("Test CFG-203 step 3 passed");

		WebElement cancelDelete = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div[2]/div/div[1]/a  "));
		cancelDelete.click();
		Temp.tryCatch(4000);
		System.out.println("Test CFG-203 step 4 passed");
		System.out.println("==Test CFG-203 passed==");
	}

	// User details - in left side of configuration screen
	public void CFG301() {
		// check you are in configuration screen
		WebElement configScreen = webDriver.findElement(By.xpath("//*[@id=\"user-details\"]/span/div/span[2]"));
		configScreen.click();
		Temp.tryCatch(1000);
		Temp.checkUrl("https://alpha.audyx.com/#/configuration", webDriver.getCurrentUrl(), "Test CFG-301 step 1");
// view all user details
		WebElement userName1 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[1]/div[1]/div/ng-include/div/div[1]/h3"));
		String userName11 = userName1.getAttribute("innerHTML");
		WebElement emailUser = webDriver.findElement(By.xpath(
				"//*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[1]/div[1]/div/ng-include/div/div[2]/div/div[2]/p[1]"));
		WebElement portalUser = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[1]/div[1]/div/ng-include/div/div[2]/div/div[2]/p[4]"));
		System.out.println("Test CFG-301 step 2 passed");
// edit user details and cancel
		WebElement userName2 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[1]/div[1]/div/ng-include/div/div[1]/h3"));
		String userName22 = userName2.getAttribute("innerHTML");
		WebElement editButton = webDriver.findElement(By.cssSelector(
				" #audyx-main > div > div > div > div.body > section > div.view-animate > div > div.col-lg-4 > div.panel.user-panel > div > ng-include > div > span > i.fa.fa-pencil"));
		editButton.click();
		Temp.tryCatch(3000);
		WebElement editUserName = webDriver.findElement(By.cssSelector(" #lastname"));
		editUserName.sendKeys("No name");

		Temp.tryCatch(1000);
		WebElement cancelEdit = webDriver.findElement(By.cssSelector(
				"#audyx-main > div > div > div > div.body > section > div.view-animate > div > div.col-lg-4 > div.panel.user-panel > div > ng-include > div > div.panel-body > div > form > div.row > div > div > a"));
		cancelEdit.click();
		Temp.tryCatch(5000);
		WebElement userName3 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[1]/div[1]/div/ng-include/div/div[1]/h3"));
		String userName33 = userName3.getAttribute("innerHTML");
		Temp.checkEqualString(userName22, userName33, "Test CFG-301 step 3");

// edit user details and accept
		WebElement userName4 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[1]/div[1]/div/ng-include/div/div[1]/h3"));
		String userName44 = userName4.getAttribute("innerHTML");
		WebElement editButton1 = webDriver.findElement(By.cssSelector(
				" #audyx-main > div > div > div > div.body > section > div.view-animate > div > div.col-lg-4 > div.panel.user-panel > div > ng-include > div > span > i.fa.fa-pencil"));
		editButton1.click();
		Temp.tryCatch(3000);
		WebElement editUserName2 = webDriver.findElement(By.cssSelector(" #lastname"));
		editUserName2.clear();
		Temp.tryCatch(1000);
		editUserName2.sendKeys("Test " + RandomStringUtils.randomNumeric(2));

		WebElement saveEdit = webDriver.findElement(By.cssSelector(
				" #audyx-main > div > div > div > div.body > section > div.view-animate > div > div.col-lg-4 > div.panel.user-panel > div > ng-include > div > div.panel-body > div > form > div.row > div > div > button"));
		saveEdit.click();
		Temp.tryCatch(5000);
		WebElement userName5 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[1]/div[1]/div/ng-include/div/div[1]/h3"));
		String userName55 = userName5.getAttribute("innerHTML");
		Temp.checkEqualString(userName44, userName55, "Test CFG-301 step 4");
		System.out.println("==Test CFG-301 passed==");
	}

	public void CFG302() {
		// check you are in configuration screen, then click add user
		WebElement configScreen = webDriver.findElement(By.xpath("//*[@id=\"user-details\"]/span/div/span[2]"));
		configScreen.click();
		Temp.tryCatch(1000);
		WebElement addUserButton = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[2]/button[1]"));
		addUserButton.click();
		System.out.println("Test CFG-302 step 1 passed");
		Temp.tryCatch(3000);
// view add user panel
		WebElement email = webDriver.findElement(By.id("email"));
		WebElement userRole = webDriver.findElement(By.xpath(" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div/form/div[1]/div[2]"));
		WebElement language = webDriver.findElement(By.xpath(" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div/form/div[1]/div[3]"));
		WebElement cancel = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div/form/div[2]/a"));
		WebElement submit = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div/form/div[2]/button"));
		System.out.println("Test CFG-302 step 2 passed");
		// enter email address but press cancel
		email.sendKeys("esty@audyx.com");
		cancel.click();
		Temp.tryCatch(3000);
		System.out.println("Test CFG-302 step 3 passed");
//try to add user with empty name field -add button in not clickable
		WebElement addUserButton2 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[2]/button[1]"));
		addUserButton2.click();
		Temp.tryCatch(5000);
		WebElement submit2 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div/form/div[2]/button"));
		Temp.checkButtonNotClickable(submit2, "Test CFG-302 step 4");
//enter invalid email address - check if error message appears
		WebElement email2 = webDriver.findElement(By.xpath(" //*[@id=\"email\"]"));
		email2.sendKeys("a");
		WebElement invalidMessage = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div/form/div[1]/div[1]/div/span/div[2] "));
		System.out.println("Test CFG-302 step 5 passed");
//add new user and submit, check if authentication modal appears
		WebElement addUserButton3 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[2]/button[1]"));
		addUserButton3.click();
		Temp.tryCatch(5000);
		WebElement email3 = webDriver.findElement(By.xpath(" //*[@id=\"email\"]"));
		email3.sendKeys("stw55772@gmail.com");
		WebElement submit3 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div/form/div[2]/button"));
		submit3.click();
		Temp.tryCatch(5000);
		WebElement autenticateModal = webDriver.findElement(By.cssSelector(" body > div.modal.fade.in > div > div"));
		System.out.println("Test CFG-302 step 6 passed");
		// cancel authentication modal
		WebElement cancelAutenticateModal = webDriver.findElement(By
				.cssSelector(" body > div.modal.fade.in > div > div > form > div.modal-footer > button:nth-child(1)"));
		cancelAutenticateModal.click();
		System.out.println("Test CFG-302 step 7 passed");
//re-enter data and confirm password
		WebElement addUserButton4 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[2]/button[1]"));
		addUserButton4.click();
		Temp.tryCatch(5000);
		WebElement email4 = webDriver.findElement(By.xpath(" //*[@id=\"email\"]"));
		email4.sendKeys("stw55772@gmail.com");
		// change role to administrator:
		WebElement changeRole = webDriver.findElement(By.xpath(" //*[@id=\"s2id_autogen15\"]/a/span[1]/span"));
		changeRole.click();
		Temp.tryCatch(3000);
		WebElement admin = webDriver.findElement(By.xpath(" //*[@id=\"select2-drop\"]/ul/li[1]/div"));
		admin.click();
		Temp.tryCatch(3000);
		WebElement submit4 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div/form/div[2]/button"));
		submit4.click();
		Temp.tryCatch(5000);
		WebElement enterPassword = webDriver
				.findElement(By.cssSelector("body > div.modal.fade.in > div > div > form > div.modal-body > input "));
		enterPassword.sendKeys("123456Qw");
		Temp.tryCatch(3000);
		WebElement continueButton = webDriver.findElement(By
				.cssSelector(" body > div.modal.fade.in > div > div > form > div.modal-footer > button:nth-child(2)"));
		continueButton.click();
		Temp.tryCatch(3000);
		System.out.println("Test CFG-302 step 8 passed");
//go to configuration screen and see the new user is added
		WebElement newUserInList = webDriver.findElement(
				By.xpath(" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[1]/div[2]/div[2]/div"));
		System.out.println("Test CFG-302 step 9 passed");
		// try to add user that already exist
		WebElement addUserButton5 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[1]/div/div/div[2]/button[1]"));
		addUserButton5.click();
		Temp.tryCatch(5000);
		WebElement email5 = webDriver.findElement(By.xpath(" //*[@id=\"email\"]"));
		email5.sendKeys("esty@audyx.com");
		Temp.tryCatch(3000);
		WebElement submit5 = webDriver.findElement(By.xpath(
				" //*[@id=\"audyx-main\"]/div/div/div/div[2]/section/div[2]/div/div[2]/div[2]/div/div/form/div[2]/button"));
		submit5.click();
		Temp.tryCatch(5000);
		System.out.println("Test CFG-302 step 10 passed");
		WebElement existModal = webDriver.findElement(By.cssSelector(" body > div.modal.fade.in > div > div"));
		System.out.println("Test CFG-302 step 11 passed");
		WebElement acceptExistModal = webDriver
				.findElement(By.cssSelector("body > div.modal.fade.in > div > div > div.modal-footer > button"));
		acceptExistModal.click();
		Temp.tryCatch(5000);
		Temp.checkUrl("https://alpha.audyx.com/#/configuration", webDriver.getCurrentUrl(), "Test CFG-302 step 12");
		System.out.println("==Test CFG-302 passed==");

	}

//=======================================================================================================================

}
