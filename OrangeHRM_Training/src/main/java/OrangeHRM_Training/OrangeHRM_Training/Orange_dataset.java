package OrangeHRM_Training.OrangeHRM_Training;

import org.testng.annotations.DataProvider;

public class Orange_dataset {
	@DataProvider(name = "Login")
	public Object[][] getDataforLogin() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5
		return new Object[][] {

				{"Admin", "admin123" },
				{"priya", "admin123" },
				{"dixit", "admin123" },
				{"deepp", "admin123" }
				};
	}
	@DataProvider(name = "Web")
	public Object[][] getDataforWeb() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5
		return new Object[][] {

				{"Tester", "test" },
				{"Tester", "test" },
				{"Tester", "test" },
				{"Tester", "test" }
				};
	}
	@DataProvider(name = "LoginScenario")
	public Object[][] getDataforLoginDifferentScenarios() {
		return new Object[][] { 
				{ "admin", "", "Password cannot be empty"},
				{ "", "admin123", "Username cannot be empty" }, 
				{ "AdminWrong", "admin123", "Invalid credentials" },
				{ "admin", "admin", "Invalid credentials" }, 
				{ "admin", "admin123", "Dashboard" } };

	}

}
