package com.bidbinding.auction.engine.application.port.driven;

import com.bidbinding.auction.engine.application.core.model.bid.BidNotificationCommand;

public interface EventPort {

    void bidPlacedSuccessfully(BidNotificationCommand bidNotificationCommand);
    void fraudulentBidDetected(BidNotificationCommand bidNotificationCommand);
    void itemConcluded(String itemId);
    void itemWon(String itemId);
    void itemReserveNotMet(String itemId);
    void bidFailed(BidNotificationCommand bidNotificationCommand);


}
