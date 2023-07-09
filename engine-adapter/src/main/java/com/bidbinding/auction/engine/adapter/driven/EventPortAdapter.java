package com.bidbinding.auction.engine.adapter.driven;

import com.bidbinding.auction.engine.application.core.model.bid.BidNotificationCommand;
import com.bidbinding.auction.engine.application.port.driven.EventPort;
import org.springframework.stereotype.Component;

@Component
public class EventPortAdapter implements EventPort {

    @Override
    public void bidPlacedSuccessfully(BidNotificationCommand bidNotificationCommand) {
        System.out.println("EVENT: Bid is placed");
    }

    @Override
    public void fraudulentBidDetected(BidNotificationCommand bidNotificationCommand) {
        System.out.println("EVENT: Fraud detected");
    }

    @Override
    public void itemConcluded(String itemId) {
        System.out.println("EVENT: Auction concluded");
    }

    @Override
    public void itemWon(String itemId) {
        System.out.println("EVENT: Item won");
    }

    @Override
    public void itemReserveNotMet(String itemId) {
        System.out.println("EVENT: Item reserved price not met");
    }

    @Override
    public void bidFailed(BidNotificationCommand bidNotificationCommand) {
        System.out.println("EVENT: Bid failed");
    }


}
