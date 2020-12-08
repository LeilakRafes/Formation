import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SignUpMercuryTest {

    @Test
    public void registerAccount(){
        //Ajout du chemin driver
        String s = System.setProperty("webdriver.chrome.driver", "C:\\browser_drivers\\chromedriver.exe");

        //Initialisation de l'instance du driver
        WebDriver driver = new ChromeDriver();

        //maximize
        driver.manage().window().maximize();

        //Démarrage du navigateur
        driver.get("https://www.google.fr");

        //aller sur mercury
        driver.get("http://localhost:56/servlets/com.mercurytours.servlet.WelcomeServlet");

        //click sur register
        driver.findElement(By.linkText("REGISTER")).click();

        //first
        driver.findElement(By.name("firstName")).clear();
        driver.findElement(By.name("firstName")).sendKeys("Francois");

        //last
        driver.findElement(By.name("lastName")).clear();
        driver.findElement(By.name("lastName")).sendKeys("Mitterand");

        //phone
        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone")).sendKeys("0102030405");

        //email
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("larose@gmail.com");

        //adress
        driver.findElement(By.name("address1")).clear();
        driver.findElement(By.name("address1")).sendKeys("36 quai des orfèvres");

        //city
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Paris");

        //state
        driver.findElement(By.name("state")).clear();
        driver.findElement(By.name("state")).sendKeys("idf");

        //post code
        driver.findElement(By.name("postalCode")).clear();
        driver.findElement(By.name("postalCode")).sendKeys("75000");

        //Country
        Select drpcountry = new Select(driver.findElement(By.name("country")));
        drpcountry.selectByVisibleText("FRANCE");

        //username
        driver.findElement(By.name("userName")).clear();
        driver.findElement(By.name("userName")).sendKeys("kermit");

        //password
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("azertyuiop");

        //confirm password
        driver.findElement(By.name("confirmPassword")).clear();
        driver.findElement(By.name("confirmPassword")).sendKeys("azertyuiop");

        //click sur register
        driver.findElement(By.name("register")).click();

        //wait 2 secondes
        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){
        }

        //click sur sign on
        driver.findElement(By.linkText("SIGN-ON")).click();

        //username login
        driver.findElement(By.name("userName")).clear();
        driver.findElement(By.name("userName")).sendKeys("kermit");

        //password login
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("azertyuiop");

        //click sur login
        driver.findElement(By.name("login")).click();

        //wait 2 secondes
        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){
        }

        // wait until SIGN OFF
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("SIGN-OFF"))));

        //click sur sign off
        driver.findElement(By.linkText("SIGN-OFF")).click();

        //Fermeture du navigateur
        driver.quit();
    }

}

