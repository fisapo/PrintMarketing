package model;

public class Location {

    int locationId;
    String locationName;
    int distributionCapacity;

    public int getDistributionCapacity() {
        return distributionCapacity;
    }

    public void setDistributionCapacity(int distributionCapacity) {
        this.distributionCapacity = distributionCapacity;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Location(int id, String locationName, int distributionCapacity) {
        this.locationId = id;
        this.locationName = locationName;
        this.distributionCapacity = distributionCapacity;

    }

    public Location() {

    }

    public Location(int id) {
        this.locationId = id;
    }

}
