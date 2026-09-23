package ru.ks54.tests;
import ru.ks54.pages.*;
import org.testng.annotations.*;
import org.testng.Reporter;
import static org.testng.Assert.*;
import java.util.*;
public class DropdownTest extends BaseTest {

    @Test public void optionsExistAndCanBeSelected() {
        DropdownPage page=new DropdownPage(driver).open();
        assertEquals(page.options(),List.of("Please select an option","Option 1","Option 2"));
        assertEquals(page.selectedValue(),""); assertFalse(page.placeholderEnabled());
        page.choose("1"); assertEquals(page.selectedValue(),"1");
        page.choose("2"); assertEquals(page.selectedValue(),"2");
    }
    @Test public void nonexistentOptionIsRejected() {
        DropdownPage page=new DropdownPage(driver).open();
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> page.choose("99"));
        assertEquals(page.selectedValue(),"");
    }

}
