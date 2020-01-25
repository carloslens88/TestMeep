package com.example.meep.services;

import com.example.meep.caching.CacheService;
import com.example.meep.entities.MeepResource;
import com.example.meep.entities.MeepResourceStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MeepResourceServiceTest {
    private static final String API_BASE_URL = "https://apidev.meep.me/tripplan/api/v1/routers/lisboa/resources?lowerLeftLatLon=38.711046,-9.160096&upperRightLatLon=38.739429,-9.137115&companyZoneIds=545,467,473";

    @InjectMocks
    MeepResourceService meepResourceService;

    @Mock
    RestTemplate restTemplate;
    @Mock
    CacheService cacheService;

    private List<MeepResource> apiMeepResources;
    private List<MeepResource> cachedMeepResources;

    @Before
    public void setUp() {
        apiMeepResources = newArrayList();
        cachedMeepResources = newArrayList();
    }

    @Test
    public void shouldBeAbleToGetResourcesByZone() {
        when(restTemplate.getForObject(any(), any())).thenReturn(apiMeepResources);
        when(cacheService.getMeepResources()).thenReturn(cachedMeepResources);
        doNothing().when(cacheService).addMeepResources(anyList());

        MeepResourceStatus meepResourceStatusResponse = meepResourceService.getMeepResourcesByZone();

        assertNotNull(meepResourceStatusResponse);
        //Para dar mayor sentido a la prueba unitario podríamos incluir tantos verifys como sean necesarios. Así garantizamos que se ejecutaron las llamadas que esperamos.
    }
}
