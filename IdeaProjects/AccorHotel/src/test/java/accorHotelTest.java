import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class accorHotelTest {


    protected static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        String s = System.setProperty("webdriver.chrome.driver", "C:\\browser_drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().window().maximize();

    }

    @Test
    public void VerifTest() {
        //lancement google
        System.out.println("lancement google...");
        driver.get("https://www.google.fr");
        //accor hotel
        System.out.println("connexion au site d'Accor Hotel...");
        driver.get("https://www.accorhotels.com/france/index.fr.shtml");
        //entrer la destination
        System.out.println("Selection de la destination...");
        driver.findElement(By.id("search-destination")).clear();
        String destination = "Valenciennes";
        driver.findElement(By.id("search-destination")).sendKeys(destination);
        //wait 2 secondes
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        System.out.println("selection du premier élément de la liste...");
        // click sur le premier de la liste
        driver.findElement(By.className("autocompleteItem__label")).click();
        //select date d'arrivée
        System.out.println("selection de la date d'arrivée...");
        driver.findElement(By.id("search-dateIn-boo")).click();
        //date du jour
        Date actuelle = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd");
        String arrivee = dateFormat.format(actuelle);
        //date d'arrivée
        driver.findElement(By.linkText(arrivee)).click();
        //conversion en int pour ajouter des jours pour la date de départ
        Integer a = Integer.parseInt(arrivee);
        Integer b = a + 3;
        //reconversion en string
        String depart = ""+b;
        //date de départ
        System.out.println("selection de la date de départ...");
        driver.findElement(By.id("search-dateout-boo")).click();
        driver.findElement(By.linkText(depart)).click();
        //suppression barre cookies
        driver.findElement(By.cssSelector("#cnil-banner__action-close")).click();
        System.out.println("Barre cookie fermée");
        //appel fonction selection du nombre de chambres
        System.out.println("selection du nombre de chambre...");
        int nombreChambres = selectNbChamber();
        //boucle pour selectionner le nb d'adultes et d'enfant pour chaque chambres
        for (int i = 0; i < nombreChambres; i++){
            //appel fonction selection du nombre d'adultes
            System.out.println("selection du nombre d'adultes...");
            selectNbAdult(i);
            //appel fonction selection du nombre d'enfants
            System.out.println("selection du nombre d'enfants...");
            int nombreEnfants = selectNbChild(i);
            //boucle qui selectionne l'age de chaque enfant si leur nombre est supérieur a 0
            if (nombreEnfants > 0) {
                for (int j = 0; j < nombreEnfants; j++) {
                    //appel fonction selection de l'age des enfants
                    System.out.println("selection de l'age des enfants...");
                    selectChildAge(i, j);
                }
            }
        }
        //rechercher
        driver.findElement(By.className("tSubmit")).click();
    }

    public int selectNbChamber(){
        //generation random nombre de chambres
        Random r = new Random();
        Integer chMin = 1;
        Integer chMax = 7;
        Integer valeurCh = chMin + r.nextInt(chMax - chMin);
        String nbCh = ""+valeurCh;
        //selection du nombre de chambres dans la liste déroulante
        driver.findElement(By.id("search-roomNumber-boo")).click();
        Select nbRoom = new Select(driver.findElement(By.id("search-roomNumber-boo")));
        nbRoom.selectByVisibleText(nbCh);
        System.out.println("Nombre de chambres: " + valeurCh);
        return valeurCh;
    }

    public int selectNbAdult(int i){
        //generation random nombre d'adultes
        Random r = new Random();
        Integer adultMin = 1;
        Integer adultMax = 9;
        Integer valeurAdult = adultMin + r.nextInt(adultMax - adultMin);
        String nbAdulte = ""+valeurAdult;
        //selection du nombre d'adultes dans la liste déroulante
        String adultSelector = "search.roomCriteria["+ i +"].adultNumber";
        Select nbAdult = new Select(driver.findElement(By.name(adultSelector)));
        nbAdult.selectByVisibleText(nbAdulte);
        System.out.println("Nombre d'adultes: " + valeurAdult);
        return valeurAdult;
    }

    public int selectNbChild(int i){
        //generation random nombre d'enfants
        Random r = new Random();
        Integer enfantMin = 0;
        Integer enfantMax = 3;
        Integer valeurEnfant = enfantMin + r.nextInt(enfantMax - enfantMin);
        String nbEnfant = ""+valeurEnfant;
        //selection du nombre d'enfants dans la liste déroulante
        String enfantSelector = "search.roomCriteria["+ i +"].childrenNumber";
        Select nbChild = new Select(driver.findElement(By.name(enfantSelector)));
        nbChild.selectByVisibleText(nbEnfant);
        System.out.println("Nombre d'enfants: " + valeurEnfant);
        return valeurEnfant;
    }

    public int selectChildAge(int i, int j){
        //generation random age des enfants
        Random r = new Random();
        Integer ageMin = 0;
        Integer ageMax = 16;
        Integer ageEnfant = ageMin + r.nextInt(ageMax - ageMin);
        String agChild = "" + ageEnfant;
        //selection de l'age dans la liste déroulante
        String ageSelector = "search.roomCriteria[" + i + "].children[" + j + "].age";
        Select age = new Select(driver.findElement(By.id(ageSelector)));
        age.selectByVisibleText(agChild);
        System.out.println("age enfants: " + ageEnfant);
        return ageEnfant;
    }

    @After
    public void cleanUp(){
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void tearDown(){
        driver.close();
    }
}
