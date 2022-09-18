package com.api.sat.repository;

import com.api.sat.model.SeatData;
import com.api.sat.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;


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

    public List<UserDetails> getSubordinateDetails(int id) throws SQLException {
        List<UserDetails> result = new ArrayList<>();
        try {
         Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String sql = "select * from userdetails where managerid = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                UserDetails userDetails = new UserDetails();
                userDetails.setId(rs.getString(1));
                userDetails.setfName(rs.getString(2));
                userDetails.setlName(rs.getString(3));
                userDetails.setDivision(rs.getString(4));
                userDetails.setManagerId(rs.getString(5));
                userDetails.setSubordinateCount(rs.getString(6));
                userDetails.setLevel(rs.getString(7));
                result.add(userDetails);
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
/*
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
                System.out.println(Arrays.toString(statement.executeBatch()));
                statement.close();
            }
            assert con != null;
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }*/

    public void getFloorPlan(Map<String, Map<String, List<String>>> seats, Map<String, String> floorNames, Map<String, String> wingNames) {
        String sql = "select floor_code, floor_name,  wing_code, wing_code, seat_num from seat group by floor_code, wing_code, seat_num order by floor_code, wing_code asc;";
        Connection connection = getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String floorId = resultSet.getString(1);//floor code
                    String wingId = resultSet.getString(3); //wing code
                    String seatNum = resultSet.getString(5); //seat num
                    seats.get(floorId).get(wingId).add(seatNum);
                    floorNames.put(floorId,resultSet.getString(2)); //floor name
                    wingNames.put(wingId, resultSet.getString(4)); //wing name
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void executeUpdateQueries(List<String> queries) {
        Connection connection = getConnection();
        String sql = "update seat set division = 'A' where seat_num = 1";
        if (connection != null){
            try {
                Statement statement = connection.createStatement();
                for (String query : queries) {
                    statement.addBatch(query);
                }
                int[] ints = statement.executeBatch();
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public List<SeatData> getAvailableSeats(String sql) {
        Connection connection = getConnection();
        List<SeatData> list = new ArrayList<>();
        if (connection != null){
            try {
                ResultSet rs = connection.createStatement().executeQuery(sql);
                while (rs.next()) {
                    SeatData seatData = new SeatData();
                    seatData.setSeatCode(rs.getString(1));
                    seatData.setFloorName(rs.getString(2));
                    seatData.setFloorCode(rs.getString(3));
                    seatData.setWingCode(rs.getString(4));
                    seatData.setLocation(rs.getString(5));
                    seatData.setSeatNum(rs.getString(6));
                    seatData.setDivision(rs.getString(7));
                    seatData.setAssignedTo(rs.getString(8));
                    seatData.setStatus(rs.getString(9));
                    seatData.setN1User(rs.getString(10));
                    seatData.setN1StartDate(rs.getDate(11));
                    seatData.setN1EndDate(rs.getDate(12));
                    seatData.setN2User(rs.getString(13));
                    seatData.setN2StartDate(rs.getDate(14));
                    seatData.setN2EndDate(rs.getDate(15));
                    seatData.setN3User(rs.getString(16));
                    seatData.setN3StartDate(rs.getDate(17));
                    seatData.setN3EndDate(rs.getDate(18));
                    seatData.setN1Status(rs.getString(19));
                    seatData.setN2Status(rs.getString(20));
                    seatData.setN3Status(rs.getString(21));
                    list.add(seatData);
                }
                connection.close();
                return list;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return list;
    }

    public UserDetails getMyDetails(String id) {
        Connection connection = getConnection();
        String sql = "select * from userdetails where id = " + Integer.parseInt(id);
        if (connection != null){
            try {
                ResultSet rs = connection.createStatement().executeQuery(sql);
                while (rs.next()){
                    UserDetails userDetails = new UserDetails();
                    userDetails.setId(rs.getString(1));
                    userDetails.setfName(rs.getString(2));
                    userDetails.setlName(rs.getString(3));
                    userDetails.setDivision(rs.getString(4));
                    userDetails.setManagerId(rs.getString(5));
                    userDetails.setSubordinateCount(rs.getString(6));
                    userDetails.setLevel(rs.getString(7));

                    connection.close();
                    return userDetails;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
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
