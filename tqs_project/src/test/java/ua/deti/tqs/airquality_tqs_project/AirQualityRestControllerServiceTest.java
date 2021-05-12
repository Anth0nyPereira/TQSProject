package ua.deti.tqs.airquality_tqs_project;


import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.deti.tqs.airquality_tqs_project.controller.AirQualityController;
import ua.deti.tqs.airquality_tqs_project.service.AirQualityService;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AirQualityController.class)
public class AirQualityRestControllerServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService aqService;

    @Test
    public void givenAnAirQualityObject_whenGetThisObjectByCityName_thenReturnThisObject() throws Exception {
        String cityName = "Aveiro";
        AirQuality airQuality = new AirQuality(12, 264.5, 17, 4, 12, 13, 3, "Molds", 1, 1, 1, 1);
        given(aqService.getData(cityName)).willReturn(airQuality);

        mvc.perform(get("/api/data?city=" + cityName).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.*", hasSize(12))).andExpect(jsonPath("aqIndex", is(airQuality.getAqIndex())));
        verify(aqService, VerificationModeFactory.times(1)).getData(cityName);

    }

    @Test
    public void givenAnAirQualityObject_whenGetObjectByCoords_thenReturnObject() throws Exception {
        double latitude = 40.64427;
        double longitude = -8.64554;
        AirQuality airQuality = new AirQuality(12, 264.5, 17, 4, 12, 13, 3, "Molds", 1, 1, 1, 1);

        given(aqService.getDataByCoords(latitude, longitude)).willReturn(airQuality);

        mvc.perform(get("/api/dataByCoords?lat=" + latitude + "&lon=" + longitude).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.*", hasSize(12))).andExpect(jsonPath("aqIndex", is(airQuality.getAqIndex())));
        verify(aqService, VerificationModeFactory.times(1)).getDataByCoords(latitude, longitude);
    }

}
