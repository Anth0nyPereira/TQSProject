package ua.deti.tqs.uv_tqs_project.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.deti.tqs.uv_tqs_project.AirQuality;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Component
public class AirQualityCache {
    private Map<City, AirQuality> cache = new HashMap<>();

    @Autowired
    private AirQualityStatistics stats;



    public AirQualityCache() {
    }

    public void add(City key, AirQuality value) {
        cache.put(key, value);
    }

    public boolean checkIfCityExists(String cityName) {
        Set<City> set = cache.keySet();
        Iterator<City> i = set.iterator();
        while (i.hasNext()) {
            City res = i.next();
            if (res.getCity().equals(cityName)) {
                return true;
            }
        }
        return false;
    }

    public City returnCityIfCityExists(String cityName) {
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

    public void removeByCityName(String cityName) {
        City oldCity = returnCityIfCityExists(cityName);
        if (oldCity != null) {
            cache.remove(oldCity);
        }
    }

    public long returnTimeByCityName(String cityName) {
        City existingCity = returnCityIfCityExists(cityName);
        if (existingCity != null) {
            return existingCity.getTime();
        }
        return 0;
    }

    public AirQuality getValue(String cityName) {
        City existingCity = returnCityIfCityExists(cityName);
        if (existingCity != null) {
            return cache.get(existingCity);
        }
        return null;
    }

    public int updateRequests() {
        return stats.updateRequests();
    }

    public int updateHits() {
        return stats.updateHits();
    }

    public int updateMisses() {
        return stats.updateMisses();
    }

    public AirQualityStatistics getCacheStatistics() {
        return stats;
    }

    @Override
    public String toString() {
        return "AirQualityCache{" +
                "cache=" + cache +
                '}';
    }
    // Search by coordinates - API //

    public boolean checkIfCityExists(double lat, double lon) {
        Set<City> set = cache.keySet();
        Iterator<City> i = set.iterator();
        while (i.hasNext()) {
            City res = i.next();
            if (res.getLatitude() == lat && res.getLongitude() == lon) {
                return true;
            }
        }
        return false;
    }

    public City returnCityIfCityExists(double lat, double lon) {
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

    public long returnTimeByCoords(double lat, double lon) {
        City existingCity = returnCityIfCityExists(lat, lon);
        if (existingCity != null) {
            return existingCity.getTime();
        }
        return 0;
    }

    public void removeByCoords(double lat, double lon) {
        City oldCity = returnCityIfCityExists(lat, lon);
        if (oldCity != null) {
            cache.remove(oldCity);
        }
    }

    public AirQuality getValue(double lat, double lon) {
        City existingCity = returnCityIfCityExists(lat, lon);
        if (existingCity != null) {
            return cache.get(existingCity);
        }
        return null;
    }

}