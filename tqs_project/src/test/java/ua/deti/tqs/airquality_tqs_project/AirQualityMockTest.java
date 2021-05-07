package ua.deti.tqs.airquality_tqs_project;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ua.deti.tqs.airquality_tqs_project.component.AirQualityCache;
import ua.deti.tqs.airquality_tqs_project.service.AirQualityService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class AirQualityMockTest {

    @Mock
    AirQualityCache aqCache;

    @Mock( lenient = true)
    AirQualityService aqService;


    // int aqIndex, double co, double o3, double so2, double no2, double pm10, double pm25,
    // String predominantPollenType, int pollenLevelTree, int pollenLevelWeed, int pollenLevelGrass, int pollenLevelMold

    @BeforeEach
    public void setUp() {
        String city1 = "Aveiro";
        String city2 = "New York";
        String city3 = "London";
        AirQuality aq1 = new AirQuality(12, 264.5, 17, 4, 12, 13, 3, "Molds", 1, 1, 1, 1);
        Mockito.when(aqService.getDataFromExternalAPI(city1)).thenReturn(aq1);
    }

    @Test
    public void getAirQualityDataFromMockedExternalAPI() {
        String city = "Aveiro";
        assertThat(aqCache.checkIfCityExists(city)).isFalse();
        AirQuality res = aqService.getDataFromExternalAPI(city);
        assertThat(res.getAqIndex()).isEqualTo(12);
        assertThat(res.getCo()).isEqualTo(264.5);
        assertThat(res.getO3()).isEqualTo(17);
        assertThat(res.getSo2()).isEqualTo(4);
        assertThat(res.getNo2()).isEqualTo(12);
        assertThat(res.getPm10()).isEqualTo(13);
        assertThat(res.getPm25()).isEqualTo(3);
        assertThat(res.getPredominantPollenType()).isEqualTo("Molds");
        assertThat(res.getPollenLevelTree()).isEqualTo(1);
        assertThat(res.getPollenLevelWeed()).isEqualTo(1);
        assertThat(res.getPollenLevelGrass()).isEqualTo(1);
        assertThat(res.getPollenLevelMold()).isEqualTo(1);
    }
}
