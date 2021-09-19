package br.com.systemsgs.event;

import br.com.systemsgs.dto.OrderRequestDTO;

import java.util.Date;
import java.util.UUID;

public class OrderEvent implements Event{

    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private OrderRequestDTO orderRequestDTO;
    private OrderStatus orderStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getEvent() {
        return eventDate;
    }
}
