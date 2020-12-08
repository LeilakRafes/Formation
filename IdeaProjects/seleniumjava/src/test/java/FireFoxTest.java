import org.junit.Test;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class FireFoxTest {

    @Test
    public void startFireFoxDriver(){
        //Ajout du chemin driver
        String s = System.setProperty("webdriver.gecko.driver", "C:\\browser_drivers\\geckodriver.exe");

        //Initialisation de l'instance du driver
        WebDriver driver = new FirefoxDriver();

        //Démarrage du navigateur
        driver.get("https://www.google.fr");

        //Fermeture du navigateur
        driver.quit();
    }
}
