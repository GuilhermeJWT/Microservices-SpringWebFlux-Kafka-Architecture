package br.com.systemsgs.service;

import br.com.systemsgs.event.OrderEvent;
import br.com.systemsgs.event.PaymentEvent;
import br.com.systemsgs.repository.UserBalanceRepository;
import br.com.systemsgs.repository.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PaymentService {

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @PostConstruct
    public void initUserBalanceInDb(){
        userBalanceRepository.saveAll();
    }

    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {

    }

    public PaymentEvent cancelOrderEvent(OrderEvent orderEvent) {
    }
}
