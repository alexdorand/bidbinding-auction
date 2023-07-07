package com.bidbinding.auction.engine.adapter.driver.dto;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;

import java.util.UUID;

public final class BidAdapter {

    public static Bid adapt(BidDto dto) {
        return Bid.builder()
                .id(UUID.randomUUID().toString())
                .buyer(dto.getBidder())
                .amount(dto.getAmount())
                .build();
    }

}
