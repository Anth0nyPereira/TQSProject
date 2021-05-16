package ua.deti.tqs.airquality_tqs_project.component;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.deti.tqs.airquality_tqs_project.AirQuality;
import ua.deti.tqs.airquality_tqs_project.City;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Log4j2
@Component
public class AirQualityCache {
    private Map<City, AirQuality> cache = new HashMap<>();

    @Autowired
    private AirQualityStatistics stats; // class that contains 3 statistics counters

    @Autowired
    private AirQualityLogs logs;

    public AirQualityCache() {
    }

    public void add(City key, AirQuality value) {
        cache.put(key, value);
    } // add a new pair to the cache

    public boolean checkIfCityExists(String cityName) { // checks if a City object exists in cache by cityname
        Set<City> set = cache.keySet();
        Iterator<City> i = set.iterator();
        while (i.hasNext()) {
            City res = i.next();
            if (res.getCity().equals(cityName)) {
                log.info("City with cityname " + cityName + " already exists in cache");
                logs.addLog("City with cityname " + cityName + " already exists in cache");
                return true;
            }
        }
        log.info("City with cityname " + cityName + " does not exist in cache");
        logs.addLog("City with cityname " + cityName + " does not exist in cache");
        return false;
    }

    public City returnCityIfCityExists(String cityName) { // returns city by cityname
        if (checkIfCityExists(cityName)) {
            Set<City> set = cache.keySet();
            Iterator<City> i = set.iterator();
            while (i.hasNext()) {
                City res = i.next();
                if (res.getCity().equals(cityName)) {
                    return res;
                }
            }
        }
        return null;
    }

    public void removeByCityName(String cityName) { // removes city by cityname
        City oldCity = returnCityIfCityExists(cityName);
        if (oldCity != null) {
            cache.remove(oldCity);
            log.info("City was removed successfully");
            logs.addLog("City was removed successfully");
        }
    }

    public long returnTimeByCityName(String cityName) { // returns time when the City object was created (by cityname)
        City existingCity = returnCityIfCityExists(cityName);
        if (existingCity != null) {
            return existingCity.getTime();
        }
        return 0;
    }

    public AirQuality getValue(String cityName) { // returns the AirQuality object that is the value of a City object which its cityname is cityName
        City existingCity = returnCityIfCityExists(cityName);
        if (existingCity != null) {
            return cache.get(existingCity);
        }
        return null;
    }

    public int getSize() {
        return cache.size();
    } // size of cache

    public int updateRequests() { // updates the number of requests
        log.info("Number of requests was updated successfully");
        logs.addLog("Number of requests was updated successfully");
        return stats.updateRequests();
    }

    public int updateHits() { // updates the number of hits
        log.info("Number of hits was updated successfully");
        logs.addLog("Number of hits was updated successfully");
        return stats.updateHits();
    }

    public int updateMisses() { // updates the number of misses
        log.info("Number of misses was updated successfully");
        logs.addLog("Number of misses was updated successfully");
        return stats.updateMisses();
    }

    public Map<City, AirQuality> getCache() {
        return cache;
    } // returns the cache itself

    public AirQualityStatistics getCacheStatistics() {
        return stats;
    } // returns the cache statistics

    // Search by coordinates - API //

    public boolean checkIfCityExists(double lat, double lon) { // checks if a City object exists in cache by coordinates
        Set<City> set = cache.keySet();
        Iterator<City> i = set.iterator();
        while (i.hasNext()) {
            City res = i.next();
            if (res.getLatitude() == lat && res.getLongitude() == lon) {
                log.info("City with coordinates " + lat + " and " + lon + " already exists in cache");
                logs.addLog("City with coordinates " + lat + " and " + lon + " already exists in cache");
                return true;
            }
        }
        log.info("City with coordinates " + lat + " and " + lon + " does not exist in cache");
        logs.addLog("City with coordinates " + lat + " and " + lon + " does not exist in cache");
        return false;
    }

    public City returnCityIfCityExists(double lat, double lon) { // returns city by coordinates
        if (checkIfCityExists(lat, lon)) {
            Set<City> set = cache.keySet();
            Iterator<City> i = set.iterator();
            while (i.hasNext()) {
                City res = i.next();
                if (res.getLatitude() == lat && res.getLongitude() == lon) {
                    return res;
                }
            }
        }
        return null;
    }

    public long returnTimeByCoords(double lat, double lon) { // returns time when the City object was created (by coordinates)
        City existingCity = returnCityIfCityExists(lat, lon);
        if (existingCity != null) {
            return existingCity.getTime();
        }
        return 0;
    }

    public void removeByCoords(double lat, double lon) { // removes city by coordinates
        City oldCity = returnCityIfCityExists(lat, lon);
        if (oldCity != null) {
            cache.remove(oldCity);
            log.info("City was removed successfully");
            logs.addLog("City was removed successfully");
        }
    }

    public AirQuality getValue(double lat, double lon) { // returns the AirQuality object that is the value of a City object which its coordinates are lat & lon
        City existingCity = returnCityIfCityExists(lat, lon);
        if (existingCity != null) {
            return cache.get(existingCity);
        }
        return null;
    }

    @Override
    public String toString() {
        return "AirQualityCache{" +
                "cache=" + cache +
                '}';
    }
}