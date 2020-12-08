import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;

public class Exercice6Test {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        String s = System.setProperty("webdriver.chrome.driver", "C:\\browser_drivers\\chromedriver.exe");
        //String s = System.setProperty("webdriver.chrome.driver", "D:\\TEST_Logiciel\\AUTOMATION\\Selenium\\Chrome\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().window().maximize();
    }

    @Test
    public void Flight_Details(){
        //Lancer URL
        driver.get("http://newtours.demoaut.com");

        //CLique sur sign on
        driver.findElement(By.linkText("SIGN-ON")).click();

        //Saisir  user et mpd
        driver.findElement(By.name("userName")).sendKeys("m2i");
        driver.findElement(By.name("password")).sendKeys("m2i");
        driver.findElement(By.name("login")).click();


        //Vérifier le texte "Flight Details" est affiché
        String texte = driver.findElement(By.xpath("//font[@face='ARIAL, HELVETICA'][contains(text(),'Flight ')]")).getText();
        System.out.println(texte);
        String texte2 = "Flight Details";
        //Assert.assertEquals(texte2, texte);
        //assertTrue(texte2.equals(texte));
        assertTrue(texte.contains(texte2));

        //Vérifier Airline : No Preference
        Select drpAirline = new Select(driver.findElement(By.name("airline")));
        //drpAirline.selectByVisibleText("No Preference");
        String selected = drpAirline.getFirstSelectedOption().getText();
        String selected2 = "No Preference";
        assertTrue(selected.equals(selected2));

        //Vérifier la classe économique sélectionné (checkbox)
        List<WebElement> boutonradio= driver.findElements(By.name("servClass"));
        assertTrue(boutonradio.get(0).getAttribute("checked").equals("true"));
        System.out.println("TEST : "+boutonradio.get(1).getAttribute("checked"));

    }

}