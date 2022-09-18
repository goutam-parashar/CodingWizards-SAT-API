package com.api.sat.model;

import java.sql.Date;
import java.util.List;

public class AllocatedSeatDto {
    String floorId;
    String subordinateId;
    String startDate;
    String endDate;
    List<SeatData> seats;

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getSubordinateId() {
        return subordinateId;
    }

    public void setSubordinateId(String subordinateId) {
        this.subordinateId = subordinateId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<SeatData> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatData> seats) {
        this.seats = seats;
    }



}
