package ru.ks54.tests;

import java.nio.file.*;
import java.time.Duration;
import java.util.Arrays;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.testng.*;
import org.testng.annotations.*;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        String browser = System.getProperty("browser", "chrome");
        driver = switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                if (headless) options.addArguments("--headless=new");
                options.addArguments("--window-size=1440,1000", "--disable-dev-shm-usage");
                yield new ChromeDriver(options);
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                if (headless) options.addArguments("--headless=new");
                options.addArguments("--window-size=1440,1000");
                yield new EdgeDriver(options);
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (headless) options.addArguments("-headless");
                yield new FirefoxDriver(options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
        Capabilities caps = ((org.openqa.selenium.remote.RemoteWebDriver) driver).getCapabilities();
        Reporter.log("Environment: " + System.getProperty("os.name") + "; Java " + System.getProperty("java.version")
                + "; " + caps.getBrowserName() + " " + caps.getBrowserVersion() + "; capabilities=" + caps, true);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver == null) return;
        try {
            String name = result.getTestClass().getRealClass().getSimpleName() + "-" + result.getMethod().getMethodName()
                    + Arrays.toString(result.getParameters()).replaceAll("[^a-zA-Z0-9_-]", "_");
            Path dir = Path.of("target", "screenshots");
            Files.createDirectories(dir);
            Files.write(dir.resolve(name + ".png"), ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            Files.writeString(dir.resolve(name + ".txt"), "URL: " + driver.getCurrentUrl() + "\nStatus: " + result.getStatus()
                    + "\n" + (result.getThrowable() == null ? "" : result.getThrowable().toString()));
            Reporter.log("Screenshot: " + name + ".png", true);
        } catch (Exception e) {
            Reporter.log("Could not capture artifact: " + e, true);
        } finally {
            try { driver.quit(); } finally { driver = null; }
        }
    }
}
