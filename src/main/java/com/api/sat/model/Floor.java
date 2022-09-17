package com.api.sat.model;

import lombok.Data;

import java.util.List;

@Data
public class Floor {
    String name;
    List<Wing> wings;
}
