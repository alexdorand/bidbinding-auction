package com.bidbinding.auction.engine.application.core.model.item;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;


public sealed interface Item permits ForwardAuctionItem, ReverseAuctionItem, SealedBidItem {

    boolean canPlaceBid(Bid bid);

    void recordBid(Bid bid);

    ItemBiddingType getItemBiddingType();

}
