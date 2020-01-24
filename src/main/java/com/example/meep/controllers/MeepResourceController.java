package com.example.meep.controllers;

import com.example.meep.entities.MeepResource;
import com.example.meep.services.MeepResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class MeepResourceController {
    private static final Logger logger = LoggerFactory.getLogger(MeepResourceController.class);

    @Autowired
    MeepResourceService meepResourceService;

    private List<MeepResource> meepResources = null;

    @Scheduled(cron = "*/120 * * * * *")
    public List<MeepResource> getResourcesByZone() {
        try {
            meepResources = meepResourceService.getMeepResourcesByZone();
        } catch (Exception ex) {
            logger.error("There's a problem fetching the resources. Try again latter. ", ex);
        }

        return meepResources;
    }
}
