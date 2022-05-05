package com.anisimov.parking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "parking_places")
public class ParkingPlace {
    @Id
    @Column(name = "place_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer placeId;

    @Column(nullable = false)
    private Integer square;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parkingPlace", cascade = CascadeType.ALL)
    private List<Order> orders;
}
