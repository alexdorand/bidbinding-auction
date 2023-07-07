package com.bidbinding.auction.engine.application.port.driven;

import com.bidbinding.auction.engine.application.core.model.bid.ItemBidCommand;


public interface FraudDetectionPort {

    boolean isBidFraudulent(ItemBidCommand itemBidCommand);

    void reportSuspectedFraud(String bid);

}
