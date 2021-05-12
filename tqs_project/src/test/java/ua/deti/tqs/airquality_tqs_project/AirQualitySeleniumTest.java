package ua.deti.tqs.airquality_tqs_project;// Generated by Selenium IDE
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.*;

public class AirQualitySeleniumTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @BeforeEach
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "/home/anth0nypereira/Desktop/LEI/TQS/TQSProject/tqs_project/src/test/resources/chromedriver");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void validParameterTest() {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1920, 1020));
    driver.findElement(By.cssSelector(".choices__inner")).click();
    driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys("Aveiro");
    driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys(Keys.ENTER);
    driver.findElement(By.cssSelector("svg")).click();
    assertThat(driver.findElement(By.id("cityName")).getText(), is("Aveiro"));
    driver.findElement(By.cssSelector(".go-back")).click();
  }

  @Test
  public void numericParameterTest() {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1920, 1020));
    driver.findElement(By.cssSelector(".choices__inner")).click();
    driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys("12345");
    driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys(Keys.ENTER);
    driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys(Keys.ENTER);
    assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("404 Not Found"));
    assertThat(driver.findElement(By.cssSelector("p")).getText(), is("No results were found"));
  }

  @Test
  public void invalidParameterTest() {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1920, 1020));
    driver.findElement(By.cssSelector(".choices__inner")).click();
    driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys("deywuviq632vye");
    driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys(Keys.ENTER);
    driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys(Keys.ENTER);
    assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("404 Not Found"));
    assertThat(driver.findElement(By.cssSelector("p")).getText(), is("No results were found"));
  }

  @Test
  public void emptyParameterTest() {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1920, 1020));
    driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys(Keys.ENTER);
    assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("HTTP 404"));
    assertThat(driver.findElement(By.cssSelector("p")).getText(), is("You made an empty search, so no results were found"));
  }
}
