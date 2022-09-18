package com.api.sat.service;

import com.api.sat.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ManagerService {

    @Autowired
    DatabaseRepository db;

    public void getData(){
        try {
            db.getData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveData(){
        db.saveSeatsData();
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
