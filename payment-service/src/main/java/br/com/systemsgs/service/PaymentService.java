package br.com.systemsgs.service;

import br.com.systemsgs.dto.OrderRequestDTO;
import br.com.systemsgs.dto.PaymentRequestDTO;
import br.com.systemsgs.entity.UserBalance;
import br.com.systemsgs.entity.UserTransaction;
import br.com.systemsgs.event.OrderEvent;
import br.com.systemsgs.event.PaymentEvent;
import br.com.systemsgs.event.PaymentStatus;
import br.com.systemsgs.repository.UserBalanceRepository;
import br.com.systemsgs.repository.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaymentService {

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @PostConstruct
    public void initUserBalanceInDb(){
        userBalanceRepository.saveAll(Stream.of(new UserBalance(101, 5000),
                new UserBalance(102, 3000),
                new UserBalance(103, 4200),
                new UserBalance(104, 20000),
                new UserBalance(105, 999)).collect(Collectors.toList()));
    }

    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        OrderRequestDTO orderRequestDTO = orderEvent.getOrderRequestDTO();
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO(orderRequestDTO.getOrderId(), orderRequestDTO.getUserId(), orderRequestDTO.getAmount());

        return userBalanceRepository.findById(orderRequestDTO.getUserId()).filter(ub -> ub.getPrice()> orderRequestDTO.getAmount())
                .map(ub ->{
                    ub.setPrice(ub.getPrice()- orderRequestDTO.getAmount());
                    userTransactionRepository.save(new UserTransaction(orderRequestDTO.getOrderId(), orderRequestDTO.getUserId(), orderRequestDTO.getAmount()));
                    return new PaymentEvent(paymentRequestDTO, PaymentStatus.PAYMENT_COMPLETED);
                }).orElse(new PaymentEvent(paymentRequestDTO, PaymentStatus.PAYMENT_FAILED));
    }

    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {
        userTransactionRepository.findById(orderEvent.getOrderRequestDTO().getOrderId())
                .ifPresent(ut ->{
                    userTransactionRepository.delete(ut);
                    userTransactionRepository.findById(ut.getUserId())
                            .ifPresent(ub-> ub.setAmount(ub.getAmount() + ut.getAmount()));
                });
        }
}
