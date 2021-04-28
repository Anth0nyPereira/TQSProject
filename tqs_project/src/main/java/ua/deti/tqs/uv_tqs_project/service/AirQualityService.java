package ua.deti.tqs.uv_tqs_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.deti.tqs.uv_tqs_project.AirQuality;
import ua.deti.tqs.uv_tqs_project.component.AirQualityCache;
import org.json.*;
import ua.deti.tqs.uv_tqs_project.component.City;

import java.util.Date;

@Service
public class AirQualityService {

    @Autowired
    private AirQualityCache airQualityCache;

    @Value("${apiKey}")
    private String apiKey;

    private static RestTemplate restTemplate = new RestTemplate();

    private static Date date = new Date();

    public AirQuality getData(String cityName) {
        System.out.println(airQualityCache);
        if (airQualityCache.checkIfCityExists(cityName)) {
            Date date = new Date();
            long timeMs = date.getTime();
            long oldCityTime = airQualityCache.returnTimeByCityName(cityName);
            System.out.println("timeMs: " + timeMs);
            System.out.println("difference: " + (timeMs - oldCityTime));
            if (timeMs - oldCityTime  >= 10000) { // invalid, remove and call external API
                airQualityCache.removeByCityName(cityName);
                AirQuality airQuality = getDataFromExternalAPI(cityName);
                System.out.println("came from external API because data was invalid");
                return airQuality;
            } else {
                AirQuality airQuality = airQualityCache.getValue(cityName);
                System.out.println("came from cache");
                return airQuality;
            }

        } else { // directly from external API
            AirQuality airQuality = getDataFromExternalAPI(cityName);
            return airQuality;
        }
    }

        public AirQuality getDataFromExternalAPI(String cityName) {
            RestTemplate restTemplate = getRestTemplate();
            String airQualityResults = restTemplate.getForObject("https://api.weatherbit.io/v2.0/current/airquality?city=" + cityName + "&key=" + apiKey, String.class);
            //System.out.println(airQualityResults);
            JSONObject json = new JSONObject(airQualityResults);
            JSONArray dataArray = json.getJSONArray("data");
            JSONObject data = dataArray.getJSONObject(0);
            AirQuality airQuality = new AirQuality(data.getInt("aqi"), data.getDouble("co"), data.getDouble("o3"), data.getDouble("so2"), data.getDouble("no2"), data.getDouble("pm10"), data.getDouble("pm25"), data.getString("predominant_pollen_type"), data.getInt("pollen_level_tree"), data.getInt("pollen_level_weed"), data.getInt("pollen_level_grass"), data.getInt("mold_level"));

            // Instantiate new City object to be stored in cache
            Date date = new Date();
            long timeCreated = date.getTime();
            City city = new City(cityName, timeCreated);

            // Store it in cache
            airQualityCache.add(city, airQuality);
            System.out.println("came from external API");
            return airQuality;
        }


    public static RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public static Date getDate() {
        return date;
    }

}
