package ua.deti.tqs.airquality_tqs_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import ua.deti.tqs.airquality_tqs_project.component.AirQualityCache;
import ua.deti.tqs.airquality_tqs_project.City;
import ua.deti.tqs.airquality_tqs_project.component.AirQualityLogs;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
// unit tests to test the cache -> such as, the HashMap
public class AirQualityUnitCacheTest {

    private AirQualityCache cache;

    @BeforeEach
    public void setUp() throws IOException {
        cache = new AirQualityCache();
        cache.setLogs(new AirQualityLogs());
        AirQuality aq1 = new AirQuality(12, 264.5, 17, 4, 12, 13, 3, "Molds", 1, 1, 1, 1);
        cache.add(new City("Aveiro", 1111111111, 40.64427, -8.64554), aq1);
    }

    @Test
    public void ifAddNewElementToCache_ThenCheckSize() {
        assertThat(cache.getSize()).isEqualTo(1);
        City c = new City("New York", 12345678, 40.71427, -74.00597);
        AirQuality airQuality = new AirQuality(32, 510.67, 69.3333, 0.9375, 16.75, 8.67384, 5.9, "Trees", 3, 1, 3, 0);
        cache.add(c, airQuality);
        assertThat(cache.getSize()).isEqualTo(2);
    }

    @Test
    public void ifAirQualityExists_ThenReturnTrue() {
        assertTrue(cache.checkIfCityExists("Aveiro"));
    }

    @Test
    public void ifAirQUalityExistsByCoords_ThenReturnTrue() {
        double expectedLa = 40.64427;
        double expectedLo =  -8.64554;
        assertTrue(cache.checkIfCityExists(expectedLa, expectedLo));
    }

    @Test
    public void ifAirQualityDoesNotExist_ThenReturnFalse() {
        assertFalse(cache.checkIfCityExists("Ovar"));
    }

    @Test
    public void ifAirQUalityDoesNotExistByCoords_ThenReturnFalse() {
        assertFalse(cache.checkIfCityExists(40.71427, -74.00597));
    }

    @Test
    public void ifAirQualityExists_ThenReturnObject() {
        City existingCityObject = new City("Aveiro", 1111111111, 40.64427, -8.64554);
        assertThat(cache.returnCityIfCityExists("Aveiro")).isEqualTo(existingCityObject);
    }

    @Test
    public void ifAirQualityExistsByCoords_ThenReturnObject() {
        City existingCityObject = new City("Aveiro", 1111111111, 40.64427, -8.64554);
        double expectedLa = 40.64427;
        double expectedLo =  -8.64554;
        assertThat(cache.returnCityIfCityExists(expectedLa, expectedLo)).isEqualTo(existingCityObject);
    }

    @Test
    public void ifAirQualityDoesNotExist_ThenReturnNull() {
        assertThat(cache.returnCityIfCityExists("Ovar")).isNull();
    }

    @Test
    public void ifAirQualityDoesNotExistByCoords_ThenReturnNull() {
        assertThat(cache.returnCityIfCityExists(40.71427, -74.00597)).isNull();
    }

    @Test
    public void ifRemovedCity_ThenCheckSize() {
        assertThat(cache.getSize()).isEqualTo(1);
        cache.removeByCityName("Aveiro");
        assertThat(cache.getSize()).isEqualTo(0);
    }

    @Test
    public void ifRemovedCityByCoords_ThenCheckSize() {
        assertThat(cache.getSize()).isEqualTo(1);
        cache.removeByCoords(40.64427, -8.64554);
        assertThat(cache.getSize()).isEqualTo(0);
    }

    @Test
    public void ifCityNameExists_ThenReturnAirQualityValue() {
        String cityName = "Aveiro";
        AirQuality expected = new AirQuality(12, 264.5, 17, 4, 12, 13, 3, "Molds", 1, 1, 1, 1);
        assertThat(cache.getValue(cityName)).isEqualTo(expected);
    }

    @Test
    public void ifCoordsExist_ThenReturnAirQualityValue() {
        double expectedLa = 40.64427;
        double expectedLo =  -8.64554;
        AirQuality expected = new AirQuality(12, 264.5, 17, 4, 12, 13, 3, "Molds", 1, 1, 1, 1);
        assertThat(cache.getValue(expectedLa, expectedLo)).isEqualTo(expected);
    }

    @Test
    public void ifCityExists_ThenReturnTimeDifferentFromZero() {
        assertThat(cache.returnTimeByCityName("Aveiro")).isNotZero().isGreaterThan(0);
    }

    @Test
    public void ifCityExistsByCoords_ThenReturnTimeDifferentFromZero() {
        assertThat(cache.returnTimeByCoords(40.64427, -8.64554)).isNotZero().isGreaterThan(0);
    }

    @Test
    public void ifCityDoesNotExist_ThenReturnTimeZero() {
        assertThat(cache.returnTimeByCityName("Ovar")).isZero();
    }

    @Test
    public void ifCityDoesNotExistByCoords_ThenReturnTimeZero() {
        assertThat(cache.returnTimeByCoords(40.71427, -74.00597)).isZero();
    }
}
