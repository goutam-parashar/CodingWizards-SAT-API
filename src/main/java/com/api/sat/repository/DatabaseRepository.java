package com.api.sat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Repository
public class DatabaseRepository {

    @Autowired
    DataSource ds;

    public Connection getConnection() {
        Connection conn = null;
        try {
            System.out.println("Connecting to database...");
            conn = ds.getConnection();
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
                System.out.print(rs.getString(1));
                System.out.print(rs.getString(2));
                System.out.println("");
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void saveSeatsData(){
        try {
            Connection con = getConnection();
            if (con != null) {
                Statement statement = con.createStatement();
                List<String> f = new ArrayList<>(Arrays.asList("F1", "F2", "F3", "F4"));
                List<String> w = new ArrayList<>(Arrays.asList("WA", "WB", "WC", "WD"));
                for (String floor : f) {
                    for (String wing : w) {
                        int seats = 0;
                        seats = (Objects.equals(floor, "F1") || Objects.equals(floor, "F2")) ? 160 : 165;
                        for (int i = 0; i < seats; i++) {
                            String name = "" + floor + wing + "S" + i;
                            String sql = "INSERT INTO SEAT VALUES ('S" + i + "','" + name + "', '" + wing + "', '" + floor + "', 'L2');";
                            statement.addBatch(sql);
                        }
                    }
                }
                System.out.println(statement.executeBatch());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


   /* public Location getBookingDetails(){

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
    }*/

}
