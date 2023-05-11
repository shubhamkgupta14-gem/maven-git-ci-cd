package io.github.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestHook {
    public static WebDriver driver;

    @Before
    public void set_up() {
        String browserName = ConfigReader.getInstance().getBrowserName().toUpperCase();
        String headless = ConfigReader.getInstance().getPropertyValue("headless");
        switch (browserName) {
            case "FIREFOX":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                if (headless.equalsIgnoreCase("TRUE") || headless.equalsIgnoreCase("T")) {
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--disable-gpu");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "EDGE":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                if (headless.equalsIgnoreCase("TRUE") || headless.equalsIgnoreCase("T")) {
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("--disable-gpu");
                }
                driver = new EdgeDriver(edgeOptions);
                break;
            case "GRID":
                String remote_url = ConfigReader.getInstance().getRemoteURL();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                if (headless.equalsIgnoreCase("TRUE") || headless.equalsIgnoreCase("T")) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                }
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                try {
                    driver = new RemoteWebDriver(new URL(remote_url), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Fail to open remote URL");
                }
                break;
            default:
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                if (headless.equalsIgnoreCase("TRUE") || headless.equalsIgnoreCase("T")) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                    driver = new ChromeDriver(chromeOptions);
                } else {
                    driver = new ChromeDriver();
                }
        }
        driver.get(ConfigReader.getInstance().getURL());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void after_test() {
        if (driver != null) {
            driver.quit();
        }
    }
}
