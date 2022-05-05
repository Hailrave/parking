package com.anisimov.parking.controller;

import com.anisimov.parking.entity.Order;
import com.anisimov.parking.exception.ClientNotFoundException;
import com.anisimov.parking.exception.PlaceNotFoundException;
import com.anisimov.parking.pojo.OrderRequest;
import com.anisimov.parking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOrder(@RequestBody Order order) {
        Integer id = orderService.saveOrder(order);
        return new ResponseEntity<String>("Order with " + id + " has been saved", HttpStatus.OK);
    }

    @PostMapping("/saveWithData")
    public ResponseEntity<String> saveOrderWithData(@RequestBody OrderRequest orderRequest) {
        try {
            Integer id = orderService.saveOrderWithData(orderRequest);
            return new ResponseEntity<>("Order with " + id + " has been saved", HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            return new ResponseEntity<>("Client with this id was not found", HttpStatus.NOT_FOUND);
        } catch (PlaceNotFoundException e) {
            return new ResponseEntity<>("Place with this id was not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable("id") Integer id,
                                              @RequestBody Order order) {
        Order orderFromDb = orderService.getOrderById(id);
        orderFromDb.setPrice(order.getPrice());
        orderFromDb.setStartTime(order.getStartTime());
        orderFromDb.setEndTime(order.getEndTime());
        orderFromDb.setClient(order.getClient());
        orderFromDb.setParkingPlace(order.getParkingPlace());
        Integer ide = orderService.saveOrder(orderFromDb);

        return new ResponseEntity<String>("Order with " + ide + " has been updated", HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Integer id) {
        orderService.deleteById(id);
        return new ResponseEntity<String>("Order with " + id + " has been deleted", HttpStatus.OK);
    }
}
