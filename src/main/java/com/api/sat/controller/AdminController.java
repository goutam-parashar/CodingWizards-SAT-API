package com.api.sat.controller;

import com.api.sat.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    ManagerService managerService;

    @GetMapping("/ping")
    public String ping(){
        managerService.getData();
        return "Hello From Manager Controller.";
    }

    @GetMapping("/save")
    public String save(){
        managerService.saveData();
        return "Saved data";
    }

}
