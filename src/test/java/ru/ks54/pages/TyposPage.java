package ru.ks54.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
public class TyposPage extends BasePage {
    public TyposPage(WebDriver driver) { super(driver); }

    public TyposPage open() { openPath("/typos",By.cssSelector(".example p")); return this; }
    public List<String> paragraphs() { return driver.findElements(By.cssSelector(".example p")).stream().map(e -> e.getText().trim()).toList(); }

}
