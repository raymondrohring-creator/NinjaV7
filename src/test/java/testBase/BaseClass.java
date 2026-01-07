package testBase;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Properties p;

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeClass(groups = {"sanity", "regression", "datadriven"})
    @Parameters({"os", "browser"})
    public void openApp(String os, String br) throws IOException {

        logger.debug("Starting openApp() | OS: {} | Browser: {}", os, br);

        try {
            FileReader file = new FileReader(".//src//test//resources//config.properties");
            p = new Properties();
            p.load(file);
            logger.debug("Config properties file loaded successfully");

            WebDriver localDriver = null;

            if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();

				// os
				if (os.equalsIgnoreCase("windows")) {
					capabilities.setPlatform(Platform.WIN11);
				} else if (os.equalsIgnoreCase("mac")) {
					capabilities.setPlatform(Platform.MAC);
				} else {
					System.out.println("No matching os");
					return;
				}

				String gridURL = "http://localhost:4444/wd/hub"; // Update if needed
				//String gridURL = "http://192.168.1.176:4444/wd/hub"; // this will also work
				
				switch (br.toLowerCase()) {
				case "chrome":
					ChromeOptions chromeOptions = new ChromeOptions();
					localDriver = new RemoteWebDriver(URI.create(gridURL).toURL(), chromeOptions.merge(capabilities));
					break;

				case "firefox":
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					localDriver = new RemoteWebDriver(URI.create(gridURL).toURL(), firefoxOptions.merge(capabilities));
					break;

				case "edge":
					SafariOptions safariOptions = new SafariOptions();
					localDriver = new RemoteWebDriver(URI.create(gridURL).toURL(), safariOptions.merge(capabilities));
					break;

				default:
					logger.error("No matching browser found: {}", br);
					return;
				}

			}
			
			if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

            switch (br.toLowerCase()) {
                case "chrome":
                    logger.debug("Launching Chrome browser");
                    localDriver = new ChromeDriver();
                    break;

                case "safari":
                    logger.debug("Launching Safari browser");
                    localDriver = new SafariDriver();
                    break;

                case "firefox":
                    logger.debug("Launching Firefox browser");
                    localDriver = new FirefoxDriver();
                    break;

                default:
                    logger.error("Invalid browser name provided: {}", br);
                    throw new RuntimeException("Invalid browser");
            	}
			}

            driver.set(localDriver);
            logger.debug("WebDriver instance set in ThreadLocal");

            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            logger.debug("Implicit wait set to 10 seconds");

            String appUrl = p.getProperty("appURL1");
            logger.debug("Navigating to URL: {}", appUrl);

            getDriver().get(appUrl);
            getDriver().manage().window().maximize();

            logger.debug("Browser launched and window maximized");

        } catch (Exception e) {
            logger.error("Exception occurred in openApp()", e);
            throw e;
        }
    }

    @AfterClass(groups = {"sanity", "regression", "datadriven"})
    public void closeApp() {

        logger.debug("Starting closeApp()");

        try {
            if (getDriver() != null) {
                getDriver().quit();
                logger.debug("Browser closed successfully");
            }
        } catch (Exception e) {
            logger.error("Exception occurred while closing browser", e);
        }
    }

    public String captureScreen(String tname) throws IOException {

        logger.debug("Capturing screenshot for test: {}", tname);

        try {
            String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

            File sourceFile = ((TakesScreenshot) getDriver())
                    .getScreenshotAs(OutputType.FILE);

            String targetFilePath = System.getProperty("user.dir")
                    + "//screenshots//" + tname + "_" + timeStamp + ".png";

            File targetFile = new File(targetFilePath);
            sourceFile.renameTo(targetFile);

            logger.debug("Screenshot saved at: {}", targetFilePath);
            return targetFilePath;

        } catch (Exception e) {
            logger.error("Failed to capture screenshot for test: {}", tname, e);
            throw e;
        }
    }
}
