package com.api.sat.model;

import java.util.List;

public class FloorPlanData {
    List<FloorPlan> data;

    public FloorPlanData(List<FloorPlan> list) {
        data = list;
    }

    public FloorPlanData(){}

    public List<FloorPlan> getData() {
        return data;
    }

    public void setData(List<FloorPlan> data) {
        this.data = data;
    }
}
