package com.bidbinding.auction.engine.adapter.driver.dto;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import lombok.Data;

@Data
public class BidFraudDto {

    private Bid bid;
    private String onItemId;

}
