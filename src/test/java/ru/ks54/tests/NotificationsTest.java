package ru.ks54.tests;
import ru.ks54.pages.*;
import org.testng.annotations.*;
import org.testng.Reporter;
import static org.testng.Assert.*;
import java.util.*;
public class NotificationsTest extends BaseTest {

    @Test public void reloadShowsAllowedNotification() {
        NotificationsPage page=new NotificationsPage(driver).open(); page.loadMessage();
        String actual=page.message(); Reporter.log("Notification: "+actual,true);
        assertTrue(Set.of("Action successful", "Action unsuccessful, please try again", "Action unsuccesful, please try again").contains(actual),"Unexpected message: "+actual);
    }
    @Test public void notificationCanBeDismissed() {
        NotificationsPage page=new NotificationsPage(driver).open(); assertTrue(page.messageVisible());
        page.dismiss(); assertFalse(page.messageVisible());
    }

}
