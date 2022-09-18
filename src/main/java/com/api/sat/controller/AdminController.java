package com.api.sat.controller;

import com.api.sat.model.FloorPlan;
import com.api.sat.model.FloorPlanData;
import com.api.sat.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping(value = "/floorplan")
    public ResponseEntity<FloorPlanData> floorPlan(){
        FloorPlanData details = adminService.getFloorPlan();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(details);
    }

}
