package br.com.systemsgs.service;

import br.com.systemsgs.dto.OrderRequestDTO;
import br.com.systemsgs.entity.PurchaseOrder;
import br.com.systemsgs.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /*
    @Transactional
    public PurchaseOrder createOrder(OrderRequestDTO orderRequestDTO) {
        orderRepository.save(PurchaseOrder)
    }

     */



}
