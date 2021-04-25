package ua.deti.tqs.uv_tqs_project.entity;

import ua.deti.tqs.uv_tqs_project.UVValue;

import java.util.HashMap;
import java.util.Map;

public class UVCache {
    private Map<Integer, UVValue> cache = new HashMap<>();

    public UVCache(Map<Integer, UVValue> cache) {
        this.cache = cache;
    }

    public void add(UVValue value) {
        cache.put(1, value);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}
