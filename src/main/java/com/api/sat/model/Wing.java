package com.api.sat.model;

import lombok.Data;

import java.util.List;

@Data
public class Wing {
    String name;
    List<Row> rows;
}
