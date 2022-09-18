package com.api.sat.model;

import java.sql.Date;

public class SeatData {

    String seatCode;
    String floorName;
    String floorCode;
    String wingCode;
    String location;
    String seatNum;
    String division;
    String assignedTo;
    String status;
    String n1User;
    Date n1StartDate;
    Date n1EndDate;
    String n2User;
    Date n2StartDate;
    Date n2EndDate;
    String n3User;
    Date n3StartDate;
    Date n3EndDate;

    String n1Status;
    String n2Status;
    String n3Status;

    public Date getN1StartDate() {
        return n1StartDate;
    }

    public void setN1StartDate(Date n1StartDate) {
        this.n1StartDate = n1StartDate;
    }

    public Date getN1EndDate() {
        return n1EndDate;
    }

    public void setN1EndDate(Date n1EndDate) {
        this.n1EndDate = n1EndDate;
    }

    public Date getN2StartDate() {
        return n2StartDate;
    }

    public void setN2StartDate(Date n2StartDate) {
        this.n2StartDate = n2StartDate;
    }

    public Date getN2EndDate() {
        return n2EndDate;
    }

    public void setN2EndDate(Date n2EndDate) {
        this.n2EndDate = n2EndDate;
    }

    public Date getN3StartDate() {
        return n3StartDate;
    }

    public void setN3StartDate(Date n3StartDate) {
        this.n3StartDate = n3StartDate;
    }

    public Date getN3EndDate() {
        return n3EndDate;
    }

    public void setN3EndDate(Date n3EndDate) {
        this.n3EndDate = n3EndDate;
    }

    public String getN1Status() {
        return n1Status;
    }

    public void setN1Status(String n1Status) {
        this.n1Status = n1Status;
    }

    public String getN2Status() {
        return n2Status;
    }

    public void setN2Status(String n2Status) {
        this.n2Status = n2Status;
    }

    public String getN3Status() {
        return n3Status;
    }

    public void setN3Status(String n3Status) {
        this.n3Status = n3Status;
    }


    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getFloorCode() {
        return floorCode;
    }

    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }

    public String getWingCode() {
        return wingCode;
    }

    public void setWingCode(String wingCode) {
        this.wingCode = wingCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getN1User() {
        return n1User;
    }

    public void setN1User(String n1User) {
        this.n1User = n1User;
    }

    public String getN2User() {
        return n2User;
    }

    public void setN2User(String n2User) {
        this.n2User = n2User;
    }

    public String getN3User() {
        return n3User;
    }

    public void setN3User(String n3User) {
        this.n3User = n3User;
    }

}
