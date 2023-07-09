package com.bidbinding.auction.engine.adapter.driven;

import com.bidbinding.auction.engine.adapter.common.Adaptor;
import com.bidbinding.auction.engine.application.core.model.bid.BidsHistory;
import com.bidbinding.auction.engine.application.core.model.item.ItemAuctionType;
import com.bidbinding.auction.engine.application.core.model.item.ForwardAuctionItem;
import com.bidbinding.auction.engine.application.port.driven.ItemPort;

import java.util.ArrayList;
import java.util.UUID;

@Adaptor
public class ItemPortAdapter implements ItemPort<ForwardAuctionItem> {
    @Override
    public ItemAuctionType getListingTypeFor(String itemId) {
        return ItemAuctionType.FORWARD_AUCTION;
    }

    @Override
    public void updateItem(ForwardAuctionItem item) {

    }

    @Override
    public ForwardAuctionItem getItem(String itemId) {
        return  ForwardAuctionItem.builder().id(UUID.randomUUID().toString()).bidsHistory(new BidsHistory()).startTimestamp(System.currentTimeMillis()).endTimestamp(System.currentTimeMillis()).build();
    }

}
