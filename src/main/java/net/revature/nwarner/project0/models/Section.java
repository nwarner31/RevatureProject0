package net.revature.nwarner.project0.models;

import net.revature.nwarner.project0.collections.MyArrayList;


public class Section {

    private String area;
    private String aisle;
    private String section;
    private String type;
    private MyArrayList<Location> modular;

    public Section(String area, String aisle, String section, String type) {
        this.area = area;
        this.aisle = aisle;
        this.section = section;
        this.type = type;
        this.modular = new MyArrayList<>();
    }

    public Section(String area, String aisle, String section, String type, MyArrayList<Location> modular) {
        this(area, aisle, section, type);
        this.modular = modular;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s", area, aisle, section, type);
    }
}
