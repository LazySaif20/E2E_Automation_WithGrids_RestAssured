package com.autogrid.utils;

import com.autogrid.steps.FlightRegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class LaunchDriver {
    private static final Logger logger = LoggerFactory.getLogger(FlightRegistrationPage.class);
    private static WebDriver driver;
    private static final int TIME_OUT = 50;

    private LaunchDriver() throws MalformedURLException {
        Config.initialize();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(Arrays.asList("--no-sandbox", "--verbose", "--window-size=1920,1080",
                "--ignore-certificate-errors", "--disable-notifications", "--remote-allow-origins=*"));

        if(Config.get("browser").equalsIgnoreCase("chrome")
                && Config.get("selenium.grid.enabled").equalsIgnoreCase("false")) {
            logger.info("Initializing WebDriver & launching Chrome Locally");

            driver = new ChromeDriver(options);
        }else if(Config.get("browser").equalsIgnoreCase("firefox")
                && Config.get("selenium.grid.enabled").equalsIgnoreCase("false")){
            logger.info("Initializing WebDriver & launching Firefox Locally");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments(Arrays.asList("--no-sandbox", "--verbose", "--window-size=1920,1080",
                    "--ignore-certificate-errors", "--disable-notifications"));

            driver = new FirefoxDriver(firefoxOptions);
        }else if(Config.get("browser").equalsIgnoreCase("chrome")
                && Config.get("selenium.grid.enabled").equalsIgnoreCase("true")){
            logger.info("Initializing WebDriver & launching Chrome on Grid");

            URL gridUrl = new URL(Config.get("selenium.grid.urlFormat"));
            driver = new RemoteWebDriver(gridUrl, options);
            logger.info("Remote WebDriver launched on Grid");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME_OUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIME_OUT));
    }

    // Synchronized to ensure only one thread can initialize the driver
    public static synchronized void setUpDriver() throws MalformedURLException {
        if (driver == null) {
            logger.info("Setting up the WebDriver");
            new LaunchDriver();  // Calls the private constructor to initialize driver
        } else {
            logger.info("WebDriver is already initialized.");
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            logger.warn("Driver is not initialized!");
            throw new IllegalStateException("Driver is not initialized. Call setUpDriver() first.");
        }
        return driver;
    }

    public static void launchSite() {
        if (driver != null) {
            logger.info("Launching site: " + Config.get("flightReservation.url"));
            driver.get(Config.get("flightReservation.url"));
        } else {
            logger.warn("Driver is not initialized. Cannot launch site.");
        }
    }

    public static synchronized void tearDown() {
        logger.info("Tearing down the WebDriver.");
        if (driver != null) {
            try {
                driver.quit();
                driver = null;
                logger.info("WebDriver closed successfully.");
            } catch (Exception e) {
                logger.warn("Error during WebDriver teardown: " + e);
            } finally {
                driver = null;  // Ensure driver is set to null after quit
                try {
                    if (System.getProperty("os.name").toLowerCase().contains("win")) {
                        // Windows: Kill chromedriver.exe using taskkill
                        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
                        logger.info("WebDriver Killed successfully on win.");
                    } else {
                        // macOS/Linux: Kill chromedriver using killall
                        Runtime.getRuntime().exec("killall chromedriver");
                        logger.info("WebDriver Killed successfully on others.");
                    }
                } catch (IOException e) {
                    logger.error("An exception has been thrown. Please review: {}", e);
                }
            }
        } else {
            logger.warn("Driver is already null. Nothing to tear down.");
        }
    }

}
