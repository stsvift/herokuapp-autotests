package ru.ks54.tests;
import ru.ks54.pages.*;
import org.testng.annotations.*;
import org.testng.Reporter;
import static org.testng.Assert.*;
import java.util.*;
public class TyposTest extends BaseTest {

    private static final String CORRECT="Sometimes you'll see a typo, other times you won't.";
    private static final String KNOWN_TYPO="Sometimes you'll see a typo, other times you won,t.";
    @Test public void paragraphsMatchDocumentedVariants() {
        List<String> text=new TyposPage(driver).open().paragraphs();
        assertEquals(text.size(),2);
        assertEquals(text.get(0),"This example demonstrates a typo being introduced. It does it randomly on each page load.");
        assertTrue(Set.of(CORRECT,KNOWN_TYPO).contains(text.get(1)),"Unexpected paragraph: "+text.get(1));
        Reporter.log("Observed spelling: "+text.get(1),true);
    }
    @Test(groups="known-defect") public void paragraphHasCorrectSpelling() {
        assertEquals(new TyposPage(driver).open().paragraphs().get(1),CORRECT,"TYPO-01: random spelling defect");
    }

}
