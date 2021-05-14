package ua.deti.tqs.airquality_tqs_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.deti.tqs.airquality_tqs_project.component.AirQualityCache;
import ua.deti.tqs.airquality_tqs_project.City;
import ua.deti.tqs.airquality_tqs_project.service.AirQualityService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AirQualityServiceTest {

    @Autowired
    private AirQualityCache airQualityCache;

    @Autowired
    private AirQualityService airQualityService;

    @BeforeEach
    public void setUp() {
        City c1 = new City("Aveiro", 1620483090160L, 40.64427, -8.64554);
        AirQuality aq1 = new AirQuality(12, 264.5, 17, 4, 12, 13, 3, "Molds", 1, 1, 1, 1);
        airQualityCache.add(c1, aq1);
    }

    @Test
    public void givenExistingAirQualityInCache_whenSearchForCity_AndAirQualityIsInvalid_ThenCheckUpdateMisses() { // test to get data of a city that already exists in cache but its content is outdated
        System.out.println(airQualityService.getData("Aveiro"));
        assertThat(airQualityCache.getCacheStatistics().getCountRequests()).isEqualTo(1);
        assertThat(airQualityCache.getCacheStatistics().getMisses()).isEqualTo(1);
        assertThat(airQualityCache.getCacheStatistics().getHits()).isEqualTo(0);
    }
}
