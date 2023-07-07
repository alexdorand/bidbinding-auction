package com.bidbinding.auction.engine.application.port.driven;

import com.bidbinding.auction.engine.application.core.model.bid.BidNotificationCommand;

public interface EventPort {

    void notifyBidPlaced(BidNotificationCommand bidNotificationCommand);
    void notifyFraudulentBidDetected(BidNotificationCommand bidNotificationCommand);
    void notifyItemConcluded(String itemId);
    void notifyItemWon(String itemId);
    void notifyItemReserveNotMet(String itemId);
    void notifyBidFailed(BidNotificationCommand bidNotificationCommand);


}
