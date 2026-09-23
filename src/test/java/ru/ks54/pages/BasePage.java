package ru.ks54.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    protected void openPath(String path, By ready) {
        driver.get(System.getProperty("baseUrl", "https://the-internet.herokuapp.com").replaceAll("/$", "") + path);
        visible(ready);
    }
    protected WebElement visible(By locator) { return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); }
    protected void click(By locator) { wait.until(ExpectedConditions.elementToBeClickable(locator)).click(); }
}
