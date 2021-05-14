package ua.deti.tqs.airquality_tqs_project;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class AirQualityChromeJupiterExtensionTest {

    @Test
    void testChromeJupiterExtension(ChromeDriver driver) { // test with jupiter selenium extension, checks if the search by cityname "Aveiro"
        driver.get("http://localhost:8080/");
        driver.manage().window().setSize(new Dimension(1920, 1020));
        driver.findElement(By.cssSelector(".choices__inner")).click();
        driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys("Aveiro");
        driver.findElement(By.cssSelector(".choices__input--cloned")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("svg")).click();
        assertThat(driver.findElement(By.id("cityName")).getText(), is("Aveiro"));
        driver.findElement(By.cssSelector(".go-back")).click();
    }
}
