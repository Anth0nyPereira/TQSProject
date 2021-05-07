package ua.deti.tqs.airquality_tqs_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.deti.tqs.airquality_tqs_project.component.AirQualityCache;
import ua.deti.tqs.airquality_tqs_project.component.City;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class AirQualityUnitCacheTest {

    private AirQualityCache cache;

    @BeforeEach
    public void setUp() {
        cache = new AirQualityCache();
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
    public void ifAirQualityDoesNotExist_ThenReturnFalse() {
        assertFalse(cache.checkIfCityExists("Ovar"));
    }

    @Test
    public void ifAirQualityExists_ThenReturnObject() {
        City existingCityObject = new City("Aveiro", 1111111111, 40.64427, -8.64554);
        assertThat(cache.returnCityIfCityExists("Aveiro")).isEqualTo(existingCityObject);
    }

    @Test
    public void ifAirQualityDoesNotExist_ThenReturnNull() {
        assertThat(cache.returnCityIfCityExists("Ovar")).isNull();
    }

    @Test
    public void ifRemovedCity_ThenCheckSize() {
        assertThat(cache.getSize()).isEqualTo(1);
        cache.removeByCityName("Aveiro");
        assertThat(cache.getSize()).isEqualTo(0);
    }

    @Test
    public void ifCityNameExists_ThenReturnAirQualityValue() {
        String cityName = "Aveiro";
        AirQuality expected = new AirQuality(12, 264.5, 17, 4, 12, 13, 3, "Molds", 1, 1, 1, 1);
        assertThat(cache.getValue(cityName)).isEqualTo(expected);
    }
}
