import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class webosteoTest {

        protected static WebDriver driver;


        private String baseUrl;
        private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();

        /*Classe permettant de piloter le driver Chrome et les commandes Selenium*/
        @Before
        public void setUp() throws Exception {
            String s = System.setProperty("webdriver.chrome.driver", "C:\\browser_drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            baseUrl = "https://www.google.fr";
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //driver.manage().window().maximize();
        }

        @Test
        public void testWebOsteo() throws Exception {
            driver.get("https://demo.webosteo.com/");
            System.out.println("connexion au site...");
            driver.findElement(By.id("zt_login")).clear();
            driver.findElement(By.id("zt_login")).sendKeys("admin");
            driver.findElement(By.id("zt_password")).clear();
            driver.findElement(By.id("zt_password")).sendKeys("admin");
            driver.findElement(By.id("bt_connexion")).click();
            System.out.println("Rédation d'une note...");
            driver.findElement(By.xpath("//*[@id=\"menuGlobal\"]/li[3]/a")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Couleur :'])[1]/following::a[3]")).click();
            driver.findElement(By.id("contenuNote")).click();
            driver.findElement(By.id("contenuNote")).clear();
            driver.findElement(By.id("contenuNote")).sendKeys("Test automatique de saisie d'une note dans WebOsteo");
            System.out.println("Selectionner la visibilité de la note");
            driver.findElement(By.xpath("//*[@id=\"htmlFormNote\"]/div[2]/div[3]/div/div/button")).click();

            /*driver.findElement(By.id("btUploadNote")).click();
            driver.findElement(By.id("bt_EnregAddNote")).click();
            driver.findElement(By.linkText("Statistiques")).click();
            driver.findElement(By.linkText("Tableau de bord")).click();
            try {
                assertTrue(isElementPresent(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Les notes'])[1]/following::div[3]")));
            } catch (Error e) {
                verificationErrors.append(e.toString());
            }
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Les notes'])[1]/following::button[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Supprimer définitivement cette note ?'])[1]/following::button[1]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Les notes'])[1]/following::button[1]")).click();
            driver.findElement(By.id("bt_DeleteNote")).click();*/

        }

        /*@After
        public void tearDown() throws Exception {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }

        private boolean isElementPresent(By by) {
            try {
                driver.findElement(by);
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        private boolean isAlertPresent() {
            try {
                driver.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

        private String closeAlertAndGetItsText() {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                if (acceptNextAlert) {
                    alert.accept();
                } else {
                    alert.dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }*/

}
