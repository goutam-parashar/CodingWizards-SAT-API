package com.api.sat.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Seat {
    int number;
    boolean alloted;
    boolean booked;
    String allotedTo;
    String bookedFor;
    String bookedBy;
    Date allotmentStart;
    Date allotmentEnd;
    Date bookingStart;
    Date bookingEnd;
}
