import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;


public class LancementChromeTest {

    @Test
    public void launchChromeDriver(){
        //Ajout du chemin driver
        String s = System.setProperty("webdriver.chrome.driver", "C:\\browser_drivers\\chromedriver.exe");

        //Initialisation de l'instance du driver
        WebDriver driver = new ChromeDriver();

        //maximize
        driver.manage().window().maximize();

        //Démarrage du navigateur
        driver.get("https://www.google.fr");

        //aller sur demoaut
        //driver.get("http://newtours.demoaut.com");
        driver.get("http://localhost:56/servlets/com.mercurytours.servlet.WelcomeServlet");

        try{
            Thread.sleep(5000);
        }
        catch(InterruptedException ie){
        }

        //Fermeture du navigateur
        driver.quit();
    }
}