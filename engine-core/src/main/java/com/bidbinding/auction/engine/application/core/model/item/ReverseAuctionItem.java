package com.bidbinding.auction.engine.application.core.model.item;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import com.bidbinding.auction.engine.application.core.model.bid.BidPlacementStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ReverseAuctionItem implements Item {


    @Override
    public BidPlacementStatus recordBid(Bid bid) {
        return null;
    }

    @Override
    public void conclude() {

    }
}
