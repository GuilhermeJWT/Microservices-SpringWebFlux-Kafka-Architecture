package br.com.systemsgs.service;

import br.com.systemsgs.dto.OrderRequestDTO;
import br.com.systemsgs.entity.PurchaseOrder;
import br.com.systemsgs.event.OrderStatus;
import br.com.systemsgs.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public PurchaseOrder createOrder(OrderRequestDTO orderRequestDTO) {
        PurchaseOrder order = orderRepository.save(converteDtoToEntity(orderRequestDTO));
        orderRequestDTO.setOrderId(order.getId());
        /* produce kafka event with status ORDER_CREATED*/
        orderStatusPublisher.publishOrderEvent(orderRequestDTO, OrderStatus.ORDER_CREATED);

        return order;
    }

    private PurchaseOrder converteDtoToEntity(OrderRequestDTO orderRequestDTO){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(orderRequestDTO.getProductId());
        purchaseOrder.setUserId(orderRequestDTO.getUserId());
        purchaseOrder.setPrice(orderRequestDTO.getAmount());
        purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);

        return purchaseOrder;
    }

    public List<PurchaseOrder> getAllOrders(){
        return orderRepository.findAll();
    }

}
