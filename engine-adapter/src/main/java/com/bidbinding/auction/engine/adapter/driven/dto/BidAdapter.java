package com.bidbinding.auction.engine.adapter.driven.dto;

import com.bidbinding.auction.engine.adapter.driver.dto.BidPlacementRequest;
import com.bidbinding.auction.engine.application.core.model.bid.Bid;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class BidAdapter {

    public static Bid adapt(com.bidbinding.auction.engine.adapter.driven.dto.Bid bid) {
        return Bid.builder()
                .amount(BigDecimal.valueOf(bid.getAmount()))
                .buyer(bid.getBuyer())
                .id(bid.getBid_id())
                .build();
    }

    public static Bid adapt(BidPlacementRequest bidPlacementRequest) {
        return Bid.builder()
                .id(UUID.randomUUID().toString())
                .buyer(bidPlacementRequest.bidder())
                .amount(bidPlacementRequest.amount())
                .build();
    }

    public static List<Bid> adapt(List<com.bidbinding.auction.engine.adapter.driven.dto.Bid> bids) {
        return bids.stream()
                .map(BidAdapter::adapt)
                .collect(Collectors.toList());
    }


    public static com.bidbinding.auction.engine.adapter.driven.dto.Bid adapt(Bid bid, int itemId) {
        return com.bidbinding.auction.engine.adapter.driven.dto.Bid.builder()
                .itemId(itemId)
                .amount(bid.getAmount().floatValue())
                .buyer(bid.getBuyer())
                .build();
    }
}
