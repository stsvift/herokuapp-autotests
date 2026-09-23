package ru.ks54.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
public class NotificationsPage extends BasePage {
    public NotificationsPage(WebDriver driver) { super(driver); }

    private final By flash=By.id("flash");
    private final By reload=By.linkText("Click here");
    public NotificationsPage open() { openPath("/notification_message",reload); visible(flash); return this; }
    public String message() { return visible(flash).getText().replace("×", "").trim(); }
    public void loadMessage() {
        WebElement previous=visible(flash); click(reload);
        wait.until(ExpectedConditions.stalenessOf(previous)); visible(flash);
    }
    public void dismiss() { click(By.cssSelector("#flash .close")); wait.until(ExpectedConditions.invisibilityOfElementLocated(flash)); }
    public boolean messageVisible() { return driver.findElements(flash).stream().anyMatch(WebElement::isDisplayed); }

}
