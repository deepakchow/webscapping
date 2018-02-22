package details;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AutoUtil {

	public static WebDriver getDriver() {

		System.setProperty("webdriver.gecko.driver", "\\home\\deepak\\Documents\\gecko\\geckodriver.exe");

		WebDriver driver;

		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "false");
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser,popups.ShowPopupBlocker", false);
		profile.setPreference("plugin.scan.plid.all", false);
		capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		driver = new FirefoxDriver(capabilities);
		return driver;
	}

}
