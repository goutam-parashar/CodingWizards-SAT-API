package com.api.sat.repository;

import com.api.sat.model.*;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DatabaseRepository {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";
    static final String USER = "sa";
    static final String PASS = "";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public void getData() throws SQLException {
        String result = "";
        try {
         Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from TEST");
            while (rs.next()){
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Location getBookingDetails(){

        Seat seat1 = new Seat();
        seat1.setNumber(1);
        seat1.setAlloted(false);
        seat1.setBooked(false);
        seat1.setAllotedTo("");
        seat1.setBookedFor("");
        seat1.setBookedBy("");
        seat1.setAllotmentStart(null);
        seat1.setAllotmentEnd(null);
        seat1.setBookingStart(null);
        seat1.setBookingEnd(null);
        List<Seat> seats = new ArrayList<>();
        seats.add(seat1);

        //Rows
        List<Row> rows = new ArrayList<>();
        Row row1 = new Row();
        row1.setNumber(1);
        row1.setSeats(seats);
        rows.add(row1);
        //wings
        Wing wing1 = new Wing();
        wing1.setName("Wing A");
        wing1.setRows(rows);
        List<Wing> wings = new ArrayList<>();
        wings.add(wing1);
        //floors
        Floor floor1 = new Floor();
        floor1.setName("Floor1");
        floor1.setWings(wings);
        List<Floor> floors = new ArrayList<>();
        floors.add(floor1);
        //location
        Location location = new Location();
        location.setCountry("India");
        location.setCity("Pune");
        location.setOffice("EON2");
        location.setFloors(floors);
        return location;
    }

}
