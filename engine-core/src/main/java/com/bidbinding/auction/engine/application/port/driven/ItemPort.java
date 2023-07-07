package com.bidbinding.auction.engine.application.port.driven;

import com.bidbinding.auction.engine.application.core.model.item.ItemBiddingType;

public interface ItemPort {
    ItemBiddingType getItemBiddingType(String itemId);

    ItemBiddingType detectTypeForItem(String itemId);
}
