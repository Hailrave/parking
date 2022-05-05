package com.anisimov.parking.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderRequest {

    private Integer id;
    private Integer price;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer client_id;
    private Integer place_id;

}
