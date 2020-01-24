package com.example.meep.caching;

import com.example.meep.entities.MeepResource;
import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;

import java.util.List;

public class CacheService {

    private static final String CACHE_NAME = "meepResourcesCache";
    private static final String CACHE_KEY = "meepResourcesCacheKey";
    private Cache<String, List<MeepResource>> cache;

    public CacheService() {
        cache = new Cache2kBuilder<String, List<MeepResource>>() {}
                .name(CACHE_NAME)
                .eternal(true)
                .entryCapacity(100)
                .build();
    }

    public boolean existsMeepResourcesKey() {
        return cache.containsKey(CACHE_KEY);
    }

    public void addMeepResources(List<MeepResource> meepResources) {
        cache.put(CACHE_KEY, meepResources);
    }

    public List<MeepResource> getMeepResources() {
        return cache.get(CACHE_KEY);
    }
}
