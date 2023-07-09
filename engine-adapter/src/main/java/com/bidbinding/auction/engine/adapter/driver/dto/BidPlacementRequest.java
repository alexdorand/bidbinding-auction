package com.bidbinding.auction.engine.adapter.driver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import static com.bidbinding.auction.engine.adapter.common.Validator.validate;


public record BidPlacementRequest(
        @NotBlank(message = "missing.bidder") String bidder,
        @NotBlank(message = "midding.item") String itemId,
        @NotNull(message = "missing.amount") BigDecimal amount) {

    public BidPlacementRequest(String bidder, String itemId, BigDecimal amount) {
        this.bidder = bidder;
        this.itemId = itemId;
        this.amount = amount;
        validate(this);
    }

}
