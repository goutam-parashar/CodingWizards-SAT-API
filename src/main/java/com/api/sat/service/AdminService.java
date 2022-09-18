package com.api.sat.service;

import com.api.sat.model.AllocationData;
import com.api.sat.model.FloorPlan;
import com.api.sat.model.FloorPlanData;
import com.api.sat.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    DatabaseRepository db;



    public FloorPlanData getFloorPlan() {
        //TODO hardcoded values needs to be fetched from DB/Source
        Map<String, Map<String, List<String>>> seats = new HashMap<>();
        List<String> floors = new ArrayList<>(Arrays.asList("L1", "L2", "L3", "L4"));
        List<String> wings = new ArrayList<>(Arrays.asList("W1", "W2", "W3", "W4"));
        for (String floor : floors) {
            Map<String, List<String>> floorMap = new HashMap<>();
            for (String wing : wings) {
                floorMap.put(wing, new ArrayList<String>());
            }
            seats.put(floor, floorMap);
        }
        Map<String, String > floorNames = new HashMap<>();
        Map<String, String > wingNames = new HashMap<>();
        db.getFloorPlan(seats, floorNames, wingNames);

        List<FloorPlan> list = new ArrayList<FloorPlan>();
        for (Map.Entry<String, Map<String, List<String>>> floor : seats.entrySet()) {
            for (Map.Entry<String, List<String>> wing : floor.getValue().entrySet()) {
                FloorPlan floorPlan = new FloorPlan();
                floorPlan.setFloorId(floor.getKey());
                floorPlan.setFloorName(floorNames.get(floor.getKey()));
                floorPlan.setWingId(wing.getKey());
                floorPlan.setWingName(wingNames.get(wing.getKey()));
                List<Integer> nums = wing.getValue().stream().map(Integer::parseInt).collect(Collectors.toList());
                floorPlan.setSeatStartNo(Collections.min(nums).toString());
                floorPlan.setSeatEndNo(Collections.max(nums).toString());
                list.add(floorPlan);
            }
        }
        return new FloorPlanData(list);
    }

    public void allocateDivision(FloorPlanData fpd, String division) {
        List<FloorPlan> data = fpd.getData();
        List<String> queries = new ArrayList<>();
        StringBuilder sb =  new StringBuilder();
        for (FloorPlan d : data) {
            int start = Integer.parseInt(d.getSeatStartNo());
            int end = Integer.parseInt(d.getSeatEndNo());
            while (start<= end) {
                sb.append("UPDATE SEAT SET DIVISION = '").append(division)
                        .append("' WHERE SEAT_NUM = ").append(start)
                        .append(" and FLOOR_CODE = '").append(d.getFloorId()).append("';");
                queries.add(sb.toString());
                sb.delete(0, sb.length());
                start++;
            }
        }
        db.executeUpdateQueries(queries);
    }

    public void allocateSeats(FloorPlanData fpd, String toManager) {
        List<FloorPlan> data = fpd.getData();
        List<String> queries = new ArrayList<>();
        StringBuilder sb =  new StringBuilder();
        for (FloorPlan d : data) {
            int start = Integer.parseInt(d.getSeatStartNo());
            int end = Integer.parseInt(d.getSeatEndNo());
            while (start<= end) {
                sb.append("UPDATE SEAT SET DIVISION = '").append(toManager)
                        .append("' WHERE SEAT_NUM = ").append(start).append(";");
                queries.add(sb.toString());
                sb.delete(0, sb.length());
                start++;
            }
        }
        db.executeUpdateQueries(queries);
    }
}
