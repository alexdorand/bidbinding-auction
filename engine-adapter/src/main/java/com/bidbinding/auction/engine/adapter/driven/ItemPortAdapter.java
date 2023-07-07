package com.bidbinding.auction.engine.adapter.driven;

import com.bidbinding.auction.engine.adapter.common.Adaptor;
import com.bidbinding.auction.engine.application.core.model.item.ItemBiddingType;
import com.bidbinding.auction.engine.application.port.driven.ItemPort;

@Adaptor
public class ItemPortAdapter implements ItemPort {
    @Override
    public ItemBiddingType getItemBiddingType(String itemId) {
        return ItemBiddingType.FORWARD_AUCTION;
    }

    @Override
    public ItemBiddingType detectTypeForItem(String itemId) {
        return ItemBiddingType.FORWARD_AUCTION;
    }
}
