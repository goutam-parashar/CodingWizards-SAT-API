package com.api.sat.service;

import com.api.sat.model.SeatData;
import com.api.sat.model.UserDetails;
import com.api.sat.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Service
public class ManagerService {

    @Autowired
    DatabaseRepository db;

    public List<UserDetails> getSubordinateDetails(int id){
        try {
            return db.getSubordinateDetails(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SeatData> getAvailableSeats(int id){
        /*List<String> ids = new ArrayList<>();
        try {
            List<UserDetails> subordinateDetails = db.getSubordinateDetails(id);
            for (UserDetails s : subordinateDetails) {
//                if (s.getLevel().equals("LOW"))
                ids.add(s.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        UserDetails myDetails = db.getMyDetails(id);
        String myLevel = myDetails.getLevel();
        String userIdColName = myLevel.equals("HIGH") ? "N1" : myLevel.equals("MID") ? "N2" : "N3";
        userIdColName = userIdColName + "_USER_ID";

        String sql = "select * from seat where "+userIdColName +" = " + myDetails.getId();
        List<SeatData> availableSeats = db.getAvailableSeats(sql);
        availableSeats.forEach(ManagerService::setStatus);
        return availableSeats;
    }

    private static void setStatus(SeatData seatData) {
        Date n1StartDate = seatData.getN1StartDate();
        Date n1EndDate = seatData.getN1EndDate();
        java.util.Date date = new java.util.Date();
        if (n1StartDate.getTime() < date.getTime() && date.getTime() < n1EndDate.getTime()) {
            seatData.setN1Status("allocated");
        }
        Date n2StartDate = seatData.getN2StartDate();
        Date n2EndDate = seatData.getN2EndDate();
        if (n2StartDate.getTime() < date.getTime() && date.getTime() < n2EndDate.getTime()) {
            seatData.setN3Status("allocated");
        }
        Date n3StartDate = seatData.getN3StartDate();
        Date n3EndDate = seatData.getN3EndDate();
        if (n3StartDate.getTime() < date.getTime() && date.getTime() < n3EndDate.getTime()) {
            seatData.setN3Status("allocated");
        }
    }


  /*  public void saveData(){
        db.saveSeatsData();
    }*/

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
