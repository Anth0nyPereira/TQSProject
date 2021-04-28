package ua.deti.tqs.uv_tqs_project.entity;

import ua.deti.tqs.uv_tqs_project.AirQuality;

import java.util.HashMap;
import java.util.Map;

public class AirQualityCache {
    private Map<String, AirQuality> cache;

    public AirQualityCache(Map<String, AirQuality> cache) {
        this.cache = new HashMap<>();
    }

    public void add(String key, AirQuality value) {
        cache.put(key, value);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}
