import au.com.bytecode.opencsv.CSVReader;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static junit.framework.TestCase.assertTrue;
import org.apache.commons.io.FileUtils;



public class Exercice7Test {

    protected static WebDriver driver;


    @BeforeClass
    public static void setUp(){
        String s = System.setProperty("webdriver.chrome.driver", "C:\\browser_drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().window().maximize();
    }

    @Test
    public void bookFlight(){
        //appel de la fonction launch site
        launchSite();
        //appel de la fonction connexion
        connexion();
        //appel de la fonction selectFromPort qui selectionne la ville de départ
        selectFromPort();
        //appel de la fonction selectToPort qui selectionne la ville d'arrivée
        selectToPort();
        //appel de la fonction selectOneWay qui selectionne un vol aller simple
        selectOneWay();
        //click sur continue
        System.out.println("Click sur continue...");
        driver.findElement(By.name("findFlights")).click();
        //appel de la fonction recoverFirstFlightNumber qui recupere le numéro du premier vol
        String flight_number = recoverFirstFlightNumber();
        //click sur continue
        System.out.println("Click sur continue...");
        driver.findElement(By.name("reserveFlights")).click();
        //appel de la fonction recoverTicketPrice qui récupère le prix du billet
        String ticket_price = recoverTicketPrice();
        //appel de la fonction fillRequiredField
        fillRequiredField();
        //click sur continue
        System.out.println("Click sur continue...");
        driver.findElement(By.name("buyFlights")).click();
        //appel de la fonction checkText qui verifie la présence du texte "Your itinerary has been booked!"
        System.out.println("Verification de la présence du texte...");
        checkText();
        //appel de la fonction checkFlightNumber qui verifie la présence du numéro de vol
        System.out.println("Verification de la présence du numéro de vol...");
        checkFlightNumber(flight_number);
        //appel de la fonction checkTicketPrice qui verifie la présence du prix du billet
        System.out.println("Verification de la présence du prix du billet...");
        checkTicketPrice(ticket_price);
        //appel de la fonction makeScreenshot
        System.out.println("Screenshot en cours...");
        makeScreenshot();
        //appel de la fonction logout
        System.out.println("Déconnexion...");
        //logOut();
    }

    public void launchSite(){
        //lancement google
        System.out.println("lancement google...");
        driver.get("https://www.google.fr");

        //Lancer URL
        System.out.println("connexion a newtours...");
        driver.get("http://localhost:56/servlets/com.mercurytours.servlet.WelcomeServlet");
    }


    public void connexion(){
        //CLique sur sign on
        System.out.println("Click sur SIGN-ON...");
        driver.findElement(By.linkText("SIGN-ON")).click();
        //Saisir  user et mpd
        System.out.println("saisie des identifiant et password...");

        /***********DEBUT CSV**********************/

        String CSV_file = "src/test/resources/login.csv";

        //READ CSV
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(CSV_file));
            String[] cell;
            //Reader lit chaque colonne (readNext
            while((cell = reader.readNext())!=null) {
                String colonne1 = cell[0]; // username
                String colonne2 = cell[1]; //password
                driver.findElement(By.linkText("SIGN-ON")).click();
                driver.findElement(By.name("userName")).sendKeys(colonne1);
                driver.findElement(By.name("password")).sendKeys(colonne2);
                driver.findElement(By.name("login")).click();

            }
        }catch (IOException e) {
            e.printStackTrace();
        }



        /***********FIN CSV************************/
    }

    public void selectOneWay(){
        //selection vol aller simple
        System.out.println("Click sur One way...");
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td[2]/b/font/input[2]")).click();
    }

    public void selectFromPort(){
        //selection de la ville de départ
        String ville_depart = "Sydney";
        System.out.println("selection de la ville de départ...");
        System.out.println("Ville de départ : "+ville_depart);
        driver.findElement(By.name("fromPort")).click();
        Select depart = new Select(driver.findElement(By.name("fromPort")));
        depart.selectByVisibleText(ville_depart);
    }

    public void selectToPort(){
        //selection de la ville d'arrivée
        String ville_arrivee = "Portland";
        System.out.println("selection de la ville d'arrivée...");
        System.out.println("Ville d'arrivée: "+ville_arrivee);
        driver.findElement(By.name("fromPort")).click();
        Select depart = new Select(driver.findElement(By.name("toPort")));
        depart.selectByVisibleText(ville_arrivee);
    }

    public String recoverFirstFlightNumber(){
        //récupére le numéro du 1Er vol
        System.out.println("récupération du numéro de vol...");
        String flight_number = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[3]/td[2]/font/b")).getText();
        System.out.println("Numéro de vol: "+flight_number);
        return flight_number;
    }

    public String recoverTicketPrice(){
        //récupére le prix du billet
        System.out.println("récupération du prix du ticket...");
        String price = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[6]/td[2]/font/b")).getText();
        String ticket_price = price + " USD";
        System.out.println("prix retourné : " + ticket_price);
        return ticket_price;
    }

    public void fillRequiredField(){
        //remplis les champs obligatoire
        System.out.println("Remplissage des champs obligatoire pour la réservation...");
        //First name
        driver.findElement(By.name("passFirst0")).clear();
        driver.findElement(By.name("passFirst0")).sendKeys("Jacques");
        //Last name
        driver.findElement(By.name("passLast0")).clear();
        driver.findElement(By.name("passLast0")).sendKeys("Chirac");
        //card number
        driver.findElement(By.name("creditnumber")).clear();
        driver.findElement(By.name("creditnumber")).sendKeys("1010101010101010");
    }

    public void checkText(){
        //Vérifier le texte "Your itinerary has been booked!" est affiché
        String text = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/p/font/b/font[2]")).getText();
        String text2 = "Your itinerary has been booked!";
        assertTrue(text.contains(text2));
    }

    public void checkFlightNumber(String flight_number){
        driver.getPageSource().contains(flight_number);
    }

    public void checkTicketPrice(String ticket_price){
        driver.getPageSource().contains(ticket_price);
    }

    public void makeScreenshot() {
        // Take screenshot and store as a file format
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            // now copy the  screenshot to desired location using copyFile //method
            FileUtils.copyFile(src, new File("C:/selenium/"+System.currentTimeMillis()+".png"));
            System.out.println("Screenshot terminé et enregistré");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void logOut(){
        //click sur sign off
        driver.findElement(By.linkText("SIGN-OFF")).click();
    }

    /*@After
    public void cleanUp(){
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void tearDown(){
        driver.close();
    }*/

}
