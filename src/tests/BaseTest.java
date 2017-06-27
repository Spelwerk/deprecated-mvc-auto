package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import common.TestData;
import common.enums.Browser;
import common.enums.TestEnvironment;
import modules.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

class BaseTest {

    // CORE

    protected WebDriver driver;
    protected TestData data;
    protected String baseUrl;

    // MODULES

    //protected ModuleName moduleName;

    // GENERAL

    protected StringBuffer verificationErrors = new StringBuffer();

    private String osName = System.getProperty("os.name").toUpperCase();

    // METHODS

    @Before
    public void setUp() throws Exception {
        startup(TestEnvironment.STAGING, Browser.CHROME);
    }

    @After
    public void tearDown() throws Exception {
        //driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    // PRIVATE

    void startup(TestEnvironment testEnvironment, Browser browser) {
        DesiredCapabilities capa = new DesiredCapabilities();
        capa.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capa.setCapability("nativeEvents", true);

        if (browser == Browser.FIREFOX) {
            // todo let us use this when actions is not broken in driver anymore
            driver = new FirefoxDriver();
        }

        if (browser == Browser.SAFARI) {
            // todo actions broken in safari? investigate
            driver = new SafariDriver();
        }

        if (browser == Browser.IEXPLORER) {
            driver = new InternetExplorerDriver();
        }

        if (browser == Browser.CHROME) {
            if(isMac()) System.setProperty("webdriver.chrome.driver", "./lib/chromedriver-osx");
            if(isWindows()) System.setProperty("webdriver.chrome.driver", "./lib/chromedriver-win.exe");
            if(isNix()) System.setProperty("webdriver.chrome.driver", "./lib/chromedriver-nix");

            ChromeOptions options = new ChromeOptions();

            Map<String, Object> prefs = new HashMap<>();

            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            capa.setCapability(ChromeOptions.CAPABILITY, options);

            driver = new ChromeDriver(capa);
        }

        // environment setup
        data = new TestData(testEnvironment);

        // general setup
        verificationErrors.setLength(0);

        baseUrl = data.getTestEnvironmentURL();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        verificationErrors.setLength(0);
        driver.get(baseUrl + "login.do");

        // objects setup
        //moduleName = new ModuleName(driver);
    }

    private boolean isMac() {
        return osName.contains("MAC");
    }

    private boolean isWindows() {
        return osName.contains("WIN");
    }

    private boolean isNix() {
        return osName.contains("NIX") || osName.contains("NUX") || osName.contains("AIX");
    }
}
