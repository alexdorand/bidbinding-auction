package com.bidbinding.auction.engine.application.port.driven;

import com.bidbinding.auction.engine.application.core.model.item.AuctionType;
import com.bidbinding.auction.engine.application.core.model.item.ForwardAuctionItem;
import com.bidbinding.auction.engine.application.core.model.item.Item;

public interface ItemPort<T extends Item> {
    AuctionType getListingTypeFor(String itemId);

    void updateItem(T item);

    T getItem(String itemId);

}
