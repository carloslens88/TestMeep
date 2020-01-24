package com.example.meep.services;

import com.example.meep.caching.CacheService;
import com.example.meep.entities.MeepResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.util.Lists.newArrayList;

@Service
public class MeepResourceService {
    private static final Logger logger = LoggerFactory.getLogger(MeepResourceService.class);

    @Value("${base.api.url.to.fetch.resources}")
    private String baseApiUrlToFetchResources;

    private RestTemplate restTemplate;
    private CacheService cacheService;

    private List<MeepResource> apiMeepResources;
    private List<MeepResource> cachedMeepResources;
    private List<MeepResource> unavailableMeepResources;
    private List<MeepResource> newAvailableMeepResources;

    public List<MeepResource> getMeepResourcesByZone() {
        restTemplate = new RestTemplate();
        cacheService = new CacheService();
        unavailableMeepResources = newArrayList();
        newAvailableMeepResources = newArrayList();

        apiMeepResources = Arrays.asList(restTemplate.getForObject(baseApiUrlToFetchResources, MeepResource[].class));
        cachedMeepResources = cacheService.getMeepResources();

        if (cacheService.existsMeepResourcesKey() && cacheService.getMeepResources() != null) {
            if (apiMeepResources.equals(cachedMeepResources)) {
                logger.info("There are not changes on the list of Meep resources.");
            } else {
                cachedMeepResources.stream().forEach(cachedMeepResource -> {
                    if (!apiMeepResources.contains(cachedMeepResource)) {
                        unavailableMeepResources.add(cachedMeepResource);
                        logger.info("The resource: " + cachedMeepResource + " isn't longer available.");
                    }
                });

                apiMeepResources.stream().forEach(apiMeepResource -> {
                    if (!cachedMeepResources.contains(apiMeepResource)) {
                        newAvailableMeepResources.add(apiMeepResource);
                        logger.info("The resource: " + apiMeepResource + " is now available for book.");
                    }
                });
            }
        }

        cacheService.addMeepResources(apiMeepResources);
        return apiMeepResources;
    }
}
