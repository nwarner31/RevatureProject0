package net.revature.nwarner.project0.models;

import java.util.Objects;

public class Location {

    private int id;
    private String area;
    private String aisle;
    private String section;
    private int productId;
    private int capacity;
    private int currentStock;

    public Location(String area, String aisle, String section, int productId, int capacity) {
        this.area = area;
        this.aisle = aisle;
        this.section = section;
        this.productId = productId;
        this.capacity = capacity;
        this.currentStock = 0;
    }

    public Location(int id, String area, String aisle, String section, int productId, int capacity, int currentStock) {
        this(area, aisle, section, productId, capacity);
        this.id = id;
        this.currentStock = currentStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    @Override
    public String toString() {
        return String.format("%s %s-%s\tCap: %s\tCurrent: %s", area, aisle, section, capacity, currentStock);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return productId == location.productId && capacity == location.capacity && currentStock == location.currentStock && area.equals(location.area) && aisle.equals(location.aisle) && section.equals(location.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(area, aisle, section, productId, capacity, currentStock);
    }
}
