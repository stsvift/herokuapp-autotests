package ru.ks54.tests;
import ru.ks54.pages.*;
import org.testng.annotations.*;
import org.testng.Reporter;
import static org.testng.Assert.*;
import java.util.*;
public class AddRemoveTest extends BaseTest {

    @Test public void addTwoAndDeleteOne() {
        AddRemovePage page = new AddRemovePage(driver).open();
        assertEquals(page.count(),0); page.add().add(); assertEquals(page.count(),2);
        page.deleteFirst(); assertEquals(page.count(),1);
    }
    @Test public void deleteLastElementLeavesEmptyList() {
        AddRemovePage page = new AddRemovePage(driver).open().add().deleteFirst();
        assertEquals(page.count(),0);
    }

}
