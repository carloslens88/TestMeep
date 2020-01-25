package com.example.meep.services;

import com.example.meep.caching.CacheService;
import com.example.meep.entities.MeepResource;
import com.example.meep.entities.MeepResourceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.util.Lists.newArrayList;

@Service
public class MeepResourceService {
    private static final Logger logger = LoggerFactory.getLogger(MeepResourceService.class);

    @Value("${base.api.url}")
    private String baseApiUrlToFetchResources;

    private RestTemplate restTemplate;
    private CacheService cacheService;

    private List<MeepResource> apiMeepResources;
    private List<MeepResource> cachedMeepResources;
    private List<MeepResource> unavailableMeepResources;
    private List<MeepResource> newAvailableMeepResources;

    public MeepResourceService() {
        //NO ES NADA ELEGANTE, PERO POR MOMENTOS, DE FORMA ALEATORIA NO RECUPERA EL VALOR DEL APPLICATION PROPERTIES FILE!
        if (baseApiUrlToFetchResources == null) {
            baseApiUrlToFetchResources = "https://apidev.meep.me/tripplan/api/v1/routers/lisboa/resources?lowerLeftLatLon=38.711046,-9.160096&upperRightLatLon=38.739429,-9.137115&companyZoneIds=545,467,473";
        }
    }

    public MeepResourceStatus getMeepResourcesByZone() {
        MeepResourceStatus meepResourceStatusResponse = new MeepResourceStatus();
        restTemplate = new RestTemplate();
        cacheService = new CacheService();
        unavailableMeepResources = newArrayList();
        newAvailableMeepResources = newArrayList();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

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
        meepResourceStatusResponse.setApiMeepResourcesResponse(apiMeepResources);
        meepResourceStatusResponse.setNewAvailableMeepResources(newAvailableMeepResources);
        meepResourceStatusResponse.setUnavailableMeepResources(unavailableMeepResources);
        return meepResourceStatusResponse;
    }
}
