package com.bidbinding.auction.engine.application.port.driven;

import com.bidbinding.auction.engine.application.core.model.item.ItemAuctionType;
import com.bidbinding.auction.engine.application.core.model.item.Item;

public interface ItemPort<T extends Item> {
    ItemAuctionType getListingTypeFor(String itemId);

    void updateItem(T item);

    T getItem(String itemId);

}
