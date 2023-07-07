package com.bidbinding.auction.engine.application.core.model.item;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public final class SealedBidItem implements Item {

    private final String id;
    private List<Bid> bids;
    private Map<String, Bid> participantsLastBids;
    private Bid winningBid;
    private BigDecimal reservedPrice;


    @Override
    public boolean canPlaceBid(Bid bid) {
        return false;
    }

    @Override
    public void recordBid(Bid bid) {

    }

    public void calculateWinners() {

    }

    @Override
    public ItemBiddingType getItemBiddingType() {
        return ItemBiddingType.SEALED_BID_AUCTION;
    }
}
