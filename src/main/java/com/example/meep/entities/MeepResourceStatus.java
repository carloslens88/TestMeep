package com.example.meep.entities;

import java.util.List;

public class MeepResourceStatus {
    private List<MeepResource> apiMeepResourcesResponse;
    private List<MeepResource> unavailableMeepResources;
    private List<MeepResource> newAvailableMeepResources;

    public List<MeepResource> getApiMeepResourcesResponse() {
        return apiMeepResourcesResponse;
    }

    public void setApiMeepResourcesResponse(List<MeepResource> apiMeepResourcesResponse) {
        this.apiMeepResourcesResponse = apiMeepResourcesResponse;
    }

    public List<MeepResource> getUnavailableMeepResources() {
        return unavailableMeepResources;
    }

    public void setUnavailableMeepResources(List<MeepResource> unavailableMeepResources) {
        this.unavailableMeepResources = unavailableMeepResources;
    }

    public List<MeepResource> getNewAvailableMeepResources() {
        return newAvailableMeepResources;
    }

    public void setNewAvailableMeepResources(List<MeepResource> newAvailableMeepResources) {
        this.newAvailableMeepResources = newAvailableMeepResources;
    }
}
