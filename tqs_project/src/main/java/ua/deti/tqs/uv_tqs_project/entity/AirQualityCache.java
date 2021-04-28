package ua.deti.tqs.uv_tqs_project.entity;

import org.springframework.stereotype.Component;
import ua.deti.tqs.uv_tqs_project.AirQuality;

import java.util.HashMap;
import java.util.Map;

@Component
public class AirQualityCache {
    private Map<String, AirQuality> cache = new HashMap<>();

    public AirQualityCache() {

    }

    public void add(String key, AirQuality value) {
        cache.put(key, value);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    public AirQuality getValue(String key) {
        return cache.get(key);
    }

    @Override
    public String toString() {
        return "AirQualityCache{" +
                "cache=" + cache +
                '}';
    }
}
