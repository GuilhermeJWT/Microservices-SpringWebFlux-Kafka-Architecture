package br.com.systemsgs.event;

import java.util.Date;
import java.util.UUID;

public class PaymentEvent implements Event{

    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getEvent() {
        return eventDate;
    }
}
