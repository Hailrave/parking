package com.anisimov.parking.repository;

import com.anisimov.parking.entity.ParkingPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<ParkingPlace, Integer> {
}
