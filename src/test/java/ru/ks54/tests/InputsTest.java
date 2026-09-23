package ru.ks54.tests;
import ru.ks54.pages.*;
import org.testng.annotations.*;
import org.testng.Reporter;
import static org.testng.Assert.*;
import java.util.*;
public class InputsTest extends BaseTest {

    @DataProvider public Object[][] numbers() { return new Object[][]{{"0"},{"42"},{"-7"},{"3.5"},{"1000000"}}; }
    @Test(dataProvider="numbers") public void acceptsNumericValues(String value) {
        InputsPage page=new InputsPage(driver).open(); page.enter(value); assertEquals(page.value(),value);
    }
    @Test public void arrowsIncrementAndDecrement() {
        InputsPage page=new InputsPage(driver).open(); page.enter("10");
        page.arrowUp(); assertEquals(page.value(),"11"); page.arrowDown(); assertEquals(page.value(),"10");
    }
    @DataProvider public Object[][] invalid() { return new Object[][]{{"abc"},{"!@#"}}; }
    @Test(dataProvider="invalid") public void rejectsNonNumericValues(String value) {
        InputsPage page=new InputsPage(driver).open(); page.enter(value); assertEquals(page.value(),"");
    }

}
