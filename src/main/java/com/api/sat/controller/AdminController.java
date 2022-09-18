package com.api.sat.controller;

import com.api.sat.model.UserDetails;
import com.api.sat.service.ManagerService;
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
    ManagerService managerService;

    @GetMapping(value = "/manager")
    public ResponseEntity<List<UserDetails>> managerDetails(@RequestParam String id){
        List<UserDetails> details = managerService.getManagerDetails(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(details);
    }



    @GetMapping("/createDummyData")
    public String save(){
        managerService.saveData();
        return "Saved data";
    }

}
