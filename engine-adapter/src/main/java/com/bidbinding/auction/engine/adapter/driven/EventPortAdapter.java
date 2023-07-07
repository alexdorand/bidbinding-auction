package com.bidbinding.auction.engine.adapter.driven;

import com.bidbinding.auction.engine.application.core.model.bid.BidNotificationCommand;
import com.bidbinding.auction.engine.application.port.driven.EventPort;
import org.springframework.stereotype.Component;

@Component
public class EventPortAdapter implements EventPort {

    @Override
    public void notifyBidPlaced(BidNotificationCommand bidNotificationCommand) {
        System.out.println("EVENT: Bid is placed");
    }

    @Override
    public void notifyFraudulentBidDetected(BidNotificationCommand bidNotificationCommand) {
        System.out.println("EVENT: Fraud detected");
    }

    @Override
    public void notifyItemConcluded(String itemId) {
        System.out.println("EVENT: Auction concluded");
    }

    @Override
    public void notifyItemWon(String itemId) {
        System.out.println("EVENT: Item won");
    }

    @Override
    public void notifyItemReserveNotMet(String itemId) {
        System.out.println("EVENT: Item reserved price not met");
    }

    @Override
    public void notifyBidFailed(BidNotificationCommand bidNotificationCommand) {
        System.out.println("EVENT: Bid failed");
    }


}
