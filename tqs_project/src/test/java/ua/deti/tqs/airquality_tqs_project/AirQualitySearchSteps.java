package ua.deti.tqs.airquality_tqs_project;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Quand;
import io.cucumber.java.fr.Soit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AirQualitySearchSteps {

    private WebDriver driver;

    @Given("an application just turned on")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/anth0nypereira/Desktop/LEI/TQS/TQSProject/tqs_project/src/test/resources/chromedriver");
        driver = new ChromeDriver();
    }

    @When("a user goes to the homepage")
    public void goToHomePage() {
        driver.get("http://localhost:8080/");
        driver.manage().window().setSize(new Dimension(1920, 1020));

    }

    @And("types {string} in the city name search bar")
    public void typesInTheCityNameSearchBar(String city) {
        driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys(city);
    }


    @And("clicks Enter")
    public void clicksEnter() {
        driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys(Keys.ENTER);
    }

    @Then("{string} should appear in the header")
    public void shouldAppearInTheHeader(String city) {
        assertThat(driver.findElement(By.id("cityName")).getText(), is(city));
    }

    @Soit("que l'application a été mise en route")
    public void queLApplicationAÉtéMiseEnRoute() {
        System.setProperty("webdriver.chrome.driver", "/home/anth0nypereira/Desktop/LEI/TQS/TQSProject/tqs_project/src/test/resources/chromedriver");
        driver = new ChromeDriver();
    }


    @Quand("un utilisateur vá sur la page d'accueil")
    public void unUtilisateurVáSurLaPageDAccueil() {
        driver.get("http://localhost:8080/");
        driver.manage().window().setSize(new Dimension(1920, 1020));
    }


    @Et("écrit {double} et {double} sur la barre de recherche réservé pour la recherche par coordonnées")
    public void écritEtSurLaBarreDeRechercheRéservéPourLaRechercheParCoordonnées(double latitude, double longitude) {
        driver.findElement(By.xpath("/html/body/div/div[3]/form/fieldset/div/div/div/div[1]/input[2]")).sendKeys(String.valueOf(latitude) + " " + String.valueOf(longitude));
    }


    @Et("appuie sur Enter")
    public void appuieSurEnter() {
        driver.findElement(By.xpath("/html/body/div/div[3]/form/fieldset/div/div/div/div[1]/input[2]")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div/div[3]/form/fieldset/div/div/div/div[1]/input[2]")).sendKeys(Keys.ENTER);
    }


    @Alors("{double} et {double} apparaissent en-tête de page")
    public void etApparaissentEnTêteDePage(double latitude, double longitude) {
        assertThat(driver.findElement(By.id("latitude")).getText(), is(String.valueOf(latitude)));
        assertThat(driver.findElement(By.id("longitude")).getText(), is(String.valueOf(longitude)));
    }
}
