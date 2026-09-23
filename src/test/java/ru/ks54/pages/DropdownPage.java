package ru.ks54.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
public class DropdownPage extends BasePage {
    public DropdownPage(WebDriver driver) { super(driver); }

    private final By dropdown=By.id("dropdown");
    public DropdownPage open() { openPath("/dropdown",dropdown); return this; }
    private Select select() { return new Select(visible(dropdown)); }
    public List<String> options() { return select().getOptions().stream().map(WebElement::getText).toList(); }
    public void choose(String value) { select().selectByValue(value); wait.until(d -> selectedValue().equals(value)); }
    public String selectedValue() { return select().getFirstSelectedOption().getDomAttribute("value"); }
    public boolean placeholderEnabled() { return select().getOptions().get(0).isEnabled(); }

}
