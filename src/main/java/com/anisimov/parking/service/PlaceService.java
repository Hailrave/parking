package com.anisimov.parking.service;

import com.anisimov.parking.entity.ParkingPlace;
import com.anisimov.parking.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public ParkingPlace getPlaceById(Integer id) {
        return placeRepository.findById(id).get();
    }

    public List<ParkingPlace> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Integer savePlace(ParkingPlace parkingPlace) {
        return placeRepository.save(parkingPlace).getPlaceId();
    }

    public void deleteById(Integer id) {
        placeRepository.deleteById(id);
    }
}
