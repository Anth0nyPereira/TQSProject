package ua.deti.tqs.uv_tqs_project.entity;

import ua.deti.tqs.uv_tqs_project.AirQuality;

import java.util.HashMap;
import java.util.Map;

public class AirQualityCache {
    private Map<Integer, AirQuality> cache = new HashMap<>();

    public AirQualityCache(Map<Integer, AirQuality> cache) {
        this.cache = cache;
    }

    public void add(AirQuality value) {
        cache.put(1, value);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}
