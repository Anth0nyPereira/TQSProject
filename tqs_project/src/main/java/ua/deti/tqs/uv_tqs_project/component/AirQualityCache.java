package ua.deti.tqs.uv_tqs_project.component;

import org.springframework.stereotype.Component;
import ua.deti.tqs.uv_tqs_project.AirQuality;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Component
public class AirQualityCache {
    private Map<City, AirQuality> cache = new HashMap<>();

    public AirQualityCache() {

    }

    public void add(City key, AirQuality value) {
        cache.put(key, value);
    }

    public boolean containsKey(City key) {
        return cache.containsKey(key);
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

    @Override
    public String toString() {
        return "AirQualityCache{" +
                "cache=" + cache +
                '}';
    }
}
