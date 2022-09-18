package com.api.sat.controller;

import com.api.sat.model.AllocationData;
import com.api.sat.model.FloorPlan;
import com.api.sat.model.FloorPlanData;
import com.api.sat.service.AdminService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping(value = "/floorplan")
    @CrossOrigin(origins = "*")
    public ResponseEntity<FloorPlanData> floorPlan(){
        FloorPlanData details = adminService.getFloorPlan();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(details);
    }

    @PostMapping(value = "allocate/division")
    @CrossOrigin(origins = "*")
    public void allocateDivision(@RequestBody() String allocationData, @RequestParam("division") String division){
        Gson gson= new Gson();
        FloorPlanData data=gson.fromJson(allocationData,FloorPlanData.class);
        adminService.allocateDivision(data, division);
    }

    @PostMapping(value = "allocate/seat")
    @CrossOrigin(origins = "*")
    public void allocateSeats(@RequestBody() String allocationData, @RequestParam("to") String division){
        Gson gson= new Gson();
        FloorPlanData data=gson.fromJson(allocationData,FloorPlanData.class);
        adminService.allocateSeats(data, division);
    }



}
