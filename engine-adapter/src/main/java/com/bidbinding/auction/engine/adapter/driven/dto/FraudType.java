package com.bidbinding.auction.engine.adapter.driven.dto;

import lombok.Getter;

public enum FraudType {
    NONE(false),
    SHILL_BIDDING(true),
    MAN_IN_MIDDLE_ATTACH(true);

    @Getter
    private boolean fraudDetected;

    FraudType(boolean detected) {
        this.fraudDetected = detected;
    }
}
