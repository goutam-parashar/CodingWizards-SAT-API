package com.api.sat.controller;

import com.api.sat.model.FloorDto;
import com.api.sat.model.SeatData;
import com.api.sat.model.UserDetails;
import com.api.sat.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/myAvailableSeats")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<FloorDto>> getAvailableSeats(@RequestParam String id){
        List<FloorDto>  details = managerService.getAvailableSeats(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(details);
    }



/*
    @GetMapping("/createDummyData")
    public String save(){
        managerService.saveData();
        return "Saved data";
    }*/

}
