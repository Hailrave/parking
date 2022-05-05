package com.anisimov.parking.service;

import com.anisimov.parking.entity.Client;
import com.anisimov.parking.entity.Order;
import com.anisimov.parking.entity.ParkingPlace;
import com.anisimov.parking.exception.ClientNotFoundException;
import com.anisimov.parking.exception.PlaceNotFoundException;
import com.anisimov.parking.pojo.OrderRequest;
import com.anisimov.parking.repository.ClientRepository;
import com.anisimov.parking.repository.OrderRepository;
import com.anisimov.parking.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ClientRepository clientRepository;
    private PlaceRepository placeRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, PlaceRepository placeRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.placeRepository = placeRepository;
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).get();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Integer saveOrder(Order order) {
        return orderRepository.save(order).getOrderId();
    }

    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    public Integer saveOrderWithData(OrderRequest orderRequest) throws ClientNotFoundException, PlaceNotFoundException {
        Client client = clientRepository.findById(orderRequest.getClient_id()).orElseThrow(ClientNotFoundException::new);
        ParkingPlace parkingPlace = placeRepository.findById(orderRequest.getPlace_id()).orElseThrow(PlaceNotFoundException::new);

        if(isTimeBusy(orderRequest.getStartTime(), orderRequest.getEndTime(), parkingPlace.getOrders()))
            throw new IllegalArgumentException("This time is busy"); //Unchecked

        Order order = new Order();
        order.setStartTime(orderRequest.getStartTime());
        order.setEndTime(orderRequest.getEndTime());
        order.setPrice(orderRequest.getPrice());
        order.setClient(client);
        order.setParkingPlace(parkingPlace);

        return orderRepository.save(order).getOrderId();
    }

    private boolean isTimeBusy(Timestamp startTime, Timestamp endTime, List<Order> orders) {
        for(Order order : orders) {
            Timestamp otherStartTime = order.getStartTime();
            Timestamp otherEndTime = order.getEndTime();
            if (startTime.before(otherStartTime)) {
                if(endTime.after(otherStartTime) && endTime.before(otherEndTime))
                    return true;
                if(endTime.after(otherEndTime))
                    return true;
            }
            else {
                if(otherEndTime.after(startTime) && otherEndTime.before(endTime))
                    return true;
                if(otherEndTime.after(endTime))
                    return true;
            }
        }
        return false;
    }
}
