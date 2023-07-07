package com.bidbinding.auction.engine.adapter.driver.dto;

import com.bidbinding.auction.engine.application.core.model.item.ItemBiddingType;
import lombok.Data;

@Data
public class ItemDto {

    private ItemBiddingType itemBiddingType;
    private String id;


}
