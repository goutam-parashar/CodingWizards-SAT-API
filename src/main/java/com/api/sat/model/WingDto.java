package com.api.sat.model;

import java.util.List;

public class WingDto {
    String wingNum;
    String wingId;
    List<RowDto> rowList;

    public String getWingNum() {
        return wingNum;
    }

    public void setWingNum(String wingNum) {
        this.wingNum = wingNum;
    }

    public String getWingId() {
        return wingId;
    }

    public void setWingId(String wingId) {
        this.wingId = wingId;
    }

    public List<RowDto> getRowList() {
        return rowList;
    }

    public void setRowList(List<RowDto> rowList) {
        this.rowList = rowList;
    }
}
