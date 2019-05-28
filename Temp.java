package expert;

import org.openqa.selenium.WebElement;

public class Temp {

	public static void checkUrl(String expectedUrl, String actualUrl, String currentStep) {
		if (expectedUrl.equals(actualUrl))
			System.out.println(currentStep + " passed");
		else
			System.out.println(currentStep + " failed");
	}

	public static void tryCatch(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void checkDiffString(String string1, String string2, String currentStep) {
		if (string1.equals(string2))
			System.out.println(currentStep + " failed");
		else
			System.out.println(currentStep + " passed");
	}

	public static void checkEqualString(String string1, String string2, String currentStep) {
		if (string1.equals(string2))
			System.out.println(currentStep + " passed");
		else
			System.out.println(currentStep + " failed");
	}

	public static void checkButtonClickable(WebElement button, String currentStep) {
		boolean enable1 = button.isEnabled();
		if (enable1)
			System.out.println(currentStep + " passed");
		else
			System.out.println(currentStep + " failed");
	}

	public static void checkButtonNotClickable(WebElement button, String currentStep) {
		boolean enable1 = button.isEnabled();
		if (!enable1)
			System.out.println(currentStep + " passed");
		else
			System.out.println(currentStep + " failed");
	}
}
