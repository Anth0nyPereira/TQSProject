package ua.deti.tqs.airquality_tqs_project;

import com.github.dockerjava.api.model.ResponseItem;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.deti.tqs.airquality_tqs_project.component.AirQualityCache;
import ua.deti.tqs.airquality_tqs_project.City;
import org.springframework.http.HttpMethod;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirQualityIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AirQualityCache airQualityCache;

    @Test
    public void whenGetCache_ThenReturnCache()  { // checks if previously added City exists in cache
        City city1 = new City("Lajes das Flores", 123456789, 40, -40);
        AirQuality aq1 = new AirQuality(25, 122.1, 13, 6, 12, 13, 5, "Molds", 1, 1, 0, 3);
        airQualityCache.add(city1, aq1);

        ResponseEntity<Object> response = restTemplate
                .exchange("/api/cache", HttpMethod.GET, null, new ParameterizedTypeReference<Object>() {
                });

        String contentResult = response.getBody().toString();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertTrue(contentResult.contains("Lajes das Flores"));
        assertTrue(contentResult.contains("latitude=40.0"));
        assertTrue(contentResult.contains("longitude=-40.0"));
    }

    @Test
    public void whenGetCacheStatistics_ThenReturnCacheStatistics()  { // checks the updated statistics of cache
        City city1 = new City("Lajes das Flores", 123456789, 40, -40);
        AirQuality aq1 = new AirQuality(25, 122.1, 13, 6, 12, 13, 5, "Molds", 1, 1, 0, 3);
        airQualityCache.add(city1, aq1);

        ResponseEntity<Object> response = restTemplate
                .exchange("/api/dataByCoords?lat=" + city1.getLatitude() + "&lon=" + city1.getLongitude(), HttpMethod.GET, null, new ParameterizedTypeReference<Object>() {
                });

        ResponseEntity<Object> response2 = restTemplate
                .exchange("/api/stats", HttpMethod.GET, null, new ParameterizedTypeReference<Object>() {
                });

        String contentResult = response2.getBody().toString();
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response2.getBody()).isNotNull();
        System.out.println(contentResult);
        assertTrue(contentResult.contains("countRequests=1"));
        assertTrue(contentResult.contains("hits=0"));
        assertTrue(contentResult.contains("misses=1"));
    }


}
