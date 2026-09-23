package ru.ks54.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
public class InputsPage extends BasePage {
    public InputsPage(WebDriver driver) { super(driver); }

    private final By input=By.cssSelector("input[type='number']");
    public InputsPage open() { openPath("/inputs",input); return this; }
    public void enter(String text) { WebElement field=visible(input); field.clear(); field.sendKeys(text); }
    public String value() { return visible(input).getDomProperty("value"); }
    public void arrowUp() { visible(input).sendKeys(Keys.ARROW_UP); }
    public void arrowDown() { visible(input).sendKeys(Keys.ARROW_DOWN); }

}
