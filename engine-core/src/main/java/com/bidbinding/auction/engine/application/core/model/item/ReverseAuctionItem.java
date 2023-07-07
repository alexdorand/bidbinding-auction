package com.bidbinding.auction.engine.application.core.model.item;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ReverseAuctionItem implements Item {


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
        return ItemBiddingType.REVERSE_AUCTION;
    }
}
