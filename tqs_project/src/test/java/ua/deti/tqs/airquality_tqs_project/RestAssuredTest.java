package ua.deti.tqs.airquality_tqs_project;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTest {
    @Test
    public void whenGetUrlThenStatusCode200() {
        String url = "http://localhost:8080";
        given().when().get(url).then().assertThat().statusCode(200);
    }

    @Test
    public void whenGetValidAirQualityDataByCityNameThenCheckBody() {
        String url = "http://localhost:8080/api/data?city=Aveiro";
        given()
                .when()
                .get(url)
                .then().assertThat()
                .statusCode(200)
                .and().body("isEmpty()", Matchers.is(false));
    }

    @Test
    public void whenGetInvalidAirQualityDataByCityNameThenCheckError() {
        String url = "http://localhost:8080/api/data?city=12345";
        given()
                .when()
                .get(url)
                .then().assertThat()
                .statusCode(404);
    }

    @Test
    public void whenEmptyCityNameThenCheckError() {
        String url = "http://localhost:8080/api/data?city=";
        given()
                .when()
                .get(url)
                .then().assertThat()
                .statusCode(400);
    }

    @Test
    public void whenGetValidAirQualityDataByCoordsThenCheckBody() {
        String url = "http://localhost:8080/api/dataByCoords?lat=40&lon=-40";
        given()
                .when()
                .get(url)
                .then().assertThat()
                .statusCode(200)
                .and().body("isEmpty()", Matchers.is(false));
    }
}
