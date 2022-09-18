package com.api.sat.model;

public class UserDetails {
    String id;

    public String getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getDivision() {
        return division;
    }

    public String getManagerId() {
        return managerId;
    }

    public String getSubordinateCount() {
        return subordinateCount;
    }

    public String getLevel() {
        return level;
    }

    String fName;
    String lName;
    String division;
    String managerId;
    String subordinateCount;
    String level;

    public void setId(String id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public void setSubordinateCount(String subordinateCount) {
        this.subordinateCount = subordinateCount;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
