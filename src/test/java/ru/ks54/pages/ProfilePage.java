package ru.ks54.pages;
import org.openqa.selenium.*;
public class ProfilePage extends BasePage {
    public ProfilePage(WebDriver driver) { super(driver); }
    public boolean isNotFound() {
        String text=visible(By.tagName("body")).getText();
        return text.contains("Not Found") || text.contains("404");
    }
}
