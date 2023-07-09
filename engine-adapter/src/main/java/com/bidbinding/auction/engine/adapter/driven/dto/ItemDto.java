package com.bidbinding.auction.engine.adapter.driven.dto;

import com.bidbinding.auction.engine.application.core.model.item.AuctionType;
import lombok.Data;

@Data
public class ItemDto {

    private AuctionType itemBiddingType;
    private String id;


}
