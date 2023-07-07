package com.bidbinding.auction.engine.application.port.driver;

import com.bidbinding.auction.engine.application.core.model.bid.ItemBidCommand;
import com.bidbinding.auction.engine.application.core.model.bid.PlaceBidResult;

public interface PlaceBidPort {

    PlaceBidResult placeBid(ItemBidCommand itemBidCommand);

}
