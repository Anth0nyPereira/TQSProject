package ua.deti.tqs.uv_tqs_project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.deti.tqs.uv_tqs_project.AirQuality;
import ua.deti.tqs.uv_tqs_project.entity.AirQualityCache;
import org.json.*;

@Service
public class AirQualityService {
    private AirQualityCache airQualityCache;

    public AirQuality getData(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String airQualityResults = restTemplate.getForObject("https://api.weatherbit.io/v2.0/current/airquality?city=" + city + "&key=08e72c5cff1b4b7794a4272224d1597f", String.class);
        //System.out.println(airQualityResults);
        JSONObject json = new JSONObject(airQualityResults);
        JSONArray dataArray = json.getJSONArray("data");
        JSONObject data = dataArray.getJSONObject(0);
        AirQuality airQuality = new AirQuality(data.getInt("aqi"), data.getDouble("co"), data.getDouble("o3"), data.getDouble("so2"), data.getDouble("no2"), data.getDouble("pm10"), data.getDouble("pm25"), data.getString("predominant_pollen_type"), data.getInt("pollen_level_tree"), data.getInt("pollen_level_weed"), data.getInt("pollen_level_grass"), data.getInt("mold_level"));
        return airQuality;
    }

}
