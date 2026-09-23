package ru.ks54.tests;
import ru.ks54.pages.*;
import org.testng.annotations.*;
import org.testng.Reporter;
import static org.testng.Assert.*;
import java.util.*;
public class HoversTest extends BaseTest {

    @DataProvider public Object[][] users() { return new Object[][]{{1},{2},{3}}; }
    @Test(dataProvider="users") public void hoverRevealsCorrectUser(int user) {
        HoversPage page=new HoversPage(driver).open(); assertEquals(page.count(),3);
        assertFalse(page.captionVisible(user)); page.hover(user); assertTrue(page.captionVisible(user));
        assertEquals(page.name(user),"name: user"+user); assertEquals(page.href(user),"/users/"+user);
    }
    @Test(dataProvider="users",groups="known-defect") public void profileLinkDoesNotOpen404(int user) {
        HoversPage page=new HoversPage(driver).open(); page.hover(user);
        assertEquals(page.name(user),"name: user"+user);
        assertFalse(page.openProfile(user).isNotFound(),"HOVER-01: /users/"+user+" returns Not Found");
    }

}
