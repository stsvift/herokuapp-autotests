package ru.ks54.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;
public class TablesPage extends BasePage {
    public TablesPage(WebDriver driver) { super(driver); }

    public TablesPage open() { openPath("/tables",By.id("table1")); return this; }
    public String cell(int table,int row,int column) { return visible(By.xpath("//table[@id='table"+table+"']/tbody/tr["+row+"]/td["+column+"]")).getText(); }
    public List<String> lastNames(int table) { return driver.findElements(By.cssSelector("#table"+table+" tbody tr td:first-child")).stream().map(WebElement::getText).toList(); }
    public void sortByLastName(int table) {
        click(By.xpath("//table[@id='table"+table+"']//th[.//span[normalize-space()='Last Name']]"));
        wait.until(d -> lastNames(table).equals(List.of("Bach","Conway","Doe","Smith")));
    }

}
