package ru.ks54.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
public class AddRemovePage extends BasePage {
    public AddRemovePage(WebDriver driver) { super(driver); }

    private final By add = By.xpath("//button[text()='Add Element']");
    private final By deletes = By.xpath("//button[text()='Delete']");
    public AddRemovePage open() { openPath("/add_remove_elements/", add); return this; }
    public int count() { return driver.findElements(deletes).size(); }
    public AddRemovePage add() { int n=count(); click(add); wait.until(ExpectedConditions.numberOfElementsToBe(deletes,n+1)); return this; }
    public AddRemovePage deleteFirst() { int n=count(); click(deletes); wait.until(ExpectedConditions.numberOfElementsToBe(deletes,n-1)); return this; }

}
