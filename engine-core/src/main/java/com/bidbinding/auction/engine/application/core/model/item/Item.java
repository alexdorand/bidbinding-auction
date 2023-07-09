package com.bidbinding.auction.engine.application.core.model.item;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import com.bidbinding.auction.engine.application.core.model.bid.BidPlacementStatus;


public sealed interface Item permits ForwardAuctionItem, ReverseAuctionItem, SealedBidItem {

    BidPlacementStatus recordBid(Bid bid);

    void conclude();


}
