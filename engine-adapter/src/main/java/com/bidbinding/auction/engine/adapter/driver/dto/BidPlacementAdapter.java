package com.bidbinding.auction.engine.adapter.driver.dto;

import com.bidbinding.auction.engine.application.core.model.bid.Bid;
import com.bidbinding.auction.engine.application.core.model.bid.BidPlacementStatus;

import java.util.UUID;

public class BidPlacementAdapter {

    public static Bid toBid(BidPlacementRequest request) {
        return Bid.builder()
                .id(UUID.randomUUID().toString())
                .buyer(request.bidder())
                .amount(request.amount())
                .bidPlacementStatus(BidPlacementStatus.PENDING)
                .build();
    }

}
