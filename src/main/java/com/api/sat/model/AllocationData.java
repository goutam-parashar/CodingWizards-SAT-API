package com.api.sat.model;

//TODO use spring jpa; eliminate getters setters
public class AllocationData {
    String division;
    FloorPlanData data;

    public FloorPlanData getData() {
        return data;
    }

    public void setData(FloorPlanData data) {
        this.data = data;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
}
