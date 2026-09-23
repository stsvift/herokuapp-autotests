package ru.ks54.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
public class CheckboxesPage extends BasePage {
    public CheckboxesPage(WebDriver driver) { super(driver); }

    private By checkbox(int index) { return By.cssSelector("#checkboxes input:nth-of-type("+index+")"); }
    public CheckboxesPage open() { openPath("/checkboxes",checkbox(1)); return this; }
    public boolean checked(int index) { return visible(checkbox(index)).isSelected(); }
    public void setChecked(int index, boolean value) {
        if (checked(index)!=value) click(checkbox(index));
        wait.until(ExpectedConditions.elementSelectionStateToBe(checkbox(index),value));
    }

}
