import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ChromeTest {

    @Test
    public void startChromeDriver(){
        //Ajout du chemin driver
        String s = System.setProperty("webdriver.chrome.driver", "C:\\browser_drivers\\chromedriver.exe");

        //Initialisation de l'instance du driver
        WebDriver driver = new ChromeDriver();

        //Démarrage du navigateur
        driver.get("https://www.google.fr");

        //Fermeture du navigateur
        driver.quit();
    }

}