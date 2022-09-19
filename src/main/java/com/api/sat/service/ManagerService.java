package com.api.sat.service;

import com.api.sat.model.*;
import com.api.sat.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public int getSubordinateCount(int id){
        int count = 0;
        List<UserDetails> totoal = new ArrayList<>();
        List<UserDetails> subordinateDetails = getSubordinateDetails(id);
        if (!subordinateDetails.isEmpty()){
            String level = subordinateDetails.get(0).getLevel();
            if (level.equals("MID")){
                count = count+subordinateDetails.size();
                List<UserDetails> totoalMid = new ArrayList<>();
                subordinateDetails.forEach(s -> {
                            totoalMid.addAll(getSubordinateDetails(Integer.parseInt(s.getId())));
                        });
                count=count+totoalMid.size();
                for (UserDetails userDetails : totoalMid) {
                    count=count+Integer.parseInt(userDetails.getSubordinateCount());
                }
            }
            if (level.equals("LOW")){
                for (UserDetails subordinateDetail : subordinateDetails) {
                    count = count + Integer.parseInt(subordinateDetail.getSubordinateCount());
                }
            }
        } else {
            UserDetails myDetails = db.getMyDetails(String.valueOf(id));
            if (myDetails!=null){
                count=Integer.parseInt(myDetails.getSubordinateCount());
            }
        }
        return count;
    }

    public List<FloorDto>  getAvailableSeats(String id, String dateSelected){
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
//        String userIdColName = myLevel.equals("HIGH") ? "N1" : myLevel.equals("MID") ? "N2" : "N3";

//        userIdColName = userIdColName + "_USER_ID";

//        String sql = "select * from seat where "+userIdColName +" = " + myDetails.getId();
        String allDataSql= "select * from seat";
        List<SeatData> availableSeats = db.getAvailableSeats(allDataSql);
        availableSeats.forEach(seatData -> setStatus(seatData,id, myLevel,dateSelected));

        List<FloorDto> floorList= new ArrayList<>();
        Map<String, List<SeatData>> sum = availableSeats.stream().collect(
                Collectors.groupingBy(SeatData::getFloorCode));
        sum.entrySet().forEach(entry->{
            FloorDto floorDt= new FloorDto();
            String floor= entry.getKey();
            floorDt.setFloorId(floor);
            List<SeatData> seatList= entry.getValue();

            Map<String, List<SeatData>> wingMap = seatList.stream().collect(
                    Collectors.groupingBy(SeatData::getWingCode));
        List<WingDto> wingList= new ArrayList<>();
            wingMap.entrySet().forEach(wingRec->{
//                String wing= wingRec.getKey();
                WingDto wingDt= new WingDto();
                wingDt.setWingNum(wingRec.getKey());
                List<RowDto> rowList= new LinkedList<>();
                int counter=0;
                for(int i=1; i<=5;i++){

                    RowDto r1= new RowDto();
                    r1.setRowNo(""+i);
                    r1.setSeatList(wingRec.getValue().subList(counter,counter+8));
                    counter= counter+8;
                    rowList.add(r1);
                }

                wingDt.setRowList(rowList);
                wingList.add(wingDt);
            });
            floorDt.setWingList(wingList);
            floorList.add(floorDt);
        });
        return floorList;
    }

    private static void setStatus(SeatData seatData, String id, String myLevel, String dateSelected)  {
        Date n1StartDate = seatData.getN1StartDate();
        Date n1EndDate = seatData.getN1EndDate();
        String userId="";
//        java.util.Date date = new java.util.Date();
        Date date=null;
        try {
             date = new SimpleDateFormat("yyyy-MM-dd").parse(dateSelected);
        }catch(Exception ex){

        }

        if(myLevel.equals("HIGH")){
            userId= seatData.getN1User();
        }else if(myLevel.equals("MID")){
            userId= seatData.getN2User();
        }else{
            userId= seatData.getN3User();
        }
        if (userId!=null && userId.equals(id) && n1StartDate.getTime() < date.getTime() && date.getTime() < n1EndDate.getTime()) {
            seatData.setN1Status("allocated");
            seatData.setStatus("allocated");
        }
        Date n2StartDate = seatData.getN2StartDate();
        Date n2EndDate = seatData.getN2EndDate();
        if (userId!=null && userId.equals(id) && n2StartDate!= null && n2StartDate.getTime() < date.getTime() && date.getTime() < n2EndDate.getTime()) {
            seatData.setN2Status("allocated");
            seatData.setStatus("allocated");
        }
        Date n3StartDate = seatData.getN3StartDate();
        Date n3EndDate = seatData.getN3EndDate();
        if (userId!=null && userId.equals(id) && n3StartDate!= null &&  n3StartDate.getTime() < date.getTime() && date.getTime() < n3EndDate.getTime()) {
            seatData.setN3Status("allocated");
            seatData.setStatus("allocated");
        }
    }

    public void allocateSeats(AllocatedSeatDto data){
        String subordinateId = data.getSubordinateId();
        UserDetails myDetails = db.getMyDetails(subordinateId);
        String myLevel = myDetails.getLevel();
        String user = myLevel.equals("HIGH")?"N1":myLevel.equals("MID")?"N2":"N3";
        String userId = user+"_USER_ID";
        String startDate = user+"_START_DATE";
        String endDate = user+"_END_DATE";
        String nStatus = user+"_STATUS";
        StringBuilder sb = new StringBuilder();
        List<String> queries = new ArrayList<>();
        data.getSeats().forEach(seatData -> {
//            String floorCode = seatData.getFloorCode();
//            String windCode = seatData.getWingCode();
            String seatCode = seatData.getSeatCode();
            //update seat set status = 'alloted' where seat_num = 1 and floor_code = 'L1' and wing_code = 'W1'
            sb.append("update seat set ")
                    .append(userId).append(" = '").append(subordinateId).append("',")
                    .append(startDate).append(" = '").append(data.getStartDate()).append("', ")
                    .append(endDate).append(" = '").append(data.getEndDate()).append("', ")
                    .append(nStatus).append(" = '").append("allocated").append("'")
                    .append(" where ")
                    .append("seat_code = '").append(seatCode).append("'");

                   /* .append("floor_code = '").append(floorCode).append("' and ")
                    .append("wing_code = '").append(windCode).append("' and ")
                    .append("seat_num = '").append(seatNum).append("' and ")*/

            queries.add(sb.toString());
            sb.delete(0, sb.length());
        });
        db.executeUpdateQueries(queries);
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
