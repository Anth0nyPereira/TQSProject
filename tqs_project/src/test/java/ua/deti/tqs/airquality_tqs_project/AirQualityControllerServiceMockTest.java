package ua.deti.tqs.airquality_tqs_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ua.deti.tqs.airquality_tqs_project.component.AirQualityLogs;
import ua.deti.tqs.airquality_tqs_project.controller.AirQualityController;
import ua.deti.tqs.airquality_tqs_project.service.AirQualityService;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class AirQualityControllerServiceMockTest {

    @Spy
    AirQualityLogs logs;

    @InjectMocks
    AirQualityController airQualityController;

    @Mock(lenient = true)
    AirQualityService airQualityService;

    @BeforeEach
    public void setUp() {
        String ovarCity = "Ovar";
        AirQuality aq1 = new AirQuality(25, 122.1, 13, 6, 12, 13, 5, "Molds", 1, 1, 0, 3);
        Mockito.when(airQualityService.getData(ovarCity)).thenReturn(aq1);
    }

    @Test
    public void getAirQualityDataFromMockedService() {
        String city = "Ovar";
        int expectedLevelMold = 3;
        Object returnedAirQualityObject = airQualityController.getData(city).getBody(); // returns the airquality object defined earlier
        String contentResult = returnedAirQualityObject.toString(); // basically the airquality object as a String, the way I found to make some kind of tests,
        // because "Quem não tem cão caça com gato!!" --> if you dont speak Portuguese, it's a portuguese expression, google it ig
        assertTrue(contentResult.contains("PollenLevelMold=" + expectedLevelMold));
    }

}
