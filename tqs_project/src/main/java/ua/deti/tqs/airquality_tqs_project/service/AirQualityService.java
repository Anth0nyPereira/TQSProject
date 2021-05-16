package ua.deti.tqs.airquality_tqs_project.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.deti.tqs.airquality_tqs_project.AirQuality;
import ua.deti.tqs.airquality_tqs_project.component.AirQualityLogs;
import ua.deti.tqs.airquality_tqs_project.component.AirQualityCache;
import org.json.*;
import ua.deti.tqs.airquality_tqs_project.component.AirQualityStatistics;
import ua.deti.tqs.airquality_tqs_project.City;

import java.util.*;

@Log4j2
@Service
public class AirQualityService {

    @Autowired
    private AirQualityCache airQualityCache;

    @Autowired
    private AirQualityLogs logs;

    @Value("${apiKey}") // my API KEY DONT SEE IT PLS :P
    private String apiKey;

    private static RestTemplate restTemplate = new RestTemplate();

    public AirQuality getData(String cityName) { // get Data by cityname, 1st it checks if the City already exists in cache and if the AirQuality data is "recent" (with a recent TTL - time attribute)
        // each 3-5 seconds is a miss basically, so the best way to actually get the information from the cache is reloading the page in the reload button or spamming the POSTMAN's Send button
        cityName = cityName.toLowerCase();
        cityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
        log.info("Cache at the moment: " + airQualityCache);
        logs.addLog("Cache at the moment: " + airQualityCache);
        if (airQualityCache.checkIfCityExists(cityName)) {
            Date date = new Date();
            long timeMs = date.getTime(); // fresh-new TTL :)
            long oldCityTime = airQualityCache.returnTimeByCityName(cityName);
            if (timeMs - oldCityTime  >= 10000) { // invalid, remove and call external API
                airQualityCache.removeByCityName(cityName);
                AirQuality airQuality = getDataFromExternalAPI(cityName); // call the method that consults the external API because the data found was outdated
                log.info("came from external API because data was outdated");
                logs.addLog("came from external API because data was outdated");
                airQualityCache.updateRequests();
                airQualityCache.updateMisses();
                log.info("Cache statistics: " + airQualityCache.getCacheStatistics());
                logs.addLog("Cache statistics: " + airQualityCache.getCacheStatistics());
                return airQuality;
            } else {
                AirQuality airQuality = airQualityCache.getValue(cityName);
                log.info("came from cache");
                logs.addLog("came from cache");
                airQualityCache.updateRequests();
                airQualityCache.updateHits();
                log.info("Cache statistics: " + airQualityCache.getCacheStatistics());
                logs.addLog("Cache statistics: " + airQualityCache.getCacheStatistics());
                return airQuality;
            }

        } else { // directly from external API
            AirQuality airQuality = getDataFromExternalAPI(cityName);
            return airQuality;
        }
    }

    public AirQuality getDataFromExternalAPI(String cityName) { // call external API
        RestTemplate restTemplate = getRestTemplate();
        String airQualityResults = restTemplate.getForObject("https://api.weatherbit.io/v2.0/current/airquality?city=" + cityName + "&key=" + apiKey, String.class);
        if (airQualityResults != null) {
            log.info("airqualityresults: " + airQualityResults);
            logs.addLog("airqualityresults: " + airQualityResults);
            JSONObject json = new JSONObject(airQualityResults);
            JSONArray dataArray = json.getJSONArray("data");
            JSONObject data = dataArray.getJSONObject(0);
            AirQuality airQuality = new AirQuality(data.getInt("aqi"), data.getDouble("co"), data.getDouble("o3"), data.getDouble("so2"), data.getDouble("no2"), data.getDouble("pm10"), data.getDouble("pm25"), data.getString("predominant_pollen_type"), data.getInt("pollen_level_tree"), data.getInt("pollen_level_weed"), data.getInt("pollen_level_grass"), data.getInt("mold_level"));

            // Instantiate new City object to be stored in cache
            Date date = new Date();
            long timeCreated = date.getTime();
            double latitude = json.getDouble("lat");
            double longitude = json.getDouble("lon");
            City city = new City(cityName, timeCreated, latitude, longitude);

            // Store it in cache
            airQualityCache.add(city, airQuality);
            log.info("came from external API");
            logs.addLog("came from external API");
            return airQuality;
        } else {
            return null;
        }
    }


