package com.api.sat.service;

import com.api.sat.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ManagerService {

    @Autowired
    DatabaseRepository repo;

    public void getData(){
        try {
            repo.getData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveData(){
        repo.saveSeatsData();
    }

    /*public void getData(){
        try {
            repo.getData();
            Location bookingDetails = repo.getBookingDetails();
            ObjectMapper om = new ObjectMapper();
            String jsonString = om.writerWithDefaultPrettyPrinter().writeValueAsString(bookingDetails);
            System.out.println(jsonString);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }*/
}
