package com.anisimov.parking.controller;

import com.anisimov.parking.entity.Order;
import com.anisimov.parking.entity.ParkingPlace;
import com.anisimov.parking.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/parkingPlaces")
public class PlaceController {
    private PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> savePlace(@RequestBody ParkingPlace parkingPlace) {
        Integer id = placeService.savePlace(parkingPlace);
        return new ResponseEntity<String>("Place with " + id + " has been saved", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ParkingPlace>> getAllPlaces() {
        List<ParkingPlace> places = placeService.getAllPlaces();
        return new ResponseEntity<List<ParkingPlace>>(places, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ParkingPlace> getPlaceById(@PathVariable("id") Integer id) {
        ParkingPlace parkingPlace = placeService.getPlaceById(id);
        return new ResponseEntity<ParkingPlace>(parkingPlace, HttpStatus.OK);
    }

    @GetMapping("/getOrders/{id}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable("id") Integer id) {
        ParkingPlace parkingPlace = placeService.getPlaceById(id);
        List<Order> orderList = parkingPlace.getOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePlace(@PathVariable("id") Integer id,
                                               @RequestBody ParkingPlace parkingPlace) {
        ParkingPlace ppFrDb = placeService.getPlaceById(id);
        ppFrDb.setSquare(parkingPlace.getSquare());
        Integer ide = placeService.savePlace(ppFrDb);
        return new ResponseEntity<String>("Place with " + ide + " has been updated", HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePlace(@PathVariable("id") Integer id) {
        placeService.deleteById(id);
        return new ResponseEntity<String>("Place with " + id + " has been deleted", HttpStatus.OK);
    }
}