    public AirQualityStatistics getStats() { // get cache statistics
        AirQualityStatistics aqs = airQualityCache.getCacheStatistics();
        return aqs;
    }

    public AirQuality getDataByCoords(double lat, double lon) { // get data by geographical coordinates (similar to the cityname one)
        log.info("Cache at the moment: " + airQualityCache);
        logs.addLog("Cache at the moment: " + airQualityCache);
        if (airQualityCache.checkIfCityExists(lat, lon)) {
            Date date = new Date();
            long timeMs = date.getTime();
            long oldCityTime = airQualityCache.returnTimeByCoords(lat, lon);
            if (timeMs - oldCityTime  >= 10000) { // invalid, remove and call external API
                airQualityCache.removeByCoords(lat, lon);
                AirQuality airQuality = getDataByCoordsFromExternalAPI(lat, lon);
                log.info("came from external API because data was outdated");
                logs.addLog("came from external API because data was outdated");
                airQualityCache.updateRequests();
                airQualityCache.updateMisses();
                log.info("Cache statistics: " + airQualityCache.getCacheStatistics());
                logs.addLog("Cache statistics: " + airQualityCache.getCacheStatistics());
                return airQuality;
            } else {
                AirQuality airQuality = airQualityCache.getValue(lat, lon);
                log.info("came from cache");
                logs.addLog("came from cache");
                airQualityCache.updateRequests();
                airQualityCache.updateHits();
                log.info("Cache statistics: " + airQualityCache.getCacheStatistics());
                logs.addLog("Cache statistics: " + airQualityCache.getCacheStatistics());
                return airQuality;
            }

        } else { // directly from external API
            AirQuality airQuality = getDataByCoordsFromExternalAPI(lat, lon);
            return airQuality;
        }
    }

    public AirQuality getDataByCoordsFromExternalAPI(double lat, double lon) { // call external API by coordinates
        RestTemplate restTemplate = getRestTemplate();
        String airQualityResults = restTemplate.getForObject("https://api.weatherbit.io/v2.0/current/airquality?lat=" + lat + "&lon=" + lon + "&key=" + apiKey, String.class);
        JSONObject json = new JSONObject(airQualityResults);
        JSONArray dataArray = json.getJSONArray("data");
        JSONObject data = dataArray.getJSONObject(0);
        if (!data.get("aqi").equals(null)) { // sometimes the response has fields with null
            AirQuality airQuality = new AirQuality(data.getInt("aqi"), data.getDouble("co"), data.getDouble("o3"), data.getDouble("so2"), data.getDouble("no2"), data.getDouble("pm10"), data.getDouble("pm25"), data.getString("predominant_pollen_type"), data.getInt("pollen_level_tree"), data.getInt("pollen_level_weed"), data.getInt("pollen_level_grass"), data.getInt("mold_level"));

            // Instantiate new City object to be stored in cache
            Date date = new Date();
            long timeCreated = date.getTime();
            double latitude = json.getDouble("lat");
            double longitude = json.getDouble("lon");
            String cityName = json.getString("city_name");
            City city = new City(cityName, timeCreated, latitude, longitude);

            // Store it in cache
            airQualityCache.add(city, airQuality);
            log.info("came from external API");
            logs.addLog("came from external API");
            return airQuality;
        } else {
            return null;
        }
    }

    public Map<City, AirQuality> findAll() { // get content of cache
        return airQualityCache.getCache();
    }

    public static RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public AirQualityCache getAirQualityCache() {
        return airQualityCache;
    }

    public String getCityNameByCoords(double lat, double lon) {
        City searchedCity = airQualityCache.returnCityIfCityExists(lat, lon);
        return searchedCity.getCity();
    }
}