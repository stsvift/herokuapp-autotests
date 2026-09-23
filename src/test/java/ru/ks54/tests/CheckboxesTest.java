package ru.ks54.tests;
import ru.ks54.pages.*;
import org.testng.annotations.*;
import org.testng.Reporter;
import static org.testng.Assert.*;
import java.util.*;
public class CheckboxesTest extends BaseTest {

    @Test public void checkFirstAndUncheckSecond() {
        CheckboxesPage page=new CheckboxesPage(driver).open();
        assertFalse(page.checked(1)); assertTrue(page.checked(2));
        page.setChecked(1,true); assertTrue(page.checked(1));
        page.setChecked(2,false); assertFalse(page.checked(2));
    }
    @Test public void settingSameStateDoesNotToggle() {
        CheckboxesPage page=new CheckboxesPage(driver).open();
        page.setChecked(1,false); page.setChecked(2,true);
        assertFalse(page.checked(1)); assertTrue(page.checked(2));
    }

}
