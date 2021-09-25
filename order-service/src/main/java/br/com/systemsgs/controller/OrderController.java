package br.com.systemsgs.controller;

import br.com.systemsgs.dto.OrderRequestDTO;
import br.com.systemsgs.entity.PurchaseOrder;
import br.com.systemsgs.service.OrderService;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return orderService.createOrder(orderRequestDTO);
    }

    @GetMapping(value = "/")
    public List<PurchaseOrder> getOrders(){
        return orderService.getAllOrders();
    }

}
