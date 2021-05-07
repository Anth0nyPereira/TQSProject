package ua.deti.tqs.airquality_tqs_project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.deti.tqs.airquality_tqs_project.controller.AirQualityController;
import ua.deti.tqs.airquality_tqs_project.service.AirQualityService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.arrayWithSize;
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
    public void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
        String cityName = "Aveiro";
        AirQuality airQuality = new AirQuality(12, 264.5, 17, 4, 12, 13, 3, "Molds", 1, 1, 1, 1);
        AirQuality airQuality2 = new AirQuality(3, 79, 24, 4.5, 34, 21.1, 3.4, "Trees", 2, 1, 1, 1);

        given(aqService.getData(cityName)).willReturn(airQuality);

        mvc.perform(get("/api/data?city=" + cityName).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.*", hasSize(12))).andExpect(jsonPath("aqIndex", is(airQuality.getAqIndex())));
        verify(aqService, VerificationModeFactory.times(1)).getData(cityName);

    }

}
