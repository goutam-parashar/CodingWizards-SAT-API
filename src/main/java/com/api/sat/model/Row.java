package com.api.sat.model;

import lombok.Data;

import java.util.List;

@Data
public class Row {
    int number;
    List<Seat> seats;
}
