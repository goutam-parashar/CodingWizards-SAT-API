package com.api.sat.model;

import java.util.List;

public class FloorDto {

    String floorName;
    String floorId;
    List<WingDto> wingList;

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public List<WingDto> getWingList() {
        return wingList;
    }

    public void setWingList(List<WingDto> wingList) {
        this.wingList = wingList;
    }
}
