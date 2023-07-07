package com.bidbinding.auction.engine.adapter.driver.dto;

import com.bidbinding.auction.engine.adapter.driven.dto.ItemForward;
import com.bidbinding.auction.engine.application.core.model.item.ForwardAuctionItem;
import com.bidbinding.auction.engine.application.core.model.item.ReverseAuctionItem;

public class ItemAdaptor {

    public static ForwardAuctionItem toForward(ItemForward itemForward) {
        return ForwardAuctionItem.builder()
                .id(itemForward.getItem_id())
                .build();
    }

    public static ReverseAuctionItem toReverse(ItemDto dto) {
        return null;
    }

    public static ItemDto from(ForwardAuctionItem itemForward) {
        return null;
    }

    public static ItemDto from(ReverseAuctionItem itemReverse) {
        return null;
    }
}
