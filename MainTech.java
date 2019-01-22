package technician;

public class MainTech {
	public static void main(String[] args) {
		PatientManagement  a1 = new PatientManagement ("https://alpha.audyx.com",
				"C:/Users/Esty Wolpo/Desktop/chromedriver_win32/chromedriver.exe");
		a1.signIn();
		a1.Tec101();
	}
}
