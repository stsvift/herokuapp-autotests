package ru.ks54.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
public class HoversPage extends BasePage {
    public HoversPage(WebDriver driver) { super(driver); }

    private By figure(int user) { return By.cssSelector(".figure:nth-of-type("+user+")"); }
    public HoversPage open() { openPath("/hovers",By.className("figure")); return this; }
    public int count() { return driver.findElements(By.className("figure")).size(); }
    public boolean captionVisible(int user) { return driver.findElement(figure(user)).findElement(By.className("figcaption")).isDisplayed(); }
    public void hover(int user) {
        new org.openqa.selenium.interactions.Actions(driver).moveToElement(visible(figure(user))).perform();
        wait.until(d -> captionVisible(user));
    }
    public String name(int user) { return driver.findElement(figure(user)).findElement(By.tagName("h5")).getText(); }
    public String href(int user) { return driver.findElement(figure(user)).findElement(By.tagName("a")).getDomAttribute("href"); }
    public ProfilePage openProfile(int user) {
        driver.findElement(figure(user)).findElement(By.tagName("a")).click();
        wait.until(ExpectedConditions.urlContains("/users/"+user));
        return new ProfilePage(driver);
    }

}
