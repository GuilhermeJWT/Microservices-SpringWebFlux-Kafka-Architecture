package br.com.systemsgs.config;

import br.com.systemsgs.event.OrderEvent;
import br.com.systemsgs.event.OrderStatus;
import br.com.systemsgs.event.PaymentEvent;
import br.com.systemsgs.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConsumerConfig {

    @Autowired
    private PaymentService paymentService;

    @Bean
    public Function<Flux<OrderEvent>,Flux<PaymentEvent>> paymentProcessor(){
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent){
        if(OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            
        }
    }

}