package br.com.systemsgs.service;

import br.com.systemsgs.dto.OrderRequestDTO;
import br.com.systemsgs.event.OrderEvent;
import br.com.systemsgs.event.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;

    public void publishOrderEvent(OrderRequestDTO orderRequestDTO, OrderStatus orderStatus){
        OrderEvent orderEvent = new OrderEvent(orderRequestDTO, orderStatus);
        orderSinks.tryEmitNext(orderEvent);
    }

}
