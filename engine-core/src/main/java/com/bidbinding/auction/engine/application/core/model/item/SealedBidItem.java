package com.bidbinding.auction.engine.application.core.model.item;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import com.bidbinding.auction.engine.application.core.model.bid.BidPlacementStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public final class SealedBidItem implements Item {

    @Override
    public BidPlacementStatus recordBid(Bid bid) {
        return null;
    }

    @Override
    public void conclude() {

    }
}
