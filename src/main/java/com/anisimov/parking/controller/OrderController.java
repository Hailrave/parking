package com.anisimov.parking.controller;

import com.anisimov.parking.entity.Order;
import com.anisimov.parking.exception.ClientNotFoundException;
import com.anisimov.parking.exception.PlaceNotFoundException;
import com.anisimov.parking.pojo.OrderRequest;
import com.anisimov.parking.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/save")
    public String saveOrder(@RequestBody Order order) {
        Integer id = orderService.saveOrder(order);
        return "Order with " + id + " has been saved";
    }

    @PostMapping("/save-with-data")
    public String saveOrderWithData(@RequestBody OrderRequest orderRequest) {
        try {
            Integer id = orderService.saveOrderWithData(orderRequest);
            return "Order with " + id + " has been saved";
        } catch (ClientNotFoundException e) {
            return "Client with this id was not found";
        } catch (PlaceNotFoundException e) {
            return "Place with this id was not found";
        }
    }

    @GetMapping("/get-all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/get/{id}")
    public Order getOrderById(@PathVariable("id") Integer id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/update/{id}")
    public String updateOrder(@PathVariable("id") Integer id,
                                              @RequestBody Order order) {
        Order orderFromDb = orderService.getOrderById(id);
        orderFromDb.setPrice(order.getPrice());
        orderFromDb.setStartTime(order.getStartTime());
        orderFromDb.setEndTime(order.getEndTime());
        orderFromDb.setClient(order.getClient());
        orderFromDb.setParkingPlace(order.getParkingPlace());
        Integer ide = orderService.saveOrder(orderFromDb);

        return "Order with " + ide + " has been updated";
    }

    @DeleteMapping("delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id) {
        orderService.deleteById(id);
        return "Order with " + id + " has been deleted";
    }
}
