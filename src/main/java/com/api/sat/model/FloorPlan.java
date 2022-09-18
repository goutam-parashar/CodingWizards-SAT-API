package com.api.sat.model;

public class FloorPlan {

    String floorId;
    String floorName;
    String wingId;
    String wingName;
    String seatStartNo;
    String seatEndNo;

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getWingId() {
        return wingId;
    }

    public void setWingId(String wingId) {
        this.wingId = wingId;
    }

    public String getWingName() {
        return wingName;
    }

    public void setWingName(String wingName) {
        this.wingName = wingName;
    }

    public String getSeatStartNo() {
        return seatStartNo;
    }

    public void setSeatStartNo(String seatStartNo) {
        this.seatStartNo = seatStartNo;
    }

    public String getSeatEndNo() {
        return seatEndNo;
    }

    public void setSeatEndNo(String seatEndNo) {
        this.seatEndNo = seatEndNo;
    }
}
