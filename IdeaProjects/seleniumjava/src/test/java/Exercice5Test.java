import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Exercice5Test {

    @Test
    public void valeursAverifier(){
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
        driver.findElement(By.name("userName")).sendKeys("m2i");

        //password login
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("m2i");

        //click sur login
        driver.findElement(By.name("login")).click();

        //wait 2 secondes
        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){
        }

        String title1 = driver.getTitle();
        Assert.assertEquals("Find a Flight: Mercury Tours:",title1);


        // wait until SIGN OFF
        WebDriverWait wait1 = new WebDriverWait(driver, 3);
        wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("SIGN-OFF"))));

        //click sur sign off
        driver.findElement(By.linkText("SIGN-OFF")).click();

        String title2 = driver.getTitle();
        Assert.assertEquals("Sign-on: Mercury Tours",title2);

        // wait until SIGN on
        WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("SIGN-ON"))));

        // wait until SIGN on
        WebDriverWait wait3 = new WebDriverWait(driver,3);
        wait3.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("userName"))));

        //verifier le champs username
        driver.findElement(By.name("userName")).clear();

        //Fermeture du navigateur
        //driver.quit();
    }

}