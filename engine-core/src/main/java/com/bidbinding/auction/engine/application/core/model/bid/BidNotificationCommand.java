package com.bidbinding.auction.engine.application.core.model.bid;

public record BidNotificationCommand(Bid bid, String onItemId) {
}
