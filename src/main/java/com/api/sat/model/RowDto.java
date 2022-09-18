package com.api.sat.model;

import java.util.List;

public class RowDto {
    String rowNo;
    List<SeatData> seatList;

    public String getRowNo() {
        return rowNo;
    }

    public void setRowNo(String rowNo) {
        this.rowNo = rowNo;
    }

    public List<SeatData> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<SeatData> seatList) {
        this.seatList = seatList;
    }
}
