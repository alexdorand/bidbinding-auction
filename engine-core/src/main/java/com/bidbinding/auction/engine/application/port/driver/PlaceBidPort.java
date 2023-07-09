package com.bidbinding.auction.engine.application.port.driver;

import com.bidbinding.auction.engine.application.core.model.bid.ItemBidCommand;
import com.bidbinding.auction.engine.application.core.model.bid.BidPlacementStatus;

public interface PlaceBidPort {

    BidPlacementStatus placeBid(ItemBidCommand itemBidCommand);

}
