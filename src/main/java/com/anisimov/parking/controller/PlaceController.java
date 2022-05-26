package com.anisimov.parking.controller;

import com.anisimov.parking.entity.Order;
import com.anisimov.parking.entity.ParkingPlace;
import com.anisimov.parking.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping("/save")
    public String savePlace(@RequestBody ParkingPlace parkingPlace) {
        Integer id = placeService.savePlace(parkingPlace);
        return "Place with " + id + " has been saved";
    }

    @GetMapping("/get-all")
    public List<ParkingPlace> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @GetMapping("/get/{id}")
    public ParkingPlace getPlaceById(@PathVariable("id") Integer id) {
        return placeService.getPlaceById(id);
    }

    @GetMapping("/get-orders/{id}")
    public List<Order> getOrders(@PathVariable("id") Integer id) {
        ParkingPlace parkingPlace = placeService.getPlaceById(id);
        return parkingPlace.getOrders();
    }

    @PutMapping("/update/{id}")
    public String updatePlace(@PathVariable("id") Integer id,
                                               @RequestBody ParkingPlace parkingPlace) {
        ParkingPlace ppFrDb = placeService.getPlaceById(id);
        ppFrDb.setSquare(parkingPlace.getSquare());
        Integer ide = placeService.savePlace(ppFrDb);
        return "Place with " + ide + " has been updated";
    }

    @DeleteMapping("delete/{id}")
    public String deletePlace(@PathVariable("id") Integer id) {
        placeService.deleteById(id);
        return "Place with " + id + " has been deleted";
    }
}
