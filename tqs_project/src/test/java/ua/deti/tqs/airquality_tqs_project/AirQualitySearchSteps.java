package ua.deti.tqs.airquality_tqs_project;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
        assertThat(driver.findElement(By.id("cityName")).getText(), is("Aveiro"));
    }
}
