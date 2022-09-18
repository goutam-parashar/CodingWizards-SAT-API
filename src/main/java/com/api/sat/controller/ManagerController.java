package com.api.sat.controller;

import com.api.sat.model.*;
import com.api.sat.service.ManagerService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @GetMapping(value = "/subordinates")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<UserDetails>> getSubordinateDetails(@RequestParam String id){
        List<UserDetails> details = managerService.getSubordinateDetails(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(details);
    }

    @GetMapping(value = "/subordinates/count")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Integer> getSubordinateCount(@RequestParam String id){
        int count = managerService.getSubordinateCount(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(Integer.valueOf(count));
    }


    @GetMapping(value = "/myAvailableSeats")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<FloorDto>> getAvailableSeats(@RequestParam String id, @RequestParam String dateSelected){
        List<FloorDto>  details = managerService.getAvailableSeats(id,dateSelected);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(details);
    }


    @PostMapping(value = "manager/allocate/seat")
    @CrossOrigin(origins = "*")
    public void allocateSeats(@RequestBody() AllocatedSeatDto allocationData){
//        Gson gson= new Gson();
//        FloorPlanData data=gson.fromJson(allocationData,FloorPlanData.class);

        managerService.allocateSeats(allocationData);
    }


/*
    @GetMapping("/createDummyData")
    public String save(){
        managerService.saveData();
        return "Saved data";
    }*/

}
