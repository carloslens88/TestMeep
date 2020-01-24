package com.example.meep.entities;

public class MeepResource {
    private String id;
    private String name;
    private float x;
    private float y;
    private String licencePlate;
    private int range;
    private int batteryLevel;
    private int seats;
    private String model;
    private String resourceImageId;
    private Double pricePerMinuteParking;
    private Double pricePerMinuteDriving;
    private Boolean realTimeData;
    private String engineType;
    private String resourceType;
    private int companyZoneId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResourceImageId() {
        return resourceImageId;
    }

    public void setResourceImageId(String resourceImageId) {
        this.resourceImageId = resourceImageId;
    }

    public Double getPricePerMinuteParking() {
        return pricePerMinuteParking;
    }

    public void setPricePerMinuteParking(Double pricePerMinuteParking) {
        this.pricePerMinuteParking = pricePerMinuteParking;
    }

    public Double getPricePerMinuteDriving() {
        return pricePerMinuteDriving;
    }

    public void setPricePerMinuteDriving(Double pricePerMinuteDriving) {
        this.pricePerMinuteDriving = pricePerMinuteDriving;
    }

    public Boolean getRealTimeData() {
        return realTimeData;
    }

    public void setRealTimeData(Boolean realTimeData) {
        this.realTimeData = realTimeData;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getCompanyZoneId() {
        return companyZoneId;
    }

    public void setCompanyZoneId(int companyZoneId) {
        this.companyZoneId = companyZoneId;
    }
}
