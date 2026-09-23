package ru.ks54.tests;
import ru.ks54.pages.*;
import org.testng.annotations.*;
import org.testng.Reporter;
import static org.testng.Assert.*;
import java.util.*;
public class TablesTest extends BaseTest {

    @DataProvider public Object[][] tables() { return new Object[][]{{1},{2}}; }
    @Test(dataProvider="tables") public void fiveCellsHaveExpectedContent(int table) {
        TablesPage page=new TablesPage(driver).open();
        assertEquals(page.cell(table,1,1),"Smith"); assertEquals(page.cell(table,1,2),"John");
        assertEquals(page.cell(table,2,3),"fbach@yahoo.com"); assertEquals(page.cell(table,3,4),"$100.00");
        assertEquals(page.cell(table,4,5),"http://www.timconway.com");
    }
    @Test(dataProvider="tables") public void sortsLastNamesAscending(int table) {
        TablesPage page=new TablesPage(driver).open(); page.sortByLastName(table);
        assertEquals(page.lastNames(table),List.of("Bach","Conway","Doe","Smith"));
    }

}
